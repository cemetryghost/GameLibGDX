package com.game.snake.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.game.snake.asset.ColorAsset;
import com.game.snake.config.GameConfig;
import com.game.snake.factory.BitmapFontFactory;
import com.game.snake.manager.ScreenManager;

public class SplashScreen implements Screen {

    private static final String MESSAGE = "SNAKE";

    private ScreenManager screenManager;
    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch spriteBatch;
    private BitmapFontFactory bitmapFontFactory;
    private BitmapFont bitmapFont;
    private float timeDelta = GameConfig.SPLASH_SCREEN_TIMEOUT;


    public SplashScreen(ScreenManager screenManager) {
        this.screenManager = screenManager;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);
        viewport = new FitViewport(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT, camera);
        viewport.setScreenBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spriteBatch = new SpriteBatch();
        bitmapFontFactory = new BitmapFontFactory();
        bitmapFont = bitmapFontFactory.createFont(BitmapFontFactory.FontType.SPLASH);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // clear screen
        Gdx.gl.glClearColor(
                ColorAsset.SCREEN_BACKGROUND.r,
                ColorAsset.SCREEN_BACKGROUND.g,
                ColorAsset.SCREEN_BACKGROUND.b,
                ColorAsset.SCREEN_BACKGROUND.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // setup renderer
        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);

        spriteBatch.begin();
        drawScreen();
        spriteBatch.end();

        // timer task
        countDownChangeScreen(delta);
    }

    @Override
    public void resize(int width, int height) {

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
        bitmapFont.dispose();
        spriteBatch.dispose();
    }

    private void drawScreen() {
        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(bitmapFont, MESSAGE);

        Vector2 position = new Vector2(
                (GameConfig.SCREEN_WIDTH - glyphLayout.width) / 2,
                GameConfig.SCREEN_HEIGHT / 2 + 30
        );

        bitmapFont.draw(spriteBatch, glyphLayout, position.x, position.y);
    }
    private void countDownChangeScreen(float delta) {
        timeDelta -= delta;

        if (timeDelta <= 0) {
            screenManager.changeScreen(ScreenManager.ScreenType.GAME);
        }
    }
}
