package com.game.snake.actor.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.game.snake.config.GameConfig;


public class Body {

    private static final float ALPHA_FOG_VALUE = 0.5f;

    private Sprite sprite;
    private Vector2 position;


    public Body(Texture texture, Vector2 position) {
        this.position = position;
        sprite = new Sprite(texture);
        sprite.setSize(GameConfig.SNAKE_BODY_CELL_SIZE, GameConfig.SNAKE_BODY_CELL_SIZE);
    }


    public Vector2 getPosition() {

        return position;
    }


    public void updatePosition(Vector2 position)
    {
        this.position = position;
    }


    public void updatePositionX(float x)
    {
        position.x = x;
    }


    public void updatePositionY(float y) {
        position.y = y;
    }


    public float getPositionX() {
        return position.x;
    }


    public float getPositionY() {
        return position.y;
    }


    public void render(SpriteBatch spriteBatch) {
        sprite.setPosition(
                GameConfig.SNAKE_BODY_X + GameConfig.SNAKE_BODY_CELL_SIZE * this.position.x,
                GameConfig.SNAKE_BODY_Y + GameConfig.SNAKE_BODY_CELL_SIZE * this.position.y);
        sprite.draw(spriteBatch);
    }


    public void fog() {
        sprite.setAlpha(ALPHA_FOG_VALUE);
    }
}
