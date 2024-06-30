package com.kreezcraft.blockblocker;
import com.kreezcraft.blockblocker.BlockConfigForge;
import com.kreezcraft.blockblocker.Constants;
import java.util.Collections;
import java.util.List;
import net.minecraftforge.common.ForgeConfigSpec;

public class General {
  public final ForgeConfigSpec.ConfigValue<List<? extends String>> dontInteract;
  
  public final ForgeConfigSpec.ConfigValue<List<? extends String>> dontHarvest;
  
  public final ForgeConfigSpec.ConfigValue<List<? extends String>> dontPlace;
  
  public General(ForgeConfigSpec.Builder builder) {
    builder.push("General");
    this
      
      .dontInteract = builder.comment("Prevent these BLOCKS from being interacted with entirely (Only works if configured on client as well as server). If dimensions are desired, syntax is \"modid:block$dimensions\"").translation("bbconfig.interact").defineListAllowEmpty(Collections.singletonList("no-interact"), () -> Constants.defaultValues, o -> o instanceof String);
    this
      
      .dontHarvest = builder.comment("BLOCK id's in this list will not be harvestable. If dimensions are desired, syntax is \"modid:block$dimension\"").translation("bbconfig.harvest").defineListAllowEmpty(Collections.singletonList("no-harvest"), () -> Constants.defaultValues, o -> o instanceof String);
    this
      
      .dontPlace = builder.comment("BLOCK id's in this list will not be placeable and\nwill not spawn in the world nor return to the player. If dimensions are desired, syntax is \"modid:block$dimensions\"").translation("bbconfig.placement").defineListAllowEmpty(Collections.singletonList("no-place"), () -> Constants.defaultValues, o -> o instanceof String);
    builder.pop();
  }
}


