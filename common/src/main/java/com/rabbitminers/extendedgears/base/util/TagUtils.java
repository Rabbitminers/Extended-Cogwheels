package com.rabbitminers.extendedgears.base.util;

import com.simibubi.create.AllTags;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

@Deprecated
public class TagUtils {
    @ExpectPlatform
    public static TagKey<Item> steelIngot() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static TagKey<Item> steelNugget() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static TagKey<Item> copperNugget() {
        throw new AssertionError();
    }
}
