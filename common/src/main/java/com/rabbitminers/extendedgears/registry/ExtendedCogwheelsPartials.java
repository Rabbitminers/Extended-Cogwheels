package com.rabbitminers.extendedgears.registry;

import com.jozufozu.flywheel.core.PartialModel;
import com.rabbitminers.extendedgears.ExtendedCogwheels;
public class ExtendedCogwheelsPartials {
    public static final PartialModel
        COGWHEEL = block("proc_cogwheel"),
        LARGE_COGWHEEL = block("proc_large_cogwheel");

    private static PartialModel block(String path) {
        return new PartialModel(ExtendedCogwheels.asResource("block/" + path));
    }
    public static void init() {

    }
}
