package com.rabbitminers.extendedgears.fabric.datagen;

import com.rabbitminers.extendedgears.ExtendedCogwheels;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

public class ExtendedCogwheelsData implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        Path resources = Paths.get(System.getProperty(ExistingFileHelper.EXISTING_RESOURCES));
        ExistingFileHelper helper = new ExistingFileHelper(
                Set.of(resources), Set.of("create"), true, null, null
        );
        ExtendedCogwheels.gatherData(generator,false);
        ExtendedCogwheels.registrate().setupDatagen(generator, helper);
    }
}
