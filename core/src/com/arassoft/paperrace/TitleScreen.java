package com.arassoft.paperrace;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

public class TitleScreen implements Screen {
    PaperRace game;
    Skin skin;
    private Stage stage;
    private SpriteBatch batch;
    Music music = Gdx.audio.newMusic(Gdx.files.internal("Sounds/TitleScreenMusic.ogg"));

    public TitleScreen(PaperRace game){
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        stage = new Stage();
        skin = new Skin(Gdx.files.internal("UI/skin/uiskin.json"));
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
        muteButton.setColor(Color.RED);
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

        stage.addActor(startGameButton);
        stage.addActor(muteButton);
        stage.addActor(label);
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        stage.draw();
        batch.end();
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
