package com.rabbitminers.extendedgears.base.util.fabric;

import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsTags;
import com.simibubi.create.AllItems;
import com.simibubi.create.AllTags;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class TagUtilsImpl {

    public static TagKey<Item> steelIngot() {
        return ExtendedCogwheelsTags.optionalTag(Registry.ITEM, new ResourceLocation("c", "steel_ingots"));
    }

    public static TagKey<Item> steelNugget() {
        return ExtendedCogwheelsTags.optionalTag(Registry.ITEM, new ResourceLocation("c", "nuggets/steel"));
    }

    public static TagKey<Item> copperNugget() {
        return ExtendedCogwheelsTags.optionalTag(Registry.ITEM, new ResourceLocation("c", "nuggets/copper"));
    }
}
