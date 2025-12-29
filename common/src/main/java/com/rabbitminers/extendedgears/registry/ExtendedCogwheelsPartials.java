package com.rabbitminers.extendedgears.registry;

import com.rabbitminers.extendedgears.ExtendedCogwheels;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;

public class ExtendedCogwheelsPartials {
    public static final PartialModel
        COGWHEEL = block("shaftless_cogwheel"),
        LARGE_COGWHEEL = block("large_shaftless_cogwheel"),
        CHUNKY_COGWHEEL = block("chunky_cogwheel"),
        LARGE_CHUNKY_COGWHEEL = block("large_chunky_cogwheel");

    private static PartialModel block(String path) {
        return PartialModel.of(ExtendedCogwheels.asResource("block/" + path));
    }
    public static void init() {

    }
}
