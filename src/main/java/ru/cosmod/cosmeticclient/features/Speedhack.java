package ru.cosmod.cosmeticclient.features;

import net.minecraft.client.Minecraft;
import ru.cosmod.cosmeticclient.config.Config;

public class Speedhack {
    private static final Minecraft mc = Minecraft.getInstance();

    public static boolean isEnabled = true;

    public void update() {
        if (!isEnabled || !Config.SPEEDHACK_ENABLED || mc.player == null) {
            return;
        }

        if (mc.player.isOnGround()) {
            if (mc.options.keyUp.isDown() || mc.options.keyDown.isDown() || 
                mc.options.keyLeft.isDown() || mc.options.keyRight.isDown()) {
                mc.player.moveRelative(0.1f * Config.SPEEDHACK_MULTIPLIER, mc.player.getForward());
            }
        }
    }

    public static void toggle() {
        isEnabled = !isEnabled;
    }
}