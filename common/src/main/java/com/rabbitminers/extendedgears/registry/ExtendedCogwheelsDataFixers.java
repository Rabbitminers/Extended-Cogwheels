package com.rabbitminers.extendedgears.registry;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.DataFixerBuilder;
import com.mojang.datafixers.schemas.Schema;
import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.rabbitminers.extendedgears.base.data.data_fixer_api.DataFixesInternals;
import com.simibubi.create.Create;
import net.minecraft.Util;
import net.minecraft.util.datafix.fixes.BlockRenameFix;
import net.minecraft.util.datafix.fixes.ItemRenameFix;
import net.minecraft.util.datafix.fixes.OminousBannerBlockEntityRenameFix;
import net.minecraft.util.datafix.schemas.NamespacedSchema;
import net.minecraft.world.level.block.ChestBlock;

import java.util.*;
import java.util.function.BiFunction;

public class ExtendedCogwheelsDataFixers {
    private static final BiFunction<Integer, Schema, Schema> SAME = Schema::new;
    private static final BiFunction<Integer, Schema, Schema> SAME_NAMESPACED = NamespacedSchema::new;

    public static List<String> validCogwheels = Arrays.asList(
        "create:cogwheel",
        "create:large_cogwheel",
        "extendedgears:half_shaft_cogwheel",
        "extendedgears:large_half_shaft_cogwheel",
        "extendedgears:shaftless_cogwheel",
        "extendedgears:large_shaftless_cogwheel"
    );

    public static void init() {
        ExtendedCogwheels.LOGGER.info("Registering data fixers");
        DataFixesInternals api = DataFixesInternals.get();

        DataFixerBuilder builder = new DataFixerBuilder(ExtendedCogwheels.DATA_FIXER_VERSION);
        addFixers(builder);
        api.registerFixer(ExtendedCogwheels.DATA_FIXER_VERSION, builder.buildOptimized(Util.bootstrapExecutor()));
    }

    private static void addFixers(DataFixerBuilder builder) {
        builder.addSchema(0, DataFixesInternals.BASE_SCHEMA);

        Schema schemaV1 = builder.addSchema(1, SAME_NAMESPACED);
        // builder.addFixer(new CogwheelBlockDataFixer(schemaV1, "Legacy cogwheel merger"));
        // builder.addFixer(new CogwheelBlockEntityFix(schemaV1, true));
        // builder.addFixer(BlockRenameFix.create(schemaV1, "Legacy cogwheel block merger", ExtendedCogwheelsDataFixers::cogwheelFixer));
        // builder.addFixer(ItemRenameFix.create(schemaV1, "Legacy cogwheel item merger", ExtendedCogwheelsDataFixers::cogwheelFixer));
    }

    public static String cogwheelFixer(String string) {
        if (!string.endsWith("_cogwheel") || validCogwheels.contains(string))
            return string;

        String size = string.contains("large_") ? "large_" : "";
        String prefix = ExtendedCogwheels.MOD_ID + ":" + size;

        if (string.contains("shaftless")) {
            return prefix + "shaftless_cogwheel";
        }

        if (string.contains("half_shaft")) {
            return prefix + "half_shaft_cogwheel";
        }

        return Create.ID + ":" + size + "cogwheel";
    }
}
