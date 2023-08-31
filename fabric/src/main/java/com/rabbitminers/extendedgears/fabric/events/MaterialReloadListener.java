package com.rabbitminers.extendedgears.fabric.events;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.rabbitminers.extendedgears.cogwheels.materials.CogwheelMaterialManager;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class MaterialReloadListener extends SimpleJsonResourceReloadListener implements IdentifiableResourceReloadListener {
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

    @Override
    public ResourceLocation getFabricId() {
        return ExtendedCogwheels.asResource("cogwheel_material_types");
    }
}
