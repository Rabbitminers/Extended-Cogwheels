package com.rabbitminers.extendedgears.integration;

import com.simibubi.create.foundation.data.CreateRegistrate;
import dev.latvian.mods.kubejs.KubeJS;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.ScriptType;

import java.util.ArrayList;

public class ExtendedCogwheelKubeJsPlugin extends KubeJSPlugin {
    private static final CreateRegistrate REGISTRATE = CreateRegistrate.create(KubeJS.MOD_ID)
            .creativeModeTab(() -> KubeJS.tab);

    @Override
    public void afterInit() {
        CogwheelMaterialRegisterEvent event = new CogwheelMaterialRegisterEvent(new ArrayList<>());
        event.post(ScriptType.STARTUP, "create.cogwheel");
        // TODO: REGISTER SHITE
    }

    public static CreateRegistrate registrate() {
        return REGISTRATE;
    }
}
