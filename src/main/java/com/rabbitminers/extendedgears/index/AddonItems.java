package com.rabbitminers.extendedgears.index;

import com.rabbitminers.extendedgears.ExtendedGears;
import com.simibubi.create.foundation.data.CreateRegistrate;
import net.minecraftforge.eventbus.api.IEventBus;

public class AddonItems {
    private static final CreateRegistrate REGISTRATE = ExtendedGears.registrate()
            .creativeModeTab(() -> ExtendedGears.itemGroup);

    // See create git for how to register items
    // - https://github.com/Creators-of-Create/Create/blob/mc1.18/dev/src/main/java/com/simibubi/create/AllItems.java
    public static void register(IEventBus eventBus) {

    }
}
