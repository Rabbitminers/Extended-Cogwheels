package com.rabbitminers.extendedgears.base.networking.forge;

import com.rabbitminers.extendedgears.base.networking.PlayerSelection;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundCustomPayloadPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.PacketDistributor.PacketTarget;
import net.minecraftforge.server.ServerLifecycleHooks;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class PlayerSelectionImpl extends PlayerSelection {

    private static Consumer<Packet<?>> playerListAllWith(final PacketDistributor<Predicate<ServerPlayer>> distributor,
                                                         final Supplier<Predicate<ServerPlayer>> predicateSupplier) {
        return p -> {
            Predicate<ServerPlayer> predicate = predicateSupplier.get();
            for (ServerPlayer player : ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayers()) {
                if (predicate.test(player)) {
                    player.connection.send(p);
                }
            }
        };
    }

    private static final PacketDistributor<Predicate<ServerPlayer>> ALL_WITH =
            new PacketDistributor<>(PlayerSelectionImpl::playerListAllWith, NetworkDirection.PLAY_TO_CLIENT);

    final PacketTarget target;

    private PlayerSelectionImpl(PacketTarget target) {
        this.target = target;
    }

    @Override
    public void accept(ResourceLocation id, FriendlyByteBuf buffer) {
        ClientboundCustomPayloadPacket packet = new ClientboundCustomPayloadPacket(id, buffer);
        target.send(packet);
    }

    public static PlayerSelection all() {
        return new PlayerSelectionImpl(PacketDistributor.ALL.noArg());
    }

    public static PlayerSelection allWith(Predicate<ServerPlayer> condition) {
        return new PlayerSelectionImpl(ALL_WITH.with(() -> condition));
    }

    public static PlayerSelection of(ServerPlayer player) {
        return new PlayerSelectionImpl(PacketDistributor.PLAYER.with(() -> player));
    }
}
