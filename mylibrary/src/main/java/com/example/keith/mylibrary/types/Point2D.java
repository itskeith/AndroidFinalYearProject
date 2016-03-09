package com.example.keith.mylibrary.types;

/**
 * Created by Keith on 09/03/2016.
 */
public final class Point2D {
    public float x;
    public float y;

    public Point2D(float px, float py) {
        x = px;
        y = py;
    }

    public void setZero() {
        x = y = 0;
    }

    public Point2D multiply(float scalar) {
        return new Point2D(x * scalar, y * scalar);
    }
}
