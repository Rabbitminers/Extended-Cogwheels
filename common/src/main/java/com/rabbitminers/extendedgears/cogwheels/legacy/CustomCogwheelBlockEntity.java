package com.rabbitminers.extendedgears.cogwheels.legacy;

import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

@Deprecated
public class CustomCogwheelBlockEntity extends BracketedKineticBlockEntity {
    public CustomCogwheelBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
}
