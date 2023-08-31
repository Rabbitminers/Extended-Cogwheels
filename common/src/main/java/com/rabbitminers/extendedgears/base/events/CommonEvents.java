package com.rabbitminers.extendedgears.base.events;

import com.rabbitminers.extendedgears.cogwheels.materials.CogwheelMaterial;
import com.rabbitminers.extendedgears.cogwheels.materials.CogwheelMaterialManager;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.Nullable;

public class CommonEvents {
    public static void onDatapackSyncEvent(@Nullable ServerPlayer player) {
        if (player != null) {
            CogwheelMaterialManager.syncTo(player);
        } else {
            CogwheelMaterialManager.syncToAll();
        }
    }
}
