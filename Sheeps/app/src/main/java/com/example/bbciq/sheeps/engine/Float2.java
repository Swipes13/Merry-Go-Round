package com.example.bbciq.sheeps.engine;

/**
 * Created by Valentin on 10.07.2015.
 */
public class Float2 {
    public Float2(){x = y = 0.0f;}
    public Float2(float x_, float y_){x = x_; y = y_;}
    public Float2(float x_, float y_,float z_){x = x_; y = y_;}
    public float x;
    public float y;

    static Float2 normalize(Float2 vector){
        float lenght = (float)Math.sqrt(Math.pow(vector.x,2.0)+Math.pow(vector.y,2.0));
        vector.x /= lenght;
        vector.y /= lenght;
        return vector;
    }
    void normalize(){
        float lenght = (float)Math.sqrt(Math.pow(x,2.0)+Math.pow(y,2.0));
        x /= lenght;
        y /= lenght;
    }
}
