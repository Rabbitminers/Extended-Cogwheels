package com.rabbitminers.extendedgears.base.data;

import com.simibubi.create.AllTags;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public enum MetalCogwheel {
    IRON(Ingredient.of(Items.IRON_INGOT)),
    STEEL(Ingredient.of(AllTags.forgeItemTag("ingots/steel")),
          Ingredient.of(AllTags.optionalTag(Registry.ITEM, new ResourceLocation("c", "steel_ingots")))),
    COPPER(Ingredient.of(Items.COPPER_INGOT));

    public final Ingredient[] ingredients;

    MetalCogwheel(Ingredient... ingredients) {
        this.ingredients = ingredients;
    }

    public String asId() {
        return name().toLowerCase();
    }
}
