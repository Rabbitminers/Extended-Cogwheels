package com.rabbitminers.extendedgears.cogwheels;

import com.jozufozu.flywheel.core.PartialModel;
import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsTileEntities;
import com.simibubi.create.AllBlockPartials;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.Nullable;

public class HalfShaftCogwheelBlock extends CustomCogwheelBlock {
    public static final BooleanProperty AXIS_DIRECTION = BooleanProperty.create("axis_direction");

    public HalfShaftCogwheelBlock(boolean large, Properties properties, PartialModel model) {
        super(large, properties, model);
        registerDefaultState(this.defaultBlockState().setValue(AXIS_DIRECTION,
                isDirectionPosotive(Direction.AxisDirection.POSITIVE)));
    }

    // Again - Don't actually need these but removing them breaks shit
    public static CustomCogwheelBlock small(Properties properties, PartialModel model) {
        return new HalfShaftCogwheelBlock(false, properties, model);
    }

    public static CustomCogwheelBlock large(Properties properties, PartialModel model) {
        return new HalfShaftCogwheelBlock(true, properties, model);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AXIS_DIRECTION);
        super.createBlockStateDefinition(builder);
    }

    @Override
    public BlockEntityType<? extends KineticTileEntity> getTileEntityType() {
        return ExtendedCogwheelsTileEntities.SHAFTLESS_COGWHEEL_TILE_ENTITY.get();
    }

    public static boolean isDirectionPosotive(Direction.AxisDirection dir) {
        return dir == Direction.AxisDirection.POSITIVE;
    }

    public static Direction.AxisDirection directionFromValue(boolean bool) {
        return bool ? Direction.AxisDirection.POSITIVE : Direction.AxisDirection.NEGATIVE;
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
        return AllBlockPartials.SHAFT_HALF;
    }
}
