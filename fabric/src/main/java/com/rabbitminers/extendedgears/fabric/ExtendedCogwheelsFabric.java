package com.rabbitminers.extendedgears.fabric;

import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.rabbitminers.extendedgears.ExtendedCogwheelsClient;
import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsBlocks;
import io.github.fabricators_of_create.porting_lib.util.EnvExecutor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;

public class ExtendedCogwheelsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ExtendedCogwheels.init();
        ExtendedCogwheelsBlocks.REGISTRATE.register();
    }
}
