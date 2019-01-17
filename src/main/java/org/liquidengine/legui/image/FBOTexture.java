package org.liquidengine.legui.image;

import org.joml.Vector4f;
import org.liquidengine.legui.system.context.Context;
import org.liquidengine.legui.system.renderer.nvg.NvgContext;
import org.lwjgl.opengl.GL30;

import static org.liquidengine.legui.system.renderer.nvg.NvgRenderer.NVG_CONTEXT;
import static org.lwjgl.nanovg.NanoVG.nvgBeginFrame;
import static org.lwjgl.nanovg.NanoVG.nvgEndFrame;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL32.glFramebufferTexture;

public class FBOTexture {

	private FBOContext context;

	private FBOImage image;
	private int width;
	private int height;

	private int textureId;
	private int frameBufferId;
	private int renderBufferId;

	private boolean bound;

	public FBOTexture(int width, int height) {
		this.context = new FBOContext(width, height);
		this.image = createImage(width, height);
	}

	public void setSize(int width, int height) {
		cleanupImage();
		createImage(width, height);
	}

	/**
	 * Returns image width.
	 *
	 * @return image width.
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * Returns image height.
	 *
	 * @return image height.
	 */
	public int getHeight() {
		return this.height;
	}

	public Context getContext() {
		return context;
	}

	public FBOImage getImage() {
		return image;
	}

	public void bind() {
		if (bound) {
			return;
		}
		getContext().getContextData().put(NVG_CONTEXT, context.getNvgContext());

		// bind fbo
		glBindFramebuffer(GL_FRAMEBUFFER, frameBufferId);
		glViewport(0, 0, getWidth(), getHeight());

		// begin frame
		context.getNvgContext().beginFrame(getWidth(), getHeight(), 1);
		bound = true;
	}

	public void unbind() {
		if (!bound) {
			return;
		}

		// end frame
		context.getNvgContext().endFrame();

		// unbind fbo
		glBindFramebuffer(GL_FRAMEBUFFER, 0);
		bound = false;
	}

	/**
	 * Cleans up this FBO (deletes textures and buffers)
	 */
	public void destroy() {
		cleanupImage();
		this.context.destroy();
	}

	private void cleanupImage() {
		if (bound) {
			unbind();
		}
		if (textureId != 0) {
			glDeleteTextures(textureId);
		}
		if (renderBufferId != 0) {
			glDeleteRenderbuffers(renderBufferId);
		}
		if (frameBufferId != 0) {
			glDeleteFramebuffers(frameBufferId);
		}
		this.image = null;
		this.width = 0;
		this.height = 0;
	}

	private FBOImage createImage(int width, int height) {
		frameBufferId = GL30.glGenFramebuffers();
		glBindFramebuffer(GL_FRAMEBUFFER, frameBufferId);

		// The texture we're going to render to
		textureId = glGenTextures();
		// "Bind" the newly created texture : all future texture functions will modify this texture
		glBindTexture(GL_TEXTURE_2D, textureId);

		// Give an empty image to OpenGL ( the last "0" )
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, 0);

		// Poor filtering. Needed !
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);

		// The depth buffer
		renderBufferId = glGenRenderbuffers();
		glBindRenderbuffer(GL_RENDERBUFFER, renderBufferId);
		glRenderbufferStorage(GL_RENDERBUFFER, GL_DEPTH_COMPONENT, width, height);
		glFramebufferRenderbuffer(GL_FRAMEBUFFER, GL_DEPTH_ATTACHMENT, GL_RENDERBUFFER, renderBufferId);

		// Set "textureID" as our colour attachment #0
		glFramebufferTexture(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, textureId, 0);

		// Set the list of draw buffers.
		glDrawBuffer(GL_COLOR_ATTACHMENT0);
		glBindFramebuffer(GL_FRAMEBUFFER, 0);

		// update dimensions
		this.width = width;
		this.height = height;
		return new FBOImage(textureId, this.width, this.height);
	}

	public void setBackgroundColor(Vector4f color) {
		setBackgroundColor(color.x, color.y, color.z, color.z);
	}

	public void setBackgroundColor(float r, float g, float b, float a) {
		if (!bound) {
			return;
		}
		glClearColor(r, g, b, a);
	}

	private void clearBit(int bit) {
		if (!bound) {
			return;
		}
		glClear(bit);
	}

	public void clearColor() {
		clearBit(GL_COLOR_BUFFER_BIT);
	}

	public void clearDepth() {
		clearBit(GL_DEPTH_BUFFER_BIT);
	}

	private static class FBOContext extends Context {

		private final NvgContext nvgContext;

		FBOContext(int width, int height) {
			super(0);
			this.nvgContext = new NvgContext();
			update(width, height, width, height, 0, 0, false);
		}

		public NvgContext getNvgContext() {
			return nvgContext;
		}


		@Override
		public void updateGlfwWindow() {
			// do nothing
		}

		public void destroy() {
			nvgContext.destroy();
		}
	}

}