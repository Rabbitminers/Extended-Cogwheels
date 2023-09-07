package com.rabbitminers.extendedgears.base.util.forge;

import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class UtilsImpl {
    public static Path configDir() {
        return FMLPaths.CONFIGDIR.get();
    }
}
