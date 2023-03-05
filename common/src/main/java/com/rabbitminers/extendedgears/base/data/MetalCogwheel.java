package com.rabbitminers.extendedgears.base.data;

import com.rabbitminers.extendedgears.base.datatypes.IngredientProvider;
import com.rabbitminers.extendedgears.base.datatypes.IngredientProvider.Namespace;
import com.simibubi.create.AllTags;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public enum MetalCogwheel {
    IRON(new IngredientProvider(Namespace.COMMON, Ingredient.of(Items.IRON_INGOT))),
    STEEL(new IngredientProvider(Namespace.FORGE, Ingredient.of(AllTags.forgeItemTag("ingots/steel"))),
        new IngredientProvider(Namespace.FABRIC, Ingredient.of(AllTags.optionalTag(Registry.ITEM, new ResourceLocation("c", "steel_ingots"))))),
    COPPER(new IngredientProvider(Namespace.COMMON, Ingredient.of(Items.COPPER_INGOT)));

    public final IngredientProvider[] ingredients;

    MetalCogwheel(IngredientProvider... ingredients) {
        this.ingredients = ingredients;
    }

    public String asId() {
        return name().toLowerCase();
    }
}
