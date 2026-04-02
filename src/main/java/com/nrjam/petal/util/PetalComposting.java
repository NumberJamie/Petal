package com.nrjam.petal.util;

import com.nrjam.petal.block.PetalBlocks;
import com.nrjam.petal.item.PetalItems;
import net.fabricmc.fabric.api.registry.CompostableRegistry;

public class PetalComposting {
    public static void initialize() {
        CompostableRegistry.INSTANCE.add(PetalItems.TURNIP, 0.2f);
        CompostableRegistry.INSTANCE.add(PetalBlocks.TURNIP_GREENS, 0.2f);
        CompostableRegistry.INSTANCE.add(PetalBlocks.MAGMA_BLOOM, 0.2f);
        CompostableRegistry.INSTANCE.add(PetalBlocks.DEAD_ROOTS, 0.2f);
        CompostableRegistry.INSTANCE.add(PetalBlocks.LAVA_ROOT, 0.2f);
    }
}
