package com.rabbitminers.extendedgears.datagen.forge;

import com.google.gson.JsonObject;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class TagFilledCondition implements ICondition {

    private static final ResourceLocation NAME = new ResourceLocation("forge", "tag_filled");
    private final TagKey<Item> tag;

    public TagFilledCondition(String location) {
        this(new ResourceLocation(location));
    }

    public TagFilledCondition(String namespace, String path) {
        this(new ResourceLocation(namespace, path));
    }

    public TagFilledCondition(ResourceLocation tag) {
        this.tag = TagKey.create(Registry.ITEM_REGISTRY, tag);
    }

    public TagFilledCondition(TagKey<Item> tag) {
        this.tag = tag;
    }

    public ResourceLocation getID() {
        return NAME;
    }

    @Override
    public boolean test(ICondition.IContext context) {
        return false;
    }
    @Deprecated
    @Override
    public boolean test() {
        return false;
    }
    public String toString() {
        return "tag_filled(\"" + this.tag.location() + "\")";
    }

    public static class Serializer implements IConditionSerializer<TagFilledCondition> {
        public static final TagFilledCondition.Serializer INSTANCE = new TagFilledCondition.Serializer();

        public Serializer() {
        }

        public void write(JsonObject json, TagFilledCondition value) {
            json.addProperty("tag", value.tag.location().toString());
        }
        @Override
        public TagFilledCondition read(JsonObject json) {
            return new TagFilledCondition(new ResourceLocation(GsonHelper.getAsString(json, "tag")));
        }

        public ResourceLocation getID() {
            return TagFilledCondition.NAME;
        }
    }
}
