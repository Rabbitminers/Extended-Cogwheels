package com.rabbitminers.extendedgears.index;

import com.rabbitminers.extendedgears.ExtendedGears;
import com.rabbitminers.extendedgears.tileentities.CogWheelKineticTileEntity;
import com.rabbitminers.extendedgears.tileentities.CogWheelKineticTileInstance;
import com.rabbitminers.extendedgears.tileentities.CogWheelKineticTileRenderer;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

public class AddonTileEntities {
    private static final CreateRegistrate REGISTRATE = ExtendedGears.registrate();
    public static final BlockEntityEntry<CogWheelKineticTileEntity> BRACKETED_KINETIC = REGISTRATE
            .tileEntity("cogwheeltileentity", CogWheelKineticTileEntity::new)
            .instance(() -> CogWheelKineticTileInstance::new, false)
            .validBlocks(AddonBlocks.COGWHEEL, AddonBlocks.LARGE_COGWHEEL, AddonBlocks.BIRCH_COGWHEEL, AddonBlocks.LARGE_BIRCH_COGWHEEL, AddonBlocks.OAK_COGWHEEL, AddonBlocks.LARGE_OAK_COGWHEEL, AddonBlocks.DARK_OAK_COGWHEEL, AddonBlocks.LARGE_DARK_OAK_COGWHEEL, AddonBlocks.ACACIA_COGWHEEL, AddonBlocks.LARGE_ACACIA_COGWHEEL, AddonBlocks.JUNGLE_COGWHEEL, AddonBlocks.LARGE_JUNGLE_COGWHEEL, AddonBlocks.WARPED_COGWHEEL, AddonBlocks.LARGE_WARPED_COGWHEEL, AddonBlocks.CRIMSON_COGWHEEL, AddonBlocks.LARGE_CRIMSON_COGWHEEL, AddonBlocks.LARGE_STEEL_COGWHEEL, AddonBlocks.STEEL_COGWHEEL, AddonBlocks.COPPER_COGWHEEL, AddonBlocks.LARGE_COPPER_COGWHEEL, AddonBlocks.IRON_COGWHEEL, AddonBlocks.LARGE_IRON_COGWHEEL)
            .renderer(() -> CogWheelKineticTileRenderer::new)
            .register();
    public static void register() {}
}
