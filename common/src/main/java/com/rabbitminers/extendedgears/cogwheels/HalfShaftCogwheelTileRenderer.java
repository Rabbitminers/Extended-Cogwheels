package com.rabbitminers.extendedgears.cogwheels;

import com.jozufozu.flywheel.backend.Backend;
import com.jozufozu.flywheel.core.PartialModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.AllBlockPartials;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import com.simibubi.create.content.contraptions.base.KineticTileEntityRenderer;
import com.simibubi.create.content.contraptions.components.deployer.DeployerInstance;
import com.simibubi.create.content.contraptions.components.millstone.MillstoneRenderer;
import com.simibubi.create.foundation.render.CachedBufferer;
import com.simibubi.create.foundation.render.SuperByteBuffer;
import com.simibubi.create.foundation.utility.AnimationTickHolder;
import com.simibubi.create.foundation.utility.Iterate;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class HalfShaftCogwheelTileRenderer extends KineticTileEntityRenderer {
    public HalfShaftCogwheelTileRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }
    @Override
    protected SuperByteBuffer getRotatedModel(KineticTileEntity te, BlockState state) {
        PartialModel partialModel = ICustomCogwheel.getPartialModelType(state);
        return CachedBufferer.partial(partialModel, state);
    }
}
