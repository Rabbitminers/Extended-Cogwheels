package com.rabbitminers.extendedgears.cogwheels;

import com.simibubi.create.infrastructure.config.AllConfigs;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CogwheelLimits {
    private final static Map<ResourceLocation, Integer> STRESS_LIMITS = new HashMap<>();
    private final static Map<ResourceLocation, Integer> SPEED_LIMITS = new HashMap<>();

    public static int MAX_SPEED = AllConfigs.server().kinetics.maxRotationSpeed.get();

    public static int boundValues(int current, int max, int min) {
        return Math.max(min, Math.min(current, max));
    }

    public static void setStressLimit(ResourceLocation material, int stressLimit) {
        STRESS_LIMITS.put(material, stressLimit);
    }

    public static void setSpeedLimit(ResourceLocation material, int speedLimit) {
        SPEED_LIMITS.put(material, boundValues(speedLimit, MAX_SPEED, -MAX_SPEED));
    }

    public static void setLimits(ResourceLocation material, int stressLimit, int speedLimit) {
        setSpeedLimit(material, speedLimit);
        setStressLimit(material, stressLimit);
    }

    public static int getStressLimit(ResourceLocation material) {
        return Optional.ofNullable(STRESS_LIMITS.get(material)).orElse(Integer.MAX_VALUE);
    }

    public static int getSpeedLimit(ResourceLocation material) {
        return Optional.ofNullable(SPEED_LIMITS.get(material)).orElse(MAX_SPEED);
    }

    public static void init() {
        // Backup if KubeJS isn't present
    }
}
