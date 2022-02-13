package com.arassoft.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Font {
    BitmapFont font;
    SpriteBatch batch;
    private GlyphLayout glyphLayout = new GlyphLayout();

    public Font(SpriteBatch batch){
        this.batch = batch;
        this.font = new BitmapFont(Gdx.files.internal("Fonts/pixelFont.fnt"), Gdx.files.internal("Fonts/pixelFont.png"), false);
        this.font.getData().scale(2f);
        this.font.setColor(Color.BLACK);
    }
    public Font(BitmapFont font, SpriteBatch batch) {
        this.font = font;
        this.batch = batch;
        this.font.getData().scale(2f);
        this.font.setColor(Color.BLACK);
    }

    public void print(String message){
        try {
            glyphLayout.setText(font, message);
            font.draw(batch, glyphLayout, Gdx.graphics.getWidth()/2 - glyphLayout.width /2, Gdx.graphics.getHeight()/2 - glyphLayout.height/2);

            //font.draw(this.batch, message,Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void print(String message,int x, int y){
        try {
            font.draw(this.batch, message, x,y);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void print(String message,int x, int y, Color color){
        try {
            this.font.setColor(color);
            this.font.draw(this.batch, message, x,y);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void setColor(Color color){
        this.font.setColor(color);
    }
}
