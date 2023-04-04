package com.rabbitminers.extendedgears.integration.fabric;

import com.rabbitminers.extendedgears.integration.ExtendedCogwheelKubeJsPlugin;

public class ExtendedCogwheelKubeJsPluginImpl extends ExtendedCogwheelKubeJsPlugin {
    @Override
    public void afterInit() {
        registrate().register();
    }
}
