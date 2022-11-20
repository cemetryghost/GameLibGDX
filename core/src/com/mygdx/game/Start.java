package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Start extends Game {
    SpriteBatch batch;
    private Character character;
    private KeyboardAdapter inputProcessor = new KeyboardAdapter();

    @Override
    public void create () {
        setScreen(new Play());
        Gdx.input.setInputProcessor(inputProcessor);
        batch = new SpriteBatch();
        character = new Character(100,200);
    }

    @Override
    public void render () {
        super.render();
        character.moveTo(inputProcessor.getDirection());
        batch.begin();
        character.render(batch);
        batch.end();
    }

    @Override
    public void dispose () {
        batch.dispose();
        character.dispose();
    }

}
