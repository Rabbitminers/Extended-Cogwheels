package com.rabbitminers.extendedgears.registry.forge;

import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockModel;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import net.minecraft.client.resources.model.BakedModel;

import java.util.function.Supplier;

public class ExtendedCogwheelsBlocksImpl {
    public static Supplier<NonNullFunction<BakedModel, ? extends BakedModel>> cogwheelModelSupplier() {
        return () -> BracketedKineticBlockModel::new;
    }
}
