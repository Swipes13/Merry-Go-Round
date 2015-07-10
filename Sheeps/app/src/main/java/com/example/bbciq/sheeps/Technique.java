package com.example.bbciq.sheeps;
import android.opengl.GLES20;
/**
 * Created by Valentin on 10.07.2015.
 */
public class Technique {
    public Technique(final String vertexShader, final String fragmentShader){
        int VSHandle = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
        _compile(VSHandle,vertexShader);
        int FSHandle = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
        _compile(FSHandle,fragmentShader);
        handles[0] = GLES20.glCreateProgram();
        _link(VSHandle, FSHandle);
        handles[1] = GLES20.glGetUniformLocation(handles[0], "u_MVPMatrix");
        handles[2] = GLES20.glGetAttribLocation(handles[0], "a_Position");
        handles[3] = GLES20.glGetAttribLocation(handles[0], "a_Color");
    }
    private void _compile(int handle, String shader){
        if (handle != 0){
            GLES20.glShaderSource(handle, shader);
            GLES20.glCompileShader(handle);
            final int[] compileStatus = new int[1];
            GLES20.glGetShaderiv(handle, GLES20.GL_COMPILE_STATUS, compileStatus, 0);
            if (compileStatus[0] == 0){
                GLES20.glDeleteShader(handle);
                handle = 0;
            }
        }
        if (handle == 0)
            throw new RuntimeException("Error creating shader.");
    }
    private void _link(int vHandle, int fHandle){
        if (handles[0] != 0)
        {
            GLES20.glAttachShader(handles[0], vHandle);
            GLES20.glAttachShader(handles[0], fHandle);
            GLES20.glBindAttribLocation(handles[0], 0, "a_Position");
            GLES20.glBindAttribLocation(handles[0], 1, "a_Color");
            GLES20.glLinkProgram(handles[0]);
            final int[] linkStatus = new int[1];
            GLES20.glGetProgramiv(handles[0], GLES20.GL_LINK_STATUS, linkStatus, 0);
            if (linkStatus[0] == 0){
                GLES20.glDeleteProgram(handles[0]);
                handles[0] = 0;
            }
        }
        if (handles[0] == 0)
            throw new RuntimeException("Error creating program.");
    }
    int[] handles = new int[4];
}
