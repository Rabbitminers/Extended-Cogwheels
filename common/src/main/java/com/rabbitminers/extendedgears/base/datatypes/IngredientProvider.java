package com.rabbitminers.extendedgears.base.datatypes;

import net.minecraft.world.item.crafting.Ingredient;

import java.util.Locale;

public class IngredientProvider {
    private final Namespace namespace;
    private final Ingredient ingredient;

    public IngredientProvider(Namespace namespace, Ingredient ingredient) {
        this.namespace = namespace;
        this.ingredient = ingredient;
    }

    public enum Namespace {
        FORGE, FABRIC, COMMON
        ;

        public String asId() {
            return name().toLowerCase(Locale.ROOT);
        }
    }
}
