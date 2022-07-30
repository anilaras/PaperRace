package com.arassoft.paperrace;

import com.arassoft.GameObjects.*;
import com.arassoft.GameObjects.Tree;
import com.arassoft.Helper.DebugHelper;
import com.arassoft.extenders.SimpleDirectionGestureDetector;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;

import java.util.Random;

public class GameScreen implements Screen {
    PaperRace game;
    public GameScreen(PaperRace game){
        this.game = game;
    }


    Skin skin;
    private Stage stage;

    SpriteBatch batch;
    Texture background;
    Texture [] tree = new Texture[4];
    ShapeRenderer shapeRenderer;
    boolean DEBUG = true;
    GameState state;

    Label gameOverLabel;
    TextButton replayButton;
    TextButton exitButton;

    Player player;
    Road road;
    Font font;
    Tree trees;
    Enemy[] enemies;
    int enemyY = 0;
    boolean collisionDetect = true;

    @Override
    public void show () {
        batch = new SpriteBatch();

        skin = new Skin(Gdx.files.internal("UI/skin/uiskin.json"));

        gameOverLabel = new Label("GAME OVER\nDouble Click to play again!",skin,"title");
        gameOverLabel.setFontScale(3);
        gameOverLabel.setAlignment(Align.center);
        gameOverLabel.setPosition(Gdx.graphics.getWidth() /4, Gdx.graphics.getHeight()/3);
        gameOverLabel.setWidth(Gdx.graphics.getWidth() /2);
        gameOverLabel.setHeight(Gdx.graphics.getHeight()/4);



        shapeRenderer = new ShapeRenderer();
        background = new Texture("bg.png");
        enemies = new Enemy[4];
        for (int i = 0; i < 3 ; i++){
            enemies[i] = new Enemy(PositionStateHolder.possiblePositions[i],Gdx.graphics.getHeight(),new Texture("car-enemy2.png"),Gdx.graphics.getHeight()/7,Gdx.graphics.getWidth()/16 );
        }
        enemyY = Gdx.graphics.getHeight();
        road = new Road("road.png",4,200);
        player = new Player(new Texture("car-player.png"),2, Gdx.graphics.getHeight()/7,Gdx.graphics.getWidth()/16,road);
        trees = new Tree("tree.png");
        state = GameState.stop;
        font = new Font(batch);

        Gdx.input.setInputProcessor(new SimpleDirectionGestureDetector(new SimpleDirectionGestureDetector.DirectionListener() {
            @Override
            public void onDoubleTap() {
//				player.setPlayerHeight((int)(Gdx.graphics.getHeight()/7 * 1.5));
//				player.setPlayerWidth((int) (Gdx.graphics.getWidth()/16 * 1.5));
//				player.setPositionX(player.getPositionX() - Gdx.graphics.getWidth()/60);
//				player.setJump(true);
//				Timer.schedule(new Timer.Task(){
//					@Override
//					public void run() {
//						player.setPlayerHeight(Gdx.graphics.getHeight()/7);
//						player.setPlayerWidth(Gdx.graphics.getWidth()/16);
//						player.setPositionX(player.getPositionX() + Gdx.graphics.getWidth()/60);
//						player.setJump(false);
//					}
//				}, 2);
                state = GameState.start;
                enemyY = Gdx.graphics.getHeight();
                player.setScore(0);
                road.setRoadSpeed(200);
            }

            @Override
            public void onLeft() {
                if (!player.isJump() && state != GameState.gameover){
                    PositionStateHolder.position--;
                    if (PositionStateHolder.position <= 0){
                        PositionStateHolder.position = 0;
                    }
                    player.updatePosition(PositionStateHolder.position);
                }

            }

            @Override
            public void onRight() {
                if (!player.isJump() && state != GameState.gameover){
                    PositionStateHolder.position++;
                    if (PositionStateHolder.position >= 3){
                        PositionStateHolder.position = 3;
                    }
                    player.updatePosition(PositionStateHolder.position);
                }
            }

            @Override
            public void onUp() {
                if (player.isJump() == false && state != GameState.gameover) {
                    player.setPlayerHeight((int) (Gdx.graphics.getHeight() / 7 * 1.5));
                    player.setPlayerWidth((int) (Gdx.graphics.getWidth() / 16 * 1.5));
                    player.setPositionX(player.getPositionX() - Gdx.graphics.getWidth() / 60);
                    player.setJump(true);

                    player.setJumpStateY((int) (enemies[0].rectangle.height * 4));
                }
            }

            @Override
            public void onDown() {

            }
        }));

    }

    @Override
    public void render (float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        if (state != GameState.gameover){
            enemyY  -= road.getRoadSpeed() *  Gdx.graphics.getDeltaTime();

            road.update(Gdx.graphics.getDeltaTime());
            trees.update(road);
            player.update();

            if (enemyY < -200) {
                enemyY = Gdx.graphics.getHeight();
                for (int i = 0 ; i < 3 ; i++){
                    enemies[i].setPosition(i);
                    int rnd = new Random().nextInt(4);
                    enemies[i].setX(PositionStateHolder.possiblePositions[rnd]);
                    enemies[i].rectangle.setX(PositionStateHolder.possiblePositions[rnd]);

                }
            }
            for (int i = 0 ; i < 3 ; i++){
                enemies[i].setY(enemyY);
                enemies[i].rectangle.setY(enemyY);
            }
        }

        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        road.draw(batch);
        trees.draw(batch);

        for (int i = 0 ; i < 3 ; i++) {
            batch.draw(enemies[i].getTexture(),
                    enemies[i].getX(),
                    enemies[i].getY(),
                    enemies[i].getWidth(),
                    enemies[i].getHeight());
            if (enemies[i].rectangle.overlaps(player.shape) && !player.isJump()){
                state = GameState.gameover;
            }
        }

        player.draw(batch);

        if (state == GameState.gameover){
            gameOverLabel.draw(batch,1);
        }

        if (DebugHelper.DEBUG)
            //font.print("Debug  : " + enemies[0].rectangle.overlaps(player.shape) + enemies[1].rectangle.overlaps(player.shape) + enemies[2].rectangle.overlaps(player.shape));
            font.print("Debug  : " + state.name() + " EnemyY : " + enemyY + " JumpStateY : " + player.getJumpStateY());

        font.print("Score",Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() / 4), Gdx.graphics.getHeight() - 50);
        font.print(String.valueOf(player.getScore()),Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() / 4), Gdx.graphics.getHeight() - 120);
        batch.end();

        if (DebugHelper.DEBUG){
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Color.BLACK);
            shapeRenderer.rect(player.getPositionX(),player.getPositionY(),player.getPlayerWidth(),player.getPlayerHeight());
            for (int i = 0 ; i < 3 ; i++) {
                shapeRenderer.setColor(Color.RED);
                shapeRenderer.rect(enemies[i].rectangle.getX(),enemies[i].rectangle.getY(), enemies[i].rectangle.width,enemies[i].rectangle.height);
                //shapeRenderer.circle(enemies[i].rectangle.x,enemies[i].rectangle.y, 50);
            }

            shapeRenderer.end();
        }

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
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        batch.dispose();
        for (int i = 0 ; i < road.getRoadBlockNumber() ; i++) {
            road.getRoadTexture()[i].dispose();
        }
        for (int i = 0 ; i < 3 ; i++) {
            enemies[i].getTexture().dispose();
        }
        player.getTexture().dispose();
        background.dispose();
    }
}
