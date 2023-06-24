package com.rabbitminers.extendedgears.registry;

import com.jozufozu.flywheel.core.PartialModel;
import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.rabbitminers.extendedgears.base.data.ICogwheelMaterial;
import com.rabbitminers.extendedgears.base.data.MetalCogwheel;
import com.rabbitminers.extendedgears.base.data.WoodenCogwheel;
import com.rabbitminers.extendedgears.base.datatypes.CogwheelModel;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.Create;

import java.util.EnumMap;
import java.util.Map;

public class ExtendedCogwheelsPartials {

    public static <T extends Enum<T> & ICogwheelMaterial> Map<T, CogwheelModel> fillCogwheels(Class<T> materialType, String type) {
        Map<T, CogwheelModel> map = new EnumMap<>(materialType);
        String prefix = type != null ? type + "_" : "";
        for (T material : materialType.getEnumConstants()) {
            map.put(material, new CogwheelModel(block(prefix + material.asId() + "_cogwheel"),
                    block("large_" + prefix + material.asId() + "_cogwheel")));
        }
        return map;
    }

    public static final Map<MetalCogwheel, CogwheelModel>
            METAL_COGWHEELS = fillCogwheels(MetalCogwheel.class, null),
            SHAFTLESS_METAL_COGWHEELS = fillCogwheels(MetalCogwheel.class, "shaftless"),
            HALF_SHAFT_METAL_COGWHEELS = fillCogwheels(MetalCogwheel.class, "half_shaft");

    public static final Map<WoodenCogwheel, CogwheelModel>
            WOODEN_COGWHEELS = fillCogwheels(WoodenCogwheel.class, null),
            SHAFTLESS_WOODEN_COGWHEELS = fillCogwheels(WoodenCogwheel.class, "shaftless"),
            HALF_SHAFT_WOODEN_COGWHEELS = fillCogwheels(WoodenCogwheel.class, "half_shaft");


    private static PartialModel block(String path) {
        return new PartialModel(ExtendedCogwheels.asResource("block/" + path));
    }
    public static void init() {

    }
}
