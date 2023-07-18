package com.rabbitminers.extendedgears.cogwheels;

import com.jozufozu.flywheel.core.PartialModel;
import com.rabbitminers.extendedgears.mixin_interface.ICogwheelModelProvider;
import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsPartials;
import com.simibubi.create.content.kinetics.simpleRelays.CogWheelBlock;
import com.simibubi.create.foundation.utility.VoxelShaper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class ShaftlessCogwheelBlock extends CogWheelBlock implements ICogwheelModelProvider {
    public VoxelShape voxelShape = Block.box(2.0D, 6.0D, 2.0D, 14.0D, 10.0D, 14.0D);
    public VoxelShape largeVoxelShape = Block.box(0.0D, 6.0D, 0.0D, 16.0D, 10.0D, 16.0D);

    protected ShaftlessCogwheelBlock(boolean large, Properties properties) {
        super(large, properties);
    }

    public static ShaftlessCogwheelBlock small(Properties properties) {
        return new ShaftlessCogwheelBlock(false, properties);
    }

    public static ShaftlessCogwheelBlock large(Properties properties) {
        return new ShaftlessCogwheelBlock(true, properties);
    }

    @Override
    public @NotNull InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand,
                                          BlockHitResult ray) {
        return super.use(state, world, pos, player, hand, ray);
    }

    @Override
    public PartialModel getTemplate(boolean large) {
        return large ? ExtendedCogwheelsPartials.LARGE_COGWHEEL : ExtendedCogwheelsPartials.COGWHEEL;
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter worldIn, @NotNull BlockPos pos,
                                        @NotNull CollisionContext context) {
        if (!state.hasProperty(AXIS))
            return super.getShape(state, worldIn, pos, context);
        return VoxelShaper.forAxis(isLargeCog() ? largeVoxelShape : voxelShape, Direction.Axis.Y).get(state.getValue(AXIS));
    }

    // Well that was simple
    @Override
    public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
        return false;
    }
}
