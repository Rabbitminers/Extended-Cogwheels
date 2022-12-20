package com.rabbitminers.extendedgears.tileentities;

import com.rabbitminers.extendedgears.basecog.MetalCogWheel;
import com.rabbitminers.extendedgears.config.ECConfig;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllTileEntities;
import com.simibubi.create.content.contraptions.KineticNetwork;
import com.simibubi.create.content.contraptions.relays.elementary.BracketedKineticTileEntity;
import com.simibubi.create.foundation.config.AllConfigs;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class CogWheelKineticTileEntity extends BracketedKineticTileEntity {
    public CogWheelKineticTileEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void tick() {
        super.tick();
        int defaultMaxRPM = AllConfigs.SERVER.kinetics.maxRotationSpeed.get();
        int maxSU = Integer.MAX_VALUE;
        KineticNetwork network = getOrCreateNetwork();
        if (network == null) return;
        float currentStress = network.calculateCapacity();
        if (level.getBlockState(getBlockPos()).getBlock() instanceof MetalCogWheel cogWheel) {
            defaultMaxRPM = cogWheel.getMaxRPM();
            maxSU = cogWheel.getMaxSU();
        }
        if (level != null && !level.isClientSide && Math.abs(getSpeed()) > defaultMaxRPM
                || currentStress > maxSU) {
            level.destroyBlock(getBlockPos(), true);
        }
    }
}
