package com.rabbitminers.extendedgears.index;

import com.jozufozu.flywheel.core.PartialModel;
import com.rabbitminers.extendedgears.basecog.MetalCogWheel;
import com.rabbitminers.extendedgears.basecog.MetalCogWheelItem;
import com.rabbitminers.extendedgears.ExtendedGears;
import com.rabbitminers.extendedgears.config.ECConfig;
import com.simibubi.create.content.contraptions.relays.elementary.BracketedKineticBlockModel;
import com.simibubi.create.foundation.block.BlockStressDefaults;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.common.ForgeConfigSpec;

import static com.simibubi.create.AllTags.axeOrPickaxe;

public class AddonBlocks {
    private static final CreateRegistrate REGISTRATE = ExtendedGears.registrate().creativeModeTab(
            () -> ExtendedGears.itemGroup
    );

    public static BlockEntry<MetalCogWheel> largeCogWheelFactory(String name, PartialModel model, ForgeConfigSpec.IntValue maxRPMConfigValue, ForgeConfigSpec.IntValue maxSUConfigValue) {
        return REGISTRATE.block("large_" + name + "_cogwheel", p -> MetalCogWheel.large(p, model, maxRPMConfigValue, maxSUConfigValue))
                    .initialProperties(SharedProperties::stone)
                    .properties(p -> p.sound(SoundType.WOOD))
                    .properties(p -> p.color(MaterialColor.DIRT))
                    .transform(axeOrPickaxe())
                    .transform(BlockStressDefaults.setNoImpact())
                    .blockstate(BlockStateGen.axisBlockProvider(false))
                    .onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new))
                    .item(MetalCogWheelItem::new)
                    .build()
                    .register();
    }
    public static BlockEntry<MetalCogWheel> cogWheelFactory(String name, PartialModel model, ForgeConfigSpec.IntValue maxRPMConfigValue, ForgeConfigSpec.IntValue maxSUConfigValue) {
        return REGISTRATE.block(name + "_cogwheel", p -> MetalCogWheel.small(p, model, maxRPMConfigValue, maxSUConfigValue))
                    .initialProperties(SharedProperties::stone)
                    .properties(p -> p.sound(SoundType.WOOD))
                    .properties(p -> p.color(MaterialColor.DIRT))
                    .transform(BlockStressDefaults.setNoImpact())
                    .transform(axeOrPickaxe())
                    .blockstate(BlockStateGen.axisBlockProvider(false))
                    .onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new))
                    .item(MetalCogWheelItem::new)
                    .build()
                    .register();
    }
    public static final BlockEntry<MetalCogWheel> BIRCH_COGWHEEL = cogWheelFactory("birch", ECPartials.BIRCH_COGWHEEL, ECConfig.COGWHEEL_LIMITATIONS.maxBirchRPM, ECConfig.COGWHEEL_LIMITATIONS.maxBirchSU);
    public static final BlockEntry<MetalCogWheel> LARGE_BIRCH_COGWHEEL = largeCogWheelFactory("birch", ECPartials.LARGE_BIRCH_COGWHEEL, ECConfig.COGWHEEL_LIMITATIONS.maxBirchRPM, ECConfig.COGWHEEL_LIMITATIONS.maxBirchSU);
    public static final BlockEntry<MetalCogWheel> OAK_COGWHEEL = cogWheelFactory("oak", ECPartials.OAK_COGWHEEL, ECConfig.COGWHEEL_LIMITATIONS.maxOakRPM, ECConfig.COGWHEEL_LIMITATIONS.maxOakSU);
    public static final BlockEntry<MetalCogWheel> LARGE_OAK_COGWHEEL = largeCogWheelFactory("oak", ECPartials.LARGE_OAK_COGWHEEL, ECConfig.COGWHEEL_LIMITATIONS.maxOakRPM, ECConfig.COGWHEEL_LIMITATIONS.maxOakSU);
    public static final BlockEntry<MetalCogWheel> DARK_OAK_COGWHEEL = cogWheelFactory("dark_oak", ECPartials.DARK_OAK_COGWHEEL, ECConfig.COGWHEEL_LIMITATIONS.maxDarkOakRPM, ECConfig.COGWHEEL_LIMITATIONS.maxDarkOakSU);
    public static final BlockEntry<MetalCogWheel> LARGE_DARK_OAK_COGWHEEL = largeCogWheelFactory("dark_oak", ECPartials.LARGE_DARK_OAK_COGWHEEL, ECConfig.COGWHEEL_LIMITATIONS.maxDarkOakRPM, ECConfig.COGWHEEL_LIMITATIONS.maxDarkOakSU);
    public static final BlockEntry<MetalCogWheel> ACACIA_COGWHEEL = cogWheelFactory("acacia", ECPartials.ACACIA_COGWHEEL, ECConfig.COGWHEEL_LIMITATIONS.maxAcaciaRPM, ECConfig.COGWHEEL_LIMITATIONS.maxAcaciaSU);
    public static final BlockEntry<MetalCogWheel> LARGE_ACACIA_COGWHEEL = largeCogWheelFactory("acacia", ECPartials.LARGE_ACACIA_COGWHEEL, ECConfig.COGWHEEL_LIMITATIONS.maxAcaciaRPM, ECConfig.COGWHEEL_LIMITATIONS.maxAcaciaSU);
    public static final BlockEntry<MetalCogWheel> JUNGLE_COGWHEEL = cogWheelFactory("jungle", ECPartials.JUNGLE_COGWHEEL, ECConfig.COGWHEEL_LIMITATIONS.maxJungleRPM, ECConfig.COGWHEEL_LIMITATIONS.maxJungleSU);
    public static final BlockEntry<MetalCogWheel> LARGE_JUNGLE_COGWHEEL = largeCogWheelFactory("jungle", ECPartials.LARGE_JUNGLE_COGWHEEL, ECConfig.COGWHEEL_LIMITATIONS.maxJungleRPM, ECConfig.COGWHEEL_LIMITATIONS.maxJungleSU);
    public static final BlockEntry<MetalCogWheel> WARPED_COGWHEEL = cogWheelFactory("warped", ECPartials.WARPED_COGWHEEL, ECConfig.COGWHEEL_LIMITATIONS.maxWarpedRPM, ECConfig.COGWHEEL_LIMITATIONS.maxWarpedSU);
    public static final BlockEntry<MetalCogWheel> LARGE_WARPED_COGWHEEL = largeCogWheelFactory("warped", ECPartials.LARGE_WARPED_COGWHEEL, ECConfig.COGWHEEL_LIMITATIONS.maxWarpedRPM, ECConfig.COGWHEEL_LIMITATIONS.maxWarpedSU);
    public static final BlockEntry<MetalCogWheel> CRIMSON_COGWHEEL = cogWheelFactory("crimson", ECPartials.CRIMSON_COGWHEEL, ECConfig.COGWHEEL_LIMITATIONS.maxCrimsonRPM, ECConfig.COGWHEEL_LIMITATIONS.maxCrimsonSU);
    public static final BlockEntry<MetalCogWheel> LARGE_CRIMSON_COGWHEEL = largeCogWheelFactory("crimson", ECPartials.LARGE_CRIMSON_COGWHEEL, ECConfig.COGWHEEL_LIMITATIONS.maxCrimsonRPM, ECConfig.COGWHEEL_LIMITATIONS.maxCrimsonSU);
    public static final BlockEntry<MetalCogWheel> IRON_COGWHEEL = cogWheelFactory("iron", ECPartials.IRON_COGWHEEL, ECConfig.COGWHEEL_LIMITATIONS.maxIronRPM, ECConfig.COGWHEEL_LIMITATIONS.maxIronSU);
    public static final BlockEntry<MetalCogWheel> LARGE_IRON_COGWHEEL = largeCogWheelFactory("iron", ECPartials.LARGE_IRON_COGWHEEL, ECConfig.COGWHEEL_LIMITATIONS.maxIronRPM, ECConfig.COGWHEEL_LIMITATIONS.maxIronSU);
    public static final BlockEntry<MetalCogWheel> STEEL_COGWHEEL = cogWheelFactory("steel", ECPartials.STEEL_COGWHEEL, ECConfig.COGWHEEL_LIMITATIONS.maxSteelRPM, ECConfig.COGWHEEL_LIMITATIONS.maxSteelSU);
    public static final BlockEntry<MetalCogWheel> LARGE_STEEL_COGWHEEL = largeCogWheelFactory("steel", ECPartials.LARGE_STEEL_COGWHEEL, ECConfig.COGWHEEL_LIMITATIONS.maxSteelRPM, ECConfig.COGWHEEL_LIMITATIONS.maxSteelSU);
    public static final BlockEntry<MetalCogWheel> COPPER_COGWHEEL = cogWheelFactory("copper", ECPartials.COPPER_COGWHEEL, ECConfig.COGWHEEL_LIMITATIONS.maxCopperRPM, ECConfig.COGWHEEL_LIMITATIONS.maxCopperSU);
    public static final BlockEntry<MetalCogWheel> LARGE_COPPER_COGWHEEL = largeCogWheelFactory("copper", ECPartials.LARGE_COPPER_COGWHEEL, ECConfig.COGWHEEL_LIMITATIONS.maxCopperRPM, ECConfig.COGWHEEL_LIMITATIONS.maxCopperSU);


    public static void register() {}
}
