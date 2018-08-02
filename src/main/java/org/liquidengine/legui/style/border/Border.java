package org.liquidengine.legui.style.border;

import org.joml.Vector4f;
import org.joml.Vector4fc;
import org.liquidengine.legui.style.common.LineStyle;

/**
 * Abstract class defines hierarchy of borders.
 */
public class Border {

    private Vector4fc topColor;
    private LineStyle topStyle;
    private Float topWidth;

    private Vector4fc rightColor;
    private LineStyle rightStyle;
    private Float rightWidth;

    private Vector4fc bottomColor;
    private LineStyle bottomStyle;
    private Float bottomWidth;

    private Vector4fc leftColor;
    private LineStyle leftStyle;
    private Float leftWidth;

    private Float topLeftRadius;
    private Float topRightRadius;
    private Float bottomRightRadius;
    private Float bottomLeftRadius;

    public Vector4f getBorderRadius() {
        return new Vector4f(topLeftRadius == null ? 0 : topLeftRadius,
                            topRightRadius == null ? 0 : topRightRadius,
                            bottomRightRadius == null ? 0 : bottomRightRadius,
                            bottomLeftRadius == null ? 0 : bottomLeftRadius);
    }
    /**
     * Used to set border radius.
     *
     * @param topLeftBottomRight top left and bottom right radius.
     * @param topRightBottomLeft top right and bottom left radius.
     */
    public void setBorderRadius(Float topLeftBottomRight, Float topRightBottomLeft) {
        topLeftRadius = bottomRightRadius = topLeftBottomRight;
        topRightRadius = bottomLeftRadius = topRightBottomLeft;
    }

    /**
     * Used to set border radius.
     *
     * @param topLeft top left radius.
     * @param bottomRight bottom right radius.
     * @param topRightBottomLeft top right and bottom left radius.
     */
    public void setBorderRadius(Float topLeft, Float topRightBottomLeft, Float bottomRight) {
        topLeftRadius = topLeft;
        topRightRadius = bottomLeftRadius = topRightBottomLeft;
        bottomRightRadius = bottomRight;
    }

    /**
     * Used to set border radius.
     *
     * @param topLeft top left radius.
     * @param topRight top right radius.
     * @param bottomRight bottom right radius.
     * @param bottomLeft bottom left radius.
     */
    public void setBorderRadius(Float topLeft, Float topRight, Float bottomRight, Float bottomLeft) {
        topLeftRadius = topLeft;
        topRightRadius = topRight;
        bottomRightRadius = bottomRight;
        bottomLeftRadius = bottomLeft;
    }

    /**
     * Used to set border radius for all four corners.
     *
     * @param radius radius to set. Sets border radius to all corners.
     */
    public void setBorderRadius(Float radius) {
        topLeftRadius = topRightRadius =
            bottomRightRadius = bottomLeftRadius = radius;
    }


    public void setWidth(Float width) {
        topWidth = rightWidth = bottomWidth = leftWidth = width;
    }

    public void setColor(Vector4fc color) {
        leftColor = rightColor = topColor = bottomColor = color;
    }

    public Vector4fc getTopColor() {
        return topColor;
    }

    public void setTopColor(Vector4fc topColor) {
        this.topColor = topColor;
    }

    public LineStyle getTopStyle() {
        return topStyle;
    }

    public void setTopStyle(LineStyle topStyle) {
        this.topStyle = topStyle;
    }

    public Float getTopWidth() {
        return topWidth;
    }

    public void setTopWidth(Float topWidth) {
        this.topWidth = topWidth;
    }

    public Vector4fc getRightColor() {
        return rightColor;
    }

    public void setRightColor(Vector4fc rightColor) {
        this.rightColor = rightColor;
    }

    public LineStyle getRightStyle() {
        return rightStyle;
    }

    public void setRightStyle(LineStyle rightStyle) {
        this.rightStyle = rightStyle;
    }

    public Float getRightWidth() {
        return rightWidth;
    }

    public void setRightWidth(Float rightWidth) {
        this.rightWidth = rightWidth;
    }

    public Vector4fc getBottomColor() {
        return bottomColor;
    }

    public void setBottomColor(Vector4fc bottomColor) {
        this.bottomColor = bottomColor;
    }

    public LineStyle getBottomStyle() {
        return bottomStyle;
    }

    public void setBottomStyle(LineStyle bottomStyle) {
        this.bottomStyle = bottomStyle;
    }

    public Float getBottomWidth() {
        return bottomWidth;
    }

    public void setBottomWidth(Float bottomWidth) {
        this.bottomWidth = bottomWidth;
    }

    public Vector4fc getLeftColor() {
        return leftColor;
    }

    public void setLeftColor(Vector4fc leftColor) {
        this.leftColor = leftColor;
    }

    public LineStyle getLeftStyle() {
        return leftStyle;
    }

    public void setLeftStyle(LineStyle leftStyle) {
        this.leftStyle = leftStyle;
    }

    public Float getLeftWidth() {
        return leftWidth;
    }

    public void setLeftWidth(Float leftWidth) {
        this.leftWidth = leftWidth;
    }

    public Float getTopLeftRadius() {
        return topLeftRadius;
    }

    public void setTopLeftRadius(Float topLeftRadius) {
        this.topLeftRadius = topLeftRadius;
    }

    public Float getTopRightRadius() {
        return topRightRadius;
    }

    public void setTopRightRadius(Float topRightRadius) {
        this.topRightRadius = topRightRadius;
    }

    public Float getBottomRightRadius() {
        return bottomRightRadius;
    }

    public void setBottomRightRadius(Float bottomRightRadius) {
        this.bottomRightRadius = bottomRightRadius;
    }

    public Float getBottomLeftRadius() {
        return bottomLeftRadius;
    }

    public void setBottomLeftRadius(Float bottomLeftRadius) {
        this.bottomLeftRadius = bottomLeftRadius;
    }
}
