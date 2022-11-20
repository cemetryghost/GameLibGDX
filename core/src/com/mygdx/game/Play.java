package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Play extends ApplicationAdapter implements Screen{

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    SpriteBatch batch;
    private Character character;
    private KeyboardAdapter inputProcessor = new KeyboardAdapter();

    @Override
    public void show() {
        map = new TmxMapLoader().load("D:\\666\\assets\\map\\memap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();

        Gdx.input.setInputProcessor(inputProcessor);
        batch = new SpriteBatch();
        character = new Character(1280 / 2,720 / 2);
        camera.setToOrtho(true, 1280, 720);
    }
    @Override
    public void create(){

        map = new TmxMapLoader().load("D:\\666\\assets\\map\\memap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();

        Gdx.input.setInputProcessor(inputProcessor);
        batch = new SpriteBatch();
        character = new Character(100,200);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        character.moveTo(inputProcessor.getDirection());

        camera.translate(inputProcessor.getMove());

        renderer.setView(camera);
        renderer.render();
        batch.begin();
        character.render(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        batch.dispose();
        character.dispose();
    }
}
