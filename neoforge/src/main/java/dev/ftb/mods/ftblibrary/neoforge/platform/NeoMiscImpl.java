package dev.ftb.mods.ftblibrary.neoforge.platform;

import com.mojang.blaze3d.platform.InputConstants;
import dev.ftb.mods.ftblibrary.platform.Misc;
import net.minecraft.client.KeyMapping;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.BlockTransformer;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.component.BlockTransformers;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.common.extensions.IBaseRailBlockExtension;
import net.neoforged.neoforge.common.util.FakePlayer;

public class NeoMiscImpl implements Misc {
    @Override
    public boolean matchesWithoutConflicts(KeyMapping keyBinding, InputConstants.Key keyCode) {
        return keyBinding.isActiveAndMatches(keyCode);
    }

    @Override
    public Component componentWithLinks(String message) {
        return CommonHooks.newChatWithLinks(message);
    }

    @Override
    public void refreshDisplayName(Player player) {
        player.refreshDisplayName();
    }

    @Override
    public long bucketFluidAmount() {
        return 1_000;
    }

    @Override
    public boolean isFakePlayer(Player player) {
        return player instanceof FakePlayer || (player instanceof ServerPlayer && player.getClass() != ServerPlayer.class);
    }

    @Override
    public boolean isRailBlock(Block block) {
        return block instanceof IBaseRailBlockExtension;
    }

    @Override
    public boolean playerHasCorrectTool(Player player, BlockPos pos, BlockState state) {
        return player.hasCorrectToolForDrops(state, player.level(), pos);
    }

    @Override
    public boolean canAxeStrip(ItemStack stack) {
        return hasBlockTransformer(stack, BlockTransformers.AXE);
    }

    @Override
    public boolean canTillSoil(ItemStack stack) {
        return hasBlockTransformer(stack, BlockTransformers.HOE);
    }

    @Override
    public boolean canFlattenPath(ItemStack stack) {
        return hasBlockTransformer(stack, BlockTransformers.SHOVEL);
    }

    // 26.3: NeoForge removed the AXE_STRIP/HOE_TILL/SHOVEL_FLATTEN item abilities; tools now carry a vanilla
    // minecraft:block_transformer component pointing at the axe/hoe/shovel transformer.
    private static boolean hasBlockTransformer(ItemStack stack, ResourceKey<BlockTransformer> key) {
        var transformer = stack.get(DataComponents.BLOCK_TRANSFORMER);
        return transformer != null && transformer.is(key);
    }

    @Override
    public boolean hasComponentPatch(ItemStack stack) {
        return !stack.isComponentsPatchEmpty();
    }

    @Override
    public MapColor getMapColor(BlockState state, BlockGetter level, BlockPos pos, MapColor defaultColor) {
        return state.getBlock().getMapColor(state, level, pos, defaultColor);
    }
}
