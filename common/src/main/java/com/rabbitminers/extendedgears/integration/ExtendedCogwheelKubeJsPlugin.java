package com.rabbitminers.extendedgears.integration;

import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.rabbitminers.extendedgears.integration.registry.KubeJsTileEntities;
import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsItems;
import com.simibubi.create.foundation.data.CreateRegistrate;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.latvian.mods.kubejs.KubeJS;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.RegistryObjectBuilderTypes;
import dev.latvian.mods.kubejs.script.ScriptType;

import java.util.ArrayList;

public class ExtendedCogwheelKubeJsPlugin extends KubeJSPlugin {
    public static boolean ran = false;
    private static final CreateRegistrate REGISTRATE = CreateRegistrate.create(KubeJS.MOD_ID)
            .creativeModeTab(() -> ExtendedCogwheelsItems.itemGroup);

    @Override
    public void init() {
        RegistryObjectBuilderTypes.BLOCK.addType("cogwheel", KubeJsCogwheelBuilder.class,
                (location) -> new KubeJsCogwheelBuilder(location, false));
        RegistryObjectBuilderTypes.BLOCK.addType("large_cogwheel", KubeJsCogwheelBuilder.class,
                (location) -> new KubeJsCogwheelBuilder(location, true));
    }

    @Override
    public void afterInit() {
        this.registerRegistrateRegistration();
    }

    // init methods are called twice which would cause double registration
    public void registerRegistrateRegistration() {
        KubeJsTileEntities.init();
        if (!ran) {
            REGISTRATE.register();
            ran = true;
        }
    }

    public static CreateRegistrate registrate() {
        return REGISTRATE;
    }
}
