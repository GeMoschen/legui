package org.liquidengine.legui.system.renderer.nvg.border;

import org.joml.Math;
import org.joml.Vector2f;
import org.joml.Vector4f;
import org.joml.Vector4fc;
import org.liquidengine.legui.component.Component;
import org.liquidengine.legui.input.Mouse;
import org.liquidengine.legui.style.Style;
import org.liquidengine.legui.style.border.Border;
import org.liquidengine.legui.style.color.ColorConstants;
import org.liquidengine.legui.style.common.LineStyle;
import org.liquidengine.legui.system.context.Context;
import org.liquidengine.legui.system.renderer.nvg.NvgBorderRenderer;
import org.liquidengine.legui.system.renderer.nvg.util.NvgColorUtil;
import org.lwjgl.nanovg.NVGColor;
import org.lwjgl.nanovg.NVGPaint;
import org.lwjgl.nanovg.NanoVG;

/**
 * Created by ShchAlexander on 11.02.2017.
 */
public class NvgDefaultBorderRenderer<B extends Border> extends NvgBorderRenderer<B> {

    @Override
    protected void renderBorder(B border, Component component, Context context, long nanovg) {
        if (!component.isVisible()) {
            return;
        }
        // render simple rectangle border
        Vector2f absolutePosition = component.getAbsolutePosition();
        Vector2f size = component.getSize();

        float x = absolutePosition.x;
        float y = absolutePosition.y;
        float w = size.x;
        float h = size.y;

        Style style = component.getStyle();

        Float topWidth = style.getBorder().getTopWidth();
        Float leftWidth = style.getBorder().getLeftWidth();
        Float rightWidth = style.getBorder().getRightWidth();
        Float bottomWidth = style.getBorder().getBottomWidth();

        Vector4fc topColor = style.getBorder().getTopColor();
        Vector4fc leftColor = style.getBorder().getLeftColor();
        Vector4fc rightColor = style.getBorder().getRightColor();
        Vector4fc bottomColor = style.getBorder().getBottomColor();

        LineStyle topStyle = style.getBorder().getTopStyle();
        LineStyle leftStyle = style.getBorder().getLeftStyle();
        LineStyle rightStyle = style.getBorder().getRightStyle();
        LineStyle bottomStyle = style.getBorder().getBottomStyle();

        Float topRightRadius = style.getBorder().getTopRightRadius();
        Float topLeftRadius = style.getBorder().getTopLeftRadius();
        Float bottomLeftRadius = style.getBorder().getBottomLeftRadius();
        Float bottomRightRadius = style.getBorder().getBottomRightRadius();

        float tlr = (topLeftRadius != null && topLeftRadius > 0) ? topLeftRadius : 0;
        float trr = (topRightRadius != null && topRightRadius > 0) ? topRightRadius : 0;
        float blr = (bottomLeftRadius != null && bottomLeftRadius > 0) ? bottomLeftRadius : 0;
        float brr = (bottomRightRadius != null && bottomRightRadius > 0) ? bottomRightRadius : 0;

        NVGColor color = NVGColor.calloc();
        NVGColor color2 = NVGColor.calloc();
        NVGPaint paint = NVGPaint.calloc();
        float delta = 0.4f;
        if (topColor != null && topWidth != null && topWidth != 0) {
            if (!LineStyle.NONE.equals(topStyle)) {
                float bottomLeftX = x;
                float bottomLeftY = y;
                float bottomRightX = x + w;
                float bottomRightY = y;
                float topLeftX = bottomLeftX - leftWidth;
                float topLeftY = bottomLeftY - topWidth;
                float topRightX = bottomRightX + rightWidth;
                float topRightY = bottomRightY - topWidth;

                NvgColorUtil.rgba(topColor, color);
                NvgColorUtil.rgba(new Vector4f(topColor).sub(85f / 255f, 85f / 255f, 85f / 255f, 0), color2);

                if (LineStyle.SOLID.equals(topStyle)) {
                    NanoVG.nvgFillColor(nanovg, color);
                } else if (LineStyle.GROOVE.equals(topStyle)) {
                    float sy = (topLeftY + bottomLeftY) / 2f;
                    NanoVG.nvgLinearGradient(nanovg, topLeftX, sy + 0.001f, topLeftX, sy - 0.001f, color, color2, paint);
                    NanoVG.nvgFillPaint(nanovg, paint);
                }

                NanoVG.nvgBeginPath(nanovg);
                NanoVG.nvgMoveTo(nanovg, (float) (topLeftX + (1 - Math.sin(Math.PI / 4)) * tlr), (float) (topLeftY + (1 - Math.sin(Math.PI / 4)) * tlr));
                NanoVG.nvgArc(nanovg, topLeftX + tlr, topLeftY + tlr, tlr, (float) (5f * Math.PI / 4f), (float) (6f * Math.PI / 4f), NanoVG.NVG_CW);
                NanoVG.nvgLineTo(nanovg, topRightX - trr, topRightY);
                NanoVG.nvgArc(nanovg, topRightX - trr, topRightY + trr, trr, (float) (6f * Math.PI / 4f), (float) (7f * Math.PI / 4f), NanoVG.NVG_CW);
                NanoVG.nvgLineTo(nanovg, bottomRightX + delta, bottomRightY + delta);
                NanoVG.nvgLineTo(nanovg, bottomLeftX - delta, bottomLeftY + delta);
                NanoVG.nvgLineTo(nanovg, (float) (topLeftX + (1 - Math.sin(Math.PI / 4)) * tlr), (float) (topLeftY + (1 - Math.sin(Math.PI / 4)) * tlr));
                NanoVG.nvgFill(nanovg);
            }
        }
        if (rightColor != null && rightWidth != null && rightWidth != 0) {

            float topLeftX = x + w;
            float topLeftY = y;
            float topRightX = topLeftX + rightWidth;
            float topRightY = topLeftY - topWidth;
            float bottomLeftX = x + w;
            float bottomLeftY = y + h;
            float bottomRightX = bottomLeftX + rightWidth;
            float bottomRightY = bottomLeftY + bottomWidth;

            NvgColorUtil.rgba(rightColor, color);
            NanoVG.nvgFillColor(nanovg, color);

            NanoVG.nvgBeginPath(nanovg);
            NanoVG.nvgMoveTo(nanovg, (float) (topRightX - (1 - Math.sin(Math.PI / 4)) * trr), (float) (topRightY + (1 - Math.sin(Math.PI / 4)) * trr));
            NanoVG.nvgArc(nanovg, topRightX - trr, topRightY + trr, trr, (float) (7f * Math.PI / 4f), (float) (8f * Math.PI / 4f), NanoVG.NVG_CW);
            NanoVG.nvgLineTo(nanovg, bottomRightX, bottomRightY - brr);
            NanoVG.nvgArc(nanovg, bottomRightX - brr, bottomRightY - brr, brr, (float) (8f * Math.PI / 4f), (float) (1f * Math.PI / 4f), NanoVG.NVG_CW);
            NanoVG.nvgLineTo(nanovg, bottomLeftX - delta, bottomLeftY + delta);
            NanoVG.nvgLineTo(nanovg, topLeftX - delta, topLeftY - delta);
            NanoVG.nvgLineTo(nanovg, (float) (topRightX - (1 - Math.sin(Math.PI / 4)) * trr), (float) (topRightY + (1 - Math.sin(Math.PI / 4)) * trr));
            NanoVG.nvgFill(nanovg);
        }
        if (bottomColor != null && bottomWidth != null && bottomWidth != 0) {
            float topLeftX = x;
            float topLeftY = y + h;
            float topRightX = x + w;
            float topRightY = y + h;
            float bottomLeftX = topLeftX - leftWidth;
            float bottomLeftY = topLeftY + bottomWidth;
            float bottomRightX = topRightX + rightWidth;
            float bottomRightY = topRightY + bottomWidth;

            NvgColorUtil.rgba(bottomColor, color);
            NanoVG.nvgFillColor(nanovg, color);

            NanoVG.nvgBeginPath(nanovg);
            NanoVG.nvgMoveTo(nanovg, (float) (bottomRightX - (1 - Math.sin(Math.PI / 4)) * brr), (float) (bottomRightY - (1 - Math.sin(Math.PI / 4)) * brr));
            NanoVG.nvgArc(nanovg, bottomRightX - brr, bottomRightY - brr, brr, (float) (1f * Math.PI / 4f), (float) (2f * Math.PI / 4f), NanoVG.NVG_CW);
            NanoVG.nvgLineTo(nanovg, bottomLeftX + blr, bottomLeftY);
            NanoVG.nvgArc(nanovg, bottomLeftX + blr, bottomLeftY - blr, blr, (float) (2f * Math.PI / 4f), (float) (3f * Math.PI / 4f), NanoVG.NVG_CW);
            NanoVG.nvgLineTo(nanovg, topLeftX - delta, topLeftY - delta);
            NanoVG.nvgLineTo(nanovg, topRightX + delta, topRightY - delta);
            NanoVG.nvgLineTo(nanovg, (float) (bottomRightX - (1 - Math.sin(Math.PI / 4)) * brr), (float) (bottomRightY - (1 - Math.sin(Math.PI / 4)) * brr));
            NanoVG.nvgFill(nanovg);
        }

        if (leftColor != null && leftWidth != null && leftWidth != 0) {
            float topRightX = x;
            float topRightY = y;
            float topLeftX = topRightX - leftWidth;
            float topLeftY = topRightY - topWidth;
            float bottomRightX = x;
            float bottomRightY = y + h;
            float bottomLeftX = bottomRightX - leftWidth;
            float bottomLeftY = bottomRightY + bottomWidth;

            NvgColorUtil.rgba(leftColor, color);
            NanoVG.nvgFillColor(nanovg, color);

            NanoVG.nvgBeginPath(nanovg);
            NanoVG.nvgMoveTo(nanovg, (float) (bottomLeftX + (1 - Math.sin(Math.PI / 4)) * blr), (float) (bottomLeftY - (1 - Math.sin(Math.PI / 4)) * blr));
            NanoVG.nvgArc(nanovg, bottomLeftX + blr, bottomLeftY - blr, blr, (float) (3f * Math.PI / 4f), (float) (4f * Math.PI / 4f), NanoVG.NVG_CW);
            NanoVG.nvgLineTo(nanovg, topLeftX, topLeftY + tlr);
            NanoVG.nvgArc(nanovg, topLeftX + tlr, topLeftY + tlr, tlr, (float) (4f * Math.PI / 4f), (float) (5f * Math.PI / 4f), NanoVG.NVG_CW);
            NanoVG.nvgLineTo(nanovg, topRightX + delta, topRightY - delta);
            NanoVG.nvgLineTo(nanovg, bottomRightX + delta, bottomRightY + delta);
            NanoVG.nvgLineTo(nanovg, (float) (bottomLeftX + (1 - Math.sin(Math.PI / 4)) * blr), (float) (bottomLeftY - (1 - Math.sin(Math.PI / 4)) * blr));
            NanoVG.nvgFill(nanovg);
        }

        color.free();
        color2.free();
        paint.free();
    }
}
