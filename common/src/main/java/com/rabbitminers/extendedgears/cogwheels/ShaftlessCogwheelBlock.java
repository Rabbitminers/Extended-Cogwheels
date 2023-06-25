package com.rabbitminers.extendedgears.cogwheels;

import com.jozufozu.flywheel.core.PartialModel;
import com.rabbitminers.extendedgears.base.data.ICogwheelMaterial;
import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsTileEntities;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.foundation.utility.VoxelShaper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class ShaftlessCogwheelBlock extends CustomCogwheelBlock {
    public VoxelShape voxelShape = Block.box(2.0D, 6.0D, 2.0D, 14.0D, 10.0D, 14.0D);
    public VoxelShape largeVoxelShape = Block.box(0.0D, 6.0D, 0.0D, 16.0D, 10.0D, 16.0D);

    public ShaftlessCogwheelBlock(boolean large, Properties properties, ICogwheelMaterial material) {
        super(large, properties, material);
    }

    // Don't actually need these but removing them breaks shit
    public static CustomCogwheelBlock small(Properties properties, ICogwheelMaterial material) {
        return new ShaftlessCogwheelBlock(false, properties, material);
    }

    public static CustomCogwheelBlock large(Properties properties, ICogwheelMaterial material) {
        return new ShaftlessCogwheelBlock(true, properties, material);
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter worldIn, @NotNull BlockPos pos,
                                        @NotNull CollisionContext context) {
        if (!state.hasProperty(AXIS))
            return super.getShape(state, worldIn, pos, context);
        return VoxelShaper.forAxis(isLargeCog() ? largeVoxelShape : voxelShape, Direction.Axis.Y).get(state.getValue(AXIS));
    }

    @Override
    public BlockEntityType<? extends KineticBlockEntity> getBlockEntityType() {
        return ExtendedCogwheelsTileEntities.CUSTOM_COGWHEEL_TILE_ENTITY.get();
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
