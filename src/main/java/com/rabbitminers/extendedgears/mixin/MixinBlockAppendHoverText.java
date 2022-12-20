package com.rabbitminers.extendedgears.mixin;

import com.rabbitminers.extendedgears.basecog.ICustomCogWheel;
import com.rabbitminers.extendedgears.config.ECConfig;
import com.simibubi.create.content.contraptions.relays.elementary.CogwheelBlockItem;
import com.simibubi.create.foundation.config.AllConfigs;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Block.class)
public class MixinBlockAppendHoverText {
    @Inject(at = @At("HEAD"), method = "appendHoverText")
    public void appendHoverText(ItemStack stack, BlockGetter blockGetter, List<Component> components, TooltipFlag tooltipFlag, CallbackInfo ci) {
        if (stack.getItem() instanceof CogwheelBlockItem cogwheelBlockItem) {
            Block block = cogwheelBlockItem.getBlock();
            int maxRPM = block instanceof ICustomCogWheel customCogWheel
                    ? customCogWheel.getMaxRPM()
                    : ECConfig.COGWHEEL_LIMITATIONS.maxSpruceRPM.get();
            if (maxRPM != AllConfigs.SERVER.kinetics.maxRotationSpeed.get()) {
                components.add(Component.literal("Maximum Speed: ").withStyle(ChatFormatting.DARK_GRAY).append(Component.literal(maxRPM + " RPM").withStyle(ChatFormatting.GRAY)));
            }
            int maxSU = block instanceof ICustomCogWheel customCogWheel
                    ? customCogWheel.getMaxSU()
                    : ECConfig.COGWHEEL_LIMITATIONS.maxSpruceSU.get();
            if (maxSU != Integer.MAX_VALUE) {
                components.add(Component.literal("Maximum Load: ").withStyle(ChatFormatting.DARK_GRAY).append(Component.literal(maxSU + "su").withStyle(ChatFormatting.GRAY)));
            }
        }
    }

}
