package com.rabbitminers.extendedgears.base.data;

import com.rabbitminers.extendedgears.base.datatypes.IngredientProvider;
import com.rabbitminers.extendedgears.base.datatypes.IngredientProvider.Namespace;
import com.rabbitminers.extendedgears.cogwheels.CustomCogwheelBlock;
import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsBlocks;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;


public enum WoodenCogwheel implements ICogwheelMaterial {
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

    @Override
    public BlockEntry<CustomCogwheelBlock> getLargeCogwheel() {
        return ExtendedCogwheelsBlocks.LARGE_WOODEN_COGWHEELS.get(this);
    }

    @Override
    public BlockEntry<CustomCogwheelBlock> getSmallCogwheel() {
        return ExtendedCogwheelsBlocks.WOODEN_COGWHEELS.get(this);
    }


    @Override
    public @NotNull String asId() {
        return name().toLowerCase();
    }

    @Override
    public IngredientProvider[] getIngredients() {
        return this.ingredients;
    }
}
