package com.rabbitminers.extendedgears.registry;

import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.rabbitminers.extendedgears.base.data.ICogwheelMaterial;
import com.rabbitminers.extendedgears.base.datatypes.IngredientProvider;
import com.rabbitminers.extendedgears.base.datatypes.MetalBlockList;
import com.rabbitminers.extendedgears.base.datatypes.WoodenBlockList;
import com.rabbitminers.extendedgears.cogwheels.CustomCogwheelBlock;
import com.rabbitminers.extendedgears.cogwheels.HalfShaftCogwheelBlock;
import com.rabbitminers.extendedgears.cogwheels.ShaftlessCogwheelBlock;
import com.rabbitminers.extendedgears.datagen.HalfShaftGenerator;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockModel;
import com.simibubi.create.content.kinetics.simpleRelays.CogwheelBlockItem;
import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.simibubi.create.foundation.data.TagGen;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MaterialColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExtendedCogwheelsBlocks {
	public static final CreateRegistrate REGISTRATE = ExtendedCogwheels.registrate();

	public static <B extends CustomCogwheelBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> cogwheelItemTransformer(boolean isLarge, TagKey<Item> materialType) {
		return b -> b.item(CogwheelBlockItem::new)
				.tag(materialType)
				.tag(isLarge ? ExtendedCogwheelsTags.ExtendedCogwheelsItemTags.LARGE_COGWHEEL.tag
						: ExtendedCogwheelsTags.ExtendedCogwheelsItemTags.SMALL_COGWHEEL.tag)
				.tag(ExtendedCogwheelsTags.ExtendedCogwheelsItemTags.COGWHEEL.tag)
				.build();
	}

	public static <B extends CustomCogwheelBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> commonCogwheelTransformer(boolean isLarge, TagKey<Item> materialType) {
		return b -> b.blockstate(BlockStateGen.axisBlockProvider(false))
				.onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new))
				.transform(BlockStressDefaults.setNoImpact())
				.transform(cogwheelItemTransformer(isLarge, materialType));
	}

	public static <B extends CustomCogwheelBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> woodenCogwheelTransformer(boolean isLarge) {
		return b -> b.initialProperties(SharedProperties::wooden)
				.properties(p -> p.sound(SoundType.WOOD))
				.properties(p -> p.color(MaterialColor.DIRT))
				.transform(TagGen.axeOrPickaxe())
				.transform(commonCogwheelTransformer(isLarge, ExtendedCogwheelsTags.ExtendedCogwheelsItemTags.WOODEN_COGWHEEL.tag));
	}

	public static <B extends CustomCogwheelBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> metalCogwheelTransformer(boolean isLarge) {
		return b -> b.initialProperties(SharedProperties::softMetal)
				.properties(p -> p.sound(SoundType.METAL))
				.properties(p -> p.color(MaterialColor.METAL))
				.transform(TagGen.pickaxeOnly())
				.transform(commonCogwheelTransformer(isLarge, ExtendedCogwheelsTags.ExtendedCogwheelsItemTags.METAL_COGWHEEL.tag));
	}

	// Wooden Cogwheels

	public static final WoodenBlockList<CustomCogwheelBlock> WOODEN_COGWHEELS = new WoodenBlockList<>(wood ->
			REGISTRATE.block(wood.asId() + "_cogwheel", p -> CustomCogwheelBlock.small(p, wood))
					.transform(woodenCogwheelTransformer(false))
					.register());

	public static final WoodenBlockList<CustomCogwheelBlock> LARGE_WOODEN_COGWHEELS = new WoodenBlockList<>(wood ->
			REGISTRATE.block("large_" + wood.asId() + "_cogwheel", p -> CustomCogwheelBlock.large(p, wood))
					.transform(woodenCogwheelTransformer(true))
					.register());

	public static final WoodenBlockList<CustomCogwheelBlock> SHAFTLESS_WOODEN_COGWHEELS = new WoodenBlockList<>(wood ->
			REGISTRATE.block("shaftless_" + wood.asId() + "_cogwheel", p -> ShaftlessCogwheelBlock.small(p, wood))
					.transform(woodenCogwheelTransformer(false))
					.register());

	public static final WoodenBlockList<CustomCogwheelBlock> LARGE_SHAFTLESS_WOODEN_COGWHEELS = new WoodenBlockList<>(wood ->
			REGISTRATE.block("large_shaftless_" + wood.asId() + "_cogwheel", p -> ShaftlessCogwheelBlock.large(p, wood))
					.transform(woodenCogwheelTransformer(true))
					.register());
	public static final WoodenBlockList<CustomCogwheelBlock> HALF_SHAFT_WOODEN_COGWHEELS = new WoodenBlockList<>(wood ->
			REGISTRATE.block("half_shaft_" + wood.asId() + "_cogwheel", p -> HalfShaftCogwheelBlock.small(p, wood))
					.transform(woodenCogwheelTransformer(false))
					.blockstate(new HalfShaftGenerator()::generate)
					.register());

	public static final WoodenBlockList<CustomCogwheelBlock> LARGE_HALF_SHAFT_WOODEN_COGWHEELS = new WoodenBlockList<>(wood ->
			REGISTRATE.block("large_half_shaft_" + wood.asId() + "_cogwheel", p -> HalfShaftCogwheelBlock.large(p, wood))
					.transform(woodenCogwheelTransformer(true))
					.blockstate(new HalfShaftGenerator()::generate)
					.register());

	// TODO: SPRUCE SHITE

	// Metal Cogwheels
	public static final MetalBlockList<CustomCogwheelBlock> METAL_COGWHEELS = new MetalBlockList<>(metal ->
			REGISTRATE.block(metal.asId() + "_cogwheel", p -> CustomCogwheelBlock.small(p, metal))
					.transform(metalCogwheelTransformer(false))
					.register());

	public static final MetalBlockList<CustomCogwheelBlock> LARGE_METAL_COGWHEELS = new MetalBlockList<>(metal ->
			REGISTRATE.block("large_" + metal.asId() + "_cogwheel", p -> CustomCogwheelBlock.large(p, metal))
					.transform(metalCogwheelTransformer(true))
					.register());

	public static final MetalBlockList<CustomCogwheelBlock> SHAFTLESS_METAL_COGWHEELS = new MetalBlockList<>(metal ->
			REGISTRATE.block("shaftless_" + metal.asId() + "_cogwheel", p -> ShaftlessCogwheelBlock.small(p, metal))
					.transform(metalCogwheelTransformer(false))
					.register());

	public static final MetalBlockList<CustomCogwheelBlock> LARGE_SHAFTLESS_METAL_COGWHEELS = new MetalBlockList<>(metal ->
			REGISTRATE.block("large_shaftless_" + metal.asId() + "_cogwheel", p -> ShaftlessCogwheelBlock.large(p, metal))
					.transform(metalCogwheelTransformer(true))
					.register());

	public static final MetalBlockList<CustomCogwheelBlock> HALF_SHAFT_METAL_COGWHEELS = new MetalBlockList<>(metal ->
			REGISTRATE.block("half_shaft_" + metal.asId() + "_cogwheel", p -> HalfShaftCogwheelBlock.small(p, metal))
					.transform(metalCogwheelTransformer(false))
					.blockstate(new HalfShaftGenerator()::generate)
					.register());

	public static final MetalBlockList<CustomCogwheelBlock> LARGE_HALF_SHAFT_METAL_COGWHEELS = new MetalBlockList<>(metal ->
			REGISTRATE.block("large_half_shaft_" + metal.asId() + "_cogwheel", p -> HalfShaftCogwheelBlock.large(p, metal))
					.transform(metalCogwheelTransformer(true))
					.blockstate(new HalfShaftGenerator()::generate)
					.register());

	public static final BlockEntry<CustomCogwheelBlock> SPRUCE_HALF_SHAFT_COGWHEEL =
			REGISTRATE.block("half_shaft_spruce_cogwheel", p -> HalfShaftCogwheelBlock.small(p, DefaultMaterial.SPRUCE))
					.transform(woodenCogwheelTransformer(false))
					.blockstate(new HalfShaftGenerator()::generate)
					.register();

	public static final BlockEntry<CustomCogwheelBlock> LARGE_SPRUCE_HALF_SHAFT_COGWHEEL =
			REGISTRATE.block("large_half_shaft_spruce_cogwheel", p -> HalfShaftCogwheelBlock.large(p, DefaultMaterial.SPRUCE))
					.transform(woodenCogwheelTransformer(true))
					.blockstate(new HalfShaftGenerator()::generate)
					.register();

	public static final BlockEntry<CustomCogwheelBlock> SPRUCE_SHAFTLESS_COGWHEEL =
			REGISTRATE.block("shaftless_spruce_cogwheel", p -> ShaftlessCogwheelBlock.small(p, DefaultMaterial.SPRUCE))
					.transform(woodenCogwheelTransformer(false))
					.register();

	public static final BlockEntry<CustomCogwheelBlock> LARGE_SPRUCE_SHAFTLESS_COGWHEEL =
			REGISTRATE.block("large_shaftless_spruce_cogwheel", p -> ShaftlessCogwheelBlock.large(p, DefaultMaterial.SPRUCE))
					.transform(woodenCogwheelTransformer(true))
					.register();


	public enum DefaultMaterial implements ICogwheelMaterial {
		SPRUCE(Items.SPRUCE_BUTTON, Items.SPRUCE_PLANKS);

		public final IngredientProvider ingredient;
		public final IngredientProvider smallIngredient;

		DefaultMaterial(Item smallIngredient, Item ingredient) {
			this.ingredient = new IngredientProvider(IngredientProvider.Namespace.COMMON, Ingredient.of(ingredient));
			this.smallIngredient = new IngredientProvider(IngredientProvider.Namespace.COMMON,
					Ingredient.of(smallIngredient));
		}

		@Override
		public @NotNull String asId() {
			return name().toLowerCase();
		}

		@Override
		public IngredientProvider getIngredient() {
			return this.ingredient;
		}

		@Override
		public IngredientProvider getSmallIngredient() {
			return this.smallIngredient;
		}
	}

	public static void init() {
		ExtendedCogwheels.LOGGER.info("Registering blocks for " + ExtendedCogwheels.NAME);
	}
}
