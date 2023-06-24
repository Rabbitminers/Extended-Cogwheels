package com.rabbitminers.extendedgears.cogwheels;

import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class CustomCogwheelTileEntity extends BracketedKineticBlockEntity {
    public CustomCogwheelTileEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
}
