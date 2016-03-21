package com.example.keith.mylibrary.sprites;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Keith on 10/03/2016.
 */
public final class SpriteCollection {
    private Sprite[] elementData;
    private int size;

    public final Sprite get(int index) {
        if (index < size)
            return elementData[index];

        return null;
    }

    public SpriteCollection(int initialCapacity) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);

        elementData = new Sprite[initialCapacity];
        size = 0;
    }

    public SpriteCollection() {
        this(20);
    }

    private void ensureCapacity(int minCapacity) {
        int oldCapacity = elementData.length;

        if (minCapacity > oldCapacity) {
            int newCapacity = (oldCapacity * 3) / 2 + 1;

            if (newCapacity < minCapacity)
                newCapacity = minCapacity;

            final Sprite[] newElementData = new Sprite[newCapacity];
            System.arraycopy(elementData, 0, newElementData, 0, size);
            elementData = newElementData;
        }
    }

    public final void add(Sprite sprite) {
        ensureCapacity(size + 1);
        elementData[size++] = sprite;
    }

    public final void add(int index, Sprite sprite) {
        if (index >= 0 && index < size) {
            ensureCapacity(size + 1);
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
            elementData[index] = sprite;
            size++;
        }
    }

    public final boolean remove(Sprite sprite) {
        for (int index = 0; index < size; index++)
            if (sprite.equals(elementData[index])) {
                final int numMoved = size - index - 1;

                if (numMoved > 0)
                    System.arraycopy(elementData, index + 1, elementData, index, numMoved);

                elementData[--size] = null;
                return true;
            }

        return false;
    }

    public final void clear() {
        for (int i = 0; i < size; i++)
            elementData[i] = null;

        size = 0;
    }

    public final void render(GL10 gl) {
        for (int i = 0; i < size; i++)
            if (elementData[i] != null)
                elementData[i].render(gl);
    }

    public final void advance(float elapsedTime) {
        for (int i = 0; i < size; i++)
            if (elementData[i] != null)
                elementData[i].advanceAnimation(elapsedTime);
    }
}