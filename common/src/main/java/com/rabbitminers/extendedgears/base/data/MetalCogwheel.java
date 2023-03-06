package com.rabbitminers.extendedgears.base.data;

import com.rabbitminers.extendedgears.base.datatypes.IngredientProvider;
import com.rabbitminers.extendedgears.base.datatypes.IngredientProvider.Namespace;
import com.rabbitminers.extendedgears.cogwheels.CustomCogwheelBlock;
import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsBlocks;
import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsTags;
import com.simibubi.create.AllItems;
import com.simibubi.create.AllTags;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum MetalCogwheel implements ICogwheelMaterial {
    IRON(new IngredientProvider(Namespace.COMMON, Ingredient.of(Items.IRON_INGOT))),
    STEEL(new TagKey[] {ExtendedCogwheelsTags.forgeSteel, ExtendedCogwheelsTags.fabricSteel},
            new IngredientProvider(Namespace.FORGE, Ingredient.of(ExtendedCogwheelsTags.forgeSteel)),
            new IngredientProvider(Namespace.FABRIC, Ingredient.of(ExtendedCogwheelsTags.fabricSteel))),
    COPPER(new IngredientProvider(Namespace.COMMON, Ingredient.of(Items.COPPER_INGOT)));

    public final IngredientProvider[] ingredients;
    @Nullable
    public final TagKey<Item>[] tagKey;

    MetalCogwheel(IngredientProvider... ingredients) {
        this.ingredients = ingredients;
        this.tagKey = null;
    }

    MetalCogwheel(TagKey<Item>[] tagKey, IngredientProvider... ingredients) {
        this.ingredients = ingredients;
        this.tagKey = tagKey;
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
    public @Nullable TagKey<Item>[] getRecipeTags() {
        return this.tagKey;
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
