package com.rabbitminers.extendedgears.mixin;

import com.rabbitminers.extendedgears.cogwheels.ICustomCogwheel;
import com.rabbitminers.extendedgears.config.ExtendedCogwheelsConfig;
import com.simibubi.create.content.contraptions.relays.elementary.BracketedKineticTileEntity;
import com.simibubi.create.content.contraptions.relays.elementary.ICogWheel;
import com.simibubi.create.content.contraptions.relays.elementary.SimpleKineticTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BracketedKineticTileEntity.class)
public class MixinBracketedKineticTileEntity extends SimpleKineticTileEntity {
    public MixinBracketedKineticTileEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
    }

    private int getStressLimit() {
        Integer stressLimit = null;
        if (getBlockState().getBlock() instanceof ICustomCogwheel cogwheel)
            stressLimit = ExtendedCogwheelsConfig.getStressLimitByMaterial(cogwheel.getMaterial());
        return stressLimit == null ? ExtendedCogwheelsConfig.SPRUCE_COGWHEEL_STRESS_LIMITS.get()
                : stressLimit;
    }

    private int getRotationalSpeedLimit() {
        Integer stressLimit = null;
        if (getBlockState().getBlock() instanceof ICustomCogwheel cogwheel)
            stressLimit = ExtendedCogwheelsConfig.getRotationLimitByMaterial(cogwheel.getMaterial());
        return stressLimit == null ? ExtendedCogwheelsConfig.SPRUCE_COGWHEEL_ROTATION_LIMITS.get()
                : stressLimit;
    }

    public void tick() {
        super.tick();
        if (!(this.getBlockState().getBlock() instanceof ICogWheel)
                || level == null || level.isClientSide)
            return;
        boolean shouldBreak = (ExtendedCogwheelsConfig.APPLY_ROTATION_LIMITS.get()
                && Math.abs(speed) > getRotationalSpeedLimit())
                || (ExtendedCogwheelsConfig.APPLY_STRESS_LIMITS.get()
                && Math.abs(capacity) > getStressLimit());
        if (shouldBreak)
            level.destroyBlock(worldPosition, true);
    }
}
