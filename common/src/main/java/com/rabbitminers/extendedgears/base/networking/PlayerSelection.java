package com.rabbitminers.extendedgears.base.networking;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

import java.util.function.Predicate;

public abstract class PlayerSelection {
    public abstract void accept(ResourceLocation id, FriendlyByteBuf buffer);

    @ExpectPlatform
    public static PlayerSelection all() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static PlayerSelection allWith(Predicate<ServerPlayer> condition) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static PlayerSelection of(ServerPlayer player) {
        throw new AssertionError();
    }
}
