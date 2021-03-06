package com.example.keith.mylibrary;

import com.example.keith.mylibrary.sprites.Sprite;
import com.example.keith.mylibrary.sprites.SpriteCollection;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Keith on 10/03/2016.
 */
public class Scene {
    /**
     * The parent stage for this scene. You MUST not modify the value of this field as it
     * might cause inconsistency in the scene-stage relation.
     */
    protected Stage stage;

    private final SpriteCollection[] layers;
    private boolean loaded = false;
    RGB bgColor = new RGB(0, 0, 0);

    /**
     * Constructs a new scene belonging to the given {@code Stage}.
     * @param parentStage	The parent stage.
     * @see Stage
     */
    public Scene(Stage parentStage) {
        this(parentStage, 3);
    }

    /**
     * Constructs a new scene belonging to the given {@code Stage}.
     * @param parentStage	The parent stage.
     * @param layerCount	The parent stage.
     * @see Stage
     */
    public Scene(Stage parentStage, int layerCount) {
        stage = parentStage;
        layers = new SpriteCollection[layerCount];

        for(int i = 0; i < layers.length; i++)
            layers[i] = new SpriteCollection();
    }

    /**
     * Gets the background color for this scene. The returned object is not a copy, and
     * modifications will take effect in the scene.
     *
     * @return	The background color.
     */
    public final RGB getBackColor() {
        return bgColor;
    }

    /**
     * Sets the background color for this scene.
     * @param color	The background color.
     */
    public final void setBackColor(RGB color) {
        bgColor.r = color.r;
        bgColor.g = color.g;
        bgColor.b = color.b;
    }

    /**
     * Sets the background color for this scene.
     * @param r	The red component of the background color.
     * @param g The green component of the background color.
     * @param b	The blue component of the background color.
     */
    public final void setBackColor(float r, float g, float b) {
        bgColor.r = r;
        bgColor.g = g;
        bgColor.b = b;
    }

    /**
     * Adds a sprite to the scene. Note that if you are using sprites as attachments
     * in game objects, calling this method is not necessary and sprite addition and
     * remvoal will be handled automatically. Use this method only if you are using
     * sprites separately.
     *
     * @param sprite	The sprite to be added.
     * @param level		The scene level (layer) to add the sprite to.
     * @see Sprite
     */
    public final void add(Sprite sprite, int level) {
        layers[level].add(sprite);
    }

    /**
     * Removes a sprite from the scene. Note that if you are using sprites as
     * attachments in game objects, calling this method is not necessary and sprite
     * addition and remvoal will be handled automatically. Use this method only if
     * you are using sprites separately.
     *
     * @param sprite	The sprite to be removed.
     * @see Sprite
     */
    public final void remove(Sprite sprite) {
        for(int i = 0; i < layers.length; i++)
            if(layers[i].remove(sprite))
                break;
    }

    /**
     * Advances the animation for this scene. Subclasses should always call this
     * method from their superclass, as it handles physics and sprite animations.
     *
     * @param elapsedTime	The time elapsed since last frame.
     */
    public void advance(float elapsedTime) {
        for(int i = 0; i < layers.length; i++)
            layers[i].advance(elapsedTime);
    }

    /**
     * Gets the stage that this scene is displayed on.
     * @return	The parent stage.
     */
    public final Stage getStage() {
        return stage;
    }

    /**
     * Indicates whether the resources for this scene are loaded.
     *
     * @return {@code true} if loaded, and {@code false} otherwise.
     */
    public final boolean isLoaded() {
        return loaded;
    }

    /**
     * This method is called when all resources for the scene are safely in
     * place. It is a good entry point for subclasses to create their objects
     * and sprites. Remember to call the superclass method.
     */
    public void onLoaded() {
        loaded = true;
    }

    /**
     * Renders the scene on the given OpenGL context.
     *
     * @param gl	The OpenGL context.
     */
    final void render(GL10 gl) {
        gl.glPushMatrix();
        gl.glMatrixMode(GL10.GL_MODELVIEW);

        for(int i = 0; i < layers.length; i++)
            layers[i].render(gl);

        gl.glPopMatrix();
    }
}