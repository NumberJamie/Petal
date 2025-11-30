package com.nrjam.petal.datagen;

import com.nrjam.petal.block.PetalBlocks;
import com.nrjam.petal.item.PetalItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PetalRecipeProvider extends FabricRecipeProvider {
    public PetalRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter exporter) {
        return new RecipeGenerator(wrapperLookup, exporter) {
            @Override
            public void generate() {
                RegistryWrapper.Impl<Item> itemLookup = registries.getOrThrow(RegistryKeys.ITEM);

                createCondensingRecipe(RecipeCategory.FOOD, PetalBlocks.HUGE_TURNIP, Ingredient.ofItem(PetalItems.TURNIP));
                createShapeless(RecipeCategory.FOOD, PetalItems.FUGU)
                        .input(Items.PUFFERFISH)
                        .criterion(hasItem(Items.PUFFERFISH), conditionsFromItem(Items.PUFFERFISH))
                        .offerTo(exporter);
                createShapeless(RecipeCategory.FOOD, PetalItems.MOUSSE)
                        .input(ItemTags.EGGS).input(Items.MILK_BUCKET).input(Items.COCOA_BEANS).input(Items.SUGAR)
                        .criterion(hasItem(Items.COCOA_BEANS), conditionsFromItem(Items.COCOA_BEANS))
                        .offerTo(exporter);
                createShapeless(RecipeCategory.FOOD, PetalItems.TURNIP_PIE)
                        .input(ItemTags.EGGS).input(PetalItems.TURNIP).input(Items.SUGAR)
                        .criterion(hasItem(PetalItems.TURNIP), conditionsFromItem(PetalItems.TURNIP))
                        .offerTo(exporter);
                createShapeless(RecipeCategory.FOOD, PetalItems.GLAZED_TURNIP)
                        .input(PetalItems.ROASTED_TURNIP).input(Items.HONEY_BOTTLE)
                        .criterion(hasItem(PetalItems.ROASTED_TURNIP), conditionsFromItem(PetalItems.ROASTED_TURNIP))
                        .offerTo(exporter);
                offerSmelting(List.of(PetalItems.TURNIP), RecipeCategory.FOOD, PetalItems.ROASTED_TURNIP, .25f, 200, "roasted_turnip");
                offerSmelting(List.of(PetalItems.LAVA_FRUIT), RecipeCategory.FOOD, PetalItems.BAKED_LAVA_FRUIT, .25f, 200, "baked_lava_fruit");
            }
        };
    }

    @Override
    public String getName() {
        return "PetalRecipeProvider";
    }
}
