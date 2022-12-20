package com.rabbitminers.extendedgears.basecog;

import com.jozufozu.flywheel.core.PartialModel;
import com.rabbitminers.extendedgears.config.ECConfig;
import com.rabbitminers.extendedgears.index.AddonTileEntities;
import com.rabbitminers.extendedgears.tileentities.CogWheelKineticTileEntity;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import com.simibubi.create.content.contraptions.relays.elementary.CogWheelBlock;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.ForgeConfigSpec;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class MetalCogWheel extends CogWheelBlock implements ICustomCogWheel {
    public PartialModel model;
    public ForgeConfigSpec.IntValue maxRPM;
    public ForgeConfigSpec.IntValue maxSU;

    protected MetalCogWheel(boolean large, Properties properties, PartialModel model, ForgeConfigSpec.IntValue maxRPM,
            ForgeConfigSpec.IntValue maxSU) {
        super(large, properties);
        this.model = model;
        this.maxRPM = maxRPM;
        this.maxSU = maxSU;
    }
    public static MetalCogWheel small(Properties properties, PartialModel model, ForgeConfigSpec.IntValue maxRPM,
          ForgeConfigSpec.IntValue maxSU) {
        return new MetalCogWheel(false, properties, model, maxRPM, maxSU);
    }

    public static MetalCogWheel large(Properties properties, PartialModel model, ForgeConfigSpec.IntValue maxRPM,
            ForgeConfigSpec.IntValue maxSU) {
        return new MetalCogWheel(true, properties, model, maxRPM, maxSU);
    }
    @Override
    public PartialModel getPartialModelType() {
        return this.model;
    }

    @Override
    public int getMaxRPM() {
        return maxRPM.get();
    }
    @Override
    public int getMaxSU() {
        return maxSU.get();
    }

    @Override
    public BlockEntityType<? extends KineticTileEntity> getTileEntityType() {
        return AddonTileEntities.BRACKETED_KINETIC.get();
    }
    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult ray) {
        // TODO: Allow for flywheel types to be encased
        return InteractionResult.PASS;
    }
}
