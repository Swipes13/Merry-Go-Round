package com.example.bbciq.sheeps.engine;

import android.opengl.GLES20;
import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Random;

/**
 * Created by Valentin on 10.07.2015.
 */
public class Sprite {
    private final FloatBuffer _buffer;
    Float3 _position = new Float3();
    Float2 _scale = new Float2();
    int _textureHandle = 0;
    private final int _bytes = 4;
    private final int _stride = 7 * _bytes;
    private final int _posOffset = 0;
    private final int _posData = 3;
    private final int _clrOffset = 3;
    private final int _clrData = 4;

    private float[] _world = new float[16];
    public Sprite(){
        final float[] data = {
                -0.5f, -0.25f, 0.0f,
                1.0f, 0.0f, 0.0f, 1.0f,

                0.5f, -0.25f, 0.0f,
                0.0f, 0.0f, 1.0f, 1.0f,

                0.0f, 0.559016994f, 0.0f,
                0.0f, 1.0f, 0.0f, 1.0f };
        _buffer = ByteBuffer.allocateDirect(data.length * _bytes).order(ByteOrder.nativeOrder()).asFloatBuffer();
        _buffer.put(data).position(0);

        Matrix.setIdentityM(_world, 0);
    }
    public void draw(int[] handles, float[] view, float[] proj){
        _buffer.position(_posOffset);
        GLES20.glVertexAttribPointer(handles[2], _posData, GLES20.GL_FLOAT, false, _stride, _buffer);
        GLES20.glEnableVertexAttribArray(handles[2]);

        _buffer.position(_clrOffset);
        GLES20.glVertexAttribPointer(handles[3], _clrData, GLES20.GL_FLOAT, false, _stride, _buffer);
        GLES20.glEnableVertexAttribArray(handles[3]);

        float[] wvp = new float[16];
        Matrix.translateM(_world, 0, _position.x, _position.y, _position.z);
        Matrix.multiplyMM(wvp, 0, view, 0, _world, 0);
        Matrix.multiplyMM(wvp, 0, proj, 0, wvp, 0);

        GLES20.glUniformMatrix4fv(handles[1], 1, false, wvp, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);
    }
}
