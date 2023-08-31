package com.rabbitminers.extendedgears.fabric.events;


import com.rabbitminers.extendedgears.base.events.CommonEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.packs.PackType;

public class CommonEventsImpl {
    public static void addReloadListeners() {
        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(MaterialReloadListener.INSTANCE);
    }

    public static void onDatapackSync(ServerPlayer player, boolean joined) {
        CommonEvents.onDatapackSyncEvent(player);
    }

    public static void init() {
        CommonEventsImpl.addReloadListeners();
        ServerLifecycleEvents.SYNC_DATA_PACK_CONTENTS.register(CommonEventsImpl::onDatapackSync);
    }
}
