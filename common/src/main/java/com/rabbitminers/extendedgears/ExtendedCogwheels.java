package com.rabbitminers.extendedgears;

import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsBlocks;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExtendedCogwheels {
    // Keep extended gears as to not break past worlds
    public static final String MOD_ID = "extendedgears";
    public static final String NAME = "Extended Gears";
    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);


    public static void init() {
        ExtendedCogwheelsBlocks.init(); // hold registrate in a separate class to avoid loading early on forge
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
