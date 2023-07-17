package com.rabbitminers.extendedgears.base.data.data_fixers;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import net.minecraft.util.datafix.fixes.NamedEntityFix;
import net.minecraft.util.datafix.fixes.References;
import org.jetbrains.annotations.NotNull;

public class CogwheelBlockEntityFix extends NamedEntityFix {
    public CogwheelBlockEntityFix(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType, "CogwheelBlockEntityFix", References.BLOCK_ENTITY, "extendedgears:customcogwheeltileentity");
    }

    protected @NotNull Typed<?> fix(Typed<?> inputType) {
        return inputType.update(DSL.remainderFinder(), dynamic ->
                dynamic.update("id", value -> value.createString("create:simple_kinetic")));
    }
}
