package com.example.bbciq.sheeps.engine;
import android.graphics.Point;
import android.opengl.GLSurfaceView;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Валентин on 01.07.2015.
 */
public class GLScreen {
    public Point GetSize(){return _size;}
    public void SetSize(Point size){_size = size;_center.x = _size.x/2;_center.y = _size.y/2;}
    public void SetSizeX(int x){_size.x = x;_center.x = _size.x/2;}
    public void SetSizeY(int y){_size.y = y;_center.y = _size.y/2;}
    protected Point _size;
    public Point GetCenter(){return _center;}
    protected Point _center;

    public GLScreen() {
    }
    public void onDrawFrame() {

    }
    public void onUpdate() {

    }
    public void onSurfaceChanged(Point size){
        SetSize(size);
    }
}
