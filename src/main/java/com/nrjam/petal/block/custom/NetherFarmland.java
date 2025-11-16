package com.nrjam.petal.block.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Overwrite;

public class NetherFarmland extends FarmlandBlock {
    public NetherFarmland(Settings settings) {
        super(settings);
    }

    @Overwrite
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!state.canPlaceAt(world, pos)) {
            setToSoil(null, state, world, pos);
        }
    }

    private static boolean isLavaNearby(WorldView world, BlockPos pos) {
        for(BlockPos blockPos : BlockPos.iterate(pos.add(-2, 0, -2), pos.add(2, 1, 2))) {
            if (world.getFluidState(blockPos).isIn(FluidTags.LAVA)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        int i = state.get(MOISTURE);
        if (!entity.bypassesSteppingEffects() && entity instanceof LivingEntity && i == 7) {
            entity.serverDamage(world.getDamageSources().hotFloor(), 1.0F);
        }
        super.onSteppedOn(world, pos, state, entity);
    }

    @Overwrite
    private static boolean hasCrop(BlockView world, BlockPos pos) {
        return world.getBlockState(pos.up()).isIn(BlockTags.MAINTAINS_FARMLAND);
    }

    @Overwrite
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int i = state.get(MOISTURE);
        if (!isLavaNearby(world, pos)) {
            if (i > 0) {
                world.setBlockState(pos, state.with(MOISTURE, i - 1), 2);
            } else if (!hasCrop(world, pos)) {
                setToSoil(null, state, world, pos);
            }
        } else if (i < 7 && world.getDimension().ultrawarm()) {
            world.setBlockState(pos, state.with(MOISTURE, 7), 2);
        }
    }

    public static void setToSoil(@Nullable Entity entity, BlockState state, World world, BlockPos pos) {
        BlockState blockState = pushEntitiesUpBeforeBlockChange(state, Blocks.SOUL_SOIL.getDefaultState(), world, pos);
        world.setBlockState(pos, blockState);
        world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(entity, blockState));
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, double fallDistance) {
        if (world instanceof ServerWorld serverWorld
                && world.random.nextFloat() < fallDistance - 0.5
                && entity instanceof LivingEntity
                && (entity instanceof PlayerEntity || serverWorld.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING))
                && entity.getWidth() * entity.getWidth() * entity.getHeight() > 0.512F) {
            setToSoil(entity, state, world, pos);
        }
        entity.handleFallDamage(fallDistance, 1.0F, entity.getDamageSources().fall());
    }
}
