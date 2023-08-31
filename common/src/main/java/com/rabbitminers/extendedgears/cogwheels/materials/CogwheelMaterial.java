package com.rabbitminers.extendedgears.cogwheels.materials;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.simibubi.create.foundation.config.ConfigBase;
import com.simibubi.create.infrastructure.config.AllConfigs;
import net.minecraft.ResourceLocationException;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class CogwheelMaterial implements Predicate<ItemStack> {
    public static ConfigBase.ConfigInt MAX_SPEED = AllConfigs.server().kinetics.maxRotationSpeed;
    public static int MAX_STRESS = Integer.MAX_VALUE;

    public final NonNullList<Ingredient> items = NonNullList.create();

    public int stressLimit = MAX_STRESS;
    public int speedLimit = MAX_SPEED.get();

    public ResourceLocation smallModel;
    public ResourceLocation largeModel;

    public Map<ResourceLocation, ResourceLocation> textures;

    @Override
    public boolean test(ItemStack itemStack) {
        return items.stream().anyMatch(ingredient -> ingredient.test(itemStack));
    }

    public static CogwheelMaterial fromJson(JsonObject object) {
        CogwheelMaterial material = new CogwheelMaterial();
        try {
            JsonElement ingredients = object.get("items");
            if (ingredients != null && ingredients.isJsonArray()) {
                NonNullList<Ingredient> items = ingredientsFromJson(ingredients.getAsJsonArray());
                material.items.addAll(items);
            }

            JsonElement textures = object.get("textures");
            if (textures != null && textures.isJsonArray()) {
                material.textures = texturesFromJson(textures.getAsJsonArray());
            }

            JsonElement small = object.get("small_model");
            if (textures != null && small.isJsonPrimitive()) {
                String key = small.getAsString();
                material.smallModel = locationFromString(key);
            }

            JsonElement large = object.get("large_model");
            if (textures != null && large.isJsonPrimitive()) {
                String key = large.getAsString();
                material.largeModel = locationFromString(key);
            }

            parseJsonPrimitive(object, "stress_limit", JsonPrimitive::isNumber,
                    primitive -> material.speedLimit = primitive.getAsInt());

            parseJsonPrimitive(object, "speed_limit", JsonPrimitive::isNumber,
                    primitive -> material.speedLimit = primitive.getAsInt());
        } catch (Exception e) {
            //
        }
        return material;
    }

    private static Map<ResourceLocation, ResourceLocation> texturesFromJson(JsonArray textures) {
        Map<ResourceLocation, ResourceLocation> map = new HashMap<>();
        for (JsonElement element : textures) {
            if (!element.isJsonObject())
                continue;

            JsonObject object = element.getAsJsonObject();
            JsonElement repElement = object.get("replacement");
            JsonElement oldElement = object.get("old");

            if (oldElement == null || !oldElement.isJsonPrimitive() || repElement == null || !repElement.isJsonPrimitive())
                continue;

            try {
                ResourceLocation old = new ResourceLocation(oldElement.getAsString());
                ResourceLocation rep = new ResourceLocation(repElement.getAsString());

                map.put(old, rep);
            } catch (ResourceLocationException e) {
                //
            }
        }
        return map;
    }

    private static ResourceLocation locationFromString(String primitve) {
        try {
            return new ResourceLocation(primitve);
        } catch (ResourceLocationException e) {
            //
        }
        return new ResourceLocation(ExtendedCogwheels.MOD_ID, "cogwheels/missing");
    }

    private static NonNullList<Ingredient> ingredientsFromJson(JsonArray ingredientArray) {
        NonNullList<Ingredient> ingredients = NonNullList.create();
        for (int i = 0; i < ingredientArray.size(); i++) {
            Ingredient ingredient = Ingredient.fromJson(ingredientArray.get(i));
            if (!ingredient.isEmpty()) {
                ingredients.add(ingredient);
            }
        }
        return ingredients;
    }

    public void toBuffer(FriendlyByteBuf buffer) {
        buffer.writeVarInt(items.size());
        for (Ingredient item : items) {
            item.toNetwork(buffer);
        }
        buffer.writeVarInt(textures.size());
        for (Map.Entry<ResourceLocation, ResourceLocation> texture : textures.entrySet()) {
            buffer.writeResourceLocation(texture.getKey());
            buffer.writeResourceLocation(texture.getValue());
        }
        buffer.writeResourceLocation(smallModel);
        buffer.writeResourceLocation(largeModel);
        buffer.writeInt(speedLimit);
        buffer.writeInt(stressLimit);
    }

    public static CogwheelMaterial fromBuffer(FriendlyByteBuf buffer) {
        CogwheelMaterial type = new CogwheelMaterial();
        int itemSize = buffer.readVarInt();
        for (int i = 0; i < itemSize; i++) {
            type.items.add(Ingredient.fromNetwork(buffer));
        }
        int textureSize = buffer.readVarInt();
        for (int i = 0; i < textureSize; i++) {
            ResourceLocation old = buffer.readResourceLocation();
            ResourceLocation replacement = buffer.readResourceLocation();
            type.textures.put(old, replacement);
        }
        type.smallModel = buffer.readResourceLocation();
        type.largeModel = buffer.readResourceLocation();
        type.speedLimit = buffer.readInt();
        type.stressLimit = buffer.readInt();

        return type;
    }


    // Re-used from Potato cannon projectile type
    private static void parseJsonPrimitive(JsonObject object, String key, Predicate<JsonPrimitive> predicate, Consumer<JsonPrimitive> consumer) {
        JsonElement element = object.get(key);
        if (element != null && element.isJsonPrimitive()) {
            JsonPrimitive primitive = element.getAsJsonPrimitive();
            if (predicate.test(primitive)) {
                consumer.accept(primitive);
            }
        }
    }

}
