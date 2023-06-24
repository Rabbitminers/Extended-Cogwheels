package com.rabbitminers.extendedgears.integration;

import com.rabbitminers.extendedgears.cogwheels.ShaftlessCogwheelTileEntity;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.base.SingleRotatingInstance;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class KubeJsCogwheels {
    public static final Set<NonNullSupplier<? extends Block>> cogwheels = new HashSet<>();
    public static final Map<ResourceLocation, BlockEntityEntry<ShaftlessCogwheelTileEntity>> tileEntities = new HashMap();
    public static CreateRegistrate REGISTRATE = ExtendedCogwheelKubeJsPlugin.registrate();

    public static void addCogwheel(KubeJsCogwheelBuilder material) {
        NonNullSupplier<? extends Block> supplier = material::get;
        cogwheels.add(supplier);

        tileEntities.put(material.id, REGISTRATE.blockEntity(material.id.getPath() + "_tile_entity", ShaftlessCogwheelTileEntity::new)
                .instance(() -> SingleRotatingInstance::new, false)
                .validBlocks(supplier)
                .renderer(() -> KineticBlockEntityRenderer::new)
                .register());
    }

    @SuppressWarnings("unchecked")
    public static NonNullSupplier<? extends Block>[] getCogwheels() {
        return cogwheels.toArray(new NonNullSupplier[0]);
    }
}
