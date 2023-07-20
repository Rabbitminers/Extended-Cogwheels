package com.rabbitminers.extendedgears.base.util;

import dev.architectury.injectables.annotations.ExpectPlatform;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Supplier;

public enum Env {
    CLIENT, SERVER;

    public static final Env CURRENT = getCurrent();

    public boolean isCurrent() {
        return this == CURRENT;
    }

    @ApiStatus.Internal
    @ExpectPlatform
    public static Env getCurrent() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isForge() {
        throw new AssertionError();
    }
}
