package xyz.achu.vandalize;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

public class VandalizeMenu implements ModInitializer {
	public static final String MODID = "vandalizemenu";

	public static final Logger logger = LogManager.getLogger();


	@Override
	public void onInitialize() {
		logger.info("If UwU can see this, we have loaded!");
	}
}
