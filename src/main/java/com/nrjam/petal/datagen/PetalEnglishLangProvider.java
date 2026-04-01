package com.nrjam.petal.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class PetalEnglishLangProvider extends FabricLanguageProvider {
    public PetalEnglishLangProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.@NonNull Provider wrapperLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add("item.petal.dead_roots", "Dead Roots");
        translationBuilder.add("item.petal.lava_root", "Lava Root");
        translationBuilder.add("item.petal.magma_bloom", "Magma Bloom");

        translationBuilder.add("item.petal.muddy_farmland", "Muddy Farmland");
        translationBuilder.add("item.petal.nether_farmland", "Nether Farmland");

        translationBuilder.add("item.petal.turnip", "Turnip");
        translationBuilder.add("item.petal.roasted_turnip", "Roasted Turnip");
        translationBuilder.add("item.petal.glazed_turnip", "Glazed Turnip");
        translationBuilder.add("item.petal.turnip_pie", "Turnip Pie");

        translationBuilder.add("item.petal.lava_fruit", "Lava Fruit");
        translationBuilder.add("item.petal.baked_lava_fruit", "Baked Lava Fruit");
        translationBuilder.add("item.petal.magma_berry", "Magma Berry");

        translationBuilder.add("item.petal.fugu", "Fugu");
        translationBuilder.add("item.petal.mousse", "Mousse");

        translationBuilder.add("item.petal.huge_turnip", "Huge Turnip");
        translationBuilder.add("item.petal.turnip_greens", "Turnip Greens");
    }
}
