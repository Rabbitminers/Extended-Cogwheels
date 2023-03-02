package com.rabbitminers.extendedgears.cogwheels;

import com.jozufozu.flywheel.core.PartialModel;
import com.simibubi.create.AllBlockPartials;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public interface ICustomCogwheel {
    static PartialModel getPartialModelType(BlockState state) {
        return getPartialModelType(state.getBlock());
    }

    static PartialModel getPartialModelType(Block block) {
        if (!(block instanceof ICustomCogwheel cogWheel))
            return null;
        return cogWheel.getPartialModelType();
    }

    default PartialModel getPartialModelType() {
        return AllBlockPartials.SHAFTLESS_COGWHEEL;
    }
}
