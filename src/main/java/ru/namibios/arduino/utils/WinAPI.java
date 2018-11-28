package ru.namibios.arduino.utils;

import com.sun.jna.platform.win32.WinDef;
import me.coley.simplejna.Windows;

import java.util.Map;

public final class WinAPI {

    private WinAPI(){}

    public static WinDef.HWND findWindow(String name){
        Map<WinDef.HWND, String> windows = Windows.getWindows();
        for (WinDef.HWND hwnd : windows.keySet()) {
            if (Windows.getWindowTitle(hwnd).toLowerCase().startsWith(name.toLowerCase())) {
                return hwnd;
            }
        }
        return null;
    }

    public static void activateWindow(String name) {
        WinDef.HWND bdo = findWindow(name);
        Windows.showWindow(bdo, Windows.SW_SHOWNORMAL);
    }

    public static void activateWindow(WinDef.HWND windowGame) {
        Windows.showWindow(windowGame, Windows.SW_SHOWNORMAL);
    }

}
