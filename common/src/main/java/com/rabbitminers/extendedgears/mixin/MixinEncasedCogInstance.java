package com.rabbitminers.extendedgears.mixin;

import com.rabbitminers.extendedgears.cogwheels.CogwheelModelKey;
import com.rabbitminers.extendedgears.mixin_interface.IDynamicMaterialBlockEntity;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityVisual;
import com.simibubi.create.content.kinetics.base.RotatingInstance;
import com.simibubi.create.content.kinetics.simpleRelays.ICogWheel;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogVisual;
import dev.engine_room.flywheel.api.model.Model;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EncasedCogVisual.class)
@SuppressWarnings("OptionalUsedAsType")
public abstract class MixinEncasedCogInstance extends KineticBlockEntityVisual<KineticBlockEntity> {
    @Final
    @Shadow protected RotatingInstance rotatingModel;
    @Shadow @Final
    @Mutable
    @Nullable
    protected RotatingInstance rotatingTopShaft;

    @Shadow @Final @Mutable @Nullable
    protected RotatingInstance rotatingBottomShaft;

   // protected ModelData casing;

    protected KineticBlockEntity blockEntity;
    protected boolean large;
    @Nullable protected CogwheelModelKey key;


    public MixinEncasedCogInstance(VisualizationContext materialManager, KineticBlockEntity blockEntity, float partialTick) {
        super(materialManager, blockEntity, partialTick);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    public void onInit(VisualizationContext modelManager, KineticBlockEntity blockEntity, boolean large, float partialTick, Model model, CallbackInfo ci) {
        this.blockEntity = blockEntity;
        if (blockEntity instanceof IDynamicMaterialBlockEntity dynamicMaterialBlockEntity &&
                !blockEntity.getBlockState().is(AllBlocks.SHAFT.get())) {
            this.large = blockEntity.getBlockState().getBlock() instanceof ICogWheel cogWheel && cogWheel.isLargeCog();
            this.key = new CogwheelModelKey(large, blockEntity.getBlockState(), dynamicMaterialBlockEntity.getMaterial());
        }
    }

//    @Inject(method = "init", at = @At("HEAD"), remap = false)
//    public void registerCasing(CallbackInfo ci) {
//        Material<ModelData> mat = getTransformMaterial();
//        casing = mat.getModel(blockEntity.getBlockState()).createInstance();
//
//        PoseStack msLocal = new PoseStack();
//        TransformStack msr = TransformStack.cast(msLocal);
//        msr.translate(getInstancePosition());
//
//        casing.setTransform(msLocal);
//
//        msLocal.popPose();
//    }

//    @Override
//    public boolean shouldReset() {
//        return super.shouldReset() || (blockEntity instanceof IDynamicMaterialBlockEntity dynamicMaterialBlockEntity
//                && key != null && key.material() != dynamicMaterialBlockEntity.getMaterial());
//    }

//    @Redirect(
//            method = "getCogModel",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lcom/jozufozu/flywheel/api/Material;getModel(Lcom/jozufozu/flywheel/core/PartialModel;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/Direction;Ljava/util/function/Supplier;)Lcom/jozufozu/flywheel/api/Instancer;"
//            )
//    )
//    public Instancer<RotatingData> changeCogwheelModel(Material<RotatingData> instance, PartialModel partial, BlockState referenceState,
//               Direction dir, Supplier<PoseStack> modelTransform) {
//        if (key == null) return instance.getModel(partial);
//        return instance.model(key, () -> {
//            BakedModel model = DynamicCogwheelRenderer.generateModel(key);
//            PoseStack transform = CachedBufferer.rotateToFaceVertical(dir).get();
//            return BlockModel.of(model, Blocks.AIR.defaultBlockState(), transform);
//        });
//    }

//    @Inject(method = "updateLight", at = @At("HEAD"), remap = false)
//    public void updateCasingLight(CallbackInfo ci) {
//        relight(rotatingModel, rotatingTopShaft, rotatingBottomShaft);
//    }

//    @Override
//    public void remove() {
//        casing.delete();
//        rotatingModel.delete();
//        rotatingTopShaft.ifPresent(InstanceData::delete);
//        rotatingBottomShaft.ifPresent(InstanceData::delete);
//    }
}
