package com.nrjam.petal.worldgen;

import com.nrjam.petal.Petal;
import com.nrjam.petal.item.PetalItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.TradeCost;
import net.minecraft.world.item.trading.VillagerTrade;

import java.util.Optional;
import java.util.List;

public class PetalVillagerTrades {
    public static final ResourceKey<VillagerTrade> FARMER_1_TURNIP_EMERALD = registerKey("farmer/1/turnip_emerald");

    public static void initialize(BootstrapContext<VillagerTrade> ctx) {
        ctx.register(FARMER_1_TURNIP_EMERALD, new VillagerTrade(
                new TradeCost(PetalItems.TURNIP, 22),
                new ItemStackTemplate(Items.EMERALD),
                16, 2, 0.05f,
                Optional.empty(), List.of()
        ));
    }

    private static ResourceKey<VillagerTrade> registerKey(String name) {
        return ResourceKey.create(Registries.VILLAGER_TRADE, Identifier.fromNamespaceAndPath(Petal.MOD_ID, name));
    }
}
