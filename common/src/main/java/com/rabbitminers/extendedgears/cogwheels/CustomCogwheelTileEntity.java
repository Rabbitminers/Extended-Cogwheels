package com.rabbitminers.extendedgears.cogwheels;

import com.simibubi.create.content.contraptions.relays.elementary.BracketedKineticTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class CustomCogwheelTileEntity extends BracketedKineticTileEntity {
    public CustomCogwheelTileEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
}
