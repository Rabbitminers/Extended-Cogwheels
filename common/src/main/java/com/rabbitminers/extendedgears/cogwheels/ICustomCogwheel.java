package com.rabbitminers.extendedgears.cogwheels;

import com.jozufozu.flywheel.core.PartialModel;
import com.rabbitminers.extendedgears.base.data.ICogwheelMaterial;
import com.simibubi.create.AllBlockPartials;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public interface ICustomCogwheel{
    public ICogwheelMaterial getMaterial();

    default Direction.AxisDirection getAxisDirection(BlockState state) {
        return Direction.AxisDirection.POSITIVE;
    }

    @Nullable
    default PartialModel getShaftPartialModel() {
        return AllBlockPartials.COGWHEEL_SHAFT;
    }

    default PartialModel getPartialModelType() {
        return AllBlockPartials.SHAFTLESS_COGWHEEL;
    }
}
