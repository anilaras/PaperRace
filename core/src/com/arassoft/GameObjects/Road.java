package com.arassoft.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Road {
    Texture [] roadTexture;
    int roadBlockNumber = 4;
    int roadBlockY[];
    public static float roadSpeed = 200;

    public Road(String internalPath, int roadBlockNumber, float roadSpeed) {
        this.roadBlockNumber = roadBlockNumber;
        this.roadTexture = new Texture[roadBlockNumber];
        for (int i = 0; i < roadBlockNumber; i++){
            roadTexture[i] = new Texture(internalPath);
        }
        this.roadBlockY = new int[roadBlockNumber];
        this.roadSpeed = roadSpeed;
    }

    public Texture[] getRoadTexture() {
        return roadTexture;
    }

    public void setRoadTexture(Texture[] roadTexture) {
        this.roadTexture = roadTexture;
    }

    public int getRoadBlockNumber() {
        return roadBlockNumber;
    }

    public void setRoadBlockNumber(int roadBlockNumber) {
        this.roadBlockNumber = roadBlockNumber;
    }

    public int[] getRoadBlockY() {
        return roadBlockY;
    }

    public int getRoadBlockY(int i) {
        return roadBlockY[i];
    }

    public void setRoadBlockY(int[] roadBlockY) {
        this.roadBlockY = roadBlockY;
    }

    public float getRoadSpeed() {
        return roadSpeed;
    }

    public void setRoadSpeed(float roadSpeed) {
        this.roadSpeed = roadSpeed;
    }

    public void update(float deltaTime){
        for (int i = 0 ; i < roadBlockNumber; i++){
            roadBlockY[i] -= roadSpeed *  deltaTime;
        }

        roadSpeed = (float) (roadSpeed + 0.2);

    }

    public void draw(SpriteBatch batch){
        for (int i = 0 ; i < roadBlockNumber; i++){
            batch.draw(roadTexture[i], Gdx.graphics.getWidth()/5.12f, roadBlockY[i] + (Gdx.graphics.getHeight()/2.4f * i), Gdx.graphics.getWidth()/2.4f,Gdx.graphics.getHeight()/2.4f);
        }

        for (int i = 0 ; i < roadBlockNumber; i++){
            if (roadBlockY[i] + (Gdx.graphics.getHeight()/2.4f) < 0){
                roadBlockY[i] = 0;
            }
        }
    }
}
