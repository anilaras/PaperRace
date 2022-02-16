package com.arassoft.paperrace;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class TitleScreen implements Screen {
    PaperRace game;
    Skin skin;
    private Stage stage;
    private SpriteBatch batch;
    Music music = Gdx.audio.newMusic(Gdx.files.internal("Sounds/TitleScreenMusic.ogg"));
    Texture bg;
    Texture [] clouds = new Texture[7];
    int [] cloudX = new int[7];
    int [] cloudY = new int[7];
    int [] cloudSpeed = new int[7];
    int [] cloudSize = new int[7];

    public TitleScreen(PaperRace game){
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        stage = new Stage();
        skin = new Skin(Gdx.files.internal("UI/skin/uiskin.json"));
        clouds[0] = new Texture("Textures/Clouds/cumulus-big1.png");
        clouds[1] = new Texture("Textures/Clouds/cumulus-big2.png");
        clouds[2] = new Texture("Textures/Clouds/cumulus-big3.png");
        clouds[3] = new Texture("Textures/Clouds/cumulus-huge.png");
        clouds[4] = new Texture("Textures/Clouds/cumulus-small1.png");
        clouds[5] = new Texture("Textures/Clouds/cumulus-small2.png");
        clouds[6] = new Texture("Textures/Clouds/cumulus-small3.png");
        cloudSpeed[0] = 50;
        cloudSpeed[1] = 80;
        cloudSpeed[2] = 110;
        cloudSpeed[3] = 140;
        cloudSpeed[4] = 170;
        cloudSpeed[5] = 180;
        cloudSpeed[6] = 210;

        for (int i = 0; i < cloudX.length; i++){
                cloudY[i] = new Random().nextInt(Gdx.graphics.getHeight());
                cloudSize[i] = new Random().nextInt(500);
                cloudX[i] = new Random().nextInt(Gdx.graphics.getWidth());
        }
        music.setLooping(true);
        music.setPosition(4f);
        music.play();
        final Label label = new Label("Paper Race", skin, "title");
        label.setHeight(Gdx.graphics.getHeight()/8);
        label.setWidth(Gdx.graphics.getWidth()/4);
        label.setPosition(Gdx.graphics.getWidth() /2 - ((Gdx.graphics.getWidth() /4) / 2), Gdx.graphics.getHeight() - ((Gdx.graphics.getHeight()/4)));
        label.setFontScale(2);
        label.setScale(2);
        label.setAlignment(Align.center);

        final TextButton startGameButton = new TextButton("Start Game", skin, "round");
        startGameButton.setWidth(Gdx.graphics.getWidth() /4);
        startGameButton.setHeight(Gdx.graphics.getHeight()/8);
        startGameButton.getLabel().setFontScale(2);
        startGameButton.getStyle().downFontColor = Color.GREEN;
        startGameButton.setPosition(Gdx.graphics.getWidth() /2 - ((Gdx.graphics.getWidth() /4) / 2), Gdx.graphics.getHeight()/2 - ((Gdx.graphics.getHeight()/8) /2));
        startGameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                music.stop();
                music.dispose();
                game.setScreen(new GameScreen(game));
            }
        });

        final Button muteButton = new Button(skin, "music");
        muteButton.setColor(Color.GREEN);
        muteButton.setChecked(true);
        muteButton.setWidth(Gdx.graphics.getWidth() /15);
        muteButton.setHeight(Gdx.graphics.getHeight()/9);
        muteButton.setPosition(Gdx.graphics.getWidth() - ((Gdx.graphics.getWidth() /15) ), 0);
        muteButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                if (music.isPlaying())
                    music.stop();
                else
                    music.play();
            }
        });

        Pixmap bgPixmap = new Pixmap(1,1, Pixmap.Format.RGB565);
        bgPixmap.setColor(255-78, 255-179, 255-254,1);
        bgPixmap.fill();
        bg = new Texture(bgPixmap);

        stage.addActor(startGameButton);
        stage.addActor(muteButton);
        stage.addActor(label);
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT);
        //Gdx.gl.glClearColor();
        for (int i = 0; i < cloudX.length; i++){
            cloudX[i] += cloudSpeed[i] * delta;
            if (cloudX[i] > Gdx.graphics.getWidth()){
                cloudSize[i] = new Random().nextInt(7-3) + 3;
                cloudX[i] = 0 - (clouds[i].getWidth() * cloudSize[i]);
                cloudY[i] = new Random().nextInt(Gdx.graphics.getHeight() - clouds[i].getHeight());
                cloudSpeed[i] = cloudSize[i];
            }
        }


        batch.begin();
        batch.draw(bg,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        for (int i = 0; i < cloudX.length; i++) {
            batch.draw(clouds[i],cloudX[i],cloudY[i],clouds[i].getWidth() * cloudSize[i], clouds[i].getHeight() * cloudSize[i]);
        }
        batch.end();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        music.stop();
    }

    @Override
    public void resume() {
        music.play();
    }

    @Override
    public void hide() {
        music.stop();
    }

    @Override
    public void dispose() {
        music.dispose();
    }
}
