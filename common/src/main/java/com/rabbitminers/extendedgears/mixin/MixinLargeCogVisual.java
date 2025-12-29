package com.rabbitminers.extendedgears.mixin;

import com.rabbitminers.extendedgears.cogwheels.HalfShaftCogwheelBlock;
import com.rabbitminers.extendedgears.mixin_interface.CogwheelTypeProvider;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.base.RotatingInstance;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntityRenderer;
import com.simibubi.create.foundation.render.AllInstanceTypes;
import dev.engine_room.flywheel.api.model.Model;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.model.Models;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.AxisDirection;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(
        targets = "com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntityVisual$LargeCogVisual"
)
public abstract class MixinLargeCogVisual {

    @Mutable
    @Shadow @Final protected RotatingInstance additionalShaft;

    @Inject(method = "<init>", at = @At("TAIL"), remap = false)
    private void extendedgears$replaceShaft(
            VisualizationContext context,
            BracketedKineticBlockEntity blockEntity,
            float partialTick,
            CallbackInfo ci
    ) {
        BlockState state = blockEntity.getBlockState();
        Block block = state.getBlock();

        Model shaftModel = resolveShaftModel(block);
        if (shaftModel == null)
            return;

        Direction.Axis axis = KineticBlockEntityRenderer.getRotationAxisOf(blockEntity);

        AxisDirection axisDirection = block instanceof HalfShaftCogwheelBlock
                ? HalfShaftCogwheelBlock.getAxisDirection(state)
                : AxisDirection.POSITIVE;

        Direction facing = Direction.fromAxisAndDirection(axis, axisDirection);

        // Remove vanilla shaft
        additionalShaft.delete();

        // Create replacement shaft
        additionalShaft = context.instancerProvider()
                .instancer(AllInstanceTypes.ROTATING, shaftModel)
                .createInstance();

        additionalShaft
                .rotateToFace(facing)
                .setup(blockEntity)
                .setRotationOffset(
                        BracketedKineticBlockEntityRenderer.getShaftAngleOffset(axis, blockEntity.getBlockPos())
                )
                .setChanged();
    }

    @Unique
    private static Model resolveShaftModel(Block block) {
        if (!(block instanceof CogwheelTypeProvider provider))
            return Models.partial(AllPartialModels.COGWHEEL_SHAFT);

        return switch (provider.getType()) {
            case STANDARD -> Models.partial(AllPartialModels.COGWHEEL_SHAFT);
            case HALF_SHAFT -> Models.partial(AllPartialModels.SHAFT_HALF);
            case SHAFLTESS -> null;
        };
    }
}