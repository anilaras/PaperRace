package com.arassoft.paperrace;

import com.arassoft.GameObjects.*;
import com.arassoft.Helper.DebugHelper;
import com.arassoft.extenders.SimpleDirectionGestureDetector;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;

public class PaperRace extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture [] tree = new Texture[4];
	ShapeRenderer shapeRenderer;
	boolean DEBUG = true;


	Player player;
	Road road;
	Font font;
	Tree trees;
	boolean collisionDetect = true;

	@Override
	public void create () {
		batch = new SpriteBatch();


		shapeRenderer = new ShapeRenderer();
		background = new Texture("bg.png");
		player = new Player(new Texture("car-player.png"),2,Gdx.graphics.getHeight()/7,Gdx.graphics.getWidth()/16);

		road = new Road("road.png",4,200);
		trees = new Tree("tree.png");
//		treeY = Gdx.graphics.getHeight() - Gdx.graphics.getHeight() /11;
		font = new Font(batch);
		Gdx.input.setInputProcessor(new SimpleDirectionGestureDetector(new SimpleDirectionGestureDetector.DirectionListener() {
			@Override
			public void onDoubleTap() {
				player.setPlayerHeight((int)(Gdx.graphics.getHeight()/7 * 1.5));
				player.setPlayerWidth((int) (Gdx.graphics.getWidth()/16 * 1.5));
				player.setPositionX(player.getPositionX() - Gdx.graphics.getWidth()/60);
				player.setJump(true);
				Timer.schedule(new Timer.Task(){
					@Override
					public void run() {
						player.setPlayerHeight(Gdx.graphics.getHeight()/7);
						player.setPlayerWidth(Gdx.graphics.getWidth()/16);
						player.setPositionX(player.getPositionX() + Gdx.graphics.getWidth()/60);
						player.setJump(false);
					}
				}, 1);

			}

			@Override
			public void onLeft() {
				if (!player.isJump()){
					PositionStateHolder.position--;
					if (PositionStateHolder.position <= 0){
						PositionStateHolder.position = 0;
					}
					player.updatePosition(PositionStateHolder.position);
				}

			}

			@Override
			public void onRight() {
				if (!player.isJump()){
					PositionStateHolder.position++;
					if (PositionStateHolder.position >= 3){
						PositionStateHolder.position = 3;
					}
					player.updatePosition(PositionStateHolder.position);
				}
			}

			@Override
			public void onUp() {
				player.setPlayerHeight((int)(Gdx.graphics.getHeight()/7 * 1.5));
				player.setPlayerWidth((int) (Gdx.graphics.getWidth()/16 * 1.5));
				player.setPositionX(player.getPositionX() - Gdx.graphics.getWidth()/60);
				player.setJump(true);
				Timer.schedule(new Timer.Task(){
					@Override
					public void run() {
						player.setPlayerHeight(Gdx.graphics.getHeight()/7);
						player.setPlayerWidth(Gdx.graphics.getWidth()/16);
						player.setPositionX(player.getPositionX() + Gdx.graphics.getWidth()/60);
						player.setJump(false);
					}
				}, 1);
			}

			@Override
			public void onDown() {

			}
		}));

	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);

		road.update(Gdx.graphics.getDeltaTime());
		trees.update(road);
		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		road.draw(batch);
		trees.draw(batch);
		player.draw(batch);

		if (DebugHelper.DEBUG)
			font.print("Debug  Active");

		font.print("Score",Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() / 4), Gdx.graphics.getHeight() - 50);
		font.print(String.valueOf(player.getScore()),Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() / 4), Gdx.graphics.getHeight() - 120);
		batch.end();

		if (DebugHelper.DEBUG){
			shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
			shapeRenderer.setColor(Color.BLACK);
			shapeRenderer.rect(player.getPositionX(),player.getPositionY(),player.getPlayerWidth(),player.getPlayerHeight());
			shapeRenderer.end();
		}

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		for (int i = 0 ; i < road.getRoadBlockNumber() ; i++) {
			road.getRoadTexture()[i].dispose();
		}
		player.getTexture().dispose();
		background.dispose();
	}
}
