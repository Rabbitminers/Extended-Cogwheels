package com.rabbitminers.extendedgears.fabric.datagen;

import com.rabbitminers.extendedgears.ExtendedCogwheels;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ExtendedCogwheelsData implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        ExistingFileHelper helper = ExistingFileHelper.withResourcesFromArg();
        ExtendedCogwheels.registrate().setupDatagen(fabricDataGenerator, helper);
        ExtendedCogwheels.gatherData(fabricDataGenerator, true);
    }
}
