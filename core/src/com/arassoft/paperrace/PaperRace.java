package com.arassoft.paperrace;

import com.arassoft.GameObjects.*;
import com.arassoft.GameObjects.Font;
import com.arassoft.Helper.DebugHelper;
import com.arassoft.extenders.SimpleDirectionGestureDetector;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;

import java.awt.*;
import java.util.Random;

public class PaperRace extends Game {

    @Override
    public void create() {
        setScreen(new SplashScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
