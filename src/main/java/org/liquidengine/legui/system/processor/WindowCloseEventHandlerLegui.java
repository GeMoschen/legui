package org.liquidengine.legui.system.processor;

import org.liquidengine.legui.component.Component;
import org.liquidengine.legui.component.Container;
import org.liquidengine.legui.component.Layer;
import org.liquidengine.legui.event.LeguiWindowCloseEvent;
import org.liquidengine.legui.system.context.LeguiContext;
import org.liquidengine.legui.system.event.LeguiSystemWindowCloseEvent;

import java.util.List;

/**
 * Created by ShchAlexander on 03.02.2017.
 */
public class WindowCloseEventHandlerLegui extends AbstractLeguiSystemEventHandler<LeguiSystemWindowCloseEvent> {
    @Override
    protected boolean handle(LeguiSystemWindowCloseEvent event, Layer layer, LeguiContext context) {
        pushEvent(layer.getContainer(), context);
        return false;
    }

    private void pushEvent(Component component, LeguiContext context) {
        if (!(component.isVisible())) return;
        context.getEventProcessor().pushEvent(new LeguiWindowCloseEvent(component, context));
        if (component instanceof Container) {
            List<Component> childs = ((Container) component).getChilds();
            for (Component child : childs) {
                pushEvent(child, context);
            }
        }
    }
}