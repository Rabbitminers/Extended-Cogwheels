package com.rabbitminers.extendedgears.base.data;

public enum MetalCogwheel {
    IRON, STEEL, COPPER
    ;

    public String asId() {
        return name().toLowerCase();
    }
}
