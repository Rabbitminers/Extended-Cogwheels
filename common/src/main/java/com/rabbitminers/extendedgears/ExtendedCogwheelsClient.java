package com.rabbitminers.extendedgears;

import com.rabbitminers.extendedgears.cogwheels.CogwheelMaterials;
import com.rabbitminers.extendedgears.mixin_interface.DynamicCogwheelRenderer;
import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsPartials;
import com.simibubi.create.foundation.render.SuperByteBufferCache;

public class ExtendedCogwheelsClient {
    public static final SuperByteBufferCache BUFFER_CACHE = new SuperByteBufferCache();
    public static void init() {
        BUFFER_CACHE.registerCompartment(DynamicCogwheelRenderer.COGWHEEL);
        ExtendedCogwheelsPartials.init();
        CogwheelMaterials.clientInit();
    }
}
