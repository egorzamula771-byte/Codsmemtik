package ru.cosmod.cosmeticclient.features;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import ru.cosmod.cosmeticclient.config.Config;

public class Killaura {
    private static final Minecraft mc = Minecraft.getInstance();
    private long lastAttackTime = 0;

    public static boolean isEnabled = true;

    public void update() {
        if (!isEnabled || !Config.KILLAURA_ENABLED || mc.player == null || mc.level == null) {
            return;
        }

        long currentTime = System.currentTimeMillis();
        long delayBetweenClicks = 1000 / Config.KILLAURA_ATTACK_SPEED;

        if (currentTime - lastAttackTime < delayBetweenClicks) {
            return;
        }

        PlayerEntity targetPlayer = getClosestPlayer();
        if (targetPlayer != null && mc.player.distanceTo(targetPlayer) <= Config.KILLAURA_RANGE) {
            attackPlayer(targetPlayer);
            lastAttackTime = currentTime;
        }
    }

    private PlayerEntity getClosestPlayer() {
        PlayerEntity closestPlayer = null;
        double closestDistance = Config.KILLAURA_RANGE;

        for (Entity entity : mc.level.entitiesForRendering()) {
            if (entity instanceof PlayerEntity && entity != mc.player) {
                PlayerEntity player = (PlayerEntity) entity;
                double distance = mc.player.distanceTo(player);

                if (distance < closestDistance && !player.isInvisible()) {
                    closestPlayer = player;
                    closestDistance = distance;
                }
            }
        }

        return closestPlayer;
    }

    private void attackPlayer(PlayerEntity player) {
        mc.gameMode.attack(player);
        mc.player.swing(net.minecraft.util.Hand.MAIN_HAND);
    }

    public static void toggle() {
        isEnabled = !isEnabled;
    }
}