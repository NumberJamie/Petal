package com.nrjam.petal.datagen;

import com.nrjam.petal.worldgen.PetalVillagerTrades;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.VillagerTradeTags;
import net.minecraft.world.item.trading.VillagerTrade;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class PetalVillagerTradeTagProvider extends FabricTagsProvider<VillagerTrade> {
    public PetalVillagerTradeTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.VILLAGER_TRADE, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider wrapperLookup) {
        builder(VillagerTradeTags.FARMER_LEVEL_1)
                .add(PetalVillagerTrades.FARMER_1_TURNIP_EMERALD);
    }
}
