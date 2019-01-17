package org.liquidengine.legui.system.renderer.nvg;

import org.lwjgl.nanovg.NanoVGGL2;
import org.lwjgl.nanovg.NanoVGGL3;
import org.lwjgl.opengl.GL30;

import static org.lwjgl.nanovg.NanoVG.nvgBeginFrame;
import static org.lwjgl.nanovg.NanoVG.nvgEndFrame;
import static org.lwjgl.opengl.GL11.glGetInteger;

public class NvgContext {

	private final boolean isVersionNew;
	private long id;

	public NvgContext() {
		isVersionNew = (glGetInteger(GL30.GL_MAJOR_VERSION) > 3) || (glGetInteger(GL30.GL_MAJOR_VERSION) == 3 && glGetInteger(GL30.GL_MINOR_VERSION) >= 2);

		if (isVersionNew) {
			int flags = NanoVGGL3.NVG_STENCIL_STROKES | NanoVGGL3.NVG_ANTIALIAS;
			id = NanoVGGL3.nvgCreate(flags);
		} else {
			int flags = NanoVGGL2.NVG_STENCIL_STROKES | NanoVGGL2.NVG_ANTIALIAS;
			id = NanoVGGL2.nvgCreate(flags);
		}
	}

	public void beginFrame(float width, float height, float pixelRatio) {
		if(getId() == Long.MIN_VALUE) {
			return;
		}
		nvgBeginFrame(getId(), width, height, pixelRatio);
	}

	public void endFrame() {
		if(getId() == Long.MIN_VALUE) {
			return;
		}
		nvgEndFrame(getId());
	}

	public long getId() {
		return id;
	}

	public void destroy() {
		if (id == Long.MIN_VALUE) {
			return;
		}
		if (isVersionNew) {
			NanoVGGL3.nnvgDelete(id);
		} else {
			NanoVGGL2.nnvgDelete(id);
		}
		id = Long.MIN_VALUE;
	}

}
