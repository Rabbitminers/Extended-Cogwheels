package com.rabbitminers.extendedgears;

import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsBlocks;
import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsItems;
import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsTags;
import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsTileEntities;
import com.simibubi.create.foundation.data.CreateRegistrate;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExtendedCogwheels {
    // Keep extended gears as to not break past worlds
    public static final String MOD_ID = "extendedgears";
    public static final String NAME = "Extended Gears";
    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

    private static final CreateRegistrate REGISTRATE = CreateRegistrate.create(ExtendedCogwheels.MOD_ID)
            .creativeModeTab(() -> ExtendedCogwheelsItems.itemGroup);

    public static void init() {
        ExtendedCogwheelsTags.init();
        ExtendedCogwheelsItems.init();
        ExtendedCogwheelsBlocks.init();
        ExtendedCogwheelsTileEntities.init();
    }

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static CreateRegistrate registrate() {
        return REGISTRATE;
    }
}
