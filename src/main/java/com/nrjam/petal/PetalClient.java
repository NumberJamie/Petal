package com.nrjam.petal;

import com.nrjam.petal.block.PetalBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.render.BlockRenderLayer;

public class PetalClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.putBlock(PetalBlocks.TURNIPS, BlockRenderLayer.CUTOUT);
    }
}
