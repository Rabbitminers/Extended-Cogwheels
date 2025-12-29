package com.rabbitminers.extendedgears;

import com.rabbitminers.extendedgears.cogwheels.DynamicCogwheelRenderer;
import com.rabbitminers.extendedgears.cogwheels.materials.CogwheelMaterialManager;
import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsPackets;
import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsPartials;
import net.createmod.catnip.render.SuperByteBufferCache;

public class ExtendedCogwheelsClient {
    public static final SuperByteBufferCache BUFFER_CACHE = new SuperByteBufferCache();

    public static void init() {
        BUFFER_CACHE.registerCompartment(DynamicCogwheelRenderer.COGWHEEL);
        ExtendedCogwheelsPackets.PACKETS.registerS2CListener();
        ExtendedCogwheelsPartials.init();
        CogwheelMaterialManager.setupClientCogwheelMaterials();
    }
}
