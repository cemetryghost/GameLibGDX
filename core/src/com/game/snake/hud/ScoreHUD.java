package com.game.snake.hud;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.game.snake.config.GameConfig;
import com.game.snake.factory.BitmapFontFactory;
import com.game.snake.game.ScoreCollector;


public class ScoreHUD implements Disposable {

    private static final String SCORE_LABEL = "SCORE: %d";

    private BitmapFontFactory bitmapFontFactory;
    private BitmapFont bitmapFont;
    private ScoreCollector scoreCollector;


    public ScoreHUD(ScoreCollector scoreCollector) {
        this.scoreCollector = scoreCollector;
        bitmapFontFactory = new BitmapFontFactory();
        bitmapFont = bitmapFontFactory.createFont(BitmapFontFactory.FontType.SCORE);
    }


    public void render(SpriteBatch spriteBatch) {
        String scoreLabel = String.format(SCORE_LABEL, scoreCollector.getScore());
        bitmapFont.draw(spriteBatch, scoreLabel, GameConfig.HUD_SCORE_X, GameConfig.HUD_SCORE_Y);
    }

    @Override
    public void dispose() {
        bitmapFont.dispose();
    }
}
