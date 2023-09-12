package com.rabbitminers.extendedgears.registry;

import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.rabbitminers.extendedgears.base.util.Env;
import com.tterrag.registrate.util.entry.RegistryEntry;
import dev.architectury.injectables.annotations.ExpectPlatform;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class ExtendedCogwheelsCreativeModeTabs {
    @ExpectPlatform
    public static CreativeModeTab getBaseTab() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static ResourceKey<CreativeModeTab> getBaseTabKey() {
        throw new AssertionError();
    }


    public static void init() {

    }

    public enum Tabs {
        MAIN(ExtendedCogwheelsCreativeModeTabs::getBaseTabKey);

        private final Supplier<ResourceKey<CreativeModeTab>> keySupplier;

        Tabs(Supplier<ResourceKey<CreativeModeTab>> keySupplier) {
            this.keySupplier = keySupplier;
        }

        public ResourceKey<CreativeModeTab> getKey() {
            return keySupplier.get();
        }
    }

    public static final class RegistrateDisplayItemsGenerator implements CreativeModeTab.DisplayItemsGenerator {

        private final Tabs tab;

        public RegistrateDisplayItemsGenerator(Tabs tab) {
            this.tab = tab;
        }

        @Override
        public void accept(CreativeModeTab.ItemDisplayParameters pParameters, CreativeModeTab.Output output) {
            ResourceKey<CreativeModeTab> tab = this.tab.getKey();

            List<Item> items = new LinkedList<>();
            Predicate<Item> is3d = Env.unsafeRunForDist(
                    () -> () -> item -> Minecraft.getInstance().getItemRenderer().getModel(new ItemStack(item), null, null, 0).isGui3d(),
                    () -> () -> item -> false // don't crash servers
            );
            items.addAll(collectItems(tab, is3d, true));
            items.addAll(collectBlocks(tab));
            items.addAll(collectItems(tab, is3d, false));

            for (Item item : items) {
                output.accept(item);
            }
        }

        private List<Item> collectBlocks(ResourceKey<CreativeModeTab> tab) {
            List<Item> items = new ReferenceArrayList<>();
            for (RegistryEntry<Block> entry : ExtendedCogwheels.registrate().getAll(Registries.BLOCK)) {
                if (!isInCreativeTab(entry, tab))
                    continue;
                Item item = entry.get().asItem();
                if (item == Items.AIR)
                    continue;
                items.add(item);
            }
            items = new ReferenceArrayList<>(new ReferenceLinkedOpenHashSet<>(items));
            return items;
        }

        private List<Item> collectItems(ResourceKey<CreativeModeTab> tab, Predicate<Item> is3d, boolean special) {
            List<Item> items = new ReferenceArrayList<>();

            for (RegistryEntry<Item> entry : ExtendedCogwheels.registrate().getAll(Registries.ITEM)) {
                if (!isInCreativeTab(entry, tab))
                    continue;
                Item item = entry.get();
                if (item instanceof BlockItem || is3d.test(item) != special)
                    continue;
                items.add(item);
            }
            return items;
        }
    }

    @ExpectPlatform
    private static boolean isInCreativeTab(RegistryEntry<?> entry, ResourceKey<CreativeModeTab> tab) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void useBaseTab() {
        throw new AssertionError();
    }

    public record TabInfo(ResourceKey<CreativeModeTab> key, CreativeModeTab tab) {
    }
}
