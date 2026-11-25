package dev.ftb.mods.ftblibrary.client.gui;

import org.jspecify.annotations.Nullable;
import org.lwjgl.sdl.SDLMouse;

public enum CursorType {
    // 26.3: Minecraft uses SDL3 instead of GLFW; these are the SDL system cursor equivalents
    // of the GLFW standard cursors used previously.
    ARROW(SDLMouse.SDL_SYSTEM_CURSOR_DEFAULT),
    IBEAM(SDLMouse.SDL_SYSTEM_CURSOR_TEXT),
    CROSSHAIR(SDLMouse.SDL_SYSTEM_CURSOR_CROSSHAIR),
    HAND(SDLMouse.SDL_SYSTEM_CURSOR_POINTER),
    HRESIZE(SDLMouse.SDL_SYSTEM_CURSOR_EW_RESIZE),
    VRESIZE(SDLMouse.SDL_SYSTEM_CURSOR_NS_RESIZE),
    MOVE(SDLMouse.SDL_SYSTEM_CURSOR_MOVE);

    private final int shape;
    private long cursor = 0L;

    CursorType(int c) {
        shape = c;
    }

    public static void set(@Nullable CursorType type) {
        if (type == null) {
            // SDL_SetCursor(NULL) only redraws the current cursor in SDL3; restore the default explicitly
            SDLMouse.SDL_SetCursor(SDLMouse.SDL_GetDefaultCursor());
            return;
        }

        if (type.cursor == 0L) {
            type.cursor = SDLMouse.SDL_CreateSystemCursor(type.shape);
        }

        SDLMouse.SDL_SetCursor(type.cursor != 0L ? type.cursor : SDLMouse.SDL_GetDefaultCursor());
    }
}
