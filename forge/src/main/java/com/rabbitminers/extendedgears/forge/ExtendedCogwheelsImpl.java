package com.rabbitminers.extendedgears.forge;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class ExtendedCogwheelsImpl {
    public static void registerConfig(ModConfig.Type type, ForgeConfigSpec spec) {
        ModLoadingContext.get().registerConfig(type, spec);
    }

    public static Path configDir() {
        return FMLPaths.CONFIGDIR.get();
    }
}
