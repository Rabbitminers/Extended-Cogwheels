package com.rabbitminers.extendedgears.base.data;

import com.rabbitminers.extendedgears.base.datatypes.IngredientProvider;
import com.rabbitminers.extendedgears.base.datatypes.IngredientProvider.Namespace;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;


public enum WoodenCogwheel {
    DARK_OAK(new IngredientProvider(Namespace.COMMON, Ingredient.of(Items.DARK_OAK_PLANKS))),
    OAK(new IngredientProvider(Namespace.COMMON, Ingredient.of(Items.OAK_PLANKS))),
    BIRCH(new IngredientProvider(Namespace.COMMON, Ingredient.of(Items.BIRCH_PLANKS))),
    JUNGLE(new IngredientProvider(Namespace.COMMON, Ingredient.of(Items.JUNGLE_PLANKS))),
    ACACIA(new IngredientProvider(Namespace.COMMON, Ingredient.of(Items.ACACIA_PLANKS))),
    WARPED(new IngredientProvider(Namespace.COMMON, Ingredient.of(Items.WARPED_PLANKS))),
    CRIMSON(new IngredientProvider(Namespace.COMMON, Ingredient.of(Items.CRIMSON_PLANKS)))
    ;

    public final IngredientProvider[] ingredients;

    WoodenCogwheel(IngredientProvider... ingredients) {
        this.ingredients = ingredients;
    }

    public String asId() {
        return name().toLowerCase();
    }
}
