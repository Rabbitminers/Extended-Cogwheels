package com.rabbitminers.extendedgears.integration.forge;

import com.rabbitminers.extendedgears.integration.ExtendedCogwheelKubeJsPlugin;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ExtendedCogwheelKubeJsPluginImpl extends ExtendedCogwheelKubeJsPlugin {
    @Override
    public void init() {
        super.init();
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        registrate().registerEventListeners(eventBus);
    }
}
