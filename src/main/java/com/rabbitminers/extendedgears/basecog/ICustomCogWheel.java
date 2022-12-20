package com.rabbitminers.extendedgears.basecog;

import com.jozufozu.flywheel.core.PartialModel;
import com.rabbitminers.extendedgears.index.ECPartials;
import com.simibubi.create.content.contraptions.base.IRotate;
import com.simibubi.create.content.contraptions.relays.elementary.ICogWheel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public interface ICustomCogWheel {
    static PartialModel getPartialModelType(BlockState state) {
        return getPartialModelType(state.getBlock());
    }
    static PartialModel getPartialModelType(Block block) {
        if (!(block instanceof ICustomCogWheel cogWheel))
            return null;
        return cogWheel.getPartialModelType();
    }

    default int getMaxRPM() {
        return 0;
    }
    default int getMaxSU() {return 0; }

    default PartialModel getPartialModelType() {
        return ECPartials.METAL_COGWHEEL;
    }
}
