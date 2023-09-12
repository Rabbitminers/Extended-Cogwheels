package com.rabbitminers.extendedgears.forge;

import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.rabbitminers.extendedgears.ExtendedCogwheelsClient;
import com.rabbitminers.extendedgears.config.forge.ExtendedCogwheelsConfigImpl;
import com.rabbitminers.extendedgears.registry.forge.ExtendedCogwheelsCreativeModeTabsImpl;
import com.simibubi.create.AllCreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ExtendedCogwheels.MOD_ID)
public class ExtendedCogwheelsForge {
    public ExtendedCogwheelsForge() {
        // registrate must be given the mod event bus on forge before registration
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ExtendedCogwheelsCreativeModeTabsImpl.register(eventBus);
        ExtendedCogwheels.init();
        ExtendedCogwheels.registrate().registerEventListeners(eventBus);

        ExtendedCogwheelsConfigImpl.register(ModLoadingContext.get());

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT,
                () -> ExtendedCogwheelsClient::init);

        eventBus.addListener(EventPriority.LOWEST, ExtendedCogwheelsForge::gatherData);
    }

    public static void gatherData(GatherDataEvent event) {
        // TODO
    }
}
