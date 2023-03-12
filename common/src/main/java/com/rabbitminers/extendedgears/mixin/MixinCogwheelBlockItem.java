package com.rabbitminers.extendedgears.mixin;

import com.rabbitminers.extendedgears.cogwheels.ICustomCogwheel;
import com.rabbitminers.extendedgears.config.ExtendedCogwheelsConfig;
import com.simibubi.create.content.contraptions.relays.elementary.CogwheelBlockItem;
import com.simibubi.create.content.contraptions.relays.elementary.ICogWheel;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;

@Mixin(CogwheelBlockItem.class)
public class MixinCogwheelBlockItem extends BlockItem {
    public MixinCogwheelBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    private int getStressLimit(Block block) {
        Integer stressLimit = null;
        if (block instanceof ICustomCogwheel cogwheel)
            stressLimit = ExtendedCogwheelsConfig.getStressLimitByMaterial(cogwheel.getMaterial());
        return stressLimit == null ? ExtendedCogwheelsConfig.SPRUCE_COGWHEEL_STRESS_LIMITS.get()
                : stressLimit;
    }

    private int getRotationalSpeedLimit(Block block) {
        Integer stressLimit = null;
        if (block instanceof ICustomCogwheel cogwheel)
            stressLimit = ExtendedCogwheelsConfig.getRotationLimitByMaterial(cogwheel.getMaterial());
        return stressLimit == null ? ExtendedCogwheelsConfig.SPRUCE_COGWHEEL_ROTATION_LIMITS.get()
                : stressLimit;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag isAdvanced) {
        if (!(stack.getItem() instanceof BlockItem blockItem))
            return;
        Block type = blockItem.getBlock();
        if (!(type instanceof ICogWheel))
            return;

        if (ExtendedCogwheelsConfig.APPLY_ROTATION_LIMITS.get()) {
            int maxSpeed = getRotationalSpeedLimit(type);
            components.add(new TextComponent("Maximum Speed: ").withStyle(ChatFormatting.DARK_GRAY)
                    .append(new TextComponent(maxSpeed + " RPM").withStyle(ChatFormatting.GRAY)));
        }
        if (ExtendedCogwheelsConfig.APPLY_STRESS_LIMITS.get()) {
            int maxLoad = getStressLimit(type);
            components.add(new TextComponent("Maximum Load: ").withStyle(ChatFormatting.DARK_GRAY)
                    .append(new TextComponent(maxLoad == Integer.MAX_VALUE ? "unlimited" : maxLoad + "su").withStyle(ChatFormatting.GRAY)));
        }

        super.appendHoverText(stack, level, components, isAdvanced);
    }
}
