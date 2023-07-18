package com.rabbitminers.extendedgears.cogwheels;

import com.jozufozu.flywheel.core.PartialModel;
import com.jozufozu.flywheel.core.StitchedSprite;
import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.rabbitminers.extendedgears.base.datatypes.ItemPredicate;
import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsPartials;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.AllTags;
import com.simibubi.create.Create;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import it.unimi.dsi.fastutil.objects.Reference2ReferenceOpenHashMap;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class CogwheelMaterials {
    private static final Map<BlockState, CogwheelMaterial> MATERIAL_MAP = new HashMap<>();
    private static final Map<ItemPredicate, BlockState> ITEM_TO_MATERIAL_MAP = new HashMap<>();

    static {
        // Following tags used in data-gen
        // https://github.com/Creators-of-Create/Create/blob/HEAD/src/main/java/com/simibubi/create/foundation/data/recipe/CreateRecipeProvider.java#L96

        create(Blocks.COPPER_BLOCK)
                .condition(Items.COPPER_INGOT)
                .condition(AllTags.forgeItemTag("plates/copper"))
                .vanilla()
                .texture(Create.asResource("block/cogwheel"), ExtendedCogwheels.asResource("block/copper_cogwheel"))
                .texture(Create.asResource("block/large_cogwheel"), ExtendedCogwheels.asResource("block/large_copper_cogwheel"))
                .build();

        create(Blocks.IRON_BLOCK)
                .condition(Items.IRON_INGOT)
                .condition(AllTags.forgeItemTag("plates/iron"))
                .vanilla()
                .texture(Create.asResource("block/cogwheel"), ExtendedCogwheels.asResource("block/iron_cogwheel"))
                .texture(Create.asResource("block/large_cogwheel"), ExtendedCogwheels.asResource("block/large_iron_cogwheel"))
                .build();

        create(AllBlocks.BRASS_BLOCK.get())
                .condition(AllTags.forgeItemTag("plates/brass"))
                .condition(AllTags.forgeItemTag("ingots/brass"))
                .chunky()
                .texture(new ResourceLocation("block/stripped_spruce_log"), ExtendedCogwheels.asResource("block/brass_cogwheel"))
                .build();

        create(AllBlocks.INDUSTRIAL_IRON_BLOCK.get())
                .condition(AllTags.forgeItemTag("plates/steel"))
                .condition(AllTags.forgeItemTag("ingots/steel"))
                .chunky()
                .texture(new ResourceLocation("block/stripped_spruce_log"), ExtendedCogwheels.asResource("block/steel_cogwheel"))
                .build();
    }

    public static CogwheelMaterialBuilder create(Block block) {
        return new CogwheelMaterialBuilder(block.defaultBlockState());
    }

    public static Optional<CogwheelMaterial> of(BlockState state) {
        return Optional.ofNullable(MATERIAL_MAP.get(state));
    }

    public static Optional<BlockState> of(ItemStack stack) {
        return ITEM_TO_MATERIAL_MAP.entrySet().stream()
                .filter(predicate -> predicate.getKey().test(stack))
                .map(Map.Entry::getValue)
                .findFirst();
    }

    public static void addItemCondition(ItemPredicate predicate, BlockState state) {
        if (!MATERIAL_MAP.containsKey(state))
            throw new IllegalStateException("Registered condition for state that doesn't exist");
        ITEM_TO_MATERIAL_MAP.put(predicate, state);
    }

    public record CogwheelMaterial(Reference2ReferenceOpenHashMap<TextureAtlasSprite, TextureAtlasSprite> texture,
                                   PartialModel smallModel, PartialModel largeModel) {
        public PartialModel getModel(boolean isLarge) {
            return isLarge ? largeModel : smallModel;
        }
    }

    public static class CogwheelMaterialBuilder {
        private final List<ItemPredicate> predicates = new ArrayList<>();
        private final BlockState state;
        private final Reference2ReferenceOpenHashMap<TextureAtlasSprite, TextureAtlasSprite> textures;

        private NonNullSupplier<PartialModel>
                small = () -> ExtendedCogwheelsPartials.COGWHEEL,
                large = () -> ExtendedCogwheelsPartials.LARGE_COGWHEEL;

        public CogwheelMaterialBuilder(BlockState state) {
            this.state = state;
            this.textures = new Reference2ReferenceOpenHashMap<>();
        }

        public CogwheelMaterialBuilder texture(ResourceLocation old, ResourceLocation replacement) {
            textures.put(new StitchedSprite(old).get(), new StitchedSprite(replacement).get());
            return this;
        }

        public CogwheelMaterialBuilder condition(Item predicate) {
            predicates.add(ItemPredicate.of(predicate));
            return this;
        }

        public CogwheelMaterialBuilder condition(TagKey<Item> predicate) {
            predicates.add(ItemPredicate.of(predicate));
            return this;
        }

        public CogwheelMaterialBuilder chunky() {
            small = () -> ExtendedCogwheelsPartials.CHUNKY_COGWHEEL;
            large = () -> ExtendedCogwheelsPartials.LARGE_CHUNKY_COGWHEEL;
            return this;
        }

        public CogwheelMaterialBuilder vanilla() {
            small = () -> AllPartialModels.SHAFTLESS_COGWHEEL;
            large = () -> AllPartialModels.SHAFTLESS_LARGE_COGWHEEL;
            return this;
        }

        public CogwheelMaterialBuilder smallModel(@NotNull PartialModel partialModel) {
            small = () -> partialModel;
            return this;
        }

        public CogwheelMaterialBuilder largeModel(@NotNull PartialModel partialModel) {
            large = () -> partialModel;
            return this;
        }

        public void build() {
            MATERIAL_MAP.put(state, new CogwheelMaterial(textures, small.get(), large.get()));
            predicates.forEach(predicate -> addItemCondition(predicate, state));
        }
    }

    public static void init() {

    }
}
