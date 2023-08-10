package com.rabbitminers.extendedgears.mixin;

import com.jozufozu.flywheel.api.InstanceData;
import com.jozufozu.flywheel.api.Instancer;
import com.jozufozu.flywheel.api.Material;
import com.jozufozu.flywheel.api.MaterialManager;
import com.jozufozu.flywheel.core.PartialModel;
import com.jozufozu.flywheel.core.materials.model.ModelData;
import com.jozufozu.flywheel.core.model.BlockModel;
import com.jozufozu.flywheel.util.transform.TransformStack;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.rabbitminers.extendedgears.cogwheels.CogwheelModelKey;
import com.rabbitminers.extendedgears.cogwheels.HalfShaftCogwheelBlock;
import com.rabbitminers.extendedgears.mixin_interface.DynamicCogwheelRenderer;
import com.rabbitminers.extendedgears.mixin_interface.IDynamicMaterialBlockEntity;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityInstance;
import com.simibubi.create.content.kinetics.base.RotatedPillarKineticBlock;
import com.simibubi.create.content.kinetics.base.flwdata.RotatingData;
import com.simibubi.create.content.kinetics.mechanicalArm.ArmRenderer;
import com.simibubi.create.content.kinetics.simpleRelays.ICogWheel;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogInstance;
import com.simibubi.create.content.kinetics.waterwheel.WaterWheelRenderer;
import com.simibubi.create.foundation.render.CachedBufferer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;
import java.util.function.Supplier;

@Mixin(EncasedCogInstance.class)
@SuppressWarnings("OptionalUsedAsType")
public class MixinEncasedCogInstance extends KineticBlockEntityInstance<KineticBlockEntity> {
    @Shadow protected RotatingData rotatingModel;
    @Shadow protected Optional<RotatingData> rotatingTopShaft;
    @Shadow protected Optional<RotatingData> rotatingBottomShaft;

    protected ModelData casing;

    protected KineticBlockEntity blockEntity;
    protected boolean large;
    @Nullable protected CogwheelModelKey key;


    public MixinEncasedCogInstance(MaterialManager materialManager, KineticBlockEntity blockEntity) {
        super(materialManager, blockEntity);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    public void onInit(MaterialManager modelManager, KineticBlockEntity blockEntity, boolean large, CallbackInfo ci) {
        this.blockEntity = blockEntity;
        if (blockEntity instanceof IDynamicMaterialBlockEntity dynamicMaterialBlockEntity &&
                !blockEntity.getBlockState().is(AllBlocks.SHAFT.get())) {
            this.large = blockEntity.getBlockState().getBlock() instanceof ICogWheel cogWheel && cogWheel.isLargeCog();
            this.key = new CogwheelModelKey(large, blockEntity.getBlockState(), dynamicMaterialBlockEntity.getMaterial());
        }
    }

    @Inject(method = "init", at = @At("HEAD"), remap = false)
    public void registerCasing(CallbackInfo ci) {
        Material<ModelData> mat = getTransformMaterial();
        casing = mat.getModel(blockEntity.getBlockState()).createInstance();

        PoseStack msLocal = new PoseStack();
        TransformStack msr = TransformStack.cast(msLocal);
        msr.translate(getInstancePosition());

        casing.setTransform(msLocal);

        msLocal.popPose();
    }

    @Override
    public boolean shouldReset() {
        return super.shouldReset() || (blockEntity instanceof IDynamicMaterialBlockEntity dynamicMaterialBlockEntity
                && key != null && key.material() != dynamicMaterialBlockEntity.getMaterial());
    }

    @Redirect(
            method = "getCogModel",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/jozufozu/flywheel/api/Material;getModel(Lcom/jozufozu/flywheel/core/PartialModel;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/Direction;Ljava/util/function/Supplier;)Lcom/jozufozu/flywheel/api/Instancer;"
            )
    )
    public Instancer<RotatingData> changeCogwheelModel(Material<RotatingData> instance, PartialModel partial, BlockState referenceState,
               Direction dir, Supplier<PoseStack> modelTransform) {
        if (key == null) return instance.getModel(partial);
        return instance.model(key, () -> {
            BakedModel model = DynamicCogwheelRenderer.generateModel(key);
            PoseStack transform = CachedBufferer.rotateToFaceVertical(dir).get();
            return BlockModel.of(model, Blocks.AIR.defaultBlockState(), transform);
        });
    }

    @Inject(method = "updateLight", at = @At("HEAD"), remap = false)
    public void updateCasingLight(CallbackInfo ci) {
        relight(pos, casing);
    }

    @Override
    public void remove() {
        casing.delete();
        rotatingModel.delete();
        rotatingTopShaft.ifPresent(InstanceData::delete);
        rotatingBottomShaft.ifPresent(InstanceData::delete);
    }
}
