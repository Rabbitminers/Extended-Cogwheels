package com.rabbitminers.extendedgears.cogwheels;

import com.jozufozu.flywheel.core.PartialModel;
import com.jozufozu.flywheel.core.StitchedSprite;
import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.rabbitminers.extendedgears.base.datatypes.ItemPredicate;
import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsPartials;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.AllTags;
import com.simibubi.create.Create;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class CogwheelMaterials {
    public static final Map<ResourceLocation, CogwheelMaterial> MATERIAL_MAP = new HashMap<>();
    public static final Map<ItemPredicate, ResourceLocation> ITEM_TO_MATERIAL_MAP = new HashMap<>();

    @Nullable
    public static CogwheelMaterial of(ResourceLocation location) {
        return MATERIAL_MAP.get(location);
    }

    public static Optional<ResourceLocation> of(ItemStack stack) {
        return ITEM_TO_MATERIAL_MAP.entrySet().stream()
                .filter(predicate -> predicate.getKey().test(stack))
                .map(Map.Entry::getValue)
                .findFirst();
    }

    public static void clientInit() {
        createMaterial("copper")
                .vanilla()
                .texture(Create.asResource("block/cogwheel"), ExtendedCogwheels.asResource("block/copper_cogwheel"))
                .texture(Create.asResource("block/large_cogwheel"), ExtendedCogwheels.asResource("block/large_copper_cogwheel"))
                .build();

        createMaterial("iron")
                .vanilla()
                .texture(Create.asResource("block/cogwheel"), ExtendedCogwheels.asResource("block/iron_cogwheel"))
                .texture(Create.asResource("block/large_cogwheel"), ExtendedCogwheels.asResource("block/large_iron_cogwheel"))
                .build();

        createMaterial("brass")
                .chunky()
                .texture(new ResourceLocation("block/stripped_spruce_log"), ExtendedCogwheels.asResource("block/brass_cogwheel"))
                .build();

        createMaterial("steel")
                .chunky()
                .texture(new ResourceLocation("block/stripped_spruce_log"), ExtendedCogwheels.asResource("block/steel_cogwheel"))
                .build();
    }
    public static void init() {
        createCondition("copper")
                .condition(Items.COPPER_INGOT)
                .condition(AllTags.forgeItemTag("plates/copper"));

        createCondition("iron")
                .condition(Items.IRON_INGOT)
                .condition(AllTags.forgeItemTag("plates/iron"));

        createCondition("brass")
                .condition(AllTags.forgeItemTag("plates/brass"))
                .condition(AllTags.forgeItemTag("ingots/brass"));

        createCondition("steel")
                .condition(AllTags.forgeItemTag("plates/steel"))
                .condition(AllTags.forgeItemTag("ingots/steel"));
    }

    public static MaterialBuilder createMaterial(String modid, String name) {
        return new MaterialBuilder(modid, name);
    }

    private static MaterialBuilder createMaterial(String name) {
        return createMaterial(ExtendedCogwheels.MOD_ID, name);
    }

    @Environment(EnvType.CLIENT)
    public static class MaterialBuilder {
        protected final Map<StitchedSprite, StitchedSprite> textures = new HashMap<>();

        protected NonNullSupplier<PartialModel>
                small = () -> ExtendedCogwheelsPartials.COGWHEEL,
                large = () -> ExtendedCogwheelsPartials.LARGE_COGWHEEL;

        protected final ResourceLocation id;

        public MaterialBuilder(String modid, String name) {
            this.id = new ResourceLocation(modid, "cogwheels/" + name);
        }

        public MaterialBuilder texture(ResourceLocation old, ResourceLocation replacement) {
            textures.put(new StitchedSprite(old), new StitchedSprite(replacement));
            return this;
        }

        public MaterialBuilder chunky() {
            small = () -> ExtendedCogwheelsPartials.CHUNKY_COGWHEEL;
            large = () -> ExtendedCogwheelsPartials.LARGE_CHUNKY_COGWHEEL;
            return this;
        }

        public MaterialBuilder vanilla() {
            small = () -> AllPartialModels.SHAFTLESS_COGWHEEL;
            large = () -> AllPartialModels.SHAFTLESS_LARGE_COGWHEEL;
            return this;
        }

        public MaterialBuilder smallModel(@NotNull PartialModel partialModel) {
            small = () -> partialModel;
            return this;
        }

        public MaterialBuilder largeModel(@NotNull PartialModel partialModel) {
            large = () -> partialModel;
            return this;
        }

        public void build() {
            MATERIAL_MAP.put(id, new CogwheelMaterial(textures, small.get(), large.get()));
        }
    }

    public static ConditionBuilder createCondition(String modid, String name) {
        return new ConditionBuilder(modid, name);
    }

    private static ConditionBuilder createCondition(String name) {
        return new ConditionBuilder(ExtendedCogwheels.MOD_ID, name);
    }

    public static class ConditionBuilder {
        private final ResourceLocation id;

        public ConditionBuilder(String modid, String name) {
            this.id = new ResourceLocation(modid, "cogwheels/" + name);
        }

        public ConditionBuilder condition(ItemPredicate predicate) {
            ITEM_TO_MATERIAL_MAP.put(predicate, id);
            return this;
        }

        public ConditionBuilder condition(Item item) {
            return this.condition(ItemPredicate.of(item));
        }

        public ConditionBuilder condition(TagKey<Item> tag) {
            return this.condition(ItemPredicate.of(tag));
        }
    }

    public record CogwheelMaterial(Map<StitchedSprite, StitchedSprite> texture,
                                   PartialModel smallModel, PartialModel largeModel) {
        public PartialModel getModel(boolean isLarge) {
            return isLarge ? largeModel : smallModel;
        }
    }
}
