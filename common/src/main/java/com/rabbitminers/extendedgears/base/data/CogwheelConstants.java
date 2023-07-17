package com.rabbitminers.extendedgears.base.data;

import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsBlocks;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.foundation.utility.Couple;
import com.tterrag.registrate.util.entry.BlockEntry;

public class CogwheelConstants {
    public static final Couple<BlockEntry<?>>
            COGWHEELS = Couple.create(AllBlocks.COGWHEEL, AllBlocks.LARGE_COGWHEEL),
            HALF_SHAFT_COGWHEELS = Couple.create(ExtendedCogwheelsBlocks.HALF_SHAFT_COGWHEEL, ExtendedCogwheelsBlocks.LARGE_HALF_SHAFT_COGWHEEL),
            SHAFTLESS_COGWHEELS = Couple.create(ExtendedCogwheelsBlocks.SHAFTLESS_COGWHEEL, ExtendedCogwheelsBlocks.LARGE_SHAFTLESS_COGWHEEL);
}
