package com.rabbitminers.extendedgears.cogwheels;

import com.jozufozu.flywheel.core.PartialModel;
import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsTileEntities;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class ShaftlessCogwheelBlock extends CustomCogwheelBlock {
    public ShaftlessCogwheelBlock(boolean large, Properties properties, PartialModel model) {
        super(large, properties, model);
    }

    public static CustomCogwheelBlock small(Properties properties, PartialModel model) {
        return new ShaftlessCogwheelBlock(false, properties, model);
    }

    public static CustomCogwheelBlock large(Properties properties, PartialModel model) {
        return new ShaftlessCogwheelBlock(true, properties, model);
    }

    @Override
    public BlockEntityType<? extends KineticTileEntity> getTileEntityType() {
        return ExtendedCogwheelsTileEntities.SHAFTLESS_COGWHEEL_TILE_ENTITY.get();
    }

    @Override
    public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
        return false;
    }

    @Override
    public PartialModel getShaftPartialModel() {
        return null;
    }
}
