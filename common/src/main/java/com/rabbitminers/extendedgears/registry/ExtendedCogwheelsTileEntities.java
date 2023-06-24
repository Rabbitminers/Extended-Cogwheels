package com.rabbitminers.extendedgears.registry;

import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.rabbitminers.extendedgears.cogwheels.*;
import com.simibubi.create.content.kinetics.simpleRelays.SimpleKineticBlockEntity;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.content.kinetics.base.SingleRotatingInstance;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

public class ExtendedCogwheelsTileEntities {
    private static final CreateRegistrate REGISTRATE = ExtendedCogwheels.registrate();

    public static final BlockEntityEntry<ShaftlessCogwheelTileEntity> CUSTOM_COGWHEEL_TILE_ENTITY =
            REGISTRATE.blockEntity("customcogwheeltileentity", ShaftlessCogwheelTileEntity::new)
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
                    .validBlocks(ExtendedCogwheelsBlocks.SPRUCE_HALF_SHAFT_COGWHEEL, ExtendedCogwheelsBlocks.SPRUCE_SHAFTLESS_COGWHEEL, ExtendedCogwheelsBlocks.LARGE_SPRUCE_HALF_SHAFT_COGWHEEL, ExtendedCogwheelsBlocks.LARGE_SPRUCE_SHAFTLESS_COGWHEEL)
                    .renderer(() -> KineticBlockEntityRenderer::new)
                    .register();


    public static void init() {

    }
}
