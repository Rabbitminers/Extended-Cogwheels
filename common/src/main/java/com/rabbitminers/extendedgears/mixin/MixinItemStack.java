package com.rabbitminers.extendedgears.mixin;

import com.rabbitminers.extendedgears.config.ExtendedCogwheelsConfig;
import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsDataFixers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemStack.class)
public class MixinItemStack {
    @ModifyVariable(method = "of", at = @At("HEAD"), argsOnly = true)
    private static CompoundTag modifyTag(CompoundTag value) {
        if (!ExtendedCogwheelsConfig.getDisableDatafixer() && value.contains("id", Tag.TAG_STRING)) {
            String item = value.getString("id");
            if (!item.endsWith("_cogwheel")) return value;
            String fixed = ExtendedCogwheelsDataFixers.cogwheelFixer(item);
            value.putString("id", fixed);
        }
        return value;
    }
}
