package com.kreezcraft.blockblocker.platform;

import com.kreezcraft.blockblocker.BlockConfigForge;
import com.kreezcraft.blockblocker.platform.services.IPlatformHelper;

import java.util.List;

public class ForgePlatformHelper implements IPlatformHelper {

    @Override
    public List<? extends String> dontHarvest() {
        return BlockConfigForge.GENERAL.dontHarvest.get();
    }

    @Override
    public List<? extends String> dontPlace() {
        return BlockConfigForge.GENERAL.dontPlace.get();
    }

    @Override
    public List<? extends String> dontInteract() {
        return BlockConfigForge.GENERAL.dontInteract.get();
    }
}
