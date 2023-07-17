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
                    .validBlocks(ExtendedCogwheelsLegacyBlocks.METAL_COGWHEELS.toArray())
                    .validBlocks(ExtendedCogwheelsLegacyBlocks.LARGE_METAL_COGWHEELS.toArray())
                    .validBlocks(ExtendedCogwheelsLegacyBlocks.WOODEN_COGWHEELS.toArray())
                    .validBlocks(ExtendedCogwheelsLegacyBlocks.LARGE_WOODEN_COGWHEELS.toArray())
                    .validBlocks(ExtendedCogwheelsLegacyBlocks.SHAFTLESS_WOODEN_COGWHEELS.toArray())
                    .validBlocks(ExtendedCogwheelsLegacyBlocks.LARGE_SHAFTLESS_WOODEN_COGWHEELS.toArray())
                    .validBlocks(ExtendedCogwheelsLegacyBlocks.SHAFTLESS_METAL_COGWHEELS.toArray())
                    .validBlocks(ExtendedCogwheelsLegacyBlocks.LARGE_SHAFTLESS_METAL_COGWHEELS.toArray())
                    .validBlocks(ExtendedCogwheelsLegacyBlocks.HALF_SHAFT_WOODEN_COGWHEELS.toArray())
                    .validBlocks(ExtendedCogwheelsLegacyBlocks.LARGE_HALF_SHAFT_WOODEN_COGWHEELS.toArray())
                    .validBlocks(ExtendedCogwheelsLegacyBlocks.LARGE_HALF_SHAFT_METAL_COGWHEELS.toArray())
                    .validBlocks(ExtendedCogwheelsLegacyBlocks.HALF_SHAFT_METAL_COGWHEELS.toArray())
                    .validBlocks(ExtendedCogwheelsLegacyBlocks.SPRUCE_HALF_SHAFT_COGWHEEL, ExtendedCogwheelsLegacyBlocks.SPRUCE_SHAFTLESS_COGWHEEL, ExtendedCogwheelsLegacyBlocks.LARGE_SPRUCE_HALF_SHAFT_COGWHEEL, ExtendedCogwheelsLegacyBlocks.LARGE_SPRUCE_SHAFTLESS_COGWHEEL)
                    .renderer(() -> KineticBlockEntityRenderer::new)
                    .register();


    public static void init() {

    }
}
