package com.example.keith.mylibrary.animation;

import com.example.keith.mylibrary.sprites.ImageSprite;
import com.example.keith.mylibrary.sprites.Sprite;

/**
 * Created by Keith on 10/03/2016.
 */
public final class ImageAnimation implements AnimationHandler {
    /**
     * An animation trend that involves playing frames in a loop.
     */
    public static final int TREND_LOOP = 0;

    /**
     * An animation trend that involves playing frames only once. Once the
     * animation reaches the final frame, it stops at that frame.
     */
    public static final int TREND_ONCE = 1;

    /**
     * An animation trend that plays frames backwards after it reaches the
     * final frame and goes into a forward trend when it reaches the first
     * frame and plays this way in a loop.
     */
    public static final int TREND_PINGPONG = 2;

    private int[] frames;
    private int trend;
    private int currentFrame;
    private int frameDelay = 33;
    private int delta;
    private long lastFrame = 0;

    /**
     * Constructs an {@code ImageAnimation} with the given set of frames and a loop trend.
     * @param animationFrames	The array of frames from the cutout to animate through.
     * @see #TREND_LOOP
     */
    public ImageAnimation(int[] animationFrames) {
        this(animationFrames, TREND_LOOP, 0);
    }

    /**
     * Constructs an {@code ImageAnimation} with the given set of frames and trend.
     * @param animationFrames	The array of frames from the cutout to animate through.
     * @param trend	The trend at which the frames will be animated.
     * @see #TREND_LOOP
     * @see #TREND_ONCE
     * @see #TREND_PINGPONG
     */
    public ImageAnimation(int[] animationFrames, int trend) {
        this(animationFrames, trend, 0);
    }

    /**
     * Constructs an {@code ImageAnimation} with the given set of frames and trend. The animation
     * is started at a given frame index.
     * @param animationFrames	The array of frames from the cutout to animate through.
     * @param trend	The trend at which the frames will be animated.
     * @param startIndex	The frame index at which the animation should start. This
     * index determines the item in the frame array and not the frame index belonging
     * to the sprite's cutout.
     * @see #TREND_LOOP
     * @see #TREND_ONCE
     * @see #TREND_PINGPONG
     * @see com.annahid.libs.artenus.sprites.ImageSprite.Cutout
     */
    public ImageAnimation(int[] animationFrames, int trend, int startIndex) {
        frames = animationFrames;
        this.trend = trend;
        delta = 1;
        currentFrame = startIndex;
    }

    /**
     * Adjusts frame interval for the animation. This is a legacy method which is still supported.
     * It is recommended to use {@link #setFrameDelay(float)} instead as it is guaranteed not to
     * be deprecated in future versions.
     * @param delay	The interval between frames in milliseconds.
     * @see #setFrameDelay(float)
     */
    public void setFrameDelay(int delay) {
        frameDelay = delay;
        lastFrame = System.currentTimeMillis();
    }

    /**
     * Adjusts frame interval for the animation. The difference between this method and
     * {@link #setFrameDelay(int)} is that this method gets the delay in seconds and in floating
     * point format, which is compatible with other functionalities of the framework.
     * @param delay	The interval between frames in seconds.
     * @see #setFrameDelay(int)
     */
    public void setFrameDelay(float delay) {
        frameDelay = (int)(delay * 1000);
        lastFrame = System.currentTimeMillis();
    }

    /**
     * Gets the current frame index of the animation.
     * @return The current frame of the animation. This value indicates the index in the frame
     * array, and not the frame index belonging to the sprite's cutout.
     *
     */
    public int getFrame() { return currentFrame; }

    /**
     * Gets the trend of this image animation. The value can  be one of {@link #TREND_LOOP},
     * {@link #TREND_ONCE} or {@link #TREND_PINGPONG}.
     * @see #TREND_LOOP
     * @see #TREND_ONCE
     * @see #TREND_PINGPONG
     * @return	The animation trend.
     */
    public int getTrend() { return trend; }

    /**
     * Called whenever the animation should update the image sprite based on elapsed time.
     * Frame timing is automatically handled based on your adjustment using {@link #setFrameDelay(int)}.
     * @see ImageAnimation#setFrameDelay(int)
     */
    public void advance(Sprite sprite, float elapsedTime) {
        if(System.currentTimeMillis() - lastFrame >= frameDelay)
            lastFrame = System.currentTimeMillis();
        else return;

        if(trend == TREND_LOOP)
            currentFrame = (currentFrame + 1) % frames.length;
        else if(trend == TREND_PINGPONG) {
            currentFrame += delta;

            if(currentFrame == 0 || currentFrame == frames.length - 1)
                delta = -delta;
        }
        else if(currentFrame < frames.length - 1)
            currentFrame++;

        ((ImageSprite)sprite).gotoFrame(frames[currentFrame]);
    }
}