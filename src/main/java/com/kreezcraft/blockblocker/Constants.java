package com.kreezcraft.blockblocker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Constants {
	public static final String MOD_ID = "blockblocker";
	public static final String MOD_NAME = "blockblocker";
	public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

	public static final List<String> defaultValues = List.of("minecraft:bedrock", "minecraft:air");
}