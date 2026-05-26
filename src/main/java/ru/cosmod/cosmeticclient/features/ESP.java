package ru.cosmod.cosmeticclient.features;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import ru.cosmod.cosmeticclient.config.Config;
import java.util.ArrayList;
import java.util.List;

public class ESP {
    private static final Minecraft mc = Minecraft.getInstance();

    public static boolean isEnabled = true;

    public static List<PlayerEntity> getVisiblePlayers() {
        List<PlayerEntity> visiblePlayers = new ArrayList<>();

        if (!isEnabled || !Config.ESP_ENABLED || mc.player == null || mc.level == null) {
            return visiblePlayers;
        }

        for (Entity entity : mc.level.entitiesForRendering()) {
            if (entity instanceof PlayerEntity && entity != mc.player) {
                PlayerEntity player = (PlayerEntity) entity;
                double distance = mc.player.distanceTo(player);

                if (distance <= Config.ESP_RANGE && !player.isInvisible()) {
                    visiblePlayers.add(player);
                }
            }
        }

        return visiblePlayers;
    }

    public static void toggle() {
        isEnabled = !isEnabled;
    }
}