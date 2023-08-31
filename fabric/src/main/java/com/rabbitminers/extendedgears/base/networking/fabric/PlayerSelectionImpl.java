package com.rabbitminers.extendedgears.base.networking.fabric;

import com.rabbitminers.extendedgears.base.networking.PlayerSelection;
import io.github.fabricators_of_create.porting_lib.util.ServerLifecycleHooks;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Predicate;

public class PlayerSelectionImpl extends PlayerSelection {

    final Collection<ServerPlayer> players;

    private PlayerSelectionImpl(Collection<ServerPlayer> players) {
        this.players = players;
    }

    @Override
    public void accept(ResourceLocation id, FriendlyByteBuf buffer) {
        Packet<?> packet = ServerPlayNetworking.createS2CPacket(id, buffer);
        for (ServerPlayer player : players) {
            ServerPlayNetworking.getSender(player).sendPacket(packet);
        }
    }

    public static PlayerSelection all() {
        return new PlayerSelectionImpl(PlayerLookup.all(ServerLifecycleHooks.getCurrentServer()));
    }

    public static PlayerSelection allWith(Predicate<ServerPlayer> condition) {
        return new PlayerSelectionImpl(PlayerLookup.all(ServerLifecycleHooks.getCurrentServer()).stream()
                .filter(condition).toList());
    }

    public static PlayerSelection of(ServerPlayer player) {
        return new PlayerSelectionImpl(Collections.singleton(player));
    }
}
