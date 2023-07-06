package com.rabbitminers.extendedgears;

import com.rabbitminers.extendedgears.base.lang.ExtendedCogwheelsLanguageProvider;
import com.rabbitminers.extendedgears.cogwheels.CogwheelLimits;
import com.rabbitminers.extendedgears.config.ExtendedCogwheelsConfig;
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
    public static final String NAME = "Extended Gears";
    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);
    public static final int DATA_FIXER_VERSION = 1;

    private static final CreateRegistrate REGISTRATE = CreateRegistrate.create(ExtendedCogwheels.MOD_ID);
            // .creativeModeTab(() -> ExtendedCogwheelsItems.itemGroup);

    public static void init() {
        ExtendedCogwheelsTags.init();
        ExtendedCogwheelsItems.init();
        ExtendedCogwheelsTablessBlocks.init();
        ExtendedCogwheelsBlocks.init();
        ExtendedCogwheelsTileEntities.init();
        // ExtendedCogwheelsDataFixers.init();

        registerConfig(ModConfig.Type.CLIENT, ExtendedCogwheelsConfig.CLIENT_CONFIG);
        registerConfig(ModConfig.Type.SERVER, ExtendedCogwheelsConfig.SERVER_CONFIG);

        CogwheelLimits.init();

        Path configDir = configDir();

        ExtendedCogwheelsConfig.loadConfig(ExtendedCogwheelsConfig.CLIENT_CONFIG, configDir.resolve(MOD_ID + "-client.toml"));
        ExtendedCogwheelsConfig.loadConfig(ExtendedCogwheelsConfig.SERVER_CONFIG, configDir.resolve(MOD_ID + "-common.toml"));

    }

    public static void gatherData(DataGenerator gen, boolean isServer) {
        if (isServer) {
            gen.addProvider(new LangMerger(gen, MOD_ID, "Extended Cogwheels",
                    ExtendedCogwheelsLanguageProvider.values()));
            gen.addProvider(ExtendedCogwheelsStandardRecipeGen.create(gen));
            gen.addProvider(ExtendedCogwheelsDeployingRecipeGen.create(gen));
            gen.addProvider(ExtendedCogwheelsCuttingRecipeGen.create(gen));
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
