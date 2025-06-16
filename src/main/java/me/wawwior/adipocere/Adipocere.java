package me.wawwior.adipocere;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Adipocere implements ModInitializer {
	public static final String MOD_ID = "adipocere";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
		AdipocereItems.initialize();
		AdipocereBlocks.initialize();
		AdipocereBlockEntities.initialize();
	}
}
