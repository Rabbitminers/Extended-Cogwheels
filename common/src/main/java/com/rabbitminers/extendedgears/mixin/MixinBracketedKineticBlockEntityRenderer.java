package com.rabbitminers.extendedgears.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.rabbitminers.extendedgears.ExtendedCogwheelsClient;
import com.rabbitminers.extendedgears.cogwheels.CogwheelModelKey;
import com.rabbitminers.extendedgears.cogwheels.HalfShaftCogwheelBlock;
import com.rabbitminers.extendedgears.mixin_interface.DynamicCogwheelRenderer;
import com.rabbitminers.extendedgears.mixin_interface.IDynamicMaterialBlockEntity;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.base.RotatedPillarKineticBlock;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntityRenderer;

import com.simibubi.create.content.kinetics.simpleRelays.ICogWheel;
import com.simibubi.create.foundation.render.BakedModelRenderHelper;
import com.simibubi.create.foundation.render.CachedBufferer;
import com.simibubi.create.foundation.render.SuperByteBuffer;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;

import net.minecraft.core.Direction.AxisDirection;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;


@Mixin(BracketedKineticBlockEntityRenderer.class)
public class MixinBracketedKineticBlockEntityRenderer extends KineticBlockEntityRenderer<BracketedKineticBlockEntity> {
    public MixinBracketedKineticBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }
    @Override
    protected SuperByteBuffer getRotatedModel(BracketedKineticBlockEntity be, BlockState state) {
        if (!(be instanceof IDynamicMaterialBlockEntity dmbe))
            return super.getRotatedModel(be, state);

        boolean large = be.getBlockState().getBlock() instanceof ICogWheel cogWheel && cogWheel.isLargeCog();
        CogwheelModelKey key = new CogwheelModelKey(large, state, dmbe.getMaterial());
        return ExtendedCogwheelsClient.BUFFER_CACHE.get(DynamicCogwheelRenderer.COGWHEEL, key, () -> {
            BakedModel model = DynamicCogwheelRenderer.generateModel(key);
            BlockState state1 = key.state();
            AxisDirection axisDirection = state1.getBlock() instanceof HalfShaftCogwheelBlock halfShaftCogwheelBlock
                    ? HalfShaftCogwheelBlock.getAxisDirection(state1)
                    : AxisDirection.POSITIVE;
            Direction dir = Direction.fromAxisAndDirection(state1.getValue(RotatedPillarKineticBlock.AXIS), axisDirection);
            PoseStack transform = CachedBufferer.rotateToFaceVertical(dir).get();
            return BakedModelRenderHelper.standardModelRender(model, Blocks.AIR.defaultBlockState(), transform);
        });
    }
}
