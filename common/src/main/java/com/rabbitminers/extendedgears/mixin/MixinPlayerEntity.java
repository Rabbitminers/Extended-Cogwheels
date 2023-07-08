package com.rabbitminers.extendedgears.mixin;

import com.rabbitminers.extendedgears.base.data.data_fixer_api.DataFixesInternals;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.nbt.CompoundTag;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class MixinPlayerEntity {
    @Inject(method = "addAdditionalSaveData", at = @At("RETURN"))
    public void addModDataVersions(CompoundTag compound, CallbackInfo ci) {
        DataFixesInternals.get().addModDataVersions(compound);
    }
}