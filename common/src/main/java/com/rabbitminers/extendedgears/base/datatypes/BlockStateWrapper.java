package com.rabbitminers.extendedgears.base.datatypes;

import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public interface BlockStateWrapper {
    BlockState getState();

    final class BlockEntryWrapper implements BlockStateWrapper {
        private final BlockEntry<?> entry;

        public BlockEntryWrapper(BlockEntry<?> entry) {
            this.entry = entry;
        }

        @Override
        public BlockState getState() {
            return entry.getDefaultState();
        }
    }

    final class BlockWrapper implements BlockStateWrapper {
        private final Block block;

        public BlockWrapper(Block block) {
            this.block = block;
        }

        @Override
        public BlockState getState() {
            return block.defaultBlockState();
        }
    }

    public static BlockStateWrapper of(BlockEntry<?> entry) {
        return new BlockEntryWrapper(entry);
    }

    public static BlockStateWrapper of(Block block) {
        return new BlockWrapper(block);
    }
}
