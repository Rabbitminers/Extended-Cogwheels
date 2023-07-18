package com.rabbitminers.extendedgears.cogwheels;

import com.jozufozu.flywheel.core.PartialModel;
import com.jozufozu.flywheel.core.StitchedSprite;
import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.rabbitminers.extendedgears.base.datatypes.ItemPredicate;
import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsPartials;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllTags;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
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

        create(Blocks.COPPER_BLOCK, "block/copper_cogwheel")
                .condition(Items.COPPER_INGOT)
                .condition(AllTags.forgeItemTag("plates/copper"))
                .build();

        create(Blocks.IRON_BLOCK, "block/iron_cogwheel")
                .condition(Items.IRON_INGOT)
                .condition(AllTags.forgeItemTag("plates/iron"))
                .build();

        create(AllBlocks.BRASS_BLOCK.get(), "block/brass_cogwheel")
                .chunky()
                .condition(AllTags.forgeItemTag("plates/brass"))
                .condition(AllTags.forgeItemTag("ingots/brass"))
                .build();

        create(AllBlocks.INDUSTRIAL_IRON_BLOCK.get(), "block/steel_cogwheel")
                .chunky()
                .condition(AllTags.forgeItemTag("plates/steel"))
                .condition(AllTags.forgeItemTag("ingots/steel"))
                .build();
    }

    public static CogwheelMaterialBuilder create(Block block, ResourceLocation texture) {
        return new CogwheelMaterialBuilder(block.defaultBlockState(), texture);
    }

    private static CogwheelMaterialBuilder create(Block block, String texture) {
        return create(block, ExtendedCogwheels.asResource(texture));
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

    public record CogwheelMaterial(StitchedSprite texture, PartialModel smallModel, PartialModel largeModel) {
        public static CogwheelMaterial of(ResourceLocation texture, PartialModel smallModel, PartialModel largeModel) {
            return new CogwheelMaterial(new StitchedSprite(texture), smallModel, largeModel);
        }

        public PartialModel getModel(boolean isLarge) {
            return isLarge ? largeModel : smallModel;
        }
    }

    public static class CogwheelMaterialBuilder {
        private final List<ItemPredicate> predicates = new ArrayList<>();
        private final BlockState state;
        private final ResourceLocation texture;
        private NonNullSupplier<PartialModel>
                small = () -> ExtendedCogwheelsPartials.COGWHEEL,
                large = () -> ExtendedCogwheelsPartials.LARGE_COGWHEEL;

        public CogwheelMaterialBuilder(BlockState state, ResourceLocation texture) {
            this.state = state;
            this.texture = texture;
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

        public CogwheelMaterialBuilder smallModel(@NotNull PartialModel partialModel) {
            small = () -> partialModel;
            return this;
        }

        public CogwheelMaterialBuilder largeModel(@NotNull PartialModel partialModel) {
            large = () -> partialModel;
            return this;
        }

        public void build() {
            MATERIAL_MAP.put(state, CogwheelMaterial.of(texture, small.get(), large.get()));
            predicates.forEach(predicate -> addItemCondition(predicate, state));
        }
    }
}
