package com.rabbitminers.extendedgears.mixin.client;

import com.rabbitminers.extendedgears.base.data.data_fixer_api.DataFixesInternals;
import net.minecraft.client.HotbarManager;
import net.minecraft.nbt.CompoundTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(HotbarManager.class)
public class MixinHotbarStorage {
    @Inject(
            method = "save",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/nbt/NbtIo;write(Lnet/minecraft/nbt/CompoundTag;Ljava/io/File;)V"),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void addModDataVersions(CallbackInfo ci, CompoundTag compound) {
        DataFixesInternals.get().addModDataVersions(compound);
    }
}
