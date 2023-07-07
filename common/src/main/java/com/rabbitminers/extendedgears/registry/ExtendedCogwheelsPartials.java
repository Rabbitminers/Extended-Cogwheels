package com.rabbitminers.extendedgears.registry;

import com.jozufozu.flywheel.core.PartialModel;
import com.rabbitminers.extendedgears.ExtendedCogwheels;
public class ExtendedCogwheelsPartials {
    public static final PartialModel
        COGWHEEL = block("proc_cogwheel"),
        LARGE_COGWHEEL = block("proc_large_cogwheel"),
        HALF_SHAFT_COGWHEEL = block("proc_half_shaft_cogwheel"),
        LARGE_HALF_SHAFT_COGWHEEL = block("proc_half_shaft_large_cogwheel"),
        SHAFTLESS_COGWHEEL = block("proc_shaftless_cogwheel"),
        LARGE_SHAFTLESS_COGWHEEL = block("proc_shaftless_large_cogwheel");

    private static PartialModel block(String path) {
        return new PartialModel(ExtendedCogwheels.asResource("block/" + path));
    }
    public static void init() {

    }
}
