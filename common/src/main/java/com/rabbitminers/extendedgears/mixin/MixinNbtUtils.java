/*
 * Copyright 2022 QuiltMC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rabbitminers.extendedgears.mixin;

import com.mojang.datafixers.DataFixer;
import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.rabbitminers.extendedgears.base.data.data_fixer_api.DataFixesInternals;
import com.rabbitminers.extendedgears.base.data.data_fixers.CogwheelBlockDataFixer;
import com.rabbitminers.extendedgears.base.util.StringHelpers;
import com.rabbitminers.extendedgears.config.ExtendedCogwheelsConfig;
import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsDataFixers;
import com.simibubi.create.Create;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(NbtUtils.class)
public abstract class MixinNbtUtils {

    // Backup for if lazy DFU (or alike) is installed
    @Inject(method = "readBlockState", at = @At("HEAD"))
    private static void forceCogwheelUpdate(CompoundTag tag, CallbackInfoReturnable<BlockState> cir) {
        if (!ExtendedCogwheelsConfig.getDisableDatafixer() && tag.contains("Name", Tag.TAG_STRING)) {
            String original = tag.getString("Name");
            if (!original.endsWith("_cogwheel"))
                return;
            String block = ExtendedCogwheelsDataFixers.cogwheelFixer(original);
            if (block.equals(original))
                return;
            tag.putString("Name", block);
        }
    }
}