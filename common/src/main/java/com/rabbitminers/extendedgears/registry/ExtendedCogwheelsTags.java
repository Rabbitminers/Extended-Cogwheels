package com.rabbitminers.extendedgears.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ExtendedCogwheelsTags {

    public static final TagKey<Item> SMALL_COGWHEEL =
            TagKey.create(Registries.ITEM, new ResourceLocation("forge", "cogwheel/small"));

    public static final TagKey<Item> LARGE_COGWHEEL =
            TagKey.create(Registries.ITEM, new ResourceLocation("forge", "cogwheel/large"));

    public static final TagKey<Item> COGWHEEL =
            TagKey.create(Registries.ITEM, new ResourceLocation("forge", "cogwheel"));
}