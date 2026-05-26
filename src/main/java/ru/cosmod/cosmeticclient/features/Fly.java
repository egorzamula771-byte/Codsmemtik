package ru.cosmod.cosmeticclient.features;

import net.minecraft.client.Minecraft;
import net.minecraft.util.math.vector.Vector3d;

public class Fly {
    private static final Minecraft mc = Minecraft.getInstance();
    private static final float FLY_SPEED = 0.5f;

    public static boolean isEnabled = false;

    public void update() {
        if (!isEnabled || mc.player == null) {
            return;
        }

        Vector3d motion = Vector3d.ZERO;

        if (mc.options.keyUp.isDown()) {
            motion = motion.add(mc.player.getLookAngle().scale(FLY_SPEED));
        }
        if (mc.options.keyDown.isDown()) {
            motion = motion.add(mc.player.getLookAngle().scale(-FLY_SPEED));
        }
        if (mc.options.keyLeft.isDown()) {
            Vector3d right = mc.player.getLookAngle().cross(new Vector3d(0, 1, 0)).normalize();
            motion = motion.add(right.scale(-FLY_SPEED));
        }
        if (mc.options.keyRight.isDown()) {
            Vector3d right = mc.player.getLookAngle().cross(new Vector3d(0, 1, 0)).normalize();
            motion = motion.add(right.scale(FLY_SPEED));
        }

        if (mc.options.keyJump.isDown()) {
            motion = motion.add(0, FLY_SPEED, 0);
        }
        if (mc.options.keyShift.isDown()) {
            motion = motion.add(0, -FLY_SPEED, 0);
        }

        mc.player.setDeltaMovement(motion);
    }

    public static void toggle() {
        isEnabled = !isEnabled;
    }
}