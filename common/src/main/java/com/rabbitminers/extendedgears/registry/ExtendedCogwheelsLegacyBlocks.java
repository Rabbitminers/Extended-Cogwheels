package com.rabbitminers.extendedgears.registry;

import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.rabbitminers.extendedgears.base.data.ICogwheelMaterial;
import com.rabbitminers.extendedgears.base.datatypes.IngredientProvider;
import com.rabbitminers.extendedgears.base.datatypes.MetalBlockList;
import com.rabbitminers.extendedgears.base.datatypes.WoodenBlockList;
import com.rabbitminers.extendedgears.cogwheels.legacy.CustomCogwheelBlock;
import com.rabbitminers.extendedgears.cogwheels.legacy.LegacyHalfShaftCogwheelBlock;
import com.rabbitminers.extendedgears.cogwheels.legacy.LegacyShaftlessCogwheelBlock;
import com.rabbitminers.extendedgears.datagen.HalfShaftGenerator;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockModel;
import com.simibubi.create.content.kinetics.simpleRelays.CogwheelBlockItem;
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
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MaterialColor;
import org.jetbrains.annotations.NotNull;

@Deprecated
public class ExtendedCogwheelsLegacyBlocks {
	public static final CreateRegistrate REGISTRATE = ExtendedCogwheels.registrate();

	public static <B extends CustomCogwheelBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> cogwheelItemTransformer(boolean isLarge, TagKey<Item> materialType) {
		return b -> b
				.item(CogwheelBlockItem::new)
				.tag(materialType)
				.tag(isLarge ? ExtendedCogwheelsTags.LARGE_COGWHEEL : ExtendedCogwheelsTags.SMALL_COGWHEEL)
				.tag(ExtendedCogwheelsTags.COGWHEEL)
				.build();
	}

	public static <B extends CustomCogwheelBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> commonCogwheelTransformer(boolean isLarge, TagKey<Item> materialType) {
		return b -> b.blockstate(BlockStateGen.axisBlockProvider(false))
				.transform(BlockStressDefaults.setNoImpact())
				.transform(cogwheelItemTransformer(isLarge, materialType));
	}

	public static <B extends CustomCogwheelBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> woodenCogwheelTransformer(boolean isLarge) {
		return b -> b.initialProperties(SharedProperties::wooden)
				.properties(p -> p.sound(SoundType.WOOD))
				.properties(p -> p.color(MaterialColor.DIRT))
				.transform(TagGen.axeOrPickaxe());
	}

	public static <B extends CustomCogwheelBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> metalCogwheelTransformer(boolean isLarge) {
		return b -> b.initialProperties(SharedProperties::softMetal)
				.properties(p -> p.sound(SoundType.METAL))
				.properties(p -> p.color(MaterialColor.METAL))
				.transform(TagGen.pickaxeOnly());
	}

	public static void init() {
		ExtendedCogwheels.LOGGER.info("Registering blocks for " + ExtendedCogwheels.NAME);
	}
}
