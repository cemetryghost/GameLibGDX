package com.game.snake.factory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.game.snake.asset.ColorAsset;
import com.game.snake.asset.FontAsset;


public class BitmapFontFactory {

    private static final int FONT_SIZE_SCORE = 18;
    private static final int FONT_SIZE_MESSAGE = 44;
    private static final int FONT_SIZE_SPLASH = 80;


    public BitmapFont createFont(FontType fontType) {
        switch (fontType) {
            case SCORE:
                return createScoreFont();
            case MESSAGE:
                return createMessageFont();
            case SPLASH:
                return createSplashFont();
        }
        return null;
    }


    private BitmapFont createScoreFont() {
        FileHandle fileHandle = Gdx.files.internal(FontAsset.PIXEL.getPath());
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fileHandle);
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = FONT_SIZE_SCORE;
        parameter.color = ColorAsset.HUD_FONT;
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();

        return font;
    }


    private BitmapFont createMessageFont() {
        FileHandle fileHandle = Gdx.files.internal(FontAsset.PIXEL.getPath());
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fileHandle);
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = FONT_SIZE_MESSAGE;
        parameter.color = ColorAsset.TOAST_MESSAGE_FONT;
        parameter.shadowOffsetX = 2;
        parameter.shadowOffsetY = 2;
        parameter.shadowColor = Color.BLACK;
        parameter.borderColor = ColorAsset.HUD_FONT;
        parameter.borderWidth = 2;
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();

        return font;
    }


    private BitmapFont createSplashFont() {
        FileHandle fileHandle = Gdx.files.internal(FontAsset.PIXEL.getPath());
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fileHandle);
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = FONT_SIZE_SPLASH;
        parameter.color = ColorAsset.TOAST_MESSAGE_FONT;
        parameter.shadowOffsetX = 2;
        parameter.shadowOffsetY = 2;
        parameter.shadowColor = Color.BLACK;
        parameter.borderColor = ColorAsset.HUD_FONT;
        parameter.borderWidth = 2;
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();

        return font;
    }


    public enum FontType {
        SCORE, MESSAGE, SPLASH
    }
}
