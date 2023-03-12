package com.rabbitminers.extendedgears.fabric;

import com.rabbitminers.extendedgears.ExtendedCogwheels;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraftforge.api.ModLoadingContext;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

import java.nio.file.Path;

public class ExtendedCogwheelsImpl {
    public static void registerConfig(ModConfig.Type type, ForgeConfigSpec spec) {
        ModLoadingContext.registerConfig(ExtendedCogwheels.MOD_ID, type, spec);
    }

    public static Path configDir() {
        return FabricLoader.getInstance().getConfigDir();
    }
}
