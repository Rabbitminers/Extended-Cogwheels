package com.rabbitminers.extendedgears.registry;

import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.rabbitminers.extendedgears.cogwheels.HalfShaftCogwheelBlock;
import com.rabbitminers.extendedgears.cogwheels.ShaftlessCogwheelBlock;
import com.rabbitminers.extendedgears.datagen.HalfShaftGenerator;
import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockModel;
import com.simibubi.create.content.kinetics.simpleRelays.CogWheelBlock;
import com.simibubi.create.content.kinetics.simpleRelays.CogwheelBlockItem;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MaterialColor;

import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;

public class ExtendedCogwheelsBlocks {
    public static <B extends CogWheelBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> cogwheelTransformer(boolean isLarge) {
        return b -> b.initialProperties(SharedProperties::stone)
                .properties(p -> p.sound(SoundType.WOOD))
                .properties(p -> p.color(MaterialColor.DIRT))
                .transform(BlockStressDefaults.setNoImpact())
                .transform(axeOrPickaxe())
                .blockstate(BlockStateGen.axisBlockProvider(false))
                .onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new))
                .item(CogwheelBlockItem::new)
                .tag(isLarge ? ExtendedCogwheelsTags.ExtendedCogwheelsItemTags.LARGE_COGWHEEL.tag
                        : ExtendedCogwheelsTags.ExtendedCogwheelsItemTags.SMALL_COGWHEEL.tag)
                .tag(ExtendedCogwheelsTags.ExtendedCogwheelsItemTags.COGWHEEL.tag)
                .build();
    }

    private static final CreateRegistrate REGISTRATE = ExtendedCogwheels.registrate();
    public static BlockEntry<HalfShaftCogwheelBlock> HALF_SHAFT_COGWHEEL =
            REGISTRATE.block("half_shaft_cogwheel", HalfShaftCogwheelBlock::small)
                    .blockstate(new HalfShaftGenerator()::generate)
                    .transform(cogwheelTransformer(false))
                    .register();

    public static BlockEntry<HalfShaftCogwheelBlock> LARGE_HALF_SHAFT_COGWHEEL =
            REGISTRATE.block("large_half_shaft_cogwheel", HalfShaftCogwheelBlock::large)
                    .transform(cogwheelTransformer(true))
                    .blockstate(new HalfShaftGenerator()::generate)
                    .register();
    public static BlockEntry<ShaftlessCogwheelBlock> SHAFTLESS_COGWHEEL =
            REGISTRATE.block("shaftless_cogwheel", ShaftlessCogwheelBlock::small)
                    .transform(cogwheelTransformer(false))
                    .register();
    public static BlockEntry<ShaftlessCogwheelBlock> LARGE_SHAFTLESS_COGWHEEL =
            REGISTRATE.block("large_shaftless_cogwheel", ShaftlessCogwheelBlock::large)
                    .transform(cogwheelTransformer(true))
                    .register();

    public static void init() {

    }
}
