package com.rabbitminers.extendedgears.cogwheels;

import net.minecraft.world.level.block.state.BlockState;

public record CogwheelModelKey(boolean large, BlockState state, BlockState material) {
}
