package org.liquidengine.legui.component.misc.listener.layercontainer;

import org.liquidengine.legui.event.WindowSizeEvent;
import org.liquidengine.legui.image.FBOTexture;
import org.liquidengine.legui.listener.WindowSizeEventListener;

/**
 * Window size event listener for layer container. Used to update layer container size on window resize.
 */
public class LayerContainerWindowSizeEventListener implements WindowSizeEventListener {

	private final FBOTexture fboTexture;

	public LayerContainerWindowSizeEventListener(final FBOTexture fboTexture) {
		this.fboTexture = fboTexture;
	}

	/**
     * Used to process {@link WindowSizeEvent}.
     *
     * @param event event to process.
     */
    @Override
    public void process(WindowSizeEvent event) {
        event.getTargetComponent().getSize().set(event.getWidth(), event.getHeight());
        event.getTargetComponent().invalidateLayout();
		fboTexture.setSize(event.getWidth(), event.getHeight());
    }

    @Override
    public boolean equals(Object obj) {
        return (obj != null) && ((obj == this) || ((obj != this) && (obj.getClass() == this.getClass())));
    }
}
