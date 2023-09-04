package com.rabbitminers.extendedgears.registry;

import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.rabbitminers.extendedgears.cogwheels.materials.CogwheelMaterial;
import com.rabbitminers.extendedgears.cogwheels.materials.CogwheelMaterialManager;
import com.simibubi.create.Create;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class ExtendedCogwheelsCogwheelMaterials {
    public static final CogwheelMaterial COPPER = create("copper")
            .texture(Create.asResource("block/cogwheel"), ExtendedCogwheels.asResource("block/copper_cogwheel"))
            .texture(Create.asResource("block/large_cogwheel"), ExtendedCogwheels.asResource("block/large_copper_cogwheel"))
            .build();

    public static final CogwheelMaterial IRON = create("iron")
            .texture(Create.asResource("block/cogwheel"), ExtendedCogwheels.asResource("block/iron_cogwheel"))
            .texture(Create.asResource("block/large_cogwheel"), ExtendedCogwheels.asResource("block/large_iron_cogwheel"))
            .build();

    public static final CogwheelMaterial BRASS = create("brass")
            .chunky()
            .texture(new ResourceLocation("block/stripped_spruce_log"), ExtendedCogwheels.asResource("block/brass_cogwheel"))
            .build();

    public static final CogwheelMaterial STEEL = create("steel")
            .chunky()
            .texture(new ResourceLocation("block/stripped_spruce_log"), ExtendedCogwheels.asResource("block/steel_cogwheel"))
            .build();

    public static void init() {
        //
    }

    private static Builder create(String name) {
        return new Builder(ExtendedCogwheels.asResource(name));
    }

    public static class Builder {
        private final Map<ResourceLocation, ResourceLocation> textures;
        private final ResourceLocation id;
        private boolean chunky = false;

        public Builder(ResourceLocation id) {
            this.id = id;
            this.textures = new HashMap<>();
        }

        public Builder chunky() {
            this.chunky = !this.chunky;
            return this;
        }

        public Builder texture(ResourceLocation old, ResourceLocation replacement) {
            textures.put(old, replacement);
            return this;
        }

        public CogwheelMaterial build() {
            CogwheelMaterial material = new CogwheelMaterial(id, textures, chunky);
            CogwheelMaterialManager.MATERIALS.put(id, material);
            return material;
        }
    }
}
