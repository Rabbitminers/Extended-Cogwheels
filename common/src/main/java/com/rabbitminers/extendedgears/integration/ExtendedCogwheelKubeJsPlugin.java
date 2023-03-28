package com.rabbitminers.extendedgears.integration;

import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.rabbitminers.extendedgears.integration.registry.KubeJsTileEntities;
import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsItems;
import com.simibubi.create.foundation.data.CreateRegistrate;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.latvian.mods.kubejs.BuiltinKubeJSPlugin;
import dev.latvian.mods.kubejs.KubeJS;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.RegistryObjectBuilderTypes;
import dev.latvian.mods.kubejs.script.ScriptType;

import java.util.ArrayList;

import static com.rabbitminers.extendedgears.integration.KubeJsCogwheelBuilder.CogwheelType.*;

public class ExtendedCogwheelKubeJsPlugin extends KubeJSPlugin {
    public static boolean ran = false;
    private static final CreateRegistrate REGISTRATE = CreateRegistrate.create(KubeJS.MOD_ID)
            .creativeModeTab(() -> ExtendedCogwheelsItems.itemGroup);

    @Override
    public void init() {
        RegistryObjectBuilderTypes.BLOCK.addType("cogwheel", KubeJsCogwheelBuilder.class,
                (location) -> new KubeJsCogwheelBuilder(location, false, STANDARD));
        RegistryObjectBuilderTypes.BLOCK.addType("large_cogwheel", KubeJsCogwheelBuilder.class,
                (location) -> new KubeJsCogwheelBuilder(location, true, STANDARD));


        RegistryObjectBuilderTypes.BLOCK.addType("half_shaft_cogwheel", KubeJsCogwheelBuilder.class,
                (location) -> new KubeJsCogwheelBuilder(location, false, HALF_SHAFT));
        RegistryObjectBuilderTypes.BLOCK.addType("large_half_shaft_cogwheel", KubeJsCogwheelBuilder.class,
                (location) -> new KubeJsCogwheelBuilder(location, true, HALF_SHAFT));


        RegistryObjectBuilderTypes.BLOCK.addType("shaftless_cogwheel", KubeJsCogwheelBuilder.class,
                (location) -> new KubeJsCogwheelBuilder(location, false, SHAFTLESS));
        RegistryObjectBuilderTypes.BLOCK.addType("large_shaftless_cogwheel", KubeJsCogwheelBuilder.class,
                (location) -> new KubeJsCogwheelBuilder(location, true, SHAFTLESS));
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