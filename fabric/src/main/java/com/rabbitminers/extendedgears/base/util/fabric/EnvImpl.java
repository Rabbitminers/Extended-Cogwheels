package com.rabbitminers.extendedgears.base.util.fabric;

import com.rabbitminers.extendedgears.base.util.Env;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.ApiStatus.Internal;

public class EnvImpl {
    @Internal
    public static Env getCurrent() {
        return FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT ? Env.CLIENT : Env.SERVER;
    }

    public static boolean isForge() {
        return false;
    }
}
