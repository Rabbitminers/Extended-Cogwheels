package com.rabbitminers.extendedgears.cogwheels;

import com.simibubi.create.foundation.tileEntity.TileEntityBehaviour;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class ShaftlessCogwheelTileEntity extends CustomCogwheelTileEntity {
    public ShaftlessCogwheelTileEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void addBehaviours(List<TileEntityBehaviour> behaviours) {
        // Disable Shaft Shite
    }
}
