package com.kreezcraft.blockblocker;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

import java.util.Collections;
import java.util.List;

public class BlockConfigForge {

	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final General GENERAL = new General(BUILDER);

	public static class General {
		public final ConfigValue<List<? extends String>> dontInteract;
		public final ConfigValue<List<? extends String>> dontHarvest;
		public final ConfigValue<List<? extends String>> dontPlace;

		public General(ForgeConfigSpec.Builder builder) {
			builder.push("General");
			dontInteract = builder
					.comment("Prevent these BLOCKS from being interacted with entirely (Only works if configured on client as well as server). If dimensions are desired, syntax is \"modid:block$dimensions\"")
					.translation("bbconfig.interact")
					.defineListAllowEmpty(Collections.singletonList("no-interact"), () -> Constants.defaultValues, o -> (o instanceof String));
			dontHarvest = builder
					.comment("BLOCK id's in this list will not be harvestable. If dimensions are desired, syntax is \"modid:block$dimension\"")
					.translation("bbconfig.harvest")
					.defineListAllowEmpty(Collections.singletonList("no-harvest"), () -> Constants.defaultValues, o -> (o instanceof String));
			dontPlace = builder
					.comment("BLOCK id's in this list will not be placeable and\nwill not spawn in the world nor return to the player. If dimensions are desired, syntax is \"modid:block$dimensions\"")
					.translation("bbconfig.placement")
					.defineListAllowEmpty(Collections.singletonList("no-place"), () -> Constants.defaultValues, o -> (o instanceof String));
			builder.pop();
		}

	}

	public static final ForgeConfigSpec spec = BUILDER.build();

}