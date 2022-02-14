package com.arassoft.GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

public class Enemy extends GameObject{
    public Rectangle rectangle;
    int position;
    public Enemy(int x, int y, Texture texture, int height, int width) {
        super(x, y, texture,height, width);
        rectangle = new Rectangle();
        rectangle.set(this.x,this.y,this.getWidth(),this.getHeight());
    }

    public Rectangle getRectangle(){
        return this.rectangle;
    }

    @Override
    public void setX(int x) {
        super.setX(x);
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
