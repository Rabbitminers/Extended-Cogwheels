/*
 * Copyright 2022 QuiltMC
 * Modified by Rabbitminers & Railways team
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
package com.rabbitminers.extendedgears.base.data.data_fixer_api;

import com.mojang.datafixers.DataFixer;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.templates.TypeTemplate;
import com.mojang.serialization.Dynamic;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.util.datafix.DataFixTypes;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.util.Map;
import java.util.function.Supplier;

@ApiStatus.Internal
public final class DataFixesInternalsImpl extends DataFixesInternals {
    private final @NotNull Schema latestVanillaSchema;

    private DataFixerEntry dataFixer;

    public DataFixesInternalsImpl(@NotNull Schema latestVanillaSchema) {
        this.latestVanillaSchema = latestVanillaSchema;

        this.dataFixer = null;
    }

    @Override
    public void registerFixer(@Range(from = 0, to = Integer.MAX_VALUE) int currentVersion,
                              @NotNull DataFixer dataFixer) {
        if (this.dataFixer != null) {
            throw new IllegalArgumentException("Extended Cogwheels already has a registered data fixer");
        }

        this.dataFixer = new DataFixerEntry(dataFixer, currentVersion);
    }

    @Override
    public @Nullable DataFixerEntry getFixerEntry() {
        return dataFixer;
    }

    @Override
    public @NotNull Schema createBaseSchema() {
        return new Schema(0, this.latestVanillaSchema) {
            @Override
            public Map<String, Supplier<TypeTemplate>> registerBlockEntities(Schema schema) {
                Map<String, Supplier<TypeTemplate>> map = super.registerBlockEntities(schema);
                super.registerSimple(map, "extendedgears:customcogwheeltileentity");
                return map;
            }
        };
    }

    @Override
    public @NotNull CompoundTag updateWithAllFixers(@NotNull DataFixTypes dataFixTypes, @NotNull CompoundTag compound) {
        var current = new Dynamic<>(NbtOps.INSTANCE, compound);

        if (dataFixer != null) {
            int modDataVersion = DataFixesInternals.getModDataVersion(compound);
            current = dataFixer.dataFixer()
                .update(dataFixTypes.getType(),
                    current,
                    modDataVersion, dataFixer.currentVersion());
        }

        return (CompoundTag) current.getValue();
    }

    @Override
    public @NotNull CompoundTag addModDataVersions(@NotNull CompoundTag compound) {
        if (dataFixer != null)
            compound.putInt("ExtendedCogwheels_DataVersion1", dataFixer.currentVersion());
        return compound;
    }
}