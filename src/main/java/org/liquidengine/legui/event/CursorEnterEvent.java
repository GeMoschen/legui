package org.liquidengine.legui.event;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joml.Vector2f;
import org.liquidengine.legui.component.Component;
import org.liquidengine.legui.component.Frame;
import org.liquidengine.legui.system.context.Context;

/**
 * Created by ShchAlexander on 2/9/2017.
 */
public class CursorEnterEvent<T extends Component> extends Event<T> {

    private final boolean entered;
    private final Vector2f delta;
    private final Vector2f cursorPosition;

    public CursorEnterEvent(T component, Context context, Frame frame, boolean entered, Vector2f delta, Vector2f cursorPosition) {
        super(component, context, frame);
        this.entered = entered;
        this.delta = delta;
        this.cursorPosition = cursorPosition;
    }

    public boolean isEntered() {
        return entered;
    }

    public Vector2f getDelta() {
        return delta;
    }

    public Vector2f getCursorPosition() {
        return cursorPosition;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("entered", entered)
            .append("delta", delta)
            .append("cursorPosition", cursorPosition)
            .toString();
    }
}
