package com.kreezcraft.blockblocker.platform.services;

import java.util.List;

public interface IPlatformHelper {
	/**
	 * @return the configured list of values for no-harvest
	 */
	List<? extends String> dontHarvest();

	/**
	 * @return the configured list of values for no-place
	 */
	List<? extends String> dontPlace();

	/**
	 * @return the configured list of values for no-interact
	 */
	List<? extends String> dontInteract();
}
