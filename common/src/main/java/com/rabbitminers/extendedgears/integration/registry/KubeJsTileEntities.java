package com.rabbitminers.extendedgears.integration.registry;

import com.rabbitminers.extendedgears.cogwheels.ShaftlessCogwheelTileEntity;
import com.rabbitminers.extendedgears.integration.ExtendedCogwheelKubeJsPlugin;
import com.rabbitminers.extendedgears.integration.KubeJsCogwheels;
import com.simibubi.create.content.contraptions.base.KineticTileEntityRenderer;
import com.simibubi.create.content.contraptions.base.SingleRotatingInstance;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

public class KubeJsTileEntities {
    private static final CreateRegistrate REGISTRATE = ExtendedCogwheelKubeJsPlugin.registrate();

    public static final BlockEntityEntry<ShaftlessCogwheelTileEntity> KUBEJS_COGWHEEL_TILE_ENTITY =
            REGISTRATE.tileEntity("kubejscogwheel", ShaftlessCogwheelTileEntity::new)
                    .instance(() -> SingleRotatingInstance::new, false)
                    .validBlocks(KubeJsCogwheels.getCogwheels())
                    .renderer(() -> KineticTileEntityRenderer::new)
                    .register();

    public static void init() {

    }
}
