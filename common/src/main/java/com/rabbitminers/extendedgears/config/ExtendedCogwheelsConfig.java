package com.rabbitminers.extendedgears.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.rabbitminers.extendedgears.base.data.ICogwheelMaterial;
import com.rabbitminers.extendedgears.base.data.MetalCogwheel;
import com.rabbitminers.extendedgears.base.data.WoodenCogwheel;
import net.minecraftforge.common.ForgeConfigSpec;

import java.nio.file.Path;
import java.util.EnumMap;

public class ExtendedCogwheelsConfig {

    public static final String CATEGORY_GENERAL = "general";
    public static final String CATEGORY_STRESS_LIMITS = "stress";
    public static final String CATEGORY_ROTATION_LIMITS = "rotation";

    public static final ForgeConfigSpec SERVER_CONFIG;
    public static final ForgeConfigSpec CLIENT_CONFIG;

    public static ForgeConfigSpec.BooleanValue APPLY_ROTATION_LIMITS;
    public static ForgeConfigSpec.BooleanValue APPLY_STRESS_LIMITS;

    public static EnumMap<WoodenCogwheel, ForgeConfigSpec.IntValue> WOODEN_COGWHEEL_STRESS_LIMITS = new EnumMap<>(WoodenCogwheel.class);
    public static EnumMap<MetalCogwheel, ForgeConfigSpec.IntValue> METAL_COGWHEEL_STRESS_LIMITS= new EnumMap<>(MetalCogwheel.class);;

    public static EnumMap<WoodenCogwheel, ForgeConfigSpec.IntValue> WOODEN_COGWHEEL_ROTATION_LIMITS= new EnumMap<>(WoodenCogwheel.class);;
    public static EnumMap<MetalCogwheel, ForgeConfigSpec.IntValue> METAL_COGWHEEL_ROTATION_LIMITS= new EnumMap<>(MetalCogwheel.class);;


    static {

        ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

        CLIENT_BUILDER.comment("General settings").push(CATEGORY_GENERAL);

        SERVER_BUILDER.comment("General settings").push(CATEGORY_GENERAL);
        APPLY_STRESS_LIMITS = SERVER_BUILDER
                .comment("Toggle To Apply Stress Limitations To Cogwheels (configurable under stress)")
                .define("apply_stress_limits", false);

        APPLY_ROTATION_LIMITS = SERVER_BUILDER
                .comment("Toggle To Apply Rotation Limitations To Cogwheels (configurable under rotation)")
                .define("apply_rotation_limits", false);

        SERVER_BUILDER.push(CATEGORY_STRESS_LIMITS);

        cogwheelStressLimitGenerator(WoodenCogwheel.class,
                WOODEN_COGWHEEL_STRESS_LIMITS, SERVER_BUILDER);
        cogwheelStressLimitGenerator(MetalCogwheel.class,
                METAL_COGWHEEL_STRESS_LIMITS, SERVER_BUILDER);

        SERVER_BUILDER.pop().push(CATEGORY_ROTATION_LIMITS);

        cogwheelRotationLimitGenerator(WoodenCogwheel.class,
                WOODEN_COGWHEEL_ROTATION_LIMITS, SERVER_BUILDER);
        cogwheelRotationLimitGenerator(MetalCogwheel.class,
                METAL_COGWHEEL_ROTATION_LIMITS, SERVER_BUILDER);

        SERVER_BUILDER.pop();


        SERVER_CONFIG = SERVER_BUILDER.build();
        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }

    public static <T extends Enum<T> & ICogwheelMaterial> void cogwheelStressLimitGenerator(Class<T> materialType,
            EnumMap<T, ForgeConfigSpec.IntValue> map, ForgeConfigSpec.Builder builder) {
        for (T material : materialType.getEnumConstants()) {
            map.put(material, builder
                .comment("Maximum stress for " + material.asId() + " cogwheels (will break anything above this value not including)")
                .defineInRange(material.asId() + "_stress_limit", Integer.MAX_VALUE, 0, Integer.MAX_VALUE));
        }
    }

    public static <T extends Enum<T> & ICogwheelMaterial> void cogwheelRotationLimitGenerator(Class<T> materialType,
            EnumMap<T, ForgeConfigSpec.IntValue> map, ForgeConfigSpec.Builder builder) {
        for (T material : materialType.getEnumConstants()) {
            map.put(material, builder
                .comment("Maximum rpm for " + material.asId() + " cogwheels (will break anything above this value not including)")
                .defineInRange(material.asId() + "_rotation_limit", 256, 0, Integer.MAX_VALUE));
        }
    }

    public static void loadConfig(ForgeConfigSpec spec, Path path) {
        final CommentedFileConfig configData = CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();
        configData.load();
        spec.setConfig(configData);
    }
}