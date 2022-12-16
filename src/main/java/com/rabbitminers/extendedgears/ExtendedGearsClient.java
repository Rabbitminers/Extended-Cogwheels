package com.rabbitminers.extendedgears;

import com.rabbitminers.extendedgears.index.ECPartials;
import com.simibubi.create.foundation.render.CachedBufferer;
import com.simibubi.create.foundation.render.SuperByteBufferCache;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
public class ExtendedGearsClient {
    public static final SuperByteBufferCache BUFFER_CACHE = new SuperByteBufferCache();
    public static void onCtorClient(IEventBus modEventBus, IEventBus forgeEventBus) {
        modEventBus.addListener(ExtendedGearsClient::clientInit);
    }

    public static void clientInit(final FMLClientSetupEvent event) {
        ECPartials.init();
    }
}
