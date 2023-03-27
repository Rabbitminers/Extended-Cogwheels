package com.rabbitminers.extendedgears.integration;

import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.block.BlockBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

import static com.rabbitminers.extendedgears.registry.ExtendedCogwheelsTags.ExtendedCogwheelsItemTags.COGWHEEL;

public class KubeJsCogwheelBuilder extends BlockBuilder {
    boolean isLarge = false;

    public KubeJsCogwheelBuilder(ResourceLocation i) {
        super(i);
        this.tagItem(COGWHEEL.tag.location());
    }

    @Override
    public Map<ResourceLocation, JsonObject> generateBlockModels(BlockBuilder builder) {
        Map<ResourceLocation, JsonObject> map = new HashMap();
        if (builder.modelJson != null) {
            map.put(builder.newID("models/block/", ""), builder.modelJson);
        } else {
            String parent = isLarge ? "extendedgears:block/base/large_cogwheel"
                : "extendedgears:block/base/cogwheel";
            modelJson.addProperty("parent", parent);
            modelJson.add("textures", builder.textures);
            map.put(builder.newID("models/block/", ""), modelJson);
        }
        return map;
    }

    @Override
    public Block createObject() {
        return new CogwheelBlockJS(this);
    }

    @Override
    public Block transformObject(Block obj) {
        if (obj instanceof CogwheelBlockJS)
            KubeJsCogwheels.addCogwheel(this);
        return super.transformObject(obj);
    }
}
