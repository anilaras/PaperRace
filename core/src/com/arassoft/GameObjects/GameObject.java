package com.arassoft.GameObjects;

import com.badlogic.gdx.graphics.Texture;

public class GameObject {
    int x;
    int y;
    Texture texture;
    int height;
    int width;
    public GameObject(int x, int y, Texture texture,int height, int width) {
        this.x = x;
        this.y = y;
        this.texture = texture;
        this.height = height;
        this.width = width;
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

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
