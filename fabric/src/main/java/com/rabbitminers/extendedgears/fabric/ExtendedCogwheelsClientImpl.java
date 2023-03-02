package com.rabbitminers.extendedgears.fabric;

import com.rabbitminers.extendedgears.ExtendedCogwheelsClient;
import net.fabricmc.api.ClientModInitializer;

public class ExtendedCogwheelsClientImpl implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ExtendedCogwheelsClient.init();
    }
}
