package com.rabbitminers.extendedgears.tileentities;

import com.simibubi.create.content.contraptions.relays.elementary.BracketedKineticTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class CogWheelKineticTileEntity extends BracketedKineticTileEntity {
    public CogWheelKineticTileEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
}
