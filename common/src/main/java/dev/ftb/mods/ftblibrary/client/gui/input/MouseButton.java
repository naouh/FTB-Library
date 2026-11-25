package dev.ftb.mods.ftblibrary.client.gui.input;

import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.util.Util;

public class MouseButton {
    private static final MouseButton[] BUTTONS = Util.make(new MouseButton[16], array -> {
        for (var i = 0; i < array.length; i++) {
            array[i] = new MouseButton(i);
        }
    });
    // 26.3: Minecraft passes SDL3 mouse button numbers (1-based: left=1, middle=2, right=3, x1=4, x2=5)
    public static final MouseButton LEFT = get(InputConstants.MOUSE_BUTTON_LEFT);
    public static final MouseButton RIGHT = get(InputConstants.MOUSE_BUTTON_RIGHT);
    public static final MouseButton MIDDLE = get(InputConstants.MOUSE_BUTTON_MIDDLE);
    public static final MouseButton BACK = get(InputConstants.MOUSE_BUTTON_4);
    public static final MouseButton NEXT = get(InputConstants.MOUSE_BUTTON_5);

    public final int id;

    private MouseButton(int b) {
        id = b;
    }

    public static MouseButton get(int i) {
        return i >= 0 && i < BUTTONS.length ? BUTTONS[i] : BUTTONS[BUTTONS.length - 1];
    }

    public int hashCode() {
        return id;
    }

    public boolean isLeft() {
        return id == LEFT.id;
    }

    public boolean isRight() {
        return id == RIGHT.id;
    }

    public boolean isMiddle() {
        return id == MIDDLE.id;
    }

    public int getId() {
        return id;
    }
}
