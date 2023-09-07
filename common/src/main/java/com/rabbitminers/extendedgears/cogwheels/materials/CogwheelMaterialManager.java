package com.rabbitminers.extendedgears.cogwheels.materials;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rabbitminers.extendedgears.base.util.Env;
import com.rabbitminers.extendedgears.base.networking.PlayerSelection;
import com.rabbitminers.extendedgears.base.networking.S2CPacket;
import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsPackets;
import com.simibubi.create.infrastructure.config.AllConfigs;
import net.minecraft.client.Minecraft;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.Map.Entry;

public class CogwheelMaterialManager {
    public static Map<ResourceLocation, CogwheelMaterial> MATERIALS = new HashMap<>();
    public static Map<ResourceLocation, ClientCogwheelMaterial> CLIENT_MATERIALS = new HashMap<>();
    public static Map<Item, ResourceLocation> ITEM_TO_MATERIALS = new IdentityHashMap<>();

    private static boolean dirty = false;

    @Nullable
    public static ResourceLocation of(ItemStack stack) {
        if (dirty) {
            fillItemMap();
            dirty = false;
        }
        return ITEM_TO_MATERIALS.get(stack.getItem());
    }
    
    public static CogwheelMaterial of(ResourceLocation location) {
        return MATERIALS.get(location);
    }

    @Nullable
    public static ClientCogwheelMaterial clientOf(ResourceLocation location) {
        return CLIENT_MATERIALS.get(location);
    }

    public static int getStressLimit(ResourceLocation location) {
        CogwheelMaterial material = of(location);
        if (material == null)
            return Integer.MAX_VALUE;
        return material.stressLimit;
    }

    public static int getSpeedLimit(ResourceLocation location) {
        CogwheelMaterial material = of(location);
        if (material == null)
            return AllConfigs.server().kinetics.maxRotationSpeed.get();
        return material.stressLimit;
    }

    public static void toBuffer(FriendlyByteBuf buffer) {
        buffer.writeVarInt(MATERIALS.size());
        for (Entry<ResourceLocation, CogwheelMaterial> entry : MATERIALS.entrySet()) {
            buffer.writeResourceLocation(entry.getKey());
            entry.getValue().toBuffer(buffer);
        }
    }

    public static void fromBuffer(FriendlyByteBuf buffer) {
        clear();

        int size = buffer.readVarInt();

        for (int i = 0; i < size; i++) {
            ResourceLocation id = buffer.readResourceLocation();
            int itemSize = buffer.readVarInt();
            NonNullList<Ingredient> items = NonNullList.create();
            for (int j = 0; j < itemSize; j++) {
                items.add(Ingredient.fromNetwork(buffer));
            }
            int speedLimit = buffer.readInt();
            int stressLimit = buffer.readInt();

            CogwheelMaterial material = MATERIALS.get(id);
            if (material == null)
                continue;

            material.items = items;
            material.speedLimit = speedLimit;
            material.stressLimit = stressLimit;
        }

        dirty = true;
    }

    public static void clear() {
        ITEM_TO_MATERIALS.clear();
    }

    public static void syncTo(ServerPlayer player) {
        ExtendedCogwheelsPackets.PACKETS.sendTo(PlayerSelection.of(player), new SyncPacket());
    }

    public static void syncToAll() {
        ExtendedCogwheelsPackets.PACKETS.sendTo(PlayerSelection.all(), new SyncPacket());
    }

    public static void applyReloadListener(Map<ResourceLocation, JsonElement> map) {
        clear();

        for (Entry<ResourceLocation, JsonElement> entry : map.entrySet()) {
            JsonElement element = entry.getValue();
            if (element.isJsonObject()) {
                CogwheelMaterial type = MATERIALS.get(entry.getKey());
                if (type == null) {
                    continue;
                }
                JsonObject object = element.getAsJsonObject();
                type.updateFromJson(object);
            }
        }

        dirty = true;
    }

    public static void setupClientCogwheelMaterials() {
        if (Env.CURRENT == Env.CLIENT) {
            for (Entry<ResourceLocation, CogwheelMaterial> entry : MATERIALS.entrySet()) {
                CLIENT_MATERIALS.put(entry.getKey(), ClientCogwheelMaterial.fromMaterial(entry.getValue()));
            }
        }
    }

    public static void fillItemMap() {
        for (CogwheelMaterial material : MATERIALS.values())
            for (Ingredient ingredient : material.items)
                for (ItemStack itemStack : ingredient.getItems())
                    ITEM_TO_MATERIALS.put(itemStack.getItem(), material.id);
    }

    public static class SyncPacket implements S2CPacket {
        private FriendlyByteBuf buffer;

        public SyncPacket() {
            //
        }

        public SyncPacket(FriendlyByteBuf buffer) {
            this.buffer = buffer;
        }

        @Override
        public void write(FriendlyByteBuf buffer) {
            toBuffer(buffer);
        }

        @Override
        public void handle(Minecraft mc) {
            try {
                fromBuffer(buffer);
            } catch (Exception e) {
                //
            }
        }
    }
}
