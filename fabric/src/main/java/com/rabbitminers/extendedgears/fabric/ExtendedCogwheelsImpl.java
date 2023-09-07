package com.rabbitminers.extendedgears.fabric;

import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.rabbitminers.extendedgears.config.ExtendedCogwheelsConfig;
import com.rabbitminers.extendedgears.config.fabric.ExtendedCogwheelsConfigImpl;
import com.rabbitminers.extendedgears.fabric.events.CommonEventsImpl;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraftforge.api.ModLoadingContext;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

import java.nio.file.Path;

public class ExtendedCogwheelsImpl implements ModInitializer {
    @Override
    public void onInitialize() {
        ExtendedCogwheelsConfigImpl.register();
        ExtendedCogwheels.init();
        ExtendedCogwheels.registrate().register();
        CommonEventsImpl.init();
    }

    public static void registerConfig(ModConfig.Type type, ForgeConfigSpec spec) {
        ModLoadingContext.registerConfig(ExtendedCogwheels.MOD_ID, type, spec);
    }

    public static Path configDir() {
        return FabricLoader.getInstance().getConfigDir();
    }
}
