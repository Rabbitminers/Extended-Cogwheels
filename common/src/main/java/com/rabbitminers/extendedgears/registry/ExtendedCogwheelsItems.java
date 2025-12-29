package com.rabbitminers.extendedgears.registry;

import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.simibubi.create.foundation.data.CreateRegistrate;
import dev.architectury.injectables.annotations.ExpectPlatform;

public class ExtendedCogwheelsItems {
    private static final CreateRegistrate REGISTRATE = ExtendedCogwheels.registrate();

    public static void init() {

    }

    @ExpectPlatform
    public static int getNextAvailableTabId() {
        throw new AssertionError();
    }
}
