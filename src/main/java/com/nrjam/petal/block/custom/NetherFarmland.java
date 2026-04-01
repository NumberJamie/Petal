package com.nrjam.petal.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FarmlandBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

public class NetherFarmland extends FarmlandBlock {
    public NetherFarmland(Properties settings) {
        super(settings);
    }

    protected void tick(BlockState state, @NonNull ServerLevel world, @NonNull BlockPos pos, @NonNull RandomSource random) {
        if (!state.canSurvive(world, pos)) {
            setToSoil(null, state, world, pos);
        }
    }

    private static boolean isLavaNearby(LevelReader world, BlockPos pos) {
        for(BlockPos blockPos : BlockPos.betweenClosed(pos.offset(-2, 0, -2), pos.offset(2, 1, 2))) {
            if (world.getFluidState(blockPos).is(FluidTags.LAVA)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void stepOn(@NonNull Level world, @NonNull BlockPos pos, BlockState state, Entity entity) {
        int i = state.getValue(MOISTURE);
        if (!entity.isSteppingCarefully() && entity instanceof LivingEntity && i == 7) {
            entity.hurt(world.damageSources().hotFloor(), 1.0F);
        }
        super.stepOn(world, pos, state, entity);
    }

    private static boolean hasCrop(BlockGetter world, BlockPos pos) {
        return world.getBlockState(pos.above()).is(BlockTags.MAINTAINS_FARMLAND);
    }

    protected void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        int i = state.getValue(MOISTURE);
        if (world.dimension() == Level.NETHER && isLavaNearby(world, pos)) {
            if (i < 7) {
                world.setBlock(pos, state.setValue(MOISTURE, 7), 2);
            }
        } else if (i > 0) {
            world.setBlock(pos, state.setValue(MOISTURE, i - 1), 2);
        } else if (!hasCrop(world, pos)) {
            setToSoil(null, state, world, pos);
        }
    }

    public static void setToSoil(@Nullable Entity entity, BlockState state, Level world, BlockPos pos) {
        BlockState blockState = pushEntitiesUp(state, Blocks.SOUL_SOIL.defaultBlockState(), world, pos);
        world.setBlockAndUpdate(pos, blockState);
        world.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(entity, blockState));
    }

    @Override
    public void fallOn(@NonNull Level world, @NonNull BlockState state, @NonNull BlockPos pos, @NonNull Entity entity, double fallDistance) {
        if (world instanceof ServerLevel serverWorld
                && world.getRandom().nextFloat() < fallDistance - 0.5
                && entity instanceof LivingEntity
                && (entity instanceof Player || serverWorld.getGameRules().get(GameRules.MOB_GRIEFING))
                && entity.getBbWidth() * entity.getBbWidth() * entity.getBbHeight() > 0.512F) {
            setToSoil(entity, state, world, pos);
        }
        entity.causeFallDamage(fallDistance, 1.0F, entity.damageSources().fall());
    }
}
