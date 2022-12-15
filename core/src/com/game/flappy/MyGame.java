package com.game.flappy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.game.flappy.states.GameStateManager;
import com.game.flappy.states.MenuState;

public class MyGame extends ApplicationAdapter implements Screen {
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;

	public static Music music;

	private GameStateManager gsm;
	private SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {

	}


    @Override
    public void show() {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		music = Gdx.audio.newMusic(Gdx.files.internal("Flappy/music.mp3"));
		music.setLooping(true);
		music.setVolume(0.1f);
		music.play();
		Gdx.gl.glClearColor(1,0,0,1);
		gsm.push(new MenuState(gsm));
    }

    @Override
    public void render(float delta) {
		ScreenUtils.clear(1, 0, 0, 1);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
    }

    @Override
    public void hide() {

    }

    @Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		music.dispose();
	}
}
