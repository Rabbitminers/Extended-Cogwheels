package com.rabbitminers.extendedgears.base.lang;

import com.google.gson.JsonElement;
import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.rabbitminers.extendedgears.mixin.AccessorLangMerger;
import com.simibubi.create.foundation.data.AllLangPartials;
import com.simibubi.create.foundation.data.LangMerger;
import com.simibubi.create.foundation.data.LangPartial;
import com.simibubi.create.foundation.utility.FilesHelper;
import com.simibubi.create.foundation.utility.Lang;
import net.minecraft.data.PackOutput;

import java.util.function.Supplier;

public enum ExtendedCogwheelsLanguageProvider implements LangPartial {
    EN_US("en_us"),

    ;

    private String display;
    private Supplier<JsonElement> provider;

    private ExtendedCogwheelsLanguageProvider(String display) {
        this.display = display;
        this.provider = this::fromResource;
    }

    private ExtendedCogwheelsLanguageProvider(String display, Supplier<JsonElement> customProvider) {
        this.display = display;
        this.provider = customProvider;
    }

    public String getDisplayName() {
        return display;
    }

    public JsonElement provide() {
        return provider.get();
    }

    private JsonElement fromResource() {
        String fileName = Lang.asId(name());
        String filepath = "assets/" + ExtendedCogwheels.MOD_ID + "/lang/default/" + fileName + ".json";
        JsonElement element = FilesHelper.loadJsonResource(filepath);
        if (element == null)
            throw new IllegalStateException(String.format("Could not find default lang file: %s", filepath));
        return element;
    }

    public static <T extends LangPartial> LangMerger createMerger(PackOutput output, String modid, String displayName,
                                                                  LangPartial[] partials) {
        LangMerger merger = new LangMerger(output, modid, displayName, new AllLangPartials[0]);
        ((AccessorLangMerger) merger).setLangPartials(partials);
        return merger;
    }
}
