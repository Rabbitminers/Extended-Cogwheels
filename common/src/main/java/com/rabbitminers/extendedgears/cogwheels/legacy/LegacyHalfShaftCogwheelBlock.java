package com.rabbitminers.extendedgears.cogwheels.legacy;

import com.jozufozu.flywheel.core.PartialModel;
import com.rabbitminers.extendedgears.base.data.ICogwheelMaterial;
import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsTileEntities;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.AllShapes;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.foundation.utility.VoxelShaper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Deprecated
public class LegacyHalfShaftCogwheelBlock extends CustomCogwheelBlock {
    public VoxelShaper voxelShape = shapeBuilder(box(2.0D, 6.0D, 2.0D, 14.0D, 10.0D, 14.0D))
            .add(6.0D, 8, 6.0D, 10.0D, 16, 10.0D).forDirectional();
    public VoxelShaper largeVoxelShape = shapeBuilder(box(0.0D, 6.0D, 0.0D, 16.0D, 10.0D, 16.0D))
            .add(6.0D, 8, 6.0D, 10.0D, 16, 10.0D).forDirectional();

    public static final BooleanProperty AXIS_DIRECTION = BooleanProperty.create("axis_direction");

    public LegacyHalfShaftCogwheelBlock(boolean large, Properties properties, ICogwheelMaterial model) {
        super(large, properties, model);
        registerDefaultState(this.defaultBlockState().setValue(AXIS_DIRECTION,
                isDirectionPosotive(Direction.AxisDirection.POSITIVE)));
    }

    // Again - Don't actually need these but removing them breaks shit
    public static CustomCogwheelBlock small(Properties properties, ICogwheelMaterial model) {
        return new LegacyHalfShaftCogwheelBlock(false, properties, model);
    }

    public static CustomCogwheelBlock large(Properties properties, ICogwheelMaterial model) {
        return new LegacyHalfShaftCogwheelBlock(true, properties, model);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AXIS_DIRECTION);
        super.createBlockStateDefinition(builder);
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter worldIn, @NotNull BlockPos pos,
                                        @NotNull CollisionContext context) {
        if (!state.hasProperty(AXIS))
            return super.getShape(state, worldIn, pos, context);
        Direction dir = Direction.fromAxisAndDirection(state.getValue(AXIS),
                directionFromValue(state.getValue(AXIS_DIRECTION)));
        return isLargeCog() ? largeVoxelShape.get(dir) : voxelShape.get(dir);
    }


    @Override
    public BlockEntityType<? extends KineticBlockEntity> getBlockEntityType() {
        return ExtendedCogwheelsTileEntities.CUSTOM_COGWHEEL_TILE_ENTITY.get();
    }

    public static boolean isDirectionPosotive(Direction.AxisDirection dir) {
        return dir == Direction.AxisDirection.POSITIVE;
    }

    public static Direction.AxisDirection directionFromValue(boolean bool) {
        return bool ? Direction.AxisDirection.POSITIVE : Direction.AxisDirection.NEGATIVE;
    }

    public static AllShapes.Builder shapeBuilder(VoxelShape shape) {
        return new AllShapes.Builder(shape);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction direction = context.getClickedFace().getOpposite();
        boolean isDirectionPosotive = isDirectionPosotive(direction.getAxisDirection());
        Direction.Axis axisFromDirection = direction.getAxis();
        if (context.getPlayer() == null)
            return super.getStateForPlacement(context);
        return super.getStateForPlacement(context)
            .setValue(AXIS_DIRECTION, context.getPlayer()
            .isShiftKeyDown() != isDirectionPosotive)
            .setValue(AXIS, axisFromDirection);
    }

    @Override
    public Direction.AxisDirection getAxisDirection(BlockState state) {
        return directionFromValue(state.getValue(AXIS_DIRECTION));
    }

    @Override
    public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
        return face.getAxis() == state.getValue(AXIS) && face.getAxisDirection() == directionFromValue(state.getValue(AXIS_DIRECTION));
    }

    @Override
    public @Nullable PartialModel getShaftPartialModel() {
        return AllPartialModels.SHAFT_HALF;
    }
}
