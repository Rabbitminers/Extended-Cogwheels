package com.rabbitminers.extendedgears.registry;

import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.rabbitminers.extendedgears.base.datatypes.MetalBlockList;
import com.rabbitminers.extendedgears.base.datatypes.WoodenBlockList;
import com.rabbitminers.extendedgears.cogwheels.CustomCogwheelBlock;
import com.rabbitminers.extendedgears.cogwheels.HalfShaftCogwheelBlock;
import com.rabbitminers.extendedgears.cogwheels.ShaftlessCogwheelBlock;
import com.rabbitminers.extendedgears.datagen.HalfShaftGenerator;
import com.simibubi.create.AllBlockPartials;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.contraptions.relays.elementary.BracketedKineticBlockModel;
import com.simibubi.create.content.contraptions.relays.elementary.CogwheelBlockItem;
import com.simibubi.create.foundation.block.BlockStressDefaults;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.simibubi.create.foundation.data.TagGen;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MaterialColor;

public class ExtendedCogwheelsBlocks {
	public static final CreateRegistrate REGISTRATE = ExtendedCogwheels.registrate();
	public static <B extends CustomCogwheelBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> commonCogwheelTransformer() {
		return b -> b.blockstate(BlockStateGen.axisBlockProvider(false))
				.onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new))
				.transform(BlockStressDefaults.setNoImpact())
				.tag(ExtendedCogwheelsTags.ExtendedCogwheelsBlockTags.COGWHEEL.tag)
				.item(CogwheelBlockItem::new)
				.build();
	}

	public static <B extends CustomCogwheelBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> woodenCogwheelTransformer() {
		return b -> b.initialProperties(SharedProperties::wooden)
				.properties(p -> p.sound(SoundType.WOOD))
				.properties(p -> p.color(MaterialColor.DIRT))
				.transform(TagGen.axeOrPickaxe())
				.tag(ExtendedCogwheelsTags.ExtendedCogwheelsBlockTags.WOODEN_COGWHEEL.tag)
				.transform(commonCogwheelTransformer());
	}

	public static <B extends CustomCogwheelBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> metalCogwheelTransformer() {
		return b -> b.initialProperties(SharedProperties::softMetal)
				.properties(p -> p.sound(SoundType.METAL))
				.properties(p -> p.color(MaterialColor.METAL))
				.transform(TagGen.pickaxeOnly())
				.tag(ExtendedCogwheelsTags.ExtendedCogwheelsBlockTags.METAL_COGWHEEL.tag)
				.transform(commonCogwheelTransformer());
	}

	// Wooden Cogwheels

	public static final WoodenBlockList<CustomCogwheelBlock> WOODEN_COGWHEELS = new WoodenBlockList<>(wood ->
			REGISTRATE.block(wood.asId() + "_cogwheel", p -> CustomCogwheelBlock.small(p,
					ExtendedCogwheelsPartials.WOODEN_COGWHEELS.get(wood).small()))
					.transform(woodenCogwheelTransformer())
					.tag(ExtendedCogwheelsTags.ExtendedCogwheelsBlockTags.SMALL_COGWHEEL.tag)
					.register());

	public static final WoodenBlockList<CustomCogwheelBlock> LARGE_WOODEN_COGWHEELS = new WoodenBlockList<>(wood ->
			REGISTRATE.block("large_" + wood.asId() + "_cogwheel", p -> CustomCogwheelBlock.large(p,
					ExtendedCogwheelsPartials.WOODEN_COGWHEELS.get(wood).large()))
					.transform(woodenCogwheelTransformer())
					.tag(ExtendedCogwheelsTags.ExtendedCogwheelsBlockTags.LARGE_COGWHEEL.tag)
					.register());

	public static final WoodenBlockList<CustomCogwheelBlock> SHAFTLESS_WOODEN_COGWHEELS = new WoodenBlockList<>(wood ->
			REGISTRATE.block("shaftless_" + wood.asId() + "_cogwheel", p -> ShaftlessCogwheelBlock.small(p,
					ExtendedCogwheelsPartials.SHAFTLESS_WOODEN_COGWHEELS.get(wood).small()))
					.transform(woodenCogwheelTransformer())
					.tag(ExtendedCogwheelsTags.ExtendedCogwheelsBlockTags.SMALL_COGWHEEL.tag)
					.register());

	public static final WoodenBlockList<CustomCogwheelBlock> LARGE_SHAFTLESS_WOODEN_COGWHEELS = new WoodenBlockList<>(wood ->
			REGISTRATE.block("large_shaftless_" + wood.asId() + "_cogwheel", p -> ShaftlessCogwheelBlock.large(p,
							ExtendedCogwheelsPartials.SHAFTLESS_WOODEN_COGWHEELS.get(wood).large()))
					.transform(woodenCogwheelTransformer())
					.tag(ExtendedCogwheelsTags.ExtendedCogwheelsBlockTags.LARGE_COGWHEEL.tag)
					.register());
	public static final WoodenBlockList<CustomCogwheelBlock> HALF_SHAFT_WOODEN_COGWHEELS = new WoodenBlockList<>(wood ->
			REGISTRATE.block("half_shaft_" + wood.asId() + "_cogwheel", p -> HalfShaftCogwheelBlock.small(p,
							ExtendedCogwheelsPartials.HALF_SHAFT_WOODEN_COGWHEELS.get(wood).small()))
					.transform(woodenCogwheelTransformer())
					.blockstate(new HalfShaftGenerator()::generate)
					.tag(ExtendedCogwheelsTags.ExtendedCogwheelsBlockTags.SMALL_COGWHEEL.tag)
					.register());

	public static final WoodenBlockList<CustomCogwheelBlock> LARGE_HALF_SHAFT_WOODEN_COGWHEELS = new WoodenBlockList<>(wood ->
			REGISTRATE.block("large_half_shaft_" + wood.asId() + "_cogwheel", p -> HalfShaftCogwheelBlock.large(p,
							ExtendedCogwheelsPartials.HALF_SHAFT_WOODEN_COGWHEELS.get(wood).large()))
					.transform(woodenCogwheelTransformer())
					.blockstate(new HalfShaftGenerator()::generate)
					.tag(ExtendedCogwheelsTags.ExtendedCogwheelsBlockTags.LARGE_COGWHEEL.tag)
					.register());

	// TODO: SPRUCE SHITE

	// Metal Cogwheels
	public static final MetalBlockList<CustomCogwheelBlock> METAL_COGWHEELS = new MetalBlockList<>(metal ->
			REGISTRATE.block(metal.asId() + "_cogwheel", p -> CustomCogwheelBlock.small(p,
					ExtendedCogwheelsPartials.METAL_COGWHEELS.get(metal).small()))
					.transform(metalCogwheelTransformer())
					.tag(ExtendedCogwheelsTags.ExtendedCogwheelsBlockTags.SMALL_COGWHEEL.tag)
					.register());

	public static final MetalBlockList<CustomCogwheelBlock> LARGE_METAL_COGWHEELS = new MetalBlockList<>(metal ->
			REGISTRATE.block("large_" + metal.asId() + "_cogwheel", p -> CustomCogwheelBlock.large(p,
					ExtendedCogwheelsPartials.METAL_COGWHEELS.get(metal).large()))
					.transform(metalCogwheelTransformer())
					.tag(ExtendedCogwheelsTags.ExtendedCogwheelsBlockTags.LARGE_COGWHEEL.tag)
					.register());

	public static final MetalBlockList<CustomCogwheelBlock> SHAFTLESS_METAL_COGWHEELS = new MetalBlockList<>(metal ->
			REGISTRATE.block("shaftless_" + metal.asId() + "_cogwheel", p -> ShaftlessCogwheelBlock.small(p,
							ExtendedCogwheelsPartials.SHAFTLESS_METAL_COGWHEELS.get(metal).small()))
					.transform(metalCogwheelTransformer())
					.tag(ExtendedCogwheelsTags.ExtendedCogwheelsBlockTags.SMALL_COGWHEEL.tag)
					.register());

	public static final MetalBlockList<CustomCogwheelBlock> LARGE_SHAFTLESS_METAL_COGWHEELS = new MetalBlockList<>(metal ->
			REGISTRATE.block("large_shaftless_" + metal.asId() + "_cogwheel", p -> ShaftlessCogwheelBlock.large(p,
							ExtendedCogwheelsPartials.SHAFTLESS_METAL_COGWHEELS.get(metal).large()))
					.transform(metalCogwheelTransformer())
					.tag(ExtendedCogwheelsTags.ExtendedCogwheelsBlockTags.LARGE_COGWHEEL.tag)
					.register());

	public static final MetalBlockList<CustomCogwheelBlock> HALF_SHAFT_METAL_COGWHEELS = new MetalBlockList<>(metal ->
			REGISTRATE.block("half_shaft_" + metal.asId() + "_cogwheel", p -> HalfShaftCogwheelBlock.small(p,
							ExtendedCogwheelsPartials.HALF_SHAFT_METAL_COGWHEELS.get(metal).small()))
					.transform(metalCogwheelTransformer())
					.blockstate(new HalfShaftGenerator()::generate)
					.tag(ExtendedCogwheelsTags.ExtendedCogwheelsBlockTags.SMALL_COGWHEEL.tag)
					.register());

	public static final MetalBlockList<CustomCogwheelBlock> LARGE_HALF_SHAFT_METAL_COGWHEELS = new MetalBlockList<>(metal ->
			REGISTRATE.block("large_half_shaft_" + metal.asId() + "_cogwheel", p -> HalfShaftCogwheelBlock.large(p,
							ExtendedCogwheelsPartials.HALF_SHAFT_METAL_COGWHEELS.get(metal).large()))
					.transform(metalCogwheelTransformer())
					.blockstate(new HalfShaftGenerator()::generate)
					.tag(ExtendedCogwheelsTags.ExtendedCogwheelsBlockTags.LARGE_COGWHEEL.tag)
					.register());

	public static final BlockEntry<CustomCogwheelBlock> SPRUCE_HALF_SHAFT_COGWHEEL =
			REGISTRATE.block("half_shaft_spruce_cogwheel", p -> HalfShaftCogwheelBlock.small(p,
					AllBlockPartials.SHAFTLESS_COGWHEEL))
					.transform(woodenCogwheelTransformer())
					.blockstate(new HalfShaftGenerator()::generate)
					.tag(ExtendedCogwheelsTags.ExtendedCogwheelsBlockTags.SMALL_COGWHEEL.tag)
					.register();

	public static final BlockEntry<CustomCogwheelBlock> LARGE_SPRUCE_HALF_SHAFT_COGWHEEL =
			REGISTRATE.block("large_half_shaft_spruce_cogwheel", p -> HalfShaftCogwheelBlock.large(p,
							AllBlockPartials.SHAFTLESS_COGWHEEL))
					.transform(woodenCogwheelTransformer())
					.blockstate(new HalfShaftGenerator()::generate)
					.tag(ExtendedCogwheelsTags.ExtendedCogwheelsBlockTags.LARGE_COGWHEEL.tag)
					.register();

	public static final BlockEntry<CustomCogwheelBlock> SPRUCE_SHAFTLESS_COGWHEEL =
			REGISTRATE.block("shaftless_spruce_cogwheel", p -> ShaftlessCogwheelBlock.small(p,
							AllBlockPartials.SHAFTLESS_COGWHEEL))
					.transform(woodenCogwheelTransformer())
					.blockstate(new HalfShaftGenerator()::generate)
					.tag(ExtendedCogwheelsTags.ExtendedCogwheelsBlockTags.SMALL_COGWHEEL.tag)
					.register();

	public static final BlockEntry<CustomCogwheelBlock> LARGE_SPRUCE_SHAFTLESS_COGWHEEL =
			REGISTRATE.block("large_shaftless_spruce_cogwheel", p -> ShaftlessCogwheelBlock.large(p,
							AllBlockPartials.SHAFTLESS_COGWHEEL))
					.transform(woodenCogwheelTransformer())
					.blockstate(new HalfShaftGenerator()::generate)
					.tag(ExtendedCogwheelsTags.ExtendedCogwheelsBlockTags.LARGE_COGWHEEL.tag)
					.register();

	public static void init() {
		ExtendedCogwheels.LOGGER.info("Registering blocks for " + ExtendedCogwheels.NAME);
	}
}
