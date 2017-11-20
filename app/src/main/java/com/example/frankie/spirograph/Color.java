package com.example.frankie.spirograph;

/**
 * Created by frankie on 20/11/2017.
 */

public class Color {
    private int r, g, b;

    public Color(int r, int g, int b){
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Color(int color){
        int A = (color >> 24) & 0xff; // or color >>> 24
        this.r = (color >> 16) & 0xff;
        this.g = (color >>  8) & 0xff;
        this.b = (color      ) & 0xff;

    }

    public Color(){
        this(255, 255, 255);
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}
