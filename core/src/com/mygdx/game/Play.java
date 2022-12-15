package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.game.college.College;
import com.game.flappy.MyGame;
import com.game.flappy.states.GameStateManager;
import com.game.SpaceX.GameMyGdx;
import com.game.snake.screen.GameScreen;
import com.usp.corrida.Core;

@SuppressWarnings("rawtypes")
public class Play extends ApplicationAdapter implements Screen{
    private TextureAtlas playerAtlas;
    public static TiledMap map;
    public BitmapFont font;
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
    public static boolean isKeyPressed = false;

    public static boolean isWin = false;
    public static boolean islose = false;
    public static int frameCounter = 0;



    GameStateManager gsm;
    @Override
    public void show() {
        map = new TmxMapLoader().load("map/gg.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("myFont.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size=30;
        Color color = new Color(0xe6e682AA);
        parameter.color = color;
        font = generator.generateFont(parameter);
        camera = new OrthographicCamera();
        sr = new ShapeRenderer();
        sr.setColor(Color.CYAN);
        playerAtlas = new TextureAtlas("Animation/player.atlas");
        Animation still, left, right, up, down;
        still = new Animation(1 / 6f, playerAtlas.findRegions("still"));
        left = new Animation(1 / 6f, playerAtlas.findRegions("left"));
        right = new Animation(1 / 6f,  playerAtlas.findRegions("right"));
        up = new Animation(1 / 6f, playerAtlas.findRegions("up"));
        down = new Animation(1 / 6f, playerAtlas.findRegions("down"));
        still.setPlayMode(Animation.PlayMode.LOOP);
        left.setPlayMode(Animation.PlayMode.LOOP);
        right.setPlayMode(Animation.PlayMode.LOOP);
        up.setPlayMode(Animation.PlayMode.LOOP);
        down.setPlayMode(Animation.PlayMode.LOOP);

        inputProcessor = new KeyboardAdapter(still, left, right, up, down, (TiledMapTileLayer) map.getLayers().get("col"));
        inputProcessor.setPosition(11 * inputProcessor.getCollisionLayer().getTileWidth(), (inputProcessor.getCollisionLayer().getHeight() - 14) * inputProcessor.getCollisionLayer().getTileHeight());


        collisionLayer = (TiledMapTileLayer) Play.map.getLayers().get("col");
        Gdx.input.setInputProcessor(inputProcessor);
        batch = new SpriteBatch();
        camera.setToOrtho( false, 1000, 1000);
        camera.update();

//            }
    }
    @Override
    public void create(){
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.position.set(inputProcessor.getX() + inputProcessor.getWidth() / 2, inputProcessor.getY() + inputProcessor.getHeight() / 2, 0);
        camera.update();

        renderer.setView(camera);
        renderer.render();


        renderer.getBatch().begin();
        inputProcessor.draw(renderer.getBatch());

        renderer.getBatch().end();
        batch.begin();

        if(KeyboardAdapter.isDialog == true) {
            if (KeyboardAdapter.getKeyValue.equals("tashkiniv")) {
                batch.draw(new Texture("Animation/Tashkiniv2.png"), 0, 0);
                if(Gdx.input.isKeyPressed(Input.Keys.E) || isKeyPressed) {
                    batch.draw(new Texture("Animation/Tashkinov.png"), 0, 0);
                    isKeyPressed = true;
                    if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {

                    }
                }
            }

            if (KeyboardAdapter.getKeyValue.equals("puzr") && MyGdxGame.b == 0) {
                batch.draw(new Texture("Animation/Puzr.png"), 0, 0);
                if(Gdx.input.isKeyPressed(Input.Keys.E) || isKeyPressed) {
                    batch.draw(new Texture("Animation/Puzr2.png"), 0, 0);
                    isKeyPressed = true;
                    if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
                        MyGdxGame.b = 1;
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new Core());
                    }
                }
            }

            if (KeyboardAdapter.getKeyValue.equals("EK") && MyGdxGame.a == 0 ) {
                batch.draw(new Texture("Animation/EK2.png"), 0, 0);
                if(Gdx.input.isKeyPressed(Input.Keys.E) || isKeyPressed) {
                    batch.draw(new Texture("Animation/EK.png"), 0, 0);
                    isKeyPressed = true;
                    if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
                        MyGdxGame.a = 1;

                        ((Game) Gdx.app.getApplicationListener()).setScreen(new College());
                    }
                }
            }
            if (KeyboardAdapter.getKeyValue.equals("saldaev")) {
                batch.draw(new Texture("Animation/Saldaev2.png"), 0, 0);
                if(Gdx.input.isKeyPressed(Input.Keys.E) || isKeyPressed) {
                    batch.draw(new Texture("Animation/Saldaev.png"), 0, 0);
                    isKeyPressed = true;
                    if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {

                    }
                }
            }
            if (KeyboardAdapter.getKeyValue.equals("nadejda")) {
                batch.draw(new Texture("Animation/Nadejda.png"), 0, 0);
                if(Gdx.input.isKeyPressed(Input.Keys.E) || isKeyPressed) {
                    batch.draw(new Texture("Animation/Nikolaeva.png"), 0, 0);
                    isKeyPressed = true;
                    if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {

                    }
                }
            }
            if (KeyboardAdapter.getKeyValue.equals("matveeva")) {
                batch.draw(new Texture("Animation/Matveewa2.png"), 0, 0);
                if(Gdx.input.isKeyPressed(Input.Keys.E) || isKeyPressed) {
                    batch.draw(new Texture("Animation/Matveeva.png"), 0, 0);
                    isKeyPressed = true;
                    if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {

                    }
                }
            }
            if (KeyboardAdapter.getKeyValue.equals("makarova.png")) {
                batch.draw(new Texture("Animation/Makarova2.png"), 0, 0);
                if(Gdx.input.isKeyPressed(Input.Keys.E) || isKeyPressed) {
                    batch.draw(new Texture("Animation/Makarova.png"), 0, 0);
                    isKeyPressed = true;
                    if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {

                    }
                }
            }
            if (KeyboardAdapter.getKeyValue.equals("akamsova")) {
                batch.draw(new Texture("Animation/Akymsova2.png"), 0, 0);
                if(Gdx.input.isKeyPressed(Input.Keys.E) || isKeyPressed) {
                    batch.draw(new Texture("Animation/Akyms.png"), 0, 0);
                    isKeyPressed = true;
                    if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {

                    }
                }
            }
            if (KeyboardAdapter.getKeyValue.equals("dorofeev") && MyGdxGame.c == 0) {
                batch.draw(new Texture("Animation/Dorofeev2.png"), 0, 0);
                if(Gdx.input.isKeyPressed(Input.Keys.E) || isKeyPressed) {
                    batch.draw(new Texture("Animation/Dorofeev.png"), 0, 0);
                    isKeyPressed = true;
                    if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
                        MyGdxGame.c = 1;
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new GameMyGdx());
                    }
                }
            }

            if (KeyboardAdapter.getKeyValue.equals("lisova") && MyGdxGame.y == 0) {
                batch.draw(new Texture("Animation/Lysova2.png"), 0, 0);
                if(Gdx.input.isKeyPressed(Input.Keys.E) || isKeyPressed) {
                    batch.draw(new Texture("Animation/lisova.png"), 0, 0);
                    isKeyPressed = true;
                    if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
                        MyGdxGame.y = 1;
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new MyGame());
                    }
                }


            }

            if (KeyboardAdapter.getKeyValue.equals("zhidkova") && MyGdxGame.z == 0) {
                batch.draw(new Texture("Animation/Jidkova2.png"), 0, 0);
                if(Gdx.input.isKeyPressed(Input.Keys.E) || isKeyPressed) {
                    batch.draw(new Texture("Animation/Jidkova.png"), 0, 0);
                    isKeyPressed = true;
                    if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
                        MyGdxGame.z = 1;
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen());

                    }
                }
            }
        }

        if(MyGdxGame.counter > 1 && MyGdxGame.z + MyGdxGame.y + MyGdxGame.a + MyGdxGame.b == 4)isWin = true; frameCounter++;
        if(MyGdxGame.z + MyGdxGame.y + MyGdxGame.a + MyGdxGame.b == 2 && MyGdxGame.counter < -1) islose = true; frameCounter++;

        if(isWin){
            batch.draw(new Texture("Animation/win.png"), 0, 0);
            if(frameCounter == 240) Gdx.app.exit();
        }
        if(islose){
            batch.draw(new Texture("Animation/lose.png"), 0, 0);
            if(frameCounter == 240) Gdx.app.exit();
        }
        if(KeyboardAdapter.isDialog == false)isKeyPressed = false;
        batch.end();

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

    }
}
