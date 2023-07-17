package com.rabbitminers.extendedgears.base.util;

import net.minecraft.core.Direction;

public class DirectionHelpers {
    public static boolean isDirectionPositive(Direction.AxisDirection dir) {
        return dir == Direction.AxisDirection.POSITIVE;
    }

    public static Direction.AxisDirection directionFromValue(boolean bool) {
        return bool ? Direction.AxisDirection.POSITIVE : Direction.AxisDirection.NEGATIVE;
    }
}
