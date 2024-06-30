package com.kreezcraft.blockblocker;

import com.kreezcraft.blockblocker.platform.Services;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonClass {
	public static final Map<ResourceLocation, List<ResourceLocation>> harvest = new HashMap<>();
	public static final Map<ResourceLocation, List<ResourceLocation>> place = new HashMap<>();
	public static final Map<ResourceLocation, List<ResourceLocation>> interact = new HashMap<>();

	/**
	 * Load the config values and use it to fill the maps
	 */
	public static void loadConfig() {
		fillMap(harvest, Services.PLATFORM.dontHarvest(), "no-harvest");
		fillMap(place, Services.PLATFORM.dontPlace(), "no-place");
		fillMap(interact, Services.PLATFORM.dontInteract(), "no-interact");
	}


	public static void fillMap(Map<ResourceLocation, List<ResourceLocation>> map, List<? extends String> entryList, String configName) {
		for (String string : entryList) {
			String[] tmp = string.split("\\$");
			ResourceLocation blockLocation = ResourceLocation.tryParse(tmp[0]);

			switch (tmp.length) {
				case 1 -> {
					if (blockLocation != null && !map.containsKey(blockLocation)) {
						map.put(blockLocation, new ArrayList<>());
					}
				}
				case 2 -> {
					if (blockLocation != null) {
						ResourceLocation dimensionLocation = ResourceLocation.tryParse(tmp[1]);
						if (dimensionLocation != null) {
							List<ResourceLocation> list = map.getOrDefault(blockLocation, new ArrayList<>());
							list.add(dimensionLocation);
							map.put(blockLocation, list);
						}
					}
				}
				default -> throw new RuntimeException("Invalid config at " + string + " in " + configName);
			}
		}
	}

	/**
	 * Check if the map contains the block and if it does, check if the list is empty or contains the dimension
	 * @param map the configured map
	 * @param blockRegistryName the block to check
	 * @param dimensionLocation the dimension to check
	 * @return If the interaction should be blocked
	 */
	public static InteractionResult checkMap(Map<ResourceLocation, List<ResourceLocation>> map,
											 ResourceLocation blockRegistryName, ResourceLocation dimensionLocation) {
		if (map.containsKey(blockRegistryName)) {
			if (!map.get(blockRegistryName).isEmpty()) {
				if (map.get(blockRegistryName).contains(dimensionLocation)) {
					return InteractionResult.FAIL;
				}
			} else {
				return InteractionResult.FAIL;
			}
		}
		return InteractionResult.PASS;
	}
}