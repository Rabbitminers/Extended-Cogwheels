package com.rabbitminers.extendedgears.registry;

import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.rabbitminers.extendedgears.base.data.WoodenCogwheel;
import com.simibubi.create.foundation.data.CreateRegistrate;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ExtendedCogwheelsItems {
    private static final CreateRegistrate REGISTRATE = ExtendedCogwheels.registrate();

    public static void init() {

    }

    @ExpectPlatform
    public static int getNextAvailableTabId() {
        throw new AssertionError();
    }
}
