package com.example.keith.mylibrary.sprites;

/**
 * Created by Keith on 10/03/2016.
 */

import com.example.keith.mylibrary.Texture;
import com.example.keith.mylibrary.TextureManager;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class ImageSprite extends Sprite {
    public static final class Cutout {
        private FloatBuffer[] textureBuffers;
        private float frameWidth, frameHeight;
        private int cols, rows;
        private int startX, startY;

        /**
         * Constructs a cutout that divides the texture into blocks of the given
         * dimensions and takes out the given number of blocks horizontally from
         * the texture. The remainder of the texture will remain unused.
         * @param frameWidth	The width of each block.
         * @param frameHeight	The height of each block.
         * @param frameCount	The number of blocks to cut out of the image.
         */
        public Cutout(float frameWidth, float frameHeight, int frameCount) {
            this(frameWidth, frameHeight, frameCount, 1, 0, 0);
        }

        /**
         * Constructs a cutout that divides the texture into blocks of the given
         * dimensions. You can specify the number of columns and rows and it will
         * cut out a grid of blocks from the texture with the given information.
         * The remainder of the texture will remain unused.
         * @param frameWidth	The width of each block.
         * @param frameHeight	The height of each block.
         * @param frameCountW	The number of horizontal blocks.
         * @param frameCountH	The number of vertical blocks.
         */
        public Cutout(float frameWidth, float frameHeight, int frameCountW, int frameCountH) {
            this(frameWidth, frameHeight, frameCountW, frameCountH, 0, 0);
        }

        /**
         * Constructs a cutout with the information given. This constructor is an
         * extension to the {@code Cutout(float, float, int, int)} constructor
         * that gives you the option to start at a given point in the texture.
         * This can be useful if you are using large atlas textures.
         * @param frameWidth	The width of each block.
         * @param frameHeight	The height of each block.
         * @param cols	The number of horizontal blocks.
         * @param rows	The number of vertical blocks.
         * @param startX	The x coordination of the starting pixel.
         * @param startY	The y coordination of the starting pixel.
         */
        public Cutout(float frameWidth, float frameHeight, int cols, int rows, int startX, int startY) {
            this.frameWidth = frameWidth;
            this.frameHeight = frameHeight;
            this.cols = cols;
            this.rows = rows;
            this.startX = startX;
            this.startY = startY;
        }

        /**
         * Gets the frame (block) width associated with this cutout.
         * @return	The frame width.
         */
        public float getFrameWidth() {
            return frameWidth;
        }

        /**
         * Gets the frame (block) height associated with this cutout.
         * @return	The frame height.
         */
        public float getFrameHeight() {
            return frameHeight;
        }

        /**
         * Determines whether the texture buffers for this cutout have already
         * been generated.
         * @return	{@code true} if buffers are generated or {@code false} otherwise.
         */
        boolean isGenerated() { return textureBuffers != null; }

        void generate(int w, int h) {
            textureBuffers = new FloatBuffer[cols * rows];

            for(int row = 0; row < rows; row++)
                for(int col = 0; col < cols; col++) {
                    final float x1 = (startX + frameWidth * (float)col) / (float)w;
                    final float x2 = (startX + frameWidth * (float)(col + 1)) / (float)w;
                    final float y1 = (startY + frameHeight * (float)row) / (float)h;
                    final float y2 = (startY + frameHeight * (float)(row + 1)) / (float)h;

                    final float texture[] = {
                            x1, y1,
                            x2, y1,
                            x1, y2,
                            x2, y2,
                    };

                    final ByteBuffer ibb = ByteBuffer.allocateDirect(texture.length * 4);
                    ibb.order(ByteOrder.nativeOrder());
                    final FloatBuffer textureBuffer = ibb.asFloatBuffer();
                    textureBuffer.put(texture);
                    textureBuffer.position(0);
                    textureBuffers[row * cols + col] = textureBuffer;
                }
        }
    }

    private Texture frames = null;
    private Cutout cutout = null;
    private int resId = -1;
    private int currentFrame;

    /**
     * Constructs an {@code ImageSprite} with the given texture and {@code Cutout}. When
     * you create an {@code ImageSprite}, make sure that the corresponding texture exists
     * and is loaded beforehand. See {@link com.annahid.libs.artenus.TextureManager} for more details.
     * @param resourceId	The resource identifier for the texture. This can be for an
     * ordinary image (png, jpeg, etc.) or an SVG file.
     * @param co	The cutout instructor to generate frames.
     * @see com.annahid.libs.artenus.TextureManager
     */
    public ImageSprite(int resourceId, Cutout co) {
        super();
        cutout = co;
        resId = resourceId;
        currentFrame = 0;
    }

    /**
     * Gets the texture associated with this {@code ImageSprite}.
     * @return	The associated {@code Texture}.
     */
    public Texture getTexture() {
        return frames;
    }

    /**
     * Sets the current frame for this {@code ImageSprite}. Frames are determined by
     * the associated {@code ImageSprite.Cutout}.
     * @param index	The frame index to change to.
     */
    public void gotoFrame(int index) {
        currentFrame = index;
    }

    /**
     * Gets the current frame for this {@code ImageSprite}. Frames are determined by
     * the associated {@code ImageSprite.Cutout}.
     * @return	The current frame.
     */
    public int getCurrentFrame() {
        return currentFrame;
    }

    @Override
    public void render(GL10 gl) {
        if(frames == null) {
            frames = TextureManager.getTexture(resId);
            return;
        }

        if(alpha != 0) {
            if(!cutout.isGenerated())
                cutout.generate(frames.getWidth(), frames.getHeight());

            final float width = scale.x * cutout.frameWidth;
            final float height = scale.y * cutout.frameHeight;

            frames.prepare(gl, cutout.textureBuffers[currentFrame], GL10.GL_CLAMP_TO_EDGE);
            frames.draw(gl, pos.x, pos.y, width, height, rotation);
        }
    }
}