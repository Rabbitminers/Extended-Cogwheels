package com.rabbitminers.extendedgears.tileentities;

import com.jozufozu.flywheel.backend.Backend;
import com.jozufozu.flywheel.core.PartialModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.rabbitminers.extendedgears.basecog.ICustomCogWheel;
import com.rabbitminers.extendedgears.index.ECPartials;
import com.simibubi.create.AllBlockPartials;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import com.simibubi.create.content.contraptions.relays.elementary.BracketedKineticTileRenderer;
import com.simibubi.create.foundation.render.CachedBufferer;
import com.simibubi.create.foundation.render.SuperByteBuffer;
import com.simibubi.create.foundation.utility.AnimationTickHolder;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

public class CogWheelKineticTileRenderer extends BracketedKineticTileRenderer {
    public CogWheelKineticTileRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }
    @Override
    protected void renderSafe(KineticTileEntity te, float partialTicks, PoseStack ms, MultiBufferSource buffer,
                              int light, int overlay) {

        if (Backend.canUseInstancing(te.getLevel()))
            return;

        PartialModel model = ICustomCogWheel.getPartialModelType(te.getBlockState());
        if (model == null) model = AllBlockPartials.SHAFTLESS_COGWHEEL;

        Direction.Axis axis = getRotationAxisOf(te);
        BlockPos pos = te.getBlockPos();

        Direction facing = Direction.fromAxisAndDirection(axis, Direction.AxisDirection.POSITIVE);

        if (!AllBlocks.LARGE_COGWHEEL.has(te.getBlockState())) {
            renderRotatingBuffer(te,
                    CachedBufferer.partialFacingVertical(model, te.getBlockState(), facing),
                    ms, buffer.getBuffer(RenderType.solid()), light);
        } else {
            renderRotatingBuffer(te,
                    CachedBufferer.partialFacingVertical(model, te.getBlockState(), facing),
                    ms, buffer.getBuffer(RenderType.solid()), light);
        }

        // Large cogs sometimes have to offset their teeth by 11.25 degrees in order to
        // mesh properly


        float offset = getShaftAngleOffset(axis, pos);
        float time = AnimationTickHolder.getRenderTime(te.getLevel());
        float angle = ((time * te.getSpeed() * 3f / 10 + offset) % 360) / 180 * (float) Math.PI;

        SuperByteBuffer shaft =
                CachedBufferer.partialFacingVertical(AllBlockPartials.COGWHEEL_SHAFT, te.getBlockState(), facing);
        kineticRotationTransform(shaft, te, axis, angle, light);
        shaft.renderInto(ms, buffer.getBuffer(RenderType.solid()));

    }

}
