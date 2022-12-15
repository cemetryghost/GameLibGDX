package com.game.college;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class College extends Game implements Screen {
    public SpriteBatch batch;
    public BitmapFont font;

    public void create() {

    }


    @Override
    public void show() {
        batch = new SpriteBatch();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("college/myFont.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size=50;
        font = generator.generateFont(parameter);
        font.setColor(0,0,0,255);

        this.setScreen(new GameScreen(this));
    }

    @Override
    public void render(float delta) {
        super.render();
    }

    @Override
    public void hide() {

    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
