package com.rabbitminers.extendedgears;

import com.rabbitminers.extendedgears.base.lang.ExtendedCogwheelsLanguageProvider;
import com.rabbitminers.extendedgears.cogwheels.CogwheelMaterials;
import com.rabbitminers.extendedgears.datagen.ExtendedCogwheelsCuttingRecipeGen;
import com.rabbitminers.extendedgears.datagen.ExtendedCogwheelsDeployingRecipeGen;
import com.rabbitminers.extendedgears.datagen.ExtendedCogwheelsStandardRecipeGen;
import com.rabbitminers.extendedgears.registry.*;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.LangMerger;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

public class ExtendedCogwheels {
    // Keep extended gears as to not break past worlds
    public static final String MOD_ID = "extendedgears";
    public static final String NAME = "Extended Cogwheels";
    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);
    public static final int DATA_FIXER_VERSION = 1;
    private static final CreateRegistrate REGISTRATE = CreateRegistrate.create(ExtendedCogwheels.MOD_ID)
            .creativeModeTab(() -> ExtendedCogwheelsItems.itemGroup);

    public static void init() {
        ExtendedCogwheelsTags.init();
        ExtendedCogwheelsItems.init();
        ExtendedCogwheelsBlocks.init();
        ExtendedCogwheelsTileEntities.init();
        ExtendedCogwheelsDataFixers.init();
        CogwheelMaterials.init();
    }

    public static void gatherData(DataGenerator gen, boolean isServer) {
        if (isServer) {
            #if MC_18
            gen.addProvider(new LangMerger(gen, MOD_ID, "Extended Cogwheels",
                    ExtendedCogwheelsLanguageProvider.values()));
            gen.addProvider(ExtendedCogwheelsStandardRecipeGen.create(gen));
            gen.addProvider(ExtendedCogwheelsDeployingRecipeGen.create(gen));
            gen.addProvider(ExtendedCogwheelsCuttingRecipeGen.create(gen));
            #else
            gen.addProvider(true, new LangMerger(gen, MOD_ID, "Extended Cogwheels",
                    ExtendedCogwheelsLanguageProvider.values()));
            gen.addProvider(true, ExtendedCogwheelsStandardRecipeGen.create(gen));
            gen.addProvider(true, ExtendedCogwheelsDeployingRecipeGen.create(gen));
            gen.addProvider(true, ExtendedCogwheelsCuttingRecipeGen.create(gen));
            #endif
        }
    }

    @ExpectPlatform
    public static void registerConfig(ModConfig.Type type, ForgeConfigSpec spec) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Path configDir() {
        throw new AssertionError();
    }

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static CreateRegistrate registrate() {
        return REGISTRATE;
    }
}
