package com.example.bbciq.sheeps.engine;

/**
 * Created by Valentin on 10.07.2015.
 */
public class Float3 extends Float2{
    public Float3(){z = 0.0f;}
    public Float3(float x_, float y_,float z_){x = x_; y = y_; z = z_;}
    public float z;

    static Float3 normalize(Float3 vector){
        float lenght = (float)Math.sqrt(Math.pow(vector.x,2.0)+Math.pow(vector.y,2.0)+Math.pow(vector.z,2.0));
        vector.x /= lenght;
        vector.y /= lenght;
        vector.z /= lenght;
        return vector;
    }
    @Override void normalize(){
        float lenght = (float)Math.sqrt(Math.pow(x,2.0)+Math.pow(y,2.0)+Math.pow(z,2.0));
        x /= lenght;
        y /= lenght;
        z /= lenght;
    }
}
