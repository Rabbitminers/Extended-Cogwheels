package com.rabbitminers.extendedgears.registry;

import com.jozufozu.flywheel.core.PartialModel;
import com.rabbitminers.extendedgears.ExtendedCogwheels;
public class ExtendedCogwheelsPartials {
    public static final PartialModel
        COGWHEEL = block("cogwheel"),
        LARGE_COGWHEEL = block("large_cogwheel"),
        HALF_SHAFT_COGWHEEL = block("half_shaft_cogwheel"),
        LARGE_HALF_SHAFT_COGWHEEL = block("large_half_shaft_cogwheel"),
        SHAFTLESS_COGWHEEL = block("shaftless_cogwheel"),
        LARGE_SHAFTLESS_COGWHEEL = block("large_shaftless_cogwheel");

    private static PartialModel block(String path) {
        return new PartialModel(ExtendedCogwheels.asResource("block/" + path));
    }
    public static void init() {

    }
}
