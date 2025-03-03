package io.github.darkkronicle.betterblockoutline.config.gui.colormods;

import fi.dy.masa.malilib.gui.interfaces.ISelectionListener;
import fi.dy.masa.malilib.gui.widgets.WidgetListBase;
import io.github.darkkronicle.betterblockoutline.config.ConfigColorModifier;
import io.github.darkkronicle.betterblockoutline.config.ConfigStorage;
import io.github.darkkronicle.betterblockoutline.config.gui.ColorModifierListScreen;
import lombok.Getter;
import net.minecraft.client.gui.screen.Screen;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class WidgetListColorModifiers extends WidgetListBase<ConfigColorModifier, WidgetColorModifier> {

    @Getter
    private final ColorModifierListScreen.Type type;

    @Getter
    private final Screen parentScreen;

    public WidgetListColorModifiers(int x, int y, int width, int height, @Nullable ISelectionListener<ConfigColorModifier> selectionListener, ColorModifierListScreen.Type type, Screen parentScreen) {
        super(x, y, width, height, selectionListener);
        this.type = type;
        this.browserEntryHeight = 22;
        this.parentScreen = parentScreen;
    }

    @Override
    protected Collection<ConfigColorModifier> getAllEntries() {
        return ConfigStorage.getColorMods(type.getConfigKey());
    }

    @Override
    protected WidgetColorModifier createListEntryWidget(int x, int y, int listIndex, boolean isOdd, ConfigColorModifier entry) {
        return new WidgetColorModifier(x, y, browserEntryWidth, getBrowserEntryHeightFor(entry), isOdd, entry, listIndex, this);
    }

}
