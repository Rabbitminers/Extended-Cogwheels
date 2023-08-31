package com.rabbitminers.extendedgears.cogwheels.materials;

import com.jozufozu.flywheel.core.PartialModel;
import com.jozufozu.flywheel.core.StitchedSprite;
import com.rabbitminers.extendedgears.cogwheels.CogwheelMaterials;
import com.rabbitminers.extendedgears.mixin_interface.PartialModelStore;
import com.simibubi.create.AllPartialModels;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

@Environment(EnvType.CLIENT)
public record ClientCogwheelMaterial(Map<StitchedSprite, StitchedSprite> textures, PartialModel smallModel, PartialModel largeModel) {
    public static ClientCogwheelMaterial fromMaterial(CogwheelMaterial material) {
        Map<StitchedSprite, StitchedSprite> textures = new HashMap<>();
        for (Entry<ResourceLocation, ResourceLocation> entry : material.textures.entrySet()) {
            textures.put(new StitchedSprite(entry.getKey()), new StitchedSprite(entry.getValue()));
        }
        PartialModel smallModel = PartialModelStore.getOrDefault(material.smallModel, AllPartialModels.SHAFTLESS_COGWHEEL);
        PartialModel largeModel = PartialModelStore.getOrDefault(material.largeModel, AllPartialModels.SHAFTLESS_LARGE_COGWHEEL);
        return new ClientCogwheelMaterial(textures, smallModel, largeModel);
    }
}
