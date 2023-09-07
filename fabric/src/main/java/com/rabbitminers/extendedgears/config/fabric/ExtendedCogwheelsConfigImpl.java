package com.rabbitminers.extendedgears.config.fabric;

import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.rabbitminers.extendedgears.config.ExtendedCogwheelsConfig;
import com.simibubi.create.foundation.config.ConfigBase;
import net.minecraftforge.api.ModLoadingContext;
import net.minecraftforge.api.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.config.ModConfig;

import java.util.Map;

public class ExtendedCogwheelsConfigImpl {
    public static void register() {
        ExtendedCogwheelsConfig.registerCommon();

        for (Map.Entry<ModConfig.Type, ConfigBase> pair : ExtendedCogwheelsConfig.CONFIGS.entrySet())
            ModLoadingContext.registerConfig(ExtendedCogwheels.MOD_ID, pair.getKey(), pair.getValue().specification);

        ModConfigEvent.LOADING.register(ExtendedCogwheelsConfig::onLoad);
        ModConfigEvent.RELOADING.register(ExtendedCogwheelsConfig::onReload);

        // ModConfig.loading(ExtendedCogwheels.MOD_ID).register(ExtendedCogwheelsConfig::onLoad);
        // ModConfigEvents.reloading(ExtendedCogwheels.MOD_ID).register(ExtendedCogwheelsConfig::onReload);
    }
}
