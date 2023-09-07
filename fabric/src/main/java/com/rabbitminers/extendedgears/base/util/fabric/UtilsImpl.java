package com.rabbitminers.extendedgears.base.util.fabric;

import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class UtilsImpl {
    public static Path configDir() {
        return FabricLoader.getInstance().getConfigDir();
    }
}
