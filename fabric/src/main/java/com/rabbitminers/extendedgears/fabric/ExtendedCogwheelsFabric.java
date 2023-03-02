package com.rabbitminers.extendedgears.fabric;

import com.rabbitminers.extendedgears.ExtendedCogwheels;
import io.github.fabricators_of_create.porting_lib.util.EnvExecutor;
import com.rabbitminers.extendedgears.ExampleBlocks;
import net.fabricmc.api.ModInitializer;

public class ExtendedCogwheelsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ExtendedCogwheels.init();
        ExtendedCogwheels.LOGGER.info(EnvExecutor.unsafeRunForDist(
                () -> () -> "{} is accessing Porting Lib on a Fabric client!",
                () -> () -> "{} is accessing Porting Lib on a Fabric server!"
                ), ExtendedCogwheels.NAME);
        // on fabric, Registrates must be explicitly finalized and registered.
        ExampleBlocks.REGISTRATE.register();
    }
}
