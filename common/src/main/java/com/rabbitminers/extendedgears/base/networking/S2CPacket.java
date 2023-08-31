package com.rabbitminers.extendedgears.base.networking;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;

public interface S2CPacket {
    void write(FriendlyByteBuf buffer);
    @Environment(EnvType.CLIENT)
    void handle(Minecraft mc);
}
