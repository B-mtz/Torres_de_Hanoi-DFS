package model;

import java.awt.*;

public class Disk {
    private  int x, y,w,h,value;
    private Color color;

    public Disk(int x, int y, int w, int h, int value, Color color) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.value = value;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
