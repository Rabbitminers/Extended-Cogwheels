package com.rabbitminers.extendedgears.base.data;

import com.rabbitminers.extendedgears.base.datatypes.IngredientProvider;
import com.rabbitminers.extendedgears.base.datatypes.IngredientProvider.Namespace;
import com.rabbitminers.extendedgears.cogwheels.CustomCogwheelBlock;
import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsBlocks;
import com.simibubi.create.AllTags;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum MetalCogwheel implements ICogwheelMaterial {
    IRON(new IngredientProvider(Namespace.COMMON, Ingredient.of(Items.IRON_INGOT))),
    STEEL(new IngredientProvider(Namespace.FORGE, Ingredient.of(AllTags.forgeItemTag("ingots/steel"))),
        new IngredientProvider(Namespace.FABRIC, Ingredient.of(AllTags.optionalTag(Registry.ITEM, new ResourceLocation("c", "steel_ingots"))))),
    COPPER(new IngredientProvider(Namespace.COMMON, Ingredient.of(Items.COPPER_INGOT)));

    public final IngredientProvider[] ingredients;

    MetalCogwheel(IngredientProvider... ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public @Nullable BlockEntry<CustomCogwheelBlock> getLargeCogwheel() {
        return ExtendedCogwheelsBlocks.LARGE_METAL_COGWHEELS.get(this);
    }

    @Override
    public @Nullable BlockEntry<CustomCogwheelBlock> getSmallCogwheel() {
        return ExtendedCogwheelsBlocks.METAL_COGWHEELS.get(this);
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
