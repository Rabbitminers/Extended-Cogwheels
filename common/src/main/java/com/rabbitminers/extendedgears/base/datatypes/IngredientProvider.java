package com.rabbitminers.extendedgears.base.datatypes;

import net.minecraft.world.item.crafting.Ingredient;

import java.util.Locale;

public record IngredientProvider(com.rabbitminers.extendedgears.base.datatypes.IngredientProvider.Namespace namespace,
                                 Ingredient ingredient) {

    public enum Namespace {
        FORGE, FABRIC, COMMON;

        public String asId() {
            return name().toLowerCase(Locale.ROOT);
        }
    }
}
