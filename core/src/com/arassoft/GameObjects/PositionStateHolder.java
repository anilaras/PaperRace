package com.arassoft.GameObjects;

import com.badlogic.gdx.Gdx;

public class PositionStateHolder {
    public static int position;
    public static int [] possiblePositions = new int[]{(int) (Gdx.graphics.getWidth() / 4.6f),(int) (Gdx.graphics.getWidth() / 3.1f),(int) (Gdx.graphics.getWidth() / 2.38f),(int) (Gdx.graphics.getWidth() / 1.9f)};

    public static int getPosition(int position) {
        return possiblePositions[position];
    }

    //playerPosition = (int) (Gdx.graphics.getWidth() / 1.9f);	//4
    //playerPosition = (int) (Gdx.graphics.getWidth() / 2.38f); //3
    //playerPosition = (int) (Gdx.graphics.getWidth() / 3.1f);	//2
    //playerPosition = (int) (Gdx.graphics.getWidth() / 4.6f);	//1
}
