package com.rabbitminers.extendedgears.mixin_interface;

import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public interface PartialModelStore {
    HashMap<ResourceLocation, PartialModel> models = new HashMap<>();

    @NotNull
    static PartialModel getOrDefault(ResourceLocation key, PartialModel def) {
        @Nullable PartialModel model = models.get(key);
        return model != null ? model : def;
    }
}
