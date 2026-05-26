package ru.cosmod.cosmeticclient.events;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.cosmod.cosmeticclient.features.*;

@Mod.EventBusSubscriber(modid = "cosmeticclient", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientTickHandler {
    private static final Killaura killaura = new Killaura();
    private static final Speedhack speedhack = new Speedhack();
    private static final Fly fly = new Fly();
    private static final TriggerBot triggerBot = new TriggerBot();

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.ClientTickEvent event) {
        killaura.update();
        speedhack.update();
        fly.update();
        triggerBot.update();
    }
}