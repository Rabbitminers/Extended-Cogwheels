package com.rabbitminers.extendedgears.base.data;

import com.jozufozu.flywheel.core.PartialModel;
import org.jetbrains.annotations.Nullable;

public enum WoodenCogwheel {
    DARK_OAK, OAK, BIRCH, JUNGLE, ACACIA, WARPED, CRIMSON
    ;

    public String asId() {
        return name().toLowerCase();
    }
}
