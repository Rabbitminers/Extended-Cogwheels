package com.rabbitminers.extendedgears.registry;

import com.jozufozu.flywheel.core.PartialModel;
import com.rabbitminers.extendedgears.ExtendedCogwheels;
public class ExtendedCogwheelsPartials {
    public static final PartialModel
        COGWHEEL = block("shaftless_cogwheel"),
        LARGE_COGWHEEL = block("large_shaftless_cogwheel"),
        CHUNKY_COGWHEEL = block("chunky_cogwheel"),
        LARGE_CHUNKY_COGWHEEL = block("large_chunky_cogwheel");

    private static PartialModel block(String path) {
        return new PartialModel(ExtendedCogwheels.asResource("block/" + path));
    }
    public static void init() {

    }
}
