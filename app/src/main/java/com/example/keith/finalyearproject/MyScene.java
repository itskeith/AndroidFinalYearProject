package com.example.keith.finalyearproject;

import com.example.keith.mylibrary.RGB;
import com.example.keith.mylibrary.Scene;
import com.example.keith.mylibrary.Stage;
import com.example.keith.mylibrary.sprites.ImageSprite;

/**
 * Created by Keith on 10/03/2016.
 */
public class MyScene extends Scene {
    public MyScene(Stage parentStage) {
        super(parentStage);
    }

    @Override
    public void onLoaded() {
        setBackColor(new RGB("ffffff"));

    /* Let the stage know that this scene is fully loaded */
        super.onLoaded();

        ImageSprite.Cutout cutout = new ImageSprite.Cutout(128, 128, 7);
        ImageSprite sprite = new ImageSprite(R.drawable.nottexturetest, cutout);
        sprite.setPosition(300, 300);
        add(sprite, 0);
    }
}