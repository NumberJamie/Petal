package com.nrjam.petal.block.custom;

import com.nrjam.petal.block.PetalBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NonNull;

public class TurnipGreens extends BushBlock {
    private static final VoxelShape SHAPE = Block.column(12.0, 0.0, 12.0);

    public TurnipGreens(Properties settings) {
        super(settings);
    }

    @Override
    protected @NonNull VoxelShape getShape(@NonNull BlockState state, @NonNull BlockGetter world, @NonNull BlockPos pos, @NonNull CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected boolean mayPlaceOn(BlockState floor, @NonNull BlockGetter world, @NonNull BlockPos pos) {
        return floor.is(PetalBlocks.HUGE_TURNIP) || super.mayPlaceOn(floor, world, pos);
    }
}
