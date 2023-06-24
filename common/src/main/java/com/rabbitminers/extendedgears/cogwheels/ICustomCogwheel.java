package com.rabbitminers.extendedgears.cogwheels;

import com.jozufozu.flywheel.core.PartialModel;
import com.rabbitminers.extendedgears.base.data.ICogwheelMaterial;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.simpleRelays.SimpleKineticBlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public interface ICustomCogwheel{
    ICogwheelMaterial getMaterial();

    default Direction.AxisDirection getAxisDirection(BlockState state) {
        return Direction.AxisDirection.POSITIVE;
    }

    @Nullable
    default PartialModel getShaftPartialModel() {
        return AllPartialModels.COGWHEEL_SHAFT;
    }

    default PartialModel getPartialModelType() {
        return AllPartialModels.SHAFTLESS_COGWHEEL;
    }

    BlockEntityType<? extends SimpleKineticBlockEntity> getTileEntityType();
}
