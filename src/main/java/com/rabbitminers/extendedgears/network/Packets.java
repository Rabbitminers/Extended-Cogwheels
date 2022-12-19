package com.rabbitminers.extendedgears.network;

import com.rabbitminers.extendedgears.ExtendedGears;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.function.Supplier;

public class Packets {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(ExtendedGears.MODID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(BreakCogwheelPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(BreakCogwheelPacket::new)
                .encoder(BreakCogwheelPacket::encode)
                .consumerMainThread(BreakCogwheelPacket::handle)
                .add();
    }

    public static <MSG> void breakCogwheelServerSide(MSG message) {
        INSTANCE.sendToServer(message);
    }
}
