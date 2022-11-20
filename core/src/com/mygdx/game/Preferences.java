package com.mygdx.game;


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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class Preferences implements Screen {
    //    private Box2DTutorial parent;
    BitmapFont font;
    //    private Stage stage;
//    private Label titleLabel;
//    private Label volumeMusicLabel;
//    private Label volumeSoundLabel;
//    private Label musicOnOffLabel;
//    private Label soundOnOffLabel;
    private SpriteBatch batch;
    //    ScaleScreen scaleScreen;
    private Viewport viewport;
    private OrthographicCamera camera;
    private TextureAtlas atlas;
    protected Skin skin;
    public TextButton changeScale;
    public TextButton backButton;
    public Texture mainMenu;
    private int width = 1280;
    private int height = 720;
    private int i = 0;
    TextButton.TextButtonStyle textButtonStyle;
    private final Game game;
    protected Stage stage;

    public Preferences(Game game){
        super();
        this.game = game;
//        parent = box2dTutorial;
        atlas = new TextureAtlas("skin.atlas");
        skin = new Skin();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("myFont.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 25;
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

        Table table = new Table();
        table.setFillParent(true);
        table.top();

        Table table1 = new Table();
        table1.setFillParent(true);
        table1.top();

        changeScale = new TextButton("Change scale screen", textButtonStyle);
        backButton = new TextButton("Back", textButtonStyle);
        changeScale.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                width = 1280 + 640 * i;
                height = 720 + 360 * i;
                i++;
                if(i == 2)i = 0;

            }
        });

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen(game));

            }
        });

        table1.add(changeScale);
        table1.center();
        table1.row();
        table.add(backButton);
        table.bottom();
        table.row();

//        table.add(titleLabel).colspan(2);
//        table.row().pad(10,0,0,10);
//        table.add(volumeMusicLabel).left();
//        table.add(volumeMusicSlider);
//        table.row().pad(10,0,0,10);
//        table.add(musicOnOffLabel).left();
//        table.add(musicCheckbox);
//        table.row().pad(10,0,0,10);
//        table.add(volumeSoundLabel).left();
//        table.add(soundMusicSlider);
//        table.row().pad(10,0,0,10);
//        table.add(soundOnOffLabel).left();
//        table.add(soundEffectsCheckbox);
//        table.row().pad(10,0,0,10);
//        table.add(backButton).colspan(2);
        stage.addActor(table1);
        stage.addActor(table);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.1f, .12f, .16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(width == 1920)Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
        else{
            Gdx.graphics.setWindowedMode(width, height);
        }
        camera.update();
        batch.begin();
        batch.draw(mainMenu, 0, 0);
        font.draw(batch, "Scale screen: " + width + ", " + height, 100, stage.getHeight() / 2);
        batch.end();
        stage.getWidth();
        stage.act();
        stage.draw();


    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(1280 / 2, 720 /2, 0);
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
