package com.arassoft.GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Player {
    Texture texture;
    int positionX;
    int positionY;
    int PlayerHeight;
    int PlayerWidth;
    long score;
    Rectangle shape;
    boolean isJump = false;

    public Player(Texture texture, int positionX, int positionY, int playerHeight, int playerWidth){
        this.texture = texture;
        shape = new Rectangle();
        this.shape.set(positionX,positionY,PlayerWidth,PlayerHeight);
        setPositionX(positionX);
        this.positionY = positionY;
        setPlayerHeight(playerHeight);
        setPlayerWidth(playerWidth);
    }


    public Player(Texture texture,int position, int playerHeight, int playerWidth){
        this.texture = texture;
        updatePosition(position);
        setPlayerHeight(playerHeight);
        setPlayerWidth(playerWidth);
        this.positionY = 10;
        shape = new Rectangle();
        this.shape.set(positionX,positionY,PlayerWidth,PlayerHeight);
    }

    public Texture getTexture(){
        return this.texture;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public int getPlayerHeight() {
        return PlayerHeight;
    }

    public int getPlayerWidth() {
        return PlayerWidth;
    }

    public void setPlayerHeight(int playerHeight) {
        PlayerHeight = playerHeight;
    }

    public void setPlayerWidth(int playerWidth) {
        PlayerWidth = playerWidth;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void updatePosition(int position){
        PositionStateHolder.position = position;
        setPositionX(PositionStateHolder.possiblePositions[position]
        );
    }

    public boolean isJump() {
        return isJump;
    }

    public void setJump(boolean jump) {
        isJump = jump;
    }

    public Rectangle getShape() {
        return shape;
    }

    public long getScore() {
        return score;
    }

    public void draw(SpriteBatch batch){
        batch.draw(this.texture, this.getPositionX(), this.getPositionY(),PlayerWidth ,PlayerHeight);
        score++;
    }
}
