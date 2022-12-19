package com.rabbitminers.extendedgears.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class BreakCogwheelPacket {
    public final BlockPos pos;
    public BreakCogwheelPacket(BlockPos blockToBreak) {
        this.pos = blockToBreak;
    }

    public BreakCogwheelPacket(FriendlyByteBuf buffer) {
        this(buffer.readBlockPos());
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeBlockPos(this.pos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        final var success = new AtomicBoolean(false);
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                ServerLevel level = player.getLevel();
                level.destroyBlock(pos, true);
                success.set(true);
            }
        });

        ctx.get().setPacketHandled(true);
        return success.get();
    }
}