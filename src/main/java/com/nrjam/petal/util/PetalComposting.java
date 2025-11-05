package com.nrjam.petal.util;

import com.nrjam.petal.item.PetalItems;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;

public class PetalComposting {
    public static void initialize() {
        CompostingChanceRegistry.INSTANCE.add(PetalItems.TURNIP, 0.2f);
    }
}
