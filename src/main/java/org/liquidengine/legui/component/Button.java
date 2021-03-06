package org.liquidengine.legui.component;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joml.Vector2f;
import org.liquidengine.legui.component.optional.TextState;
import org.liquidengine.legui.component.optional.align.HorizontalAlign;
import org.liquidengine.legui.theme.Themes;

/**
 * An implementation of "push" button.
 */
public class Button extends Component implements TextComponent {

    /**
     * Button text state.
     */
    private TextState textState;

    /**
     * Creates a button with default text.
     */
    public Button() {
        this("Button");
    }

    /**
     * Creates a button with default text and specified position and size.
     *
     * @param x x position in parent.
     * @param y y position in parent.
     * @param width width of component.
     * @param height height of component.
     */
    public Button(float x, float y, float width, float height) {
        this("Button", x, y, width, height);
    }

    /**
     * Creates a button with default text and specified position and size.
     *
     * @param position position in parent.
     * @param size size of component.
     */
    public Button(Vector2f position, Vector2f size) {
        this("Button", position, size);
    }

    /**
     * Creates a button with specified text.
     *
     * @param text button text.
     */
    public Button(String text) {
        initialize(text);
    }

    /**
     * Creates a button with specified text and specified position and size.
     *
     * @param text button text.
     * @param x x position in parent.
     * @param y y position in parent.
     * @param width width of component.
     * @param height height of component.
     */
    public Button(String text, float x, float y, float width, float height) {
        super(x, y, width, height);
        initialize(text);
    }

    /**
     * Creates a button with specified text and specified position and size.
     *
     * @param text button text.
     * @param position position in parent.
     * @param size size of component.
     */
    public Button(String text, Vector2f position, Vector2f size) {
        super(position, size);
        initialize(text);
    }

    /**
     * Initialize button with specified text.
     *
     * @param text used to initialize text state.
     */
    private void initialize(String text) {
        this.textState = new TextState(text);
        textState.setHorizontalAlign(HorizontalAlign.CENTER);
        Themes.getDefaultTheme().getThemeManager().getComponentTheme(Button.class).applyAll(this);
    }

    /**
     * Returns text data of button.
     *
     * @return text state of button.
     */
    public TextState getTextState() {
        return textState;
    }

//    /**
//     * Returns background image.
//     *
//     * @return background image.
//     */
//    public Icon getStyle().getBackground().getIcon() {
//        return backgroundIcon;
//    }

//    /**
//     * Used to change background image.
//     *
//     * @param backgroundIcon background image.
//     */
//    public void getStyle().getBackground().setIcon(Icon backgroundIcon) {
//        this.backgroundIcon = backgroundIcon;
//    }

//    /**
//     * Returns focused background image.
//     *
//     * @return focused background image.
//     */
//    public Icon getFocusedBackgroundIcon() {
//        return focusedBackgroundIcon;
//    }

//    /**
//     * Used to change focused background image.
//     *
//     * @param focusedBackgroundIcon focused background image.
//     */
//    public void setFocusedBackgroundIcon(Icon focusedBackgroundIcon) {
//        this.focusedBackgroundIcon = focusedBackgroundIcon;
//    }

    //    /**
//     * Returns pressed background image.
//     *
//     * @return pressed background image.
//     */
//    public Icon getPressedBackgroundIcon() {
//        return pressedBackgroundIcon;
//    }
//
//    /**
//     * Used to change pressed background image.
//     *
//     * @param pressedBackgroundIcon pressed background image.
//     */
//    public void setPressedBackgroundIcon(Icon pressedBackgroundIcon) {
//        this.pressedBackgroundIcon = pressedBackgroundIcon;
//    }
//
//    /**
//     * Returns hovered background image.
//     *
//     * @return hovered background image.
//     */
//    public Icon getHoveredBackgroundIcon() {
//        return hoveredBackgroundIcon;
//    }
//
//    /**
//     * Used to change hovered background image.
//     *
//     * @param hoveredBackgroundIcon hovered background image.
//     */
//    public void setHoveredBackgroundIcon(Icon hoveredBackgroundIcon) {
//        this.hoveredBackgroundIcon = hoveredBackgroundIcon;
//    }
//
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Button button = (Button) o;

        return new EqualsBuilder()
            .appendSuper(super.equals(o))
            .append(getTextState(), button.getTextState())
//            .append(getStyle().getBackground().getIcon(), button.getStyle().getBackground().getIcon())
//            .append(getFocusedBackgroundIcon(), button.getFocusedBackgroundIcon())
//            .append(getPressedBackgroundIcon(), button.getPressedBackgroundIcon())
//            .append(getHoveredBackgroundIcon(), button.getHoveredBackgroundIcon())
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(textState)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("textState", textState)
            .toString();
    }
}