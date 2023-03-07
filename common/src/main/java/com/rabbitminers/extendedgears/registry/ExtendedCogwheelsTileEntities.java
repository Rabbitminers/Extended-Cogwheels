package com.rabbitminers.extendedgears.registry;

import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.rabbitminers.extendedgears.cogwheels.CustomCogwheelTileEntity;
import com.rabbitminers.extendedgears.cogwheels.CustomCogwheelTileInstance;
import com.rabbitminers.extendedgears.cogwheels.CustomCogwheelTileRenderer;
import com.rabbitminers.extendedgears.cogwheels.ShaftlessCogwheelTileEntity;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

public class ExtendedCogwheelsTileEntities {
    private static final CreateRegistrate REGISTRATE = ExtendedCogwheels.registrate();

    public static final BlockEntityEntry<CustomCogwheelTileEntity> CUSTOM_COGWHEEL_TILE_ENTITY =
            REGISTRATE.tileEntity("customcogwheeltileentity", CustomCogwheelTileEntity::new)
                    .instance(() -> CustomCogwheelTileInstance::new, false)
                    .validBlocks(ExtendedCogwheelsBlocks.METAL_COGWHEELS.toArray())
                    .validBlocks(ExtendedCogwheelsBlocks.LARGE_METAL_COGWHEELS.toArray())
                    .validBlocks(ExtendedCogwheelsBlocks.WOODEN_COGWHEELS.toArray())
                    .validBlocks(ExtendedCogwheelsBlocks.LARGE_WOODEN_COGWHEELS.toArray())
                    .renderer(() -> CustomCogwheelTileRenderer::new)
                    .register();

    public static final BlockEntityEntry<ShaftlessCogwheelTileEntity> SHAFTLESS_COGWHEEL_TILE_ENTITY =
            REGISTRATE.tileEntity("shaftlesscogwheeltileentity", ShaftlessCogwheelTileEntity::new)
                    .instance(() -> CustomCogwheelTileInstance::new, false)
                    .validBlocks(ExtendedCogwheelsBlocks.SHAFTLESS_WOODEN_COGWHEELS.toArray())
                    .renderer(() -> CustomCogwheelTileRenderer::new)
                    .register();

    public static void init() {

    }
}
