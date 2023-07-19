package com.rabbitminers.extendedgears.cogwheels;

import com.simibubi.create.foundation.config.ConfigBase;
import com.simibubi.create.infrastructure.config.AllConfigs;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CogwheelLimits {
    private final static Map<BlockState, Integer> STRESS_LIMITS = new HashMap<>();
    private final static Map<BlockState, Integer> SPEED_LIMITS = new HashMap<>();

    public static ConfigBase.ConfigInt MAX_SPEED = AllConfigs.server().kinetics.maxRotationSpeed;

    public static int boundValues(int current, int max, int min) {
        return Math.max(min, Math.min(current, max));
    }

    public static void setStressLimit(BlockState material, int stressLimit) {
        STRESS_LIMITS.put(material, stressLimit);
    }

    public static void setSpeedLimit(BlockState material, int speedLimit) {
        SPEED_LIMITS.put(material, boundValues(speedLimit, MAX_SPEED.get(), -MAX_SPEED.get()));
    }

    public static void setLimits(BlockState material, int stressLimit, int speedLimit) {
        setSpeedLimit(material, speedLimit);
        setStressLimit(material, stressLimit);
    }

    public static int getStressLimit(BlockState material) {
        return Optional.ofNullable(STRESS_LIMITS.get(material)).orElse(Integer.MAX_VALUE);
    }

    public static int getSpeedLimit(BlockState material) {
        return Optional.ofNullable(SPEED_LIMITS.get(material)).orElse(MAX_SPEED.get());
    }

    public static void init() {
        // Backup if KubeJS isn't present
    }
}
