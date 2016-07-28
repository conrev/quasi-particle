package com.quasiparticle.Math;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by conrev on 7/13/16.
 */
public class Rectangle {
    public float x, y;
    public float width, height;

    public Rectangle () {

    }


    public Rectangle (float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


    public Rectangle (Rectangle rect) {
        x = rect.x;
        y = rect.y;
        width = rect.width;
        height = rect.height;
    }

    public Rectangle set (float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        return this;
    }


    public float getX () {
        return x;
    }


    public Rectangle setX (float x) {
        this.x = x;

        return this;
    }

    public float getY () {
        return y;
    }


    public Rectangle setY (float y) {
        this.y = y;

        return this;
    }

    public float getWidth () {
        return width;
    }


    public Rectangle setWidth (float width) {
        this.width = width;

        return this;
    }

    public float getHeight () {
        return height;
    }


    public Rectangle setHeight (float height) {
        this.height = height;

        return this;
    }

    public Vector2 getPosition (Vector2 position) {
        return position.set(x, y);
    }


    public Rectangle setPosition (Vector2 position) {
        this.x = position.x;
        this.y = position.y;

        return this;
    }


    public Rectangle setPosition (float x, float y) {
        this.x = x;
        this.y = y;

        return this;
    }


    public Rectangle setSize (float width, float height) {
        this.width = width;
        this.height = height;

        return this;
    }

    public Rectangle setSize (float sizeXY) {
        this.width = sizeXY;
        this.height = sizeXY;

        return this;
    }


    public Vector2 getSize (Vector2 size) {
        return size.set(width, height);
    }


    public boolean contains (float x, float y) {
        return this.x <= x && this.x + this.width >= x && this.y <= y && this.y + this.height >= y;
    }


    public boolean contains (Vector2 point) {
        return contains(point.x, point.y);
    }


    public boolean contains (Rectangle rectangle) {
        float xmin = rectangle.x;
        float xmax = xmin + rectangle.width;

        float ymin = rectangle.y;
        float ymax = ymin + rectangle.height;

        return ((xmin > x && xmin < x + width) && (xmax > x && xmax < x + width))
                && ((ymin > y && ymin < y + height) && (ymax > y && ymax < y + height));
    }


    public boolean overlaps (Rectangle r) {
        return x < r.x + r.width && x + width > r.x && y < r.y + r.height && y + height > r.y;
    }


    public Rectangle set (Rectangle rect) {
        this.x = rect.x;
        this.y = rect.y;
        this.width = rect.width;
        this.height = rect.height;

        return this;
    }


    public Rectangle merge (Rectangle rect) {
        float minX = Math.min(x, rect.x);
        float maxX = Math.max(x + width, rect.x + rect.width);
        x = minX;
        width = maxX - minX;

        float minY = Math.min(y, rect.y);
        float maxY = Math.max(y + height, rect.y + rect.height);
        y = minY;
        height = maxY - minY;

        return this;
    }

    public Rectangle merge (float x, float y) {
        float minX = Math.min(this.x, x);
        float maxX = Math.max(this.x + width, x);
        this.x = minX;
        this.width = maxX - minX;

        float minY = Math.min(this.y, y);
        float maxY = Math.max(this.y + height, y);
        this.y = minY;
        this.height = maxY - minY;

        return this;
    }

    public Rectangle merge (Vector2 vec) {
        return merge(vec.x, vec.y);
    }

    public Rectangle merge (Vector2[] vecs) {
        float minX = x;
        float maxX = x + width;
        float minY = y;
        float maxY = y + height;
        for (int i = 0; i < vecs.length; ++i) {
            Vector2 v = vecs[i];
            minX = Math.min(minX, v.x);
            maxX = Math.max(maxX, v.x);
            minY = Math.min(minY, v.y);
            maxY = Math.max(maxY, v.y);
        }
        x = minX;
        width = maxX - minX;
        y = minY;
        height = maxY - minY;
        return this;
    }


    public float getAspectRatio () {
        return (height == 0) ? Float.NaN : width / height;
    }

    public Vector2 getCenter (Vector2 vector) {
        vector.x = x + width / 2;
        vector.y = y + height / 2;
        return vector;
    }

    public Rectangle setCenter (float x, float y) {
        setPosition(x - width / 2, y - height / 2);
        return this;
    }

    public Rectangle setCenter (Vector2 position) {
        setPosition(position.x - width / 2, position.y - height / 2);
        return this;
    }

    public Rectangle fitOutside (Rectangle rect) {
        float ratio = getAspectRatio();

        if (ratio > rect.getAspectRatio()) {
            // Wider than tall
            setSize(rect.height * ratio, rect.height);
        } else {
            // Taller than wide
            setSize(rect.width, rect.width / ratio);
        }

        setPosition((rect.x + rect.width / 2) - width / 2, (rect.y + rect.height / 2) - height / 2);
        return this;
    }

    public Rectangle fitInside (Rectangle rect) {
        float ratio = getAspectRatio();

        if (ratio < rect.getAspectRatio()) {
            // Taller than wide
            setSize(rect.height * ratio, rect.height);
        } else {
            // Wider than tall
            setSize(rect.width, rect.width / ratio);
        }

        setPosition((rect.x + rect.width / 2) - width / 2, (rect.y + rect.height / 2) - height / 2);
        return this;
    }

    public float area () {
        return this.width * this.height;
    }

    public float perimeter () {
        return 2 * (this.width + this.height);
    }
}
