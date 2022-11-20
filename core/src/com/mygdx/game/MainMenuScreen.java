package com.mygdx.game;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class MainMenuScreen implements Screen, ApplicationListener {

    TextButton.TextButtonStyle textButtonStyle;
    BitmapFont font;
    private SpriteBatch batch;
    protected Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;
    private TextureAtlas atlas;
    protected Skin skin;
    public TextButton playButton;
    public TextButton settingsButton;
    public TextButton exitButton;
    public Texture mainMenu;

    private final Game game;

    public MainMenuScreen(Game game) {
        super();
        this.game = game;
        atlas = new TextureAtlas("skin.atlas");
        skin = new Skin();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("myFont.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 30;
        Color color = new Color(0xe6e682AA);
        parameter.color = color;

        mainMenu = new Texture(Gdx.files.internal("MainMenu.jpg"));
        font = generator.generateFont(parameter);
        font.setColor(Color.BLACK);
        skin.addRegions(atlas);
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("button");
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(1280, 720, camera);
        viewport.apply();
        stage = new Stage(viewport, batch);
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        Table table1 = new Table();
        table1.setFillParent(true);
        table1.top();

        playButton = new TextButton("PLAY", textButtonStyle);
        settingsButton = new TextButton("SETTINGS", textButtonStyle);
        exitButton = new TextButton("EXIT", textButtonStyle);


        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new Play());

            }
        });

        settingsButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new Preferences(game));
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        table1.add(playButton);
        table1.center();
        table1.row();
        table1.add(settingsButton);
        table1.center();
        table1.row();
        table1.add(exitButton);
        table1.center();
        table1.row();
        stage.addActor(table1);

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(.1f, .12f, .16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.begin();
        batch.draw(mainMenu, 0, 0);
        batch.end();
        stage.getWidth();
        stage.act();
        stage.draw();


    }

    @Override
    public void create() {

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(1280 / 2, 720 / 2, 0);
        camera.update();
    }

    @Override
    public void render() {

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
        skin.dispose();
        atlas.dispose();
    }
}