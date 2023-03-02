package com.rabbitminers.extendedgears.registry;

import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.rabbitminers.extendedgears.base.datatypes.MetalBlockList;
import com.rabbitminers.extendedgears.base.datatypes.WoodenBlockList;
import com.rabbitminers.extendedgears.cogwheels.CustomCogwheelBlock;
import com.simibubi.create.content.contraptions.relays.elementary.BracketedKineticBlockModel;
import com.simibubi.create.content.contraptions.relays.elementary.CogwheelBlockItem;
import com.simibubi.create.foundation.block.BlockStressDefaults;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.simibubi.create.foundation.data.TagGen;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MaterialColor;

public class ExtendedCogwheelsBlocks {
	public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(ExtendedCogwheels.MOD_ID);

	public static <B extends CustomCogwheelBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> commonCogwheelTransformer() {
		return b -> b.blockstate(BlockStateGen.axisBlockProvider(false))
				.onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new))
				.transform(BlockStressDefaults.setNoImpact())
				.item(CogwheelBlockItem::new)
				.build();
	}

	public static <B extends CustomCogwheelBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> woodenCogwheelTransformer() {
		return b -> b.initialProperties(SharedProperties::wooden)
				.properties(p -> p.sound(SoundType.WOOD))
				.properties(p -> p.color(MaterialColor.DIRT))
				.transform(TagGen.axeOrPickaxe())
				.transform(commonCogwheelTransformer());
	}

	public static <B extends CustomCogwheelBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> metalCogwheelTransformer() {
		return b -> b.initialProperties(SharedProperties::softMetal)
				.properties(p -> p.sound(SoundType.METAL))
				.properties(p -> p.color(MaterialColor.METAL))
				.transform(TagGen.pickaxeOnly())
				.transform(commonCogwheelTransformer());
	}

	public static final WoodenBlockList<CustomCogwheelBlock> WOODEN_COGWHEELS = new WoodenBlockList<>(wood ->
			REGISTRATE.block(wood.asId() + "_cogwheel", p -> CustomCogwheelBlock.small(p,
					ExtendedCogwheelsPartials.WOODEN_COGWHEELS.get(wood)))
							.transform(woodenCogwheelTransformer()).register()
	);

	public static final WoodenBlockList<CustomCogwheelBlock> LARGE_WOODEN_COGWHEELS = new WoodenBlockList<>(wood ->
			REGISTRATE.block("large_" + wood.asId() + "_cogwheel", p -> CustomCogwheelBlock.large(p,
					ExtendedCogwheelsPartials.LARGE_WOODEN_COGWHEELS.get(wood)))
						.transform(woodenCogwheelTransformer()).register());

	public static final MetalBlockList<CustomCogwheelBlock> METAL_COGWHEELS = new MetalBlockList<>(metal ->
			REGISTRATE.block(metal.asId() + "_cogwheel", p -> CustomCogwheelBlock.small(p,
					ExtendedCogwheelsPartials.METAL_COGWHEELS.get(metal)))
							.transform(metalCogwheelTransformer()).register()
	);

	public static final MetalBlockList<CustomCogwheelBlock> LARGE_METAL_COGWHEELS = new MetalBlockList<>(metal ->
			REGISTRATE.block("large_" + metal.asId() + "_cogwheel", p -> CustomCogwheelBlock.large(p,
					ExtendedCogwheelsPartials.LARGE_METAL_COGWHEELS.get(metal)))
							.transform(metalCogwheelTransformer()).register()
	);

	public static void init() {
		ExtendedCogwheels.LOGGER.info("Registering blocks for " + ExtendedCogwheels.NAME);
	}
}
