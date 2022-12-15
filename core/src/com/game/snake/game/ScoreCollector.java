package com.game.snake.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.game.snake.config.GameConfig;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Play;


public class ScoreCollector {

    private int score;


    public void addScore() {
        score += GameConfig.POINTS;
        Play.isKeyPressed = false;
        if(score == 50){
            MyGdxGame.counter += 1;
            ((Game) Gdx.app.getApplicationListener()).setScreen(new Play());
        }
    }


    public int getScore() {
        return score;
    }


    public void reset() {
        score = 0;
    }
}
