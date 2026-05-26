package ru.cosmod.cosmeticclient;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafxmod.FXModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("cosmeticclient")
public class CosmeticClient {
    private static final Logger LOGGER = LogManager.getLogger();

    public CosmeticClient() {
        IEventBus modEventBus = FXModLoadingContext.getInstance().getModEventBus();
        LOGGER.info("CosmeticClient initializing...");
    }
}