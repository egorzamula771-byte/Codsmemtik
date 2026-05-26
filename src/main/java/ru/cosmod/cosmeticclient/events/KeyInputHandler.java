package ru.cosmod.cosmeticclient.events;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;
import ru.cosmod.cosmeticclient.gui.ModMenu;

@Mod.EventBusSubscriber(modid = "cosmeticclient", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class KeyInputHandler {
    private static boolean lastRightShiftState = false;

    @SubscribeEvent
    public static void onKeyInput(InputEvent.KeyInputEvent event) {
        Minecraft mc = Minecraft.getInstance();

        boolean rightShiftPressed = GLFW.glfwGetKey(mc.getWindow().getWindow(), GLFW.GLFW_KEY_RIGHT_SHIFT) == GLFW.GLFW_PRESS;

        if (rightShiftPressed && !lastRightShiftState) {
            if (mc.screen == null) {
                mc.setScreen(new ModMenu(null));
            }
        }

        lastRightShiftState = rightShiftPressed;
    }
}