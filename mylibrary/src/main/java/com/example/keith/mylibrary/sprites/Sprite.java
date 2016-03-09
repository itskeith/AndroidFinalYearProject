package com.example.keith.mylibrary.sprites;

import com.example.keith.mylibrary.types.Point2D;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Keith on 09/03/2016.
 */

public abstract class Sprite {
    protected final Point2D pos;
    protected final Point2D scale;
    protected float rotation;
    protected float alpha;

    protected Sprite() {
        pos = new Point2D(0.0f, 0.0f);
        scale = new Point2D(1.0f, 1.0f);
        rotation = 0.0f;
        alpha = 1.0f;
    }

    public final Point2D getPosition() { return pos; }
    public final Point2D getScale() { return scale; }

    public final void setScale(float scaleValue) {
        scale.x = scale.y = scaleValue;
    }

    public final void setScale(float scaleX, float scaleY) {
        scale.x = scaleX;
        scale.y = scaleY;
    }

    public final void setScale(Point2D sc) {
        if(sc != null)
            setScale(sc.x, sc.y);
    }

    public final void setPosition(Point2D position) {
        if(position != null)
            setPosition(position.x, position.y);
    }

    public void setPosition(float x, float y) {
        pos.x = x;
        pos.y = y;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public final void rotate(float angle) {
        rotation += (angle % 360);
    }

    public final void move(float amountX, float amountY) {
        pos.x += amountX;
        pos.y += amountY;
    }

    public abstract void render(GL10 gl);
}
