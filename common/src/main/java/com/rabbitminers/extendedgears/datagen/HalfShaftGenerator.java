package com.rabbitminers.extendedgears.datagen;

import com.rabbitminers.extendedgears.cogwheels.HalfShaftCogwheelBlock;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.SpecialBlockStateGen;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.ModelFile;

public class HalfShaftGenerator extends SpecialBlockStateGen {
    @Override
    protected int getXRotation(BlockState state) {
        Direction.Axis axis = state.getValue(BlockStateProperties.AXIS);
        Direction.AxisDirection dir = HalfShaftCogwheelBlock
                .directionFromValue(state.getValue(HalfShaftCogwheelBlock.AXIS_DIRECTION));
        return (axis == Direction.Axis.Y ? 0 : 90) +
                (axis.isVertical() && dir == Direction.AxisDirection.NEGATIVE ? 180 : 0);
    }

    @Override
    protected int getYRotation(BlockState state) {
        Direction.Axis axis = state.getValue(BlockStateProperties.AXIS);
        Direction.AxisDirection dir = HalfShaftCogwheelBlock
                .directionFromValue(state.getValue(HalfShaftCogwheelBlock.AXIS_DIRECTION));
        return (axis == Direction.Axis.X ? 90 : (axis == Direction.Axis.Z ? 180 : 0)) +
                (axis.isHorizontal() && dir == Direction.AxisDirection.NEGATIVE ? 180 : 0);
    }

    @Override
    public <T extends Block> ModelFile getModel(DataGenContext<Block, T> ctx, RegistrateBlockstateProvider prov,
                                                BlockState state) {
        return AssetLookup.standardModel(ctx, prov);
    }
}
