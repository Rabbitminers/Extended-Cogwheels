package com.rabbitminers.extendedgears.fabric;

import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.rabbitminers.extendedgears.fabric.events.CommonEventsImpl;
import net.fabricmc.api.ModInitializer;

public class ExtendedCogwheelsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ExtendedCogwheels.init();
        ExtendedCogwheels.registrate().register();
        CommonEventsImpl.init();
    }
}
