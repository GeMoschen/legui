package org.liquidengine.legui.theme.colored.def;

import org.liquidengine.legui.component.ScrollBar;
import org.liquidengine.legui.style.color.ColorUtil;
import org.liquidengine.legui.theme.colored.FlatColoredTheme.FlatColoredThemeSettings;

/**
 * Dark ScrollBar Theme for all scroll bars. Used to make scroll bar dark.
 *
 * @param <T> {@link ScrollBar} subclasses.
 */
public class FlatScrollBarTheme<T extends ScrollBar> extends FlatComponentTheme<T> {

    private FlatColoredThemeSettings settings;

    public FlatScrollBarTheme(FlatColoredThemeSettings settings) {
        super(settings);
        this.settings = settings;
    }

    /**
     * Used to apply theme only for component and not apply for child components.
     *
     * @param component component to apply theme.
     */
    @Override
    public void apply(T component) {
        super.apply(component);
        component.getStyle().getBackground().setColor(settings.borderColor());
        component.setArrowColor(ColorUtil.oppositeBlackOrWhite(settings.borderColor()));
        component.setScrollColor(settings.backgroundColor());
        component.setArrowsEnabled(false);
    }
}
