package com.rabbitminers.extendedgears.fabric;

import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.rabbitminers.extendedgears.config.fabric.ExtendedCogwheelsConfigImpl;
import com.rabbitminers.extendedgears.fabric.events.CommonEventsImpl;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class ExtendedCogwheelsImpl implements ModInitializer {
    @Override
    public void onInitialize() {
        ExtendedCogwheelsConfigImpl.register();
        ExtendedCogwheels.init();
        ExtendedCogwheels.registrate().register();
        CommonEventsImpl.init();
    }

    public static Path configDir() {
        return FabricLoader.getInstance().getConfigDir();
    }
}
