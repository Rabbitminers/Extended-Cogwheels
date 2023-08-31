package com.rabbitminers.extendedgears.forge.events;

import com.rabbitminers.extendedgears.base.events.CommonEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.OnDatapackSyncEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

@Mod.EventBusSubscriber
public class CommonEventsImpl {
    @SubscribeEvent
    public static void addReloadListeners(AddReloadListenerEvent event) {
        event.addListener(MaterialReloadListener.INSTANCE);
    }

    @SubscribeEvent
    public static void onDatapackSync(OnDatapackSyncEvent event) {
        @Nullable ServerPlayer player = event.getPlayer();
        CommonEvents.onDatapackSyncEvent(player);
    }
}
