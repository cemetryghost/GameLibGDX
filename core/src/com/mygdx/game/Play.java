package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;
import java.util.List;

@SuppressWarnings("rawtypes")
public class Play extends ApplicationAdapter implements Screen{
    private TextureAtlas playerAtlas;
    public static TiledMap map;
    public static TiledMapTileLayer collisionLayer;
    private OrthogonalTiledMapRenderer renderer;
//    private OrthogonalTiledMapRenderer renderer;
    public static OrthographicCamera camera;
    public static String boyOrin = "RIGHT";
    final float num = 1f;
    SpriteBatch batch;
    public static Character character;
    public static KeyboardAdapter inputProcessor;
    private int i = 0;
    private ShapeRenderer sr;
    @Override
    public void show() {
        map = new TmxMapLoader().load("map/gg.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);

        camera = new OrthographicCamera();
        sr = new ShapeRenderer();
        sr.setColor(Color.CYAN);
        playerAtlas = new TextureAtlas("Animation/player.atlas");
        Animation still, left, right;
        still = new Animation(1 / 2f, playerAtlas.findRegions("still"));
        left = new Animation(1 / 6f, playerAtlas.findRegions("left"));
        right = new Animation(1 / 6f,  playerAtlas.findRegions("right"));
        still.setPlayMode(Animation.PlayMode.LOOP);
        left.setPlayMode(Animation.PlayMode.LOOP);
        right.setPlayMode(Animation.PlayMode.LOOP);

        inputProcessor = new KeyboardAdapter(still, left, right, (TiledMapTileLayer) map.getLayers().get("col"));
        inputProcessor.setPosition(11 * inputProcessor.getCollisionLayer().getTileWidth(), (inputProcessor.getCollisionLayer().getHeight() - 14) * inputProcessor.getCollisionLayer().getTileHeight());


        collisionLayer = (TiledMapTileLayer) Play.map.getLayers().get("col");
//        inputProcessor = new KeyboardAdapter(new Sprite(new Texture("character.png")), (TiledMapTileLayer) map.getLayers().get("col"));
//        inputProcessor.setPosition(100, 100);
        Gdx.input.setInputProcessor(inputProcessor);
        batch = new SpriteBatch();
//        character = new Character(100,100);
        camera.setToOrtho( false, 1000, 1000);

        camera.update();
//        // frames
//        Array<StaticTiledMapTile> frameTiles = new Array<StaticTiledMapTile>(2);
//
//        // get the frame tiles
//        Iterator<TiledMapTile> tiles = map.getTileSets().getTileSet("tiles").iterator();
//        while(tiles.hasNext()) {
//            TiledMapTile tile = tiles.next();
//            if(tile.getProperties().containsKey("animation") && tile.getProperties().get("animation", String.class).equals("flower"))
//                frameTiles.add((StaticTiledMapTile) tile);
//        }
//
//        // create the animated tile
//        AnimatedTiledMapTile animatedTile = new AnimatedTiledMapTile(1 / 3f, frameTiles);
//
//        // background layer
//        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("background");
//
//        // replace static with animated tile
//        for(int x = 0; x < layer.getWidth(); x++)
//            for(int y = 0; y < layer.getHeight(); y++) {
//                TiledMapTileLayer.Cell cell = layer.getCell(x, y);
//                if(cell.getTile().getProperties().containsKey("animation") && cell.getTile().getProperties().get("animation", String.class).equals("flower"))
//                    cell.setTile(animatedTile);
//            }
    }
    @Override
    public void create(){

//        map = new TmxMapLoader().load("map/serg.tmx");
//        renderer = new OrthogonalTiledMapRenderer(map);
//        camera = new OrthographicCamera();
//
//        Gdx.input.setInputProcessor(inputProcessor);
//        batch = new SpriteBatch();
//        character = new Character(100,200);
    }


    protected void handleInput() {
        // Обнуление counterAnimation, при нажатие (не удерживание) кнопки
//        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT) || Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) || Gdx.input.isKeyJustPressed(Input.Keys.UP) || Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
//            Character.counterAnimation = 0.0f; //Обнуление счетчика, когда меняется ориентация
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
//            boyOrin = "LEFT";
//            character.getBoyLeft();
//
//
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
//            boyOrin = "RIGHT";
//            character.getBoyRight();
//
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
//            boyOrin = "UP";
//            character.getBoyUp();
//
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
//            boyOrin = "DOWN";
//            character.getBoyDown();
//
//        }

    }


    public void update(float dt) {
        handleInput();
//        character.update(dt);
//        inputProcessor.spriteUpdate();
//        if(i == 0)
//        i++;
//        if(i == 120)i = 0;

        }

    @Override
    public void render(float delta) {

//        Gdx.gl.glClear(0);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        camera.update();
//        update(delta);
//
//
//        renderer.setView(camera);
//        renderer.render();
//        renderer.getBatch().begin();
//
////        character.moveTo(inputProcessor.getDirection());
////        camera.translate(inputProcessor.getMove());
////        inputProcessor.setSize(10,30);
//
//        inputProcessor.draw(renderer.getBatch());
//        renderer.getBatch().end();
////        batch.begin();
////        switch (boyOrin) {
////            case "RIGHT":
////
////                character.getBoyRight();
////
////                break;
////            case "LEFT":
////                character.getBoyLeft();
////
////                break;
////            case "UP":
////                character.getBoyUp();
////                break;
////            case "DOWN":
////                character.getBoyDown();
////                break;
////        }
////        character.render(batch);
////        batch.end();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.position.set(inputProcessor.getX() + inputProcessor.getWidth() / 2, inputProcessor.getY() + inputProcessor.getHeight() / 2, 0);
        camera.update();

        renderer.setView(camera);
        renderer.render();
//        renderer.render(background);

        renderer.getBatch().begin();
        inputProcessor.draw(renderer.getBatch());

        renderer.getBatch().end();

//        renderer.render(foreground);

        // render objects
        sr.setProjectionMatrix(camera.combined);
    }


    @Override
    public void resize(int width, int height) {
        camera.position.set(100, 100, 0);
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
        batch.dispose();
    }
}
