package com.rabbitminers.extendedgears.forge.events;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.rabbitminers.extendedgears.cogwheels.materials.CogwheelMaterialManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class MaterialReloadListener extends SimpleJsonResourceReloadListener {
    private static final Gson GSON = new Gson();

    public static final MaterialReloadListener INSTANCE = new MaterialReloadListener();

    protected MaterialReloadListener() {
        super(GSON, "cogwheels");
    }

    @Override
    protected void apply(@NotNull Map<ResourceLocation, JsonElement> map, @NotNull ResourceManager resourceManager,
                         @NotNull ProfilerFiller profiler) {
        CogwheelMaterialManager.applyReloadListener(map);
    }
}
