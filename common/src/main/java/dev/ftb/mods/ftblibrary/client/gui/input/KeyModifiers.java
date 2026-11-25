package dev.ftb.mods.ftblibrary.client.gui.input;

import com.mojang.blaze3d.platform.InputConstants;


public class KeyModifiers {
    public static final KeyModifiers NONE = new KeyModifiers(0);

    public final int modifiers;

    public KeyModifiers(int m) {
        modifiers = m;
    }

    public boolean shift() {
        return (modifiers & InputConstants.MOD_SHIFT) != 0;
    }

    public boolean control() {
        return (modifiers & InputConstants.MOD_CONTROL) != 0;
    }

    public boolean alt() {
        return (modifiers & InputConstants.MOD_ALT) != 0;
    }

    public boolean start() {
        return (modifiers & InputConstants.MOD_SUPER) != 0;
    }

    public boolean numLock() {
        return (modifiers & InputConstants.MOD_NUM_LOCK) != 0;
    }

    public boolean capsLock() {
        return (modifiers & InputConstants.MOD_CAPS_LOCK) != 0;
    }

    public boolean onlyControl() {
        return control() && !shift() && !alt();
    }
}
