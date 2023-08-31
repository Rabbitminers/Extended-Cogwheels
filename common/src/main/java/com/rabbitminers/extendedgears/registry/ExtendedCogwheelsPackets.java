package com.rabbitminers.extendedgears.registry;

import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.rabbitminers.extendedgears.cogwheels.materials.CogwheelMaterialManager;
import com.rabbitminers.extendedgears.base.networking.PacketSet;

public class ExtendedCogwheelsPackets {
    public static final PacketSet PACKETS = PacketSet.builder(ExtendedCogwheels.MOD_ID, 1)
            .s2c(CogwheelMaterialManager.SyncPacket.class, CogwheelMaterialManager.SyncPacket::new)
            .build();
}
