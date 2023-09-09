package com.rabbitminers.extendedgears.registry.fabric;

import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsCreativeModeTabs;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

import java.util.function.Supplier;

public class ExtendedCogwheelsCreativeModeTabsImpl {
    public static Supplier<CreativeModeTab> wrapGroup(String id, Supplier<CreativeModeTab> sup) {
        CreativeModeTab tab = sup.get();
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ExtendedCogwheelsCreativeModeTabs.makeKey(id), tab);
        return sup;
    }

    public static CreativeModeTab.Builder createBuilder() {
        return FabricItemGroup.builder();
    }

    public static void useModTab(ResourceKey<CreativeModeTab> key) { ExtendedCogwheels.registrate().useCreativeTab(key); }
}
