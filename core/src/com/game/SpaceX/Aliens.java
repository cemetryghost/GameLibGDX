package com.game.SpaceX;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Aliens {
    public Vector2 position;
    public Sprite sprite;
    public Boolean Alive = true;
    public Vector2 positionInitial;

    public Aliens(Vector2 possition, Texture img, Color color){
        position = possition;
        positionInitial = position;
        sprite = new Sprite(img);
        sprite.setColor(color);
        sprite.setScale(4);
    }
    public void Draw(SpriteBatch batch){
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }
}
