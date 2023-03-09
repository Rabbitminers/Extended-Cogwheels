package com.rabbitminers.extendedgears.registry;

import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.rabbitminers.extendedgears.cogwheels.*;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import com.simibubi.create.content.contraptions.base.KineticTileEntityRenderer;
import com.simibubi.create.content.contraptions.base.SingleRotatingInstance;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

public class ExtendedCogwheelsTileEntities {
    private static final CreateRegistrate REGISTRATE = ExtendedCogwheels.registrate();

    public static final BlockEntityEntry<ShaftlessCogwheelTileEntity> CUSTOM_COGWHEEL_TILE_ENTITY =
            REGISTRATE.tileEntity("customcogwheeltileentity", ShaftlessCogwheelTileEntity::new)
                    .instance(() -> SingleRotatingInstance::new, false)
                    .validBlocks(ExtendedCogwheelsBlocks.METAL_COGWHEELS.toArray())
                    .validBlocks(ExtendedCogwheelsBlocks.LARGE_METAL_COGWHEELS.toArray())
                    .validBlocks(ExtendedCogwheelsBlocks.WOODEN_COGWHEELS.toArray())
                    .validBlocks(ExtendedCogwheelsBlocks.LARGE_WOODEN_COGWHEELS.toArray())
                    .validBlocks(ExtendedCogwheelsBlocks.SHAFTLESS_WOODEN_COGWHEELS.toArray())
                    .validBlocks(ExtendedCogwheelsBlocks.LARGE_SHAFTLESS_WOODEN_COGWHEELS.toArray())
                    .validBlocks(ExtendedCogwheelsBlocks.SHAFTLESS_METAL_COGWHEELS.toArray())
                    .validBlocks(ExtendedCogwheelsBlocks.LARGE_SHAFTLESS_METAL_COGWHEELS.toArray())
                    .validBlocks(ExtendedCogwheelsBlocks.HALF_SHAFT_WOODEN_COGWHEELS.toArray())
                    .validBlocks(ExtendedCogwheelsBlocks.LARGE_HALF_SHAFT_WOODEN_COGWHEELS.toArray())
                    .validBlocks(ExtendedCogwheelsBlocks.LARGE_HALF_SHAFT_METAL_COGWHEELS.toArray())
                    .validBlocks(ExtendedCogwheelsBlocks.HALF_SHAFT_METAL_COGWHEELS.toArray())
                    .renderer(() -> KineticTileEntityRenderer::new)
                    .register();


    public static void init() {

    }
}
