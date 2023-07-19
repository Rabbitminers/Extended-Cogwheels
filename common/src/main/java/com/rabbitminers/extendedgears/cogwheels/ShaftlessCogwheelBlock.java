package com.rabbitminers.extendedgears.cogwheels;

import com.jozufozu.flywheel.core.PartialModel;
import com.rabbitminers.extendedgears.mixin_interface.ICogwheelModelProvider;
import com.rabbitminers.extendedgears.mixin_interface.IDynamicMaterialBlockEntity;
import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsPartials;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.CogWheelBlock;
import com.simibubi.create.content.kinetics.simpleRelays.CogwheelBlockItem;
import com.simibubi.create.content.kinetics.simpleRelays.ICogWheel;
import com.simibubi.create.foundation.utility.VoxelShaper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import static com.simibubi.create.content.kinetics.base.RotatedPillarKineticBlock.AXIS;
import static com.simibubi.create.foundation.block.ProperWaterloggedBlock.WATERLOGGED;

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
    public @NotNull InteractionResult use(BlockState state, Level level, BlockPos pos, Player player,
              InteractionHand hand, BlockHitResult ray) {
        if (player.isShiftKeyDown() || !player.mayBuild())
            return InteractionResult.PASS;
        ItemStack heldItem = player.getItemInHand(hand);
        if (heldItem.is(AllBlocks.SHAFT.get().asItem()) && !level.isClientSide) {
            boolean isLarge = ICogWheel.isLargeCog(state);
            BlockState newState = isLarge ? AllBlocks.LARGE_COGWHEEL.getDefaultState()
                    : AllBlocks.COGWHEEL.getDefaultState();
            newState = newState.setValue(AXIS, state.getValue(AXIS))
                    .setValue(WATERLOGGED, state.getValue(WATERLOGGED));
            BlockEntity oldBe = level.getBlockEntity(pos);
            if (!(oldBe instanceof IDynamicMaterialBlockEntity oldDyn))
                return InteractionResult.FAIL;
            BlockState material = oldDyn.getMaterial();
            level.setBlock(pos, newState, 3);
            if (!player.isCreative())
                heldItem.shrink(1);
            BlockEntity be = level.getBlockEntity(pos);
            if (!(be instanceof IDynamicMaterialBlockEntity newDyn))
                return InteractionResult.FAIL;
            newDyn.applyMaterial(material);
            return InteractionResult.SUCCESS;
        }
        return super.use(state, level, pos, player, hand, ray);
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
