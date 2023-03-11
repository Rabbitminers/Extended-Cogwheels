package com.rabbitminers.extendedgears.base.util.forge;

import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsTags;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class TagUtilsImpl {
    public static TagKey<Item> steelIngot() {
        return ExtendedCogwheelsTags.optionalTag(Registry.ITEM, new ResourceLocation("forge", "ingots/steel"));
    }

    public static TagKey<Item> steelNugget() {
        return ExtendedCogwheelsTags.optionalTag(Registry.ITEM, new ResourceLocation("forge", "nuggets/steel"));
    }

    public static TagKey<Item> copperNugget() {
        return ExtendedCogwheelsTags.optionalTag(Registry.ITEM, new ResourceLocation("forge", "nuggets/copper"));
    }
}
