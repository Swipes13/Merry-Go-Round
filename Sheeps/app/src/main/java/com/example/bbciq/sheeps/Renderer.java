package com.example.bbciq.sheeps;
import android.graphics.Point;
import android.opengl.GLSurfaceView;
import android.opengl.GLES20;
import android.opengl.Matrix;
import com.example.bbciq.sheeps.engine.GLScreen;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Валентин on 22.06.2015.
 */
public class Renderer implements GLSurfaceView.Renderer {
    public enum AppState { Hello, Menu, Game }
    private AppState _appState = AppState.Hello;

    private GLScreen _helloScreen = new GLScreen();
    private GLScreen _menuScreen = new GLScreen();
    private GLScreen _gameScreen = new GLScreen();

    private float[] _projectionMatrix = new float[16];
    private float[] _clearColor;// = new float[4];
    @Override public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        _clearColor = new float[]{0.3f,0.8f,0.5f,1.0f};
        GLES20.glClearColor(_clearColor[0],_clearColor[1],_clearColor[2],_clearColor[3]);
        //_helloScreen = new GLScreen(new Point())
    }

    @Override public void onSurfaceChanged(GL10 gl, int width, int height) {
        // Устанавливаем OpenGL окно просмотра того же размера что и поверхность экрана.
        GLES20.glViewport(0, 0, width, height);

        // Создаем новую матрицу проекции. Высота остается та же,
        // а ширина будет изменяться в соответствии с соотношением сторон.
        final float ratio = (float) width / height;
        final float left = -ratio;
        final float right = ratio;
        final float bottom = -1.0f;
        final float top = 1.0f;
        final float near = 1.0f;
        final float far = 100.0f;

        Matrix.frustumM(_projectionMatrix, 0, left, right, bottom, top, near, far);
        _helloScreen.onSurfaceChanged(new Point(width, height));
        _menuScreen.onSurfaceChanged(new Point(width,height));
        _gameScreen.onSurfaceChanged(new Point(width,height));
    }

    @Override public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);
        switch (_appState){
            case Hello: _helloScreen.onDrawFrame(); break;
            case Menu: _menuScreen.onDrawFrame(); break;
            case Game: _gameScreen.onDrawFrame(); break;
            default: break;
        }
    }
}
