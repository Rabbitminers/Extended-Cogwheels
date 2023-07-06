package com.rabbitminers.extendedgears.base.data;

import com.rabbitminers.extendedgears.base.datatypes.IngredientProvider;
import com.rabbitminers.extendedgears.base.datatypes.IngredientProvider.Namespace;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;


public enum WoodenCogwheel implements ICogwheelMaterial {
    DARK_OAK(Items.DARK_OAK_BUTTON, Items.DARK_OAK_PLANKS),
    OAK(Items.OAK_BUTTON, Items.OAK_PLANKS),
    BIRCH(Items.BIRCH_BUTTON, Items.BIRCH_PLANKS),
    JUNGLE(Items.JUNGLE_BUTTON, Items.JUNGLE_PLANKS),
    ACACIA(Items.ACACIA_BUTTON, Items.ACACIA_PLANKS),
    WARPED(Items.WARPED_BUTTON, Items.WARPED_PLANKS),
    CRIMSON(Items.CRIMSON_BUTTON, Items.CRIMSON_PLANKS),
    ;

    public final IngredientProvider ingredient;
    public final IngredientProvider smallIngredient;


    WoodenCogwheel(IngredientProvider smallIngredient, IngredientProvider ingredient) {
        this.ingredient = ingredient;
        this.smallIngredient = smallIngredient;
    }

    WoodenCogwheel(Item smallIngredient, Item ingredient) {
        this.ingredient = new IngredientProvider(Namespace.COMMON, Ingredient.of(ingredient));
        this.smallIngredient = new IngredientProvider(Namespace.COMMON,
                Ingredient.of(smallIngredient));
    }

    @Override
    public @NotNull String asId() {
        return name().toLowerCase();
    }

    @Override
    public IngredientProvider getIngredient() {
        return this.ingredient;
    }

    @Override
    public IngredientProvider getSmallIngredient() {
        return smallIngredient;
    }
}
