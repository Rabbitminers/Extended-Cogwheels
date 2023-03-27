package com.rabbitminers.extendedgears.integration;

import com.rabbitminers.extendedgears.integration.registry.KubeJsTileEntities;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import com.simibubi.create.content.contraptions.relays.elementary.CogWheelBlock;
import dev.latvian.mods.kubejs.block.BlockBuilder;
import dev.latvian.mods.kubejs.block.EntityBlockKJS;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class CogwheelBlockJS extends CogWheelBlock implements EntityBlockKJS {

    public final BlockBuilder blockBuilder;

    public CogwheelBlockJS(KubeJsCogwheelBuilder builder) {
        super(builder.isLarge, builder.createProperties());
        this.blockBuilder = builder;
    }

    @Override
    public @Nullable BlockBuilder getBlockBuilderKJS() {
        return blockBuilder;
    }

    @Override
    public BlockEntityType<? extends KineticTileEntity> getTileEntityType() {
        return KubeJsTileEntities.KUBEJS_COGWHEEL_TILE_ENTITY.get();
    }

    @Override
    public void withTileEntityDo(BlockGetter world, BlockPos pos, Consumer<KineticTileEntity> action) {
        super.withTileEntityDo(world, pos, action);
    }

    @Override
    public InteractionResult onTileEntityUse(BlockGetter world, BlockPos pos, Function<KineticTileEntity, InteractionResult> action) {
        return super.onTileEntityUse(world, pos, action);
    }

    @Override
    public Optional<KineticTileEntity> getTileEntityOptional(BlockGetter world, BlockPos pos) {
        return super.getTileEntityOptional(world, pos);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return super.newBlockEntity(p_153215_, p_153216_);
    }

    @Override
    public <S extends BlockEntity> BlockEntityTicker<S> getTicker(Level p_153212_, BlockState p_153213_, BlockEntityType<S> p_153214_) {
        return super.getTicker(p_153212_, p_153213_, p_153214_);
    }

    @Override
    public SpeedLevel getMinimumRequiredSpeedLevel() {
        return super.getMinimumRequiredSpeedLevel();
    }

    @Override
    public boolean hideStressImpact() {
        return super.hideStressImpact();
    }

    @Override
    public boolean showCapacityWithAnnotation() {
        return super.showCapacityWithAnnotation();
    }

    @Override
    public boolean tryRemoveBracket(UseOnContext context) {
        return super.tryRemoveBracket(context);
    }

    @Override
    public BlockState updateAfterWrenched(BlockState newState, UseOnContext context) {
        return super.updateAfterWrenched(newState, context);
    }

    @Override
    public InteractionResult onSneakWrenched(BlockState state, UseOnContext context) {
        return super.onSneakWrenched(state, context);
    }

    @Override
    public void playRemoveSound(Level world, BlockPos pos) {
        super.playRemoveSound(world, pos);
    }

    @Override
    public void playRotateSound(Level world, BlockPos pos) {
        super.playRotateSound(world, pos);
    }

    @Override
    public BlockState getRotatedBlockState(BlockState originalState, Direction targetedFace) {
        return super.getRotatedBlockState(originalState, targetedFace);
    }
}
