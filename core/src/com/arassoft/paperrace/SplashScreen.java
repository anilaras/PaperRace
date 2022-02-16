package com.arassoft.paperrace;

import com.arassoft.GameObjects.Font;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;

public class SplashScreen implements Screen {
    PaperRace game;
    Texture logo;
    SpriteBatch batch;
    Font font;
    Sound sound= Gdx.audio.newSound(Gdx.files.internal("Sounds/SplashEndSound.wav"));
    boolean soundStatus;
    float logoAlpha;
    int logoFlyX;
    int logoFlySpeed = 500;
    int stopTime;
    public SplashScreen(PaperRace game){
        this.game = game;
    }

    @Override
    public void show() {
        logo = new Texture("logo_pixelate.png");
        batch = new SpriteBatch();
        logoFlyX = ((Gdx.graphics.getWidth()/2) - logo.getWidth()/2);
        stopTime = 1;
        font = new Font(batch);
        batch.enableBlending();
        Timer.schedule(new Timer.Task(){
            @Override
            public void run() {
                sound.play();
            }
        }, 1.2f);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_ALPHA_BITS);
        stopTime++;

//        if (logoFlyX < ((Gdx.graphics.getWidth()/2) - logo.getWidth()/2)){
//            logoFlyX += logoFlySpeed * delta;
//        }

        if (logoAlpha < 1){
            logoAlpha += 0.01f;
        }

        if (stopTime >= 230){
            game.setScreen(new TitleScreen(this.game));
        }

        batch.begin();
        batch.setColor(0,0,0,logoAlpha);

        batch.draw(logo, logoFlyX ,Gdx.graphics.getHeight()/6);
        font.print("Aras  Soft",Gdx.graphics.getHeight()/4);
        font.print("2022",Gdx.graphics.getHeight()/6);

        batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        logo.dispose();
        batch.dispose();
    }
}
