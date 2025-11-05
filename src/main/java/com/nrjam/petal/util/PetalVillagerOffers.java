package com.nrjam.petal.util;

import com.nrjam.petal.item.PetalItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;

public class PetalVillagerOffers {
    public static void initialize() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 6),
                    new ItemStack(PetalItems.ROASTED_TURNIP, 4), 7, 2, 0.1f));
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(PetalItems.TURNIP, 18),
                    new ItemStack(Items.EMERALD, 1), 7, 2, 0.1f));
        });
    }
}
