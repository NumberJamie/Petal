package com.nrjam.petal.util;

import com.nrjam.petal.item.PetalItems;
import net.fabricmc.fabric.api.registry.CompostableRegistry;

public class PetalComposting {
    public static void initialize() {
        CompostableRegistry.INSTANCE.add(PetalItems.TURNIP, 0.2f);
    }
}
