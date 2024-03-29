package com.arassoft.extenders;

import com.badlogic.gdx.input.GestureDetector;

public class SimpleDirectionGestureDetector extends GestureDetector {
    public interface DirectionListener {
        void onDoubleTap();

        void onLeft();

        void onRight();

        void onUp();

        void onDown();
    }

    public SimpleDirectionGestureDetector(DirectionListener directionListener) {
        super(new DirectionGestureListener(directionListener));
    }

    private static class DirectionGestureListener extends GestureDetector.GestureAdapter {
        DirectionListener directionListener;

        public DirectionGestureListener(DirectionListener directionListener){
            this.directionListener = directionListener;
        }

        @Override
        public boolean tap(float x, float y, int count, int button) {
            if (count == 2)
                directionListener.onDoubleTap();
            return super.tap(x, y, count, button);
        }

        @Override
        public boolean fling(float velocityX, float velocityY, int button) {
            if(Math.abs(velocityX)>Math.abs(velocityY)){
                if(velocityX>0){
                    directionListener.onRight();
                }else{
                    directionListener.onLeft();
                }
            }else{
                if(velocityY>0){
                    directionListener.onDown();
                }else{
                    directionListener.onUp();
                }
            }
            return super.fling(velocityX, velocityY, button);
        }

    }

}
