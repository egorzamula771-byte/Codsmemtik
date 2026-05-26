package ru.cosmod.cosmeticclient.features;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import ru.cosmod.cosmeticclient.config.Config;

public class TriggerBot {
    private static final Minecraft mc = Minecraft.getInstance();

    public static boolean isEnabled = true;

    public void update() {
        if (!isEnabled || !Config.TRIGGERBOT_ENABLED || mc.player == null) {
            return;
        }

        RayTraceResult rayTraceResult = mc.hitResult;

        if (rayTraceResult != null && rayTraceResult.getType() == RayTraceResult.Type.ENTITY) {
            EntityRayTraceResult entityRayTrace = (EntityRayTraceResult) rayTraceResult;
            Entity entity = entityRayTrace.getEntity();

            if (entity instanceof PlayerEntity && entity != mc.player) {
                mc.gameMode.attack(entity);
                mc.player.swing(net.minecraft.util.Hand.MAIN_HAND);
            }
        }
    }

    public static void toggle() {
        isEnabled = !isEnabled;
    }
}