package com.example.bbciq.sheeps;
import android.graphics.Point;
import android.opengl.GLSurfaceView;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.view.MotionEvent;

import com.example.bbciq.sheeps.engine.GLScreen;
import com.example.bbciq.sheeps.engine.Sprite;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Valentin on 22.06.2015.
 */
public class Renderer implements GLSurfaceView.Renderer {
    private float[] _projectionMatrix = new float[16];
    private float[] _viewMatrix = new float[16];
    private float[] _clearColor;// = new float[4];
    Sprite _testSprite = new Sprite();
    Technique _defaultTech;
    public Renderer() {
        _clearColor = new float[]{0.3f,0.8f,0.5f,1.0f};
    }
    @Override public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(_clearColor[0], _clearColor[1], _clearColor[2], _clearColor[3]);
        Matrix.setIdentityM(_viewMatrix, 0);
        final float eyeX = 0.0f;
        final float eyeY = 0.0f;
        final float eyeZ = 1.5f;

        // We are looking toward the distance
        final float lookX = 0.0f;
        final float lookY = 0.0f;
        final float lookZ = -5.0f;

        // Set our up vector. This is where our head would be pointing were we holding the camera.
        final float upX = 0.0f;
        final float upY = 1.0f;
        final float upZ = 0.0f;

        // Set the view matrix. This matrix can be said to represent the camera position.
        // NOTE: In OpenGL 1, a ModelView matrix is used, which is a combination of a model and
        // view matrix. In OpenGL 2, we can keep track of these matrices separately if we choose.
        Matrix.setLookAtM(_viewMatrix, 0, eyeX, eyeY, eyeZ, lookX, lookY, lookZ, upX, upY, upZ);

        _createDefaultShader();
    }

    @Override public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);

        final float ratio = (float) width / height;
        final float left = -ratio;
        final float right = ratio;
        final float bottom = -1.0f;
        final float top = 1.0f;
        final float near = 1.0f;
        final float far = 100.0f;

        Matrix.frustumM(_projectionMatrix, 0, left, right, bottom, top, near, far);
    }

    @Override public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);
        GLES20.glUseProgram(_defaultTech.handles[0]);
        _testSprite.draw(_defaultTech.handles,_viewMatrix,_projectionMatrix);
    }

    private void _createDefaultShader(){
        final String vertexShader =
                "uniform mat4 u_MVPMatrix;      \n"
                        + "attribute vec4 a_Position;     \n"
                        + "attribute vec4 a_Color;        \n"
                        + "varying vec4 v_Color;          \n"
                        + "void main()                    \n"
                        + "{                              \n"
                        + "   v_Color = a_Color;          \n"
                        + "   gl_Position = u_MVPMatrix   \n"
                        + "               * a_Position;   \n"
                        + "}                              \n";
        final String fragmentShader =
                "precision mediump float;       \n"
                        + "varying vec4 v_Color;          \n"
                        + "void main()                    \n"
                        + "{                              \n"
                        + "   gl_FragColor = v_Color;     \n"
                        + "}                              \n";
        _defaultTech = new Technique(vertexShader, fragmentShader);
    }

    public void onTouchEvent(MotionEvent event){
        final int eventAction = event.getAction();

        switch(eventAction) {
            case MotionEvent.ACTION_MOVE: break;
            case MotionEvent.ACTION_DOWN: break;
            default: break;
        }
    }
}
