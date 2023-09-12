package com.rabbitminers.extendedgears.config.fabric;

import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.rabbitminers.extendedgears.config.ExtendedCogwheelsConfig;
import com.simibubi.create.foundation.config.ConfigBase;
import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import fuzs.forgeconfigapiport.api.config.v2.ModConfigEvents;
import net.minecraftforge.fml.config.ModConfig;

import java.util.Map;

public class ExtendedCogwheelsConfigImpl {
    public static void register() {
        ExtendedCogwheelsConfig.registerCommon();

        for (Map.Entry<ModConfig.Type, ConfigBase> pair : ExtendedCogwheelsConfig.CONFIGS.entrySet())
            ForgeConfigRegistry.INSTANCE.register(ExtendedCogwheels.MOD_ID, pair.getKey(), pair.getValue().specification);

        ModConfigEvents.loading(ExtendedCogwheels.MOD_ID).register(ExtendedCogwheelsConfig::onLoad);
        ModConfigEvents.reloading(ExtendedCogwheels.MOD_ID).register(ExtendedCogwheelsConfig::onReload);
    }
}
