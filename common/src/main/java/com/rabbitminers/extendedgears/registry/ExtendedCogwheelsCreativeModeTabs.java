package com.rabbitminers.extendedgears.registry;

import java.util.Arrays;
import java.util.function.Supplier;

import com.rabbitminers.extendedgears.ExtendedCogwheels;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ExtendedCogwheelsCreativeModeTabs {
    public static final ResourceKey<CreativeModeTab> MAIN_TAB_KEY = makeKey("base");

    public static final Supplier<CreativeModeTab> GROUP = wrapGroup("base", () -> createBuilder()
            .title(Component.translatable("itemGroup.extendedgears"))
            .icon(ExtendedCogwheelsBlocks.SHAFTLESS_COGWHEEL::asStack)
            .displayItems((params, output) -> output.acceptAll(Arrays.asList(
                    ExtendedCogwheelsBlocks.HALF_SHAFT_COGWHEEL.asStack(),
                    ExtendedCogwheelsBlocks.LARGE_HALF_SHAFT_COGWHEEL.asStack(),
                    ExtendedCogwheelsBlocks.SHAFTLESS_COGWHEEL.asStack(),
                    ExtendedCogwheelsBlocks.LARGE_SHAFTLESS_COGWHEEL.asStack()
            )))
            .build()
    );


    @ExpectPlatform
    public static Supplier<CreativeModeTab> wrapGroup(String id, Supplier<CreativeModeTab> sup) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static CreativeModeTab.Builder createBuilder() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void useModTab(ResourceKey<CreativeModeTab> key) {
        throw new AssertionError();
    }

    public static ResourceKey<CreativeModeTab> makeKey(String id) {
        return ResourceKey.create(Registries.CREATIVE_MODE_TAB, ExtendedCogwheels.asResource(id));
    }

    public static void init() {
        // just to load class
    }
}
