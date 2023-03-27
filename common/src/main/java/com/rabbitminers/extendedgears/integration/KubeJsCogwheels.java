package com.rabbitminers.extendedgears.integration;

import com.rabbitminers.extendedgears.integration.types.CogwheelMaterialJS;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import dev.latvian.mods.kubejs.KubeJSRegistries;
import dev.latvian.mods.kubejs.RegistryObjectBuilderTypes;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KubeJsCogwheels {
    public static final Set<NonNullSupplier<? extends Block>> cogwheels = new HashSet<>();

    public static void addCogwheel(KubeJsCogwheelBuilder material) {
        System.out.println("Added " + material);
        NonNullSupplier<? extends Block> supplier = material::get;
        cogwheels.add(supplier);
    }

    @SuppressWarnings("unchecked")
    public static NonNullSupplier<? extends Block>[] getCogwheels() {
        System.out.println("Cogwheels! + " + cogwheels);
        return cogwheels.toArray(new NonNullSupplier[0]);
    }
}
