package com.rabbitminers.extendedgears.registry.forge;

import net.minecraft.world.item.CreativeModeTab;

public class ExtendedCogwheelsItemsImpl {
    public static int getNextAvailableTabId() {
        return CreativeModeTab.getGroupCountSafe();
    }
}
