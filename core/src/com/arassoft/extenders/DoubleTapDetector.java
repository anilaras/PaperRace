package com.arassoft.extenders;

import com.badlogic.gdx.input.GestureDetector;


public class DoubleTapDetector extends GestureDetector.GestureAdapter {

    @Override
    public boolean tap(float x, float y, int count, int button) {
        System.out.println(count);
        return count == 2;

    }
}