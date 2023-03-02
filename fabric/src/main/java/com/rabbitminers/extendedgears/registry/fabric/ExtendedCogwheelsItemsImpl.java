package com.rabbitminers.extendedgears.registry.fabric;

import io.github.fabricators_of_create.porting_lib.util.ItemGroupUtil;

public class ExtendedCogwheelsItemsImpl {
    public static int getNextAvailableTabId() {
        return ItemGroupUtil.expandArrayAndGetId();
    }
}
