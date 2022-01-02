package io.github.darkkronicle.betterblockoutline.config.hotkeys;

import fi.dy.masa.malilib.gui.GuiBase;
import fi.dy.masa.malilib.hotkeys.IHotkeyCallback;
import fi.dy.masa.malilib.hotkeys.IKeybind;
import fi.dy.masa.malilib.hotkeys.KeyAction;
import fi.dy.masa.malilib.util.InfoUtils;
import io.github.darkkronicle.betterblockoutline.config.ConfigStorage;
import io.github.darkkronicle.betterblockoutline.config.gui.ConfigScreen;
import io.github.darkkronicle.betterblockoutline.blockinfo.AbstractBlockInfo;
import io.github.darkkronicle.betterblockoutline.renderers.BlockInfo2dRenderer;
import io.github.darkkronicle.betterblockoutline.renderers.BlockInfo3dRenderer;

public class HotkeyCallbacks {

    public static void setup() {
        Callbacks callback = new Callbacks();
        Hotkeys.MENU.config.getKeybind().setCallback(callback);
        Hotkeys.TOGGLE_MOD_ACTIVE.config.getKeybind().setCallback(new KeyToggleBoolean(ConfigStorage.General.ACTIVE.config));
        Hotkeys.DISABLE_ALL_INFO2D.config.getKeybind().setCallback(callback);
        Hotkeys.TOGGLE_INFO2D_ACTIVE.config.getKeybind().setCallback(new KeyToggleBoolean(ConfigStorage.BlockInfo2d.ACTIVE.config));
        Hotkeys.DISABLE_ALL_INFO3D.config.getKeybind().setCallback(callback);
        Hotkeys.TOGGLE_INFO3D_ACTIVE.config.getKeybind().setCallback(new KeyToggleBoolean(ConfigStorage.BlockInfo3d.ACTIVE.config));
        ConfigStorage.BlockInfoDirectionArrow.CYCLE_ARROW.config.getKeybind().setCallback(new KeyCycleOption(ConfigStorage.BlockInfoDirectionArrow.ARROW_TYPE.config));
        for (AbstractBlockInfo info : BlockInfo2dRenderer.getInstance().getRenderers()) {
            info.getActiveKey().config.getKeybind().setCallback(new KeyToggleBoolean(info.getActive().config));
        }
        for (AbstractBlockInfo info : BlockInfo3dRenderer.getInstance().getRenderers()) {
            info.getActiveKey().config.getKeybind().setCallback(new KeyToggleBoolean(info.getActive().config));
        }
    }

    public static class Callbacks implements IHotkeyCallback {

        @Override
        public boolean onKeyAction(KeyAction action, IKeybind key) {
            if (key == Hotkeys.MENU.config.getKeybind()) {
                GuiBase.openGui(new ConfigScreen());
                return true;
            }
            if (key == Hotkeys.DISABLE_ALL_INFO2D.config.getKeybind()) {
                for (AbstractBlockInfo info : BlockInfo2dRenderer.getInstance().getRenderers()) {
                    info.getActive().config.setBooleanValue(false);
                }
                InfoUtils.printActionbarMessage("betterblockoutline.message.disableall2d");
            }
            if (key == Hotkeys.DISABLE_ALL_INFO3D.config.getKeybind()) {
                for (AbstractBlockInfo info : BlockInfo3dRenderer.getInstance().getRenderers()) {
                    info.getActive().config.setBooleanValue(false);
                }
                InfoUtils.printActionbarMessage("betterblockoutline.message.disableall3d");
            }
            return false;
        }
    }

}
