package com.rabbitminers.extendedgears.config;

import net.createmod.catnip.config.ConfigBase;
import net.createmod.catnip.config.ui.ConfigAnnotations;

@SuppressWarnings("unused")
public class CommonConfig extends ConfigBase {
    public final net.createmod.catnip.config.ConfigBase.ConfigBool disableDatafixer = b(false, "disableDatafixer", Comments.disableDatafixer, ConfigAnnotations.RequiresRestart.BOTH.asComment());

    @Override
    public String getName() {
        return "common";
    }

    private static class Comments {
        static String disableDatafixer = "Disable the Extended Cogwheels datafixer. Only do this if you are certain that no pre 0.2.1 cogwheels are left in the world as they will be destroyed";
    }
}
