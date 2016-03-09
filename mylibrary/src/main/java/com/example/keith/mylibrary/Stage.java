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

    private final class MyRenderer implements GLSurfaceView.Renderer {
        public final void onDrawFrame(GL10 gl) {
            gl.glPushMatrix();
            gl.glClear(GLES10.GL_COLOR_BUFFER_BIT);
            gl.glTranslatef(w / 2, h / 2, 0);
            gl.glScalef(120, 100, 0);

            gl.glColor4f(0, 1, 0, 1);
            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
            gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
            gl.glPopMatrix();

        }

        public final void onSurfaceChanged(GL10 gl, int width, int height) {
            gl.glClearColor(0, 0, 0, 1.0f);

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

            // No depth
            gl.glDisable(GL10.GL_DEPTH_TEST);

            // Enable vertex arrays (for drawing primitives).
            gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

            // Enable texture coordination arrays.
            gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        }
    }

    /* Stage width and height */
    private float w, h;

    /* Screen width and height */
    private int screenWidth, screenHeight;

    /* Our native vertex buffer */
    private FloatBuffer vertexBuffer;

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
    }
}
