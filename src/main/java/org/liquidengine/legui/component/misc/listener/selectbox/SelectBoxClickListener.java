package org.liquidengine.legui.component.misc.listener.selectbox;

import static org.liquidengine.legui.event.MouseClickEvent.MouseClickAction.CLICK;

import org.joml.Vector2f;
import org.liquidengine.legui.component.Frame;
import org.liquidengine.legui.component.SelectBox;
import org.liquidengine.legui.component.SelectBoxLayer;
import org.liquidengine.legui.event.MouseClickEvent;
import org.liquidengine.legui.listener.MouseClickEventListener;

/**
 * Default mouse click listener for selectbox.
 * <p>
 * Used to expand/collapse selectbox if clicked on it.
 */
public class SelectBoxClickListener implements MouseClickEventListener {

    private SelectBox selectBox;

    public SelectBoxClickListener(SelectBox selectBox) {
        this.selectBox = selectBox;
    }

    @Override
    public void process(MouseClickEvent event) {
        SelectBox box = selectBox;
        if (event.getAction() == CLICK) {
            Frame frame = event.getFrame();
            SelectBoxLayer selectBoxLayer = box.getSelectBoxLayer();
            boolean collapsed = box.isCollapsed();
            box.setCollapsed(!collapsed);
            if (collapsed) {
                Vector2f layerSize = new Vector2f(frame.getContainer().getSize());
                selectBoxLayer.getContainer().setSize(layerSize);
                selectBoxLayer.getContainer().invalidateLayout();
                frame.addLayer(selectBoxLayer);
            } else {
                frame.removeLayer(selectBoxLayer);
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        return (obj != null) && ((obj == this) || ((obj != this) && (obj.getClass() == this.getClass())));
    }
}
