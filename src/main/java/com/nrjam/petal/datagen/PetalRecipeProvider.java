package com.nrjam.petal.datagen;

import com.nrjam.petal.block.PetalBlocks;
import com.nrjam.petal.item.PetalItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PetalRecipeProvider extends FabricRecipeProvider {
    public PetalRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                threeByThreePacker(RecipeCategory.FOOD, PetalBlocks.HUGE_TURNIP, PetalItems.TURNIP);
                shapeless(RecipeCategory.FOOD, PetalItems.FUGU)
                        .requires(Items.PUFFERFISH)
                        .unlockedBy(getHasName(Items.PUFFERFISH), has(Items.PUFFERFISH))
                        .save(output);
                shapeless(RecipeCategory.FOOD, PetalItems.MOUSSE)
                        .requires(ItemTags.EGGS).requires(Items.MILK_BUCKET).requires(Items.COCOA_BEANS).requires(Items.SUGAR)
                        .unlockedBy(getHasName(Items.COCOA_BEANS), has(Items.COCOA_BEANS))
                        .save(output);
                shapeless(RecipeCategory.FOOD, PetalItems.TURNIP_PIE)
                        .requires(ItemTags.EGGS).requires(PetalItems.TURNIP).requires(Items.SUGAR)
                        .unlockedBy(getHasName(PetalItems.TURNIP), has(PetalItems.TURNIP))
                        .save(output);
                shapeless(RecipeCategory.FOOD, PetalItems.GLAZED_TURNIP)
                        .requires(PetalItems.ROASTED_TURNIP).requires(Items.HONEY_BOTTLE)
                        .unlockedBy(getHasName(PetalItems.ROASTED_TURNIP), has(PetalItems.ROASTED_TURNIP))
                        .save(output);
                oreSmelting(List.of(PetalItems.TURNIP), RecipeCategory.FOOD, CookingBookCategory.FOOD, PetalItems.ROASTED_TURNIP, .25f, 200, "roasted_turnip");
                oreSmelting(List.of(PetalItems.LAVA_FRUIT), RecipeCategory.FOOD, CookingBookCategory.FOOD, PetalItems.BAKED_LAVA_FRUIT, .25f, 200, "baked_lava_fruit");
            }
        };
    }

    @Override
    public String getName() {
        return "PetalRecipeProvider";
    }
}
