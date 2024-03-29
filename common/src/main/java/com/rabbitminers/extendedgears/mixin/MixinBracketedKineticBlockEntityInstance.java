package com.rabbitminers.extendedgears.mixin;

import com.jozufozu.flywheel.api.Instancer;
import com.jozufozu.flywheel.api.MaterialManager;
import com.jozufozu.flywheel.core.PartialModel;
import com.jozufozu.flywheel.core.model.BlockModel;
import com.jozufozu.flywheel.util.transform.TransformStack;
import com.mojang.blaze3d.vertex.PoseStack;
import com.rabbitminers.extendedgears.cogwheels.CogwheelModelKey;
import com.rabbitminers.extendedgears.cogwheels.HalfShaftCogwheelBlock;
import com.rabbitminers.extendedgears.cogwheels.DynamicCogwheelRenderer;
import com.rabbitminers.extendedgears.mixin_interface.CogwheelTypeProvider;
import com.rabbitminers.extendedgears.mixin_interface.IDynamicMaterialBlockEntity;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.base.RotatedPillarKineticBlock;
import com.simibubi.create.content.kinetics.base.SingleRotatingInstance;
import com.simibubi.create.content.kinetics.base.flwdata.RotatingData;
import com.simibubi.create.content.kinetics.simpleRelays.*;
import com.simibubi.create.foundation.render.CachedBufferer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.AxisDirection;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BracketedKineticBlockEntityInstance.class)
public abstract class MixinBracketedKineticBlockEntityInstance extends SingleRotatingInstance<BracketedKineticBlockEntity> {

    @Shadow protected RotatingData additionalShaft;

    @Shadow protected abstract PoseStack rotateToAxis(Direction.Axis axis);

    @Nullable protected CogwheelModelKey key;

    protected boolean large;

    public MixinBracketedKineticBlockEntityInstance(MaterialManager materialManager, BracketedKineticBlockEntity blockEntity) {
        super(materialManager, blockEntity);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    public void onInit(MaterialManager materialManager, BracketedKineticBlockEntity blockEntity, CallbackInfo ci) {
        if (blockEntity instanceof IDynamicMaterialBlockEntity dynamicMaterialBlockEntity &&
                !blockEntity.getBlockState().is(AllBlocks.SHAFT.get())) {
            this.large = ICogWheel.isLargeCog(blockState);
            this.key = new CogwheelModelKey(large, getRenderedBlockState(), dynamicMaterialBlockEntity.getMaterial());
        }
    }

    /**
     * @author Rabbitminers
     * @reason Overwrite additional shaft
     */
    @Overwrite(remap = false)
    public void init() {
        super.init();
        if (!(blockState.getBlock() instanceof CogWheelBlock))
            return;

        // Large cogs sometimes have to offset their teeth by 11.25 degrees in order to
        // mesh properly

        Direction.Axis axis = KineticBlockEntityRenderer.getRotationAxisOf(blockEntity);
        float speed = blockEntity.getSpeed();
        float offset = BracketedKineticBlockEntityRenderer.getShaftAngleOffset(axis, pos);
        boolean isHalfShaft = blockState.getBlock() instanceof  HalfShaftCogwheelBlock;
        AxisDirection direction = isHalfShaft ? HalfShaftCogwheelBlock.getAxisDirection(blockState)
                : AxisDirection.POSITIVE;

        Direction facing = Direction.fromAxisAndDirection(axis, direction);

        @Nullable PartialModel shaftModel = getShaftModel(blockState.getBlock());
        if (shaftModel == null)
            return;

        Instancer<RotatingData> half = getRotatingMaterial().getModel(shaftModel, blockState,
                facing, () -> isHalfShaft ? this.rotateToAxis(axis, direction) : rotateToAxis(axis));

        additionalShaft = setup(half.createInstance(), speed);
        additionalShaft.setRotationOffset(offset);
    }

    private PoseStack rotateToAxis(Direction.Axis axis, AxisDirection direction) {
        Direction facing = Direction.fromAxisAndDirection(axis, direction);
        PoseStack poseStack = new PoseStack();
        TransformStack.cast(poseStack)
                .centre()
                .rotateToFace(facing.getOpposite())
                .unCentre();
        return poseStack;
    }

    @Nullable
    public PartialModel getShaftModel(Block block) {
        if (!(block instanceof CogwheelTypeProvider provider))
            return AllPartialModels.COGWHEEL_SHAFT;
        return switch (provider.getType()) {
            case STANDARD -> AllPartialModels.COGWHEEL_SHAFT;
            case HALF_SHAFT -> AllPartialModels.SHAFT_HALF;
            case SHAFLTESS -> null;
        };
    }


    @Override
    public boolean shouldReset() {
        boolean changed = false;
        if (blockEntity instanceof IDynamicMaterialBlockEntity dynamicMaterialBlockEntity && key != null)
            changed = key.material() != dynamicMaterialBlockEntity.getMaterial();
        return super.shouldReset() || changed;
    }

    @Override
    protected Instancer<RotatingData> getModel() {
        if (key == null)
            return super.getModel();
        return getRotatingMaterial().model(key, () -> {
            BakedModel model = DynamicCogwheelRenderer.generateModel(key);
            BlockState state = key.state();
            AxisDirection axisDirection = state.getBlock() instanceof HalfShaftCogwheelBlock
                    ? HalfShaftCogwheelBlock.getAxisDirection(state)
                    : AxisDirection.POSITIVE;
            Direction dir = Direction.fromAxisAndDirection(state.getValue(RotatedPillarKineticBlock.AXIS), axisDirection);
            PoseStack transform = CachedBufferer.rotateToFaceVertical(dir).get();
            return BlockModel.of(model, Blocks.AIR.defaultBlockState(), transform);
        });
    }

}
