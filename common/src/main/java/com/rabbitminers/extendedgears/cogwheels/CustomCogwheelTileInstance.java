package com.rabbitminers.extendedgears.cogwheels;

import com.jozufozu.flywheel.api.Instancer;
import com.jozufozu.flywheel.api.MaterialManager;
import com.jozufozu.flywheel.core.PartialModel;
import com.jozufozu.flywheel.util.transform.TransformStack;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.simibubi.create.AllBlockPartials;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import com.simibubi.create.content.contraptions.base.KineticTileEntityRenderer;
import com.simibubi.create.content.contraptions.base.flwdata.RotatingData;
import com.simibubi.create.content.contraptions.relays.elementary.BracketedKineticTileInstance;
import com.simibubi.create.content.contraptions.relays.elementary.BracketedKineticTileRenderer;
import com.simibubi.create.content.contraptions.relays.elementary.ICogWheel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

public class CustomCogwheelTileInstance extends BracketedKineticTileInstance {
    public CustomCogwheelTileInstance(MaterialManager modelManager, KineticTileEntity tile) {
        super(modelManager, tile);
    }

    private PoseStack rotateToAxis(Direction.Axis axis) {
        Direction facing = Direction.fromAxisAndDirection(axis, Direction.AxisDirection.POSITIVE);
        PoseStack poseStack = new PoseStack();
        TransformStack.cast(poseStack)
                .centre()
                .rotateToFace(facing)
                .multiply(Vector3f.XN.rotationDegrees(-90))
                .unCentre();
        return poseStack;
    }

    @Override
    public void init() {
        super.init();

        if (ICogWheel.isLargeCog(blockEntity.getBlockState()))
            return;

        float speed = blockEntity.getSpeed();
        Direction.Axis axis = KineticTileEntityRenderer.getRotationAxisOf(blockEntity);
        BlockPos pos = blockEntity.getBlockPos();
        float offset = BracketedKineticTileRenderer.getShaftAngleOffset(axis, pos);
        Direction facing = Direction.fromAxisAndDirection(axis, Direction.AxisDirection.POSITIVE);
        PartialModel shaftModel = ICustomCogwheel.getShaftPartialModelType(blockState);
        if (shaftModel == null)
            return;
        Instancer<RotatingData> half = getRotatingMaterial().getModel(shaftModel, blockState,
                facing, () -> this.rotateToAxis(axis));
        additionalShaft = setup(half.createInstance(), speed);
        additionalShaft.setRotationOffset(offset);
    }
    @Override
    protected Instancer<RotatingData> getModel() {
        PartialModel model = ICustomCogwheel.getPartialModelType(blockState.getBlock());
        if (model == null) model = AllBlockPartials.SHAFTLESS_COGWHEEL;

        Direction facing = Direction.fromAxisAndDirection(axis, Direction.AxisDirection.POSITIVE);
        if (!ICogWheel.isLargeCog(blockEntity.getBlockState()))
            return getRotatingMaterial().getModel(model, blockState, facing,
                    () -> this.rotateToAxis(axis));

        Direction.Axis axis = KineticTileEntityRenderer.getRotationAxisOf(blockEntity);
        return getRotatingMaterial().getModel(model, blockState, facing,
                () -> this.rotateToAxis(axis));
    }
}
