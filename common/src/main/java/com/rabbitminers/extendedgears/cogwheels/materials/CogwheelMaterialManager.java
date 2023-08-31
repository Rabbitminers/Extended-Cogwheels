package com.rabbitminers.extendedgears.cogwheels.materials;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rabbitminers.extendedgears.base.util.Env;
import com.rabbitminers.extendedgears.base.networking.PlayerSelection;
import com.rabbitminers.extendedgears.base.networking.S2CPacket;
import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsPackets;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.*;
import java.util.Map.Entry;

public class CogwheelMaterialManager {
    public static Map<ResourceLocation, CogwheelMaterial> MATERIALS = new HashMap<>();
    public static Map<ResourceLocation, ClientCogwheelMaterial> CLIENT_MATERIALS = new HashMap<>();
    public static Map<Item, CogwheelMaterial> ITEM_TO_MATERIALS = new IdentityHashMap<>();

    public static Optional<CogwheelMaterial> fromItemStack(ItemStack stack) {
        return Optional.ofNullable(ITEM_TO_MATERIALS.get(stack.getItem()));
    }

    public static void fillItemMap() {
        for (CogwheelMaterial material : MATERIALS.values()) {
            for (Ingredient ingredient : material.items) {
                for (ItemStack itemStack : ingredient.itemStacks) {
                    ITEM_TO_MATERIALS.put(itemStack.getItem(), material);
                }
            }
        }
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
            MATERIALS.put(buffer.readResourceLocation(), CogwheelMaterial.fromBuffer(buffer));
        }

        fillItemMap();
    }

    public static void clear() {
        MATERIALS.clear();
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
                ResourceLocation id = entry.getKey();
                JsonObject object = element.getAsJsonObject();
                CogwheelMaterial type = CogwheelMaterial.fromJson(object);
                MATERIALS.put(id, type);
            }
        }

        fillItemMap();
        tryFillClient();
    }

    private static void tryFillClient() {
        if (Env.CURRENT == Env.CLIENT) {
            for (Entry<ResourceLocation, CogwheelMaterial> entry : MATERIALS.entrySet()) {
                CLIENT_MATERIALS.put(entry.getKey(), ClientCogwheelMaterial.fromMaterial(entry.getValue()));
            }
        }
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
