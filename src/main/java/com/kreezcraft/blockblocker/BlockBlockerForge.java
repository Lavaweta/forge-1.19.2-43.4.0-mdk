package com.kreezcraft.blockblocker;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.registries.ForgeRegistries;

@Mod("blockblocker")
public class BlockBlockerForge {

	public BlockBlockerForge() {
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, BlockConfigForge.spec);
		MinecraftForge.EVENT_BUS.addListener(this::loadConfig);
		MinecraftForge.EVENT_BUS.addListener(this::thoseDarnBlocks);
		MinecraftForge.EVENT_BUS.addListener(this::snailRider);
		MinecraftForge.EVENT_BUS.addListener(this::foxFire);
	}

	private void loadConfig(LevelEvent.Load event) {
		CommonClass.loadConfig();
	}

	private void thoseDarnBlocks(BlockEvent.BreakEvent event) {
		Block theBlock = event.getState().getBlock();
		ResourceLocation blockRegistryName = ForgeRegistries.BLOCKS.getKey(theBlock);
		ResourceLocation dimensionLocation = event.getPlayer().level.dimension().location(); //Work-around as IWorld has no reference to the dimension
		if (CommonClass.checkMap(CommonClass.harvest, blockRegistryName, dimensionLocation) == InteractionResult.FAIL)
			event.setCanceled(true);
	}

	private void snailRider(BlockEvent.EntityPlaceEvent event) {
		LevelAccessor levelAccessor = event.getLevel();
		Entity entity = event.getEntity();
		ResourceLocation dimensionLocation = entity != null ? entity.level.dimension().location() : ((Level) levelAccessor).dimension().location(); //Work-around as IWorld has no reference to the dimension
		Block theBlock = event.getState().getBlock();
		ResourceLocation blockRegistryName = ForgeRegistries.BLOCKS.getKey(theBlock);

		if (CommonClass.checkMap(CommonClass.place, blockRegistryName, dimensionLocation) == InteractionResult.FAIL)
			event.setCanceled(true);
	}

	private void foxFire(PlayerInteractEvent.RightClickBlock event) {
		BlockPos target = event.getPos();
		Player player = event.getEntity();
		if (player.blockPosition() == target) {
			return;
		}

		Block theBlock = event.getLevel().getBlockState(target).getBlock();
		ResourceLocation blockRegistryName = ForgeRegistries.BLOCKS.getKey(theBlock);
		ResourceLocation dimensionLocation = player.level.dimension().location(); //Work-around as IWorld has no reference to the dimension
		if (CommonClass.checkMap(CommonClass.interact, blockRegistryName, dimensionLocation) == InteractionResult.FAIL)
			event.setCanceled(true);
	}
}