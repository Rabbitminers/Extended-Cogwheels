package com.rabbitminers.extendedgears.base.data.data_fixers;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.serialization.Dynamic;
import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.rabbitminers.extendedgears.base.util.StringHelpers;
import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsBlocks;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.Create;
import com.simibubi.create.content.kinetics.simpleRelays.CogWheelBlock;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.util.StringUtil;
import net.minecraft.util.datafix.fixes.*;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CogwheelBlockDataFixer extends DataFix {

    private final String name;

    public CogwheelBlockDataFixer(Schema outputSchema, String name) {
        super(outputSchema, false);
        this.name = name;
    }

    @Override
    public TypeRewriteRule makeRule() {
        return TypeRewriteRule.seq(
            this.fixTypeEverywhereTyped(this.name + " for block_state", this.getInputSchema().getType(References.BLOCK_STATE), typed -> typed.update(DSL.remainderFinder(), dynamic -> {
                Optional<String> optional = dynamic.get("Name").asString().result();
                String fixBlock = fixBlock(optional);
                if (fixBlock != null) {
                    dynamic = dynamic.set("Name", dynamic.createString(fixBlock));
                    return dynamic;
                }
                return dynamic;
            }
        )));
    }

    @Nullable
    public static String fixBlock(Optional<String> optional) {
        if (optional.isEmpty()) return null;
        String original = optional.get();
        String size = original.contains("large_") ? ":large_" : ":";
        if (isValidCogwheel(original))
            return null;
        if (StringHelpers.containsAll(original, "shaftless_cogwheel"))
            return ExtendedCogwheels.MOD_ID + size + "shaftless_cogwheel";
        if (StringHelpers.containsAll(original, "half_shaft","cogwheel"))
            return ExtendedCogwheels.MOD_ID + size + "half_shaft_cogwheel";
        if (original.contains("_cogwheel"))
            return Create.ID + size + "cogwheel";
        return null;
    }

    public static boolean isValidCogwheel(String block) {
        BlockEntry<?>[] validCogwheels = {
                AllBlocks.COGWHEEL,
                AllBlocks.LARGE_COGWHEEL,
                ExtendedCogwheelsBlocks.SHAFTLESS_COGWHEEL,
                ExtendedCogwheelsBlocks.LARGE_SHAFTLESS_COGWHEEL,
                ExtendedCogwheelsBlocks.HALF_SHAFT_COGWHEEL,
                ExtendedCogwheelsBlocks.LARGE_HALF_SHAFT_COGWHEEL
        };

        for (BlockEntry<?> cogwheel : validCogwheels)
            if (block.equals(cogwheel.getId().toString()))
                return true;
        return false;
    }
}
