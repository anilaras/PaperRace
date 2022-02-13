package com.arassoft.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class Tree {
    Texture [] tree;
    int treeNumber = 4;
    int [] treeY;
    int [] treeX;
    int treeRandomX = 200;
    public Tree(String internalPath) {
        this.tree = new Texture[this.treeNumber];
        for (int i = 0; i < 4 ; i++){
            this.tree[i] = new Texture("tree.png");
        }
        treeX = new int[treeNumber];
        treeY = new int[treeNumber];
    }

    public void draw(SpriteBatch batch){
        for (int i = 0 ; i < this.treeNumber ; i++){
            batch.draw(tree[i],treeX[i],treeY[i],Gdx.graphics.getWidth()/11,Gdx.graphics.getHeight()/11);
        }

        for (int i = 0; i < this.treeNumber ; i++){
            if (treeY[i] < 0){
                treeY[i] = Gdx.graphics.getHeight() + (i == 0 ? 1 : i * Gdx.graphics.getHeight()/4);
                treeX[i] = new Random().nextInt(treeRandomX);
            }
        }
    }

    public void update(Road road){
        for (int i = 0 ; i < this.treeNumber ;i++){
            treeY[i] -= road.getRoadSpeed() *  Gdx.graphics.getDeltaTime();
        }
    }

    public Texture[] getTree() {
        return tree;
    }

    public void setTree(Texture[] tree) {
        this.tree = tree;
    }

    public int getTreeNUmber() {
        return treeNumber;
    }

    public void setTreeNUmber(int treeNUmber) {
        this.treeNumber = treeNUmber;
    }

    public int[] getTreeY() {
        return treeY;
    }

    public void setTreeY(int[] treeY) {
        this.treeY = treeY;
    }

    public int[] getTreeX() {
        return treeX;
    }

    public void setTreeX(int[] treeX) {
        this.treeX = treeX;
    }
}
