package com.example.bbciq.sheeps;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;


public class MainActivity extends Activity {
    private GLSurfaceView _GLSurfaceView;
    private Renderer _renderer;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _GLSurfaceView = new GLSurfaceView(this);

        final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
        final boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000;

        if (supportsEs2)
        {
            _GLSurfaceView.setEGLContextClientVersion(2);
            _GLSurfaceView.setRenderer(new Renderer());
        }
        else
        {
            return;
        }
        setContentView(_GLSurfaceView);
    }

    @Override protected void onResume() {
        super.onResume();
        _GLSurfaceView.onResume();
    }

    @Override protected void onPause() {
        super.onPause();
        _GLSurfaceView.onPause();
    }

    @Override public boolean onTouchEvent(MotionEvent event) {
        _renderer.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
