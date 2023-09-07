package com.rabbitminers.extendedgears.config;

import com.simibubi.create.foundation.config.ConfigBase;
import com.simibubi.create.foundation.config.ui.ConfigAnnotations;

@SuppressWarnings("unused")
public class CommonConfig extends ConfigBase {
    public final ConfigBool disableDatafixer = b(false, "disableDatafixer", Comments.disableDatafixer, ConfigAnnotations.RequiresRestart.BOTH.asComment());

    @Override
    public String getName() {
        return "common";
    }

    private static class Comments {
        static String disableDatafixer = "Disable the Extended Cogwheels datafixer. Only do this if you are certain that no pre 0.2.1 cogwheels are left in the world as they will be destroyed";
    }
}
