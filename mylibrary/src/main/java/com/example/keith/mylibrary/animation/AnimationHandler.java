package com.example.keith.mylibrary.animation;

import com.example.keith.mylibrary.sprites.Sprite;

/**
 * Created by Keith on 10/03/2016.
 */
public interface AnimationHandler {
    public void advance(Sprite sprite, float elapsedTime);
}
