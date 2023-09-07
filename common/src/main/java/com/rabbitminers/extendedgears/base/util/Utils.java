package com.rabbitminers.extendedgears.base.util;

import dev.architectury.injectables.annotations.ExpectPlatform;

import java.nio.file.Path;

public class Utils {
    @ExpectPlatform
    public static Path configDir() {
        throw new AssertionError();
    }
}

