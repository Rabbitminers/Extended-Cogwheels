package com.rabbitminers.extendedgears.base.data;

import com.rabbitminers.extendedgears.base.datatypes.IngredientProvider;
import com.rabbitminers.extendedgears.base.datatypes.IngredientProvider.Namespace;
import com.rabbitminers.extendedgears.base.util.TagUtils;
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

    public final TagKey[] tagKey;

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
        this.tagKey = new TagKey[] { smallIngredient, ingredient };
    }

    MetalCogwheel(TagKey<Item> smallIngredient, Item ingredient) {
        this.ingredient = new IngredientProvider(Namespace.COMMON, Ingredient.of(ingredient));
        this.smallIngredient = new IngredientProvider(Namespace.COMMON,
                Ingredient.of(smallIngredient));
        this.tagKey = new TagKey[] { smallIngredient };
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
