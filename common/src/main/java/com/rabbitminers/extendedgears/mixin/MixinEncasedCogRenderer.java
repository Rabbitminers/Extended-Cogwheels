package com.rabbitminers.extendedgears.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.rabbitminers.extendedgears.ExtendedCogwheelsClient;
import com.rabbitminers.extendedgears.cogwheels.CogwheelModelKey;
import com.rabbitminers.extendedgears.cogwheels.DynamicCogwheelRenderer;
import com.rabbitminers.extendedgears.mixin_interface.IDynamicMaterialBlockEntity;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.base.RotatedPillarKineticBlock;
import com.simibubi.create.content.kinetics.simpleRelays.ICogWheel;
import com.simibubi.create.content.kinetics.simpleRelays.SimpleKineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogRenderer;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogwheelBlock;
import com.simibubi.create.foundation.render.BakedModelRenderHelper;
import com.simibubi.create.foundation.render.CachedBufferer;
import com.simibubi.create.foundation.render.SuperByteBuffer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EncasedCogRenderer.class)
public class MixinEncasedCogRenderer extends KineticBlockEntityRenderer<SimpleKineticBlockEntity> {
    public MixinEncasedCogRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Inject(method = "renderSafe(Lcom/simibubi/create/content/kinetics/simpleRelays/SimpleKineticBlockEntity;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;II)V", at = @At("TAIL"))
    public void renderCasing(SimpleKineticBlockEntity be, float partialTicks, PoseStack ms, MultiBufferSource buffer,
                             int light, int overlay, CallbackInfo ci) {
        SuperByteBuffer casing = CachedBufferer.block(be.getBlockState());
        casing.renderInto(ms, buffer.getBuffer(RenderType.solid()));
    }

    @Override
    protected SuperByteBuffer getRotatedModel(SimpleKineticBlockEntity be, BlockState state) {
        if (!(be instanceof IDynamicMaterialBlockEntity dmbe))
            return super.getRotatedModel(be, state);

        boolean large = be.getBlockState().getBlock() instanceof ICogWheel cogWheel && cogWheel.isLargeCog();
        CogwheelModelKey key = new CogwheelModelKey(large, state, dmbe.getMaterial());
        return ExtendedCogwheelsClient.BUFFER_CACHE.get(DynamicCogwheelRenderer.COGWHEEL, key, () -> {
            BakedModel model = DynamicCogwheelRenderer.generateModel(key);
            BlockState state1 = key.state();
            Direction dir = Direction.fromAxisAndDirection(state1.getValue(RotatedPillarKineticBlock.AXIS), Direction.AxisDirection.POSITIVE);
            PoseStack transform = CachedBufferer.rotateToFaceVertical(dir).get();
            return BakedModelRenderHelper.standardModelRender(model, Blocks.AIR.defaultBlockState(), transform);
        });
    }
}
