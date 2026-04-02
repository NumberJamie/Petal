package com.nrjam.petal.datagen;

import com.nrjam.petal.worldgen.PetalVillagerTrades;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.VillagerTradesTagsProvider;
import net.minecraft.tags.VillagerTradeTags;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class PetalVillagerTradeTagProvider extends VillagerTradesTagsProvider {
    public PetalVillagerTradeTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider wrapperLookup) {
        tag(VillagerTradeTags.FARMER_LEVEL_1)
                .add(PetalVillagerTrades.FARMER_1_TURNIP_EMERALD);
    }
}
