package org.liquidengine.legui.system.renderer.nvg;

import static org.liquidengine.legui.system.renderer.nvg.NvgRenderer.NVG_CONTEXT;

import org.liquidengine.legui.component.Component;
import org.liquidengine.legui.style.border.Border;
import org.liquidengine.legui.style.color.ColorConstants;
import org.liquidengine.legui.system.context.Context;
import org.liquidengine.legui.system.renderer.ComponentRenderer;
import org.liquidengine.legui.util.Utilites;

/**
 * The base NanoVG component renderer.
 *
 * @param <C> component type.
 */
public abstract class NvgComponentRenderer<C extends Component> extends ComponentRenderer<C> {

    private Border debugBorder = new Border();
    private Border debugFocusBorder = new Border();

    {
        debugBorder.setColor(ColorConstants.red());
        debugFocusBorder.setColor(ColorConstants.blue());
        debugFocusBorder.setWidth(2f);
    }


    /**
     * Used to render component.
     *
     * @param component component to render.
     * @param context legui context.
     */
    @Override
    public void renderComponent(C component, Context context) {
        long nanovgContext = (long) context.getContextData().get(NVG_CONTEXT);
        if (!component.isVisible() || !Utilites.visibleInParents(component)) {
            return;
        }
        renderComponent(component, context, nanovgContext);
        if (context.isDebugEnabled()) {
            if (component.isFocused()) {
                NvgRendererProvider.getInstance().getBorderRenderer(Border.class).renderBorder(debugFocusBorder, component, context);
            } else {
                NvgRendererProvider.getInstance().getBorderRenderer(Border.class).renderBorder(debugBorder, component, context);
            }
        }
    }

    /**
     * Used to render component.
     *
     * @param component component to render.
     * @param context legui context.
     * @param nanovg nanovg context pointer.
     */
    protected abstract void renderComponent(C component, Context context, long nanovg);

}
