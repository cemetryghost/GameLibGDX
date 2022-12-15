package com.game.snake.toast;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.game.snake.config.GameConfig;


public class Toast implements Disposable {

    private BitmapFont bitmapFont;
    private GlyphLayout glyphLayout;
    private Vector2 position;
    private PositionX positionX = PositionX.CENTER;
    private PositionY positionY = PositionY.CENTER;
    private float marginX;
    private float marginY;

    public Toast(BitmapFont bitmapFont) {
        this.bitmapFont = bitmapFont;
        glyphLayout = new GlyphLayout();
    }

    public Toast(BitmapFont bitmapFont, String message) {
        this.bitmapFont = bitmapFont;
        glyphLayout = new GlyphLayout();
        glyphLayout.setText(bitmapFont, message);
    }

    public void setPositionX(PositionX positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(PositionY positionY) {
        this.positionY = positionY;
    }

    public void setMarginX(float marginX) {
        this.marginX = marginX;
    }

    public void setMarginY(float marginY) {
        this.marginY = marginY;
    }

    public void updateMessage(String message) {
        glyphLayout.setText(bitmapFont, message);
    }

    public void render(SpriteBatch spriteBatch) {
        updatePosition();
        bitmapFont.draw(spriteBatch, glyphLayout, position.x, position.y);
    }

    @Override
    public void dispose() {
        bitmapFont.dispose();
    }

    private void updatePosition() {
        Vector2 position = new Vector2();

        switch (positionX) {
            case CENTER:
                position.x = (GameConfig.SCREEN_WIDTH - glyphLayout.width) / 2;
                break;
        }

        switch  (positionY) {
            case CENTER:
                position.y = (GameConfig.SCREEN_HEIGHT + glyphLayout.height) / 2 + marginY;
                break;
            case CENTER_ABOVE:
                position.y = (GameConfig.SCREEN_HEIGHT) / 2 + glyphLayout.height + marginY;
                break;
            case CENTER_BELOW:
                position.y = (GameConfig.SCREEN_HEIGHT) / 2 - marginY;
                break;
        }

        this.position = position;
    }
    public enum PositionX {
        CENTER
    }

    public enum PositionY {
        CENTER, CENTER_ABOVE, CENTER_BELOW
    }
}
