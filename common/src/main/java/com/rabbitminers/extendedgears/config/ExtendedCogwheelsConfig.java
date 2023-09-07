package com.rabbitminers.extendedgears.config;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.toml.TomlParser;
import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.rabbitminers.extendedgears.base.util.Utils;
import com.simibubi.create.foundation.config.ConfigBase;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.ApiStatus;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class ExtendedCogwheelsConfig {
    @ApiStatus.Internal
    public static final Map<ModConfig.Type, ConfigBase> CONFIGS = new EnumMap<>(ModConfig.Type.class);

    private static CommonConfig common;

    public static CommonConfig common() {
        return common;
    }

    public static ConfigBase byType(ModConfig.Type type) {
        return CONFIGS.get(type);
    }

    private static <T extends ConfigBase> T register(Supplier<T> factory, ModConfig.Type side) {
        Pair<T, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(builder -> {
            T config = factory.get();
            config.registerAll(builder);
            return config;
        });

        T config = specPair.getLeft();
        config.specification = specPair.getRight();
        CONFIGS.put(side, config);
        return config;
    }

    public static void register() {
        throw new AssertionError();
    }

    @ApiStatus.Internal
    public static void registerCommon() {
        common = register(CommonConfig::new, ModConfig.Type.COMMON);
    }

    public static void onLoad(ModConfig modConfig) {
        for (ConfigBase config : CONFIGS.values())
            if (config.specification == modConfig.getSpec())
                config.onLoad();
    }

    public static void onReload(ModConfig modConfig) {
        for (ConfigBase config : CONFIGS.values())
            if (config.specification == modConfig.getSpec())
                config.onReload();
    }

    private static Boolean cachedDisableDatafixer;

    public static boolean getDisableDatafixer() {
        if (common != null)
            return common.disableDatafixer.get();

        if (cachedDisableDatafixer == null) {
            preloadValues();
        }

        return cachedDisableDatafixer;
    }

    private static void preloadValues() {
        Path configDir = Utils.configDir();
        Path commonConfig = configDir.resolve(ExtendedCogwheels.MOD_ID+"-common.toml");
        try (Reader reader = new FileReader(commonConfig.toFile())) {
            CommentedConfig config = new TomlParser().parse(reader);

            cachedDisableDatafixer = config.<Boolean>getRaw("disableDatafixer");
        } catch (IOException e) {
            cachedDisableDatafixer = null;
        }

        if (cachedDisableDatafixer == null)
            cachedDisableDatafixer = false;
    }

}
