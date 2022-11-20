package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainGame implements Screen {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private final Game game;

    public MainGame(Game game) {
        super();
        this.game = game;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(1280, 720, camera);
        viewport.apply();
    }

    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(.1f, .12f, .16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.begin();
        batch.end();
    }

    @Override
    public void dispose () {

        batch.dispose();
    }

    @Override
    public void show() {

    }



    @Override
    public void resize(int width, int height){
        viewport.update(width, height);
        camera.position.set(1280 / 2, 720 /2, 0);
        camera.update();
    }

    @Override
    public void pause(){

    }

    @Override
    public void resume(){

    }

    @Override
    public void hide() {

    }
}
