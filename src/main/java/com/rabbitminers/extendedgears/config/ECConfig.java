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

        public ForgeConfigSpec.IntValue maxBirchSU;
        public ForgeConfigSpec.IntValue maxOakSU;
        public ForgeConfigSpec.IntValue maxSpruceSU;
        public ForgeConfigSpec.IntValue maxDarkOakSU;
        public ForgeConfigSpec.IntValue maxJungleSU;
        public ForgeConfigSpec.IntValue maxAcaciaSU;
        public ForgeConfigSpec.IntValue maxWarpedSU;
        public ForgeConfigSpec.IntValue maxCrimsonSU;
        public ForgeConfigSpec.IntValue maxCopperSU;
        public ForgeConfigSpec.IntValue maxIronSU;
        public ForgeConfigSpec.IntValue maxSteelSU;

        CogwheelLimitations(ForgeConfigSpec.Builder builder) {
            builder.comment("Limit the maximum RPM and or SU of cogwheels, by default these values are both set to maximum, this will not overwrite the max RPM set in the create config")
                    .push("Cogwheel Limitations");

            // TODO: Implement Translation Keys

            builder.comment("Limit the maximum RPM of cogwheels, this will not overwrite the max RPM set in the create config")
                    .push("Cogwheel Rotation Limitations");


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

            builder.comment("Limit the maximum Stress of cogwheels")
                    .push("Cogwheel Stress Limitations");

            maxBirchSU = builder
                    .comment("Hard limit the ammount of SU that a birch cogwheel can sustain")
                    .translation("todo")
                    .defineInRange("maxBirchSU", Integer.MAX_VALUE, 1, Integer.MAX_VALUE);

            maxOakSU = builder
                    .comment("Hard limit the ammount of SU that a oak cogwheel can sustain")
                    .translation("todo")
                    .defineInRange("maxOakSU", Integer.MAX_VALUE, 1, Integer.MAX_VALUE);

            maxSpruceSU = builder
                    .comment("Hard limit the ammount of SU that a spruce cogwheel can sustain")
                    .translation("todo")
                    .defineInRange("maxSpruceSU", Integer.MAX_VALUE, 1, Integer.MAX_VALUE);

            maxDarkOakSU = builder
                    .comment("Hard limit the ammount of SU that a Dark Oak cogwheel can sustain")
                    .translation("todo")
                    .defineInRange("maxDarkOakSU", Integer.MAX_VALUE, 1, Integer.MAX_VALUE);

            maxJungleSU = builder
                    .comment("Hard limit the ammount of SU that a jungle cogwheel can sustain")
                    .translation("todo")
                    .defineInRange("maxJungleSU", Integer.MAX_VALUE, 1, Integer.MAX_VALUE);

            maxAcaciaSU = builder
                    .comment("Hard limit the ammount of SU that a acacia cogwheel can sustain")
                    .translation("todo")
                    .defineInRange("maxAcaciaSU", Integer.MAX_VALUE, 1, Integer.MAX_VALUE);

            maxWarpedSU = builder
                    .comment("Hard limit the ammount of SU that a warped cogwheel can sustain")
                    .translation("todo")
                    .defineInRange("maxWarpedSU", Integer.MAX_VALUE, 1, Integer.MAX_VALUE);

            maxCrimsonSU = builder
                    .comment("Hard limit the ammount of SU that a crimson cogwheel can sustain")
                    .translation("todo")
                    .defineInRange("maxCrimsonSU", Integer.MAX_VALUE, 1, Integer.MAX_VALUE);

            maxCopperSU = builder
                    .comment("Hard limit the ammount of SU that a copper cogwheel can sustain")
                    .translation("todo")
                    .defineInRange("maxCopperSU", Integer.MAX_VALUE, 1, Integer.MAX_VALUE);

            maxIronSU = builder
                    .comment("Hard limit the ammount of SU that a iron cogwheel can sustain")
                    .translation("todo")
                    .defineInRange("maxIronSU", Integer.MAX_VALUE, 1, Integer.MAX_VALUE);

            maxSteelSU = builder
                    .comment("Hard limit the ammount of SU that a steel cogwheel can sustain")
                    .translation("todo")
                    .defineInRange("maxSteelSU", Integer.MAX_VALUE, 1, Integer.MAX_VALUE);
        }
    }
}
