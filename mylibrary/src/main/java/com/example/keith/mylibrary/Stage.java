package com.example.keith.mylibrary;

/**
 * Created by Keith on 08/03/2016.
 */

import android.opengl.GLES10;
import android.util.AttributeSet;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class Stage extends GLSurfaceView  {
    private final class StageAdvanceTask extends Thread {
        private static final int FRAME_DURATION_MS = 20;

        private long mLastTime;

        private StageAdvanceTask() {
            mLastTime = System.nanoTime();
        }

        @Override
        public void run() {
            while(advanceThread != null) {
                final long time = System.nanoTime();
                final long diff = time - mLastTime;

                if(currentScene != null)
                    if(currentScene.isLoaded())
                        currentScene.advance(diff / 1000000000.0f);

                mLastTime = time;

                // Did advancing take less than 17 milliseconds?
                if(diff < (FRAME_DURATION_MS - 2) * 1000000) {
                    try {
                        Thread.sleep(FRAME_DURATION_MS - diff / 1000000);
                    } catch (InterruptedException e) {
                        // Do nothing
                    }
                }
            }
        }
    }

    private class MyRenderer implements GLSurfaceView.Renderer {
        public final void onDrawFrame(GL10 gl) {
            final int ts = TextureManager.getState();

            if(ts != TextureManager.STATE_LOADED) {
                if(ts == TextureManager.STATE_FRESH)
                    TextureManager.loadTextures(getContext());

                return;
            }

            final RGB clearColor = currentScene == null ? defClearColor : currentScene.getBackColor();

            gl.glClearColor(clearColor.r, clearColor.g, clearColor.b, 1.0f);
            gl.glClear(GLES10.GL_COLOR_BUFFER_BIT);
            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);

            if(currentScene != null) {
                if(!currentScene.isLoaded())
                    currentScene.onLoaded();

                currentScene.render(gl);
            }
        }

        public final void onSurfaceChanged(GL10 gl, int width, int height) {
            gl.glClearColor(0, 0, 0, 1);

            if(width > height) {
                h = 600;
                w = width * h / height;
            } else {
                w = 600;
                h = height * w / width;
            }

            gl.glLoadIdentity();

            screenWidth = width;
            screenHeight = height;

            gl.glViewport(0, 0, screenWidth, screenHeight);
            gl.glMatrixMode(GL10.GL_PROJECTION);
            gl.glLoadIdentity();
            gl.glOrthof(0, w, h, 0, -1f, 1f);
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            gl.glLoadIdentity();
        }

        public final void onSurfaceCreated(GL10 gl, EGLConfig config) {
            // Set up alpha blending
            gl.glEnable(GL10.GL_ALPHA_TEST);
            gl.glEnable(GL10.GL_BLEND);

            // We will discuss this line later along with textures
            gl.glBlendFunc(GL10.GL_ONE, GL10.GL_ONE_MINUS_SRC_ALPHA);

            // We are in 2D. Who needs depth!
            gl.glDisable(GL10.GL_DEPTH_TEST);

            // Enable vertex arrays (we'll use them to draw primitives).
            gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

            // Enable texture coordinate arrays.
            gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

            TextureManager.unload();
        }
    }

    public Stage(Context context, AttributeSet attrs) {
        super(context, attrs);
        setEGLConfigChooser(8, 8, 8, 8, 0, 0);

        setRenderer(new MyRenderer());

        float vertices[] = {
                -0.5f, -0.5f,  0.0f,  // 0. left-bottom
                0.5f, -0.5f,  0.0f,  // 1. right-bottom
                -0.5f,  0.5f,  0.0f,  // 2. left-top
                0.5f,  0.5f,  0.0f   // 3. right-top
        };

        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        scheduleTimer();
    }

    @Override
    public void onPause() {
        super.onPause();

        // Once the thread sees this, it will stop running.
        advanceThread = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        scheduleTimer();
    }

    public final void setScene(Scene scene) {
        currentScene = scene;
    }

    public final Scene getScene() {
        return currentScene;
    }

    /**
     * Schedules the advance (frame) timer.
     */
    private void scheduleTimer() {
        if(advanceThread == null) {
            advanceThread = new StageAdvanceTask();
            advanceThread.start();
        }
    }

    private RGB defClearColor = new RGB(0, 0, 0);

    /* Stage width and height */
    private float w, h;

    /* Screen width and height */
    private int screenWidth, screenHeight;

    /* Our native vertex buffer */
    private FloatBuffer vertexBuffer;

    /* The scene to be rendered and animated. */
    private Scene currentScene;

    /* The thread that handles animation. */
    private Thread advanceThread;
}