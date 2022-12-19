package com.rabbitminers.extendedgears.config;

import com.jozufozu.flywheel.core.PartialModel;
import com.rabbitminers.extendedgears.basecog.MetalCogWheel;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ECConfig {
    public static final ECConfig.CogwheelLimitations COGWHEEL_LIMITATIONS;
    public static final ForgeConfigSpec spec;


    static {
        final Pair<CogwheelLimitations, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ECConfig.CogwheelLimitations::new);
        spec = specPair.getRight();
        COGWHEEL_LIMITATIONS = specPair.getLeft();
    }
    public static class CogwheelLimitations {
        public ForgeConfigSpec.IntValue maxBirchRPM;
        public ForgeConfigSpec.IntValue maxOakRPM;
        public ForgeConfigSpec.IntValue maxSpruceRPM;
        public ForgeConfigSpec.IntValue maxDarkOakRPM;
        public ForgeConfigSpec.IntValue maxJungleRPM;
        public ForgeConfigSpec.IntValue maxAcaciaRPM;
        public ForgeConfigSpec.IntValue maxWarpedRPM;
        public ForgeConfigSpec.IntValue maxCrimsonRPM;

        public ForgeConfigSpec.IntValue maxCopperRPM;
        public ForgeConfigSpec.IntValue maxIronRPM;
        public ForgeConfigSpec.IntValue maxSteelRPM;

        CogwheelLimitations(ForgeConfigSpec.Builder builder) {
            builder.comment("Limit the maximum RPM of cogwheels, not this will not overwrite the max RPM set in the create config")
                    .push("Cogwheel Limitations");

            // TODO: Implement Translation Keys

            maxBirchRPM = builder
                    .comment("Hard limit the ammount of RPM that a birch cogwheel can sustain")
                    .translation("todo")
                    .defineInRange("maxBirchRPM", 256, 1, Integer.MAX_VALUE);

            maxOakRPM = builder
                    .comment("Hard limit the ammount of RPM that a oak cogwheel can sustain")
                    .translation("todo")
                    .defineInRange("maxOakRPM", 256, 1, Integer.MAX_VALUE);

            maxSpruceRPM = builder
                    .comment("Hard limit the ammount of RPM that a spruce cogwheel can sustain")
                    .translation("todo")
                    .defineInRange("maxSpruceRPM", 256, 1, Integer.MAX_VALUE);

            maxDarkOakRPM = builder
                    .comment("Hard limit the ammount of RPM that a Dark Oak cogwheel can sustain")
                    .translation("todo")
                    .defineInRange("maxDarkOakRPM", 256, 1, Integer.MAX_VALUE);

            maxJungleRPM = builder
                    .comment("Hard limit the ammount of RPM that a jungle cogwheel can sustain")
                    .translation("todo")
                    .defineInRange("maxJungleRPM", 256, 1, Integer.MAX_VALUE);

            maxAcaciaRPM = builder
                    .comment("Hard limit the ammount of RPM that a acacia cogwheel can sustain")
                    .translation("todo")
                    .defineInRange("maxAcaciaRPM", 256, 1, Integer.MAX_VALUE);

            maxWarpedRPM = builder
                    .comment("Hard limit the ammount of RPM that a warped cogwheel can sustain")
                    .translation("todo")
                    .defineInRange("maxWarpedRPM", 256, 1, Integer.MAX_VALUE);

            maxCrimsonRPM = builder
                    .comment("Hard limit the ammount of RPM that a crimson cogwheel can sustain")
                    .translation("todo")
                    .defineInRange("maxCrimsonRPM", 256, 1, Integer.MAX_VALUE);

            maxCopperRPM = builder
                    .comment("Hard limit the ammount of RPM that a copper cogwheel can sustain")
                    .translation("todo")
                    .defineInRange("maxCopperRPM", 256, 1, Integer.MAX_VALUE);

            maxIronRPM = builder
                    .comment("Hard limit the ammount of RPM that a iron cogwheel can sustain")
                    .translation("todo")
                    .defineInRange("maxIronRPM", 256, 1, Integer.MAX_VALUE);

            maxSteelRPM = builder
                    .comment("Hard limit the ammount of RPM that a steel cogwheel can sustain")
                    .translation("todo")
                    .defineInRange("maxSteelRPM", 256, 1, Integer.MAX_VALUE);
        }
    }
}
