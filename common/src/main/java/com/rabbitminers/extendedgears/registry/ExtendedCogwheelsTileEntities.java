package com.rabbitminers.extendedgears.registry;

import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.rabbitminers.extendedgears.cogwheels.legacy.LegacyShaftlessCogwheelTileEntity;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.base.SingleRotatingInstance;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

@Deprecated
public class ExtendedCogwheelsTileEntities {
    private static final CreateRegistrate REGISTRATE = ExtendedCogwheels.registrate();

    public static final BlockEntityEntry<LegacyShaftlessCogwheelTileEntity> CUSTOM_COGWHEEL_TILE_ENTITY =
            REGISTRATE.blockEntity("customcogwheeltileentity", LegacyShaftlessCogwheelTileEntity::new)
                    .instance(() -> SingleRotatingInstance::new, false)
                    .renderer(() -> KineticBlockEntityRenderer::new)
                    .register();

    public static void init() {

    }
}
