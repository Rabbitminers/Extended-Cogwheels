package com.rabbitminers.extendedgears.index;

import com.jozufozu.flywheel.core.PartialModel;
import com.rabbitminers.extendedgears.ExtendedGears;

public class ECPartials {
    public static final PartialModel

    METAL_COGWHEEL = block("metal_cogwheel_shaftless"),
    LARGE_METAL_COGWHEEL = block("large_metal_cogwheel_shaftless"),
    OAK_COGWHEEL = block("oak_cogwheel"),
    LARGE_OAK_COGWHEEL = block("large_oak_cogwheel"),
    ACACIA_COGWHEEL = block("acacia_cogwheel"), LARGE_ACACIA_COGWHEEL = block("large_acacia_cogwheel"),
    DARK_OAK_COGWHEEL = block("dark_oak_cogwheel"), LARGE_DARK_OAK_COGWHEEL = block("large_dark_oak_cogwheel"),
    BIRCH_COGWHEEL = block("birch_cogwheel"), LARGE_BIRCH_COGWHEEL = block("large_birch_cogwheel"),
    CRIMSON_COGWHEEL = block("crimson_cogwheel"), LARGE_CRIMSON_COGWHEEL = block("large_crimson_cogwheel"),
    JUNGLE_COGWHEEL = block("jungle_cogwheel"), LARGE_JUNGLE_COGWHEEL = block("large_jungle_cogwheel"),
    WARPED_COGWHEEL = block("warped_cogwheel"), LARGE_WARPED_COGWHEEL = block("large_warped_cogwheel"),
    COPPER_COGWHEEL = block("copper_cogwheel"), LARGE_COPPER_COGWHEEL = block("large_copper_cogwheel"),
    STEEL_COGWHEEL = block("steel_cogwheel"), LARGE_STEEL_COGWHEEL = block("large_steel_cogwheel"),
    IRON_COGWHEEL = block("iron_cogwheel"), LARGE_IRON_COGWHEEL = block("large_iron_cogwheel")
    ;

    private static PartialModel block(String path) {
        return new PartialModel(ExtendedGears.asResource("block/" + path));
    }

    private static PartialModel entity(String path) {
        return new PartialModel(ExtendedGears.asResource("entity/" + path));
    }

    public static void init() {
        // init static fields
    }
}
