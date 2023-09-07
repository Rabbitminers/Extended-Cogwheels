package com.rabbitminers.extendedgears.cogwheels.materials;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.rabbitminers.extendedgears.ExtendedCogwheels;
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
    public final ResourceLocation id;

    // Defaults
    public static int MAX_STRESS = Integer.MAX_VALUE;

    // Clientside loaders
    public final Map<ResourceLocation, ResourceLocation> textures;
    public final boolean chunky;

    // Datapack fields
    public NonNullList<Ingredient> items = NonNullList.create();
    public int stressLimit = MAX_STRESS;
    public int speedLimit = 256;

    public CogwheelMaterial(ResourceLocation id, Map<ResourceLocation, ResourceLocation> textures, boolean chunky) {
        this.id = id;
        this.textures = textures;
        this.chunky = chunky;
    }

    @Override
    public boolean test(ItemStack itemStack) {
        return items.stream().anyMatch(ingredient -> ingredient.test(itemStack));
    }

    public void updateFromJson(JsonObject object) {
        try {
            JsonElement ingredients = object.get("items");
            if (ingredients != null && ingredients.isJsonArray()) {
                NonNullList<Ingredient> items = ingredientsFromJson(ingredients.getAsJsonArray());
                this.items.addAll(items);
            }

            parseJsonPrimitive(object, "stress_limit", JsonPrimitive::isNumber,
                    primitive -> this.speedLimit = primitive.getAsInt());

            parseJsonPrimitive(object, "speed_limit", JsonPrimitive::isNumber,
                    primitive -> this.speedLimit = primitive.getAsInt());
        } catch (Exception e) {
            //
        }
    }

    private static ResourceLocation locationFromString(String primitive) {
        try {
            return new ResourceLocation(primitive);
        } catch (ResourceLocationException e) {
            //
        }
        return new ResourceLocation(ExtendedCogwheels.MOD_ID, "cogwheels/missing");
    }

    private static NonNullList<Ingredient> ingredientsFromJson(JsonArray ingredientArray) {
        NonNullList<Ingredient> ingredients = NonNullList.create();

        for (JsonElement je : ingredientArray) {
            ingredients.add(Ingredient.fromJson(je));
        }

        return ingredients;
    }

    public void toBuffer(FriendlyByteBuf buffer) {
        buffer.writeResourceLocation(id);
        buffer.writeVarInt(items.size());
        for (Ingredient item : items) {
            item.toNetwork(buffer);
        }
        buffer.writeInt(speedLimit);
        buffer.writeInt(stressLimit);
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
