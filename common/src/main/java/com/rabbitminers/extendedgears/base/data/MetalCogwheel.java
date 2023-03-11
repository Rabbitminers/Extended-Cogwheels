package com.rabbitminers.extendedgears.base.data;

import com.rabbitminers.extendedgears.base.datatypes.IngredientProvider;
import com.rabbitminers.extendedgears.base.datatypes.IngredientProvider.Namespace;
import com.rabbitminers.extendedgears.base.util.TagUtils;
import com.rabbitminers.extendedgears.cogwheels.CustomCogwheelBlock;
import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsBlocks;
import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsTags;
import com.simibubi.create.AllItems;
import com.simibubi.create.AllTags;
import com.tterrag.registrate.util.entry.BlockEntry;
import dev.architectury.injectables.annotations.ExpectPlatform;
import io.github.fabricators_of_create.porting_lib.util.TagUtil;
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
    IRON(Items.IRON_NUGGET, Items.IRON_INGOT),
    STEEL(TagUtils.steelNugget(), TagUtils.steelIngot()),
    COPPER(TagUtils.copperNugget(), Items.COPPER_INGOT);

    public final IngredientProvider ingredient;
    public final IngredientProvider smallIngredient;

    @Nullable
    public final TagKey<Item>[] tagKey;

    MetalCogwheel(IngredientProvider smallIngredient, IngredientProvider ingredients) {
        this.ingredient = ingredients;
        this.smallIngredient = smallIngredient;
        this.tagKey = null;
    }

    MetalCogwheel(Item smallIngredient, Item ingredient) {
        this.ingredient = new IngredientProvider(Namespace.COMMON, Ingredient.of(ingredient));
        this.smallIngredient = new IngredientProvider(Namespace.COMMON,
                Ingredient.of(smallIngredient));
        this.tagKey = null;
    }

    MetalCogwheel(TagKey<Item> smallIngredient, TagKey<Item> ingredient) {
        this.ingredient = new IngredientProvider(Namespace.COMMON, Ingredient.of(ingredient));
        this.smallIngredient = new IngredientProvider(Namespace.COMMON,
                Ingredient.of(smallIngredient));
        this.tagKey = null;
    }

    MetalCogwheel(TagKey<Item> smallIngredient, Item ingredient) {
        this.ingredient = new IngredientProvider(Namespace.COMMON, Ingredient.of(ingredient));
        this.smallIngredient = new IngredientProvider(Namespace.COMMON,
                Ingredient.of(smallIngredient));
        this.tagKey = null;
    }


    MetalCogwheel(IngredientProvider smallIngredient, TagKey<Item>[] tagKey, IngredientProvider ingredient) {
        this.ingredient = ingredient;
        this.tagKey = tagKey;
        this.smallIngredient = smallIngredient;
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
    public IngredientProvider getIngredient() {
        return this.ingredient;
    }

    @Override
    public IngredientProvider getSmallIngredient() {
        return smallIngredient;
    }
}
