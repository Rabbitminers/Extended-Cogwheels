package com.rabbitminers.extendedgears.index;

import com.rabbitminers.extendedgears.ExtendedGears;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ECTags {
    public static class Blocks {
        public static TagKey<Block> COGWHEELS = forgeTag("cogwheels");
        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(ExtendedGears.MODID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }
}
