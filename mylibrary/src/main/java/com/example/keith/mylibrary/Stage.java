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

public class Stage extends GLSurfaceView {


    private final class MyRenderer implements Renderer {
        public final void onDrawFrame(GL10 gl) {
            gl.glClear(GLES10.GL_COLOR_BUFFER_BIT);
            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
            tex.prepare(gl, GL10.GL_CLAMP_TO_EDGE);
            tex.draw(gl, w / 2, h / 2, tex.getWidth(), tex.getHeight(), 0);
        }

        public final void onSurfaceChanged(GL10 gl, int width, int height) {
            gl.glClearColor(0, 0, 1, 1.0f);

            if(width > height) {
                h = 600;
                w = width * h / height;
            } else {
                w = 600;
                h = height * w / width;
            }

            screenWidth = width;
            screenHeight = height;

            gl.glViewport(0, 0, screenWidth, screenHeight);
            gl.glMatrixMode(GL10.GL_PROJECTION);
            gl.glLoadIdentity();
            gl.glOrthof(0, w, h, 0, -1, 1);
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            gl.glLoadIdentity();
        }

        public final void onSurfaceCreated(GL10 gl, EGLConfig config) {
            // Set up alpha blending
            gl.glEnable(GL10.GL_ALPHA_TEST);
            gl.glEnable(GL10.GL_BLEND);
            gl.glBlendFunc(GL10.GL_ONE, GL10.GL_ONE_MINUS_SRC_ALPHA);

            // We are in 2D. Who needs depth?
            gl.glDisable(GL10.GL_DEPTH_TEST);

            // Enable vertex arrays (we'll use them to draw primitives).
            gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

            tex.load(getContext());
        }
    }

    /* Stage width and height */
    private float w, h;

    /* Screen width and height */
    private int screenWidth, screenHeight;

    /* Our native vertex buffer */
    private FloatBuffer vertexBuffer;

    /* Temporary: texture to be drawn. */
    private Texture tex;

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

        tex = new Texture(R.drawable.nottexture);
    }
}
