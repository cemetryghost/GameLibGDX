package com.game.SpaceX;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Input.Keys;

public class Player {
    public Vector2 position;
    public Vector2 positionBullet;
    public Sprite sprite;
    public Sprite spriteBullet;
    public float speed = 300;
    public float speedBullet = 1000;
    public Player(Texture img,Texture imgBullet, Color color){
        sprite = new Sprite(img);
        spriteBullet = new Sprite(imgBullet);
        spriteBullet.setScale(4);
        spriteBullet.setColor(color);
        position = new Vector2(Gdx.graphics.getWidth()/2,sprite.getScaleY()*sprite.getHeight()/2);
        positionBullet = new Vector2(0, 10000);
        sprite.setScale(4);
        sprite.setColor(color);
    }
    public void Update(float deltaTime){
        if(Gdx.input.isButtonJustPressed(0) && positionBullet.y>=Gdx.graphics.getHeight()){
            positionBullet.x = position.x+4;
            positionBullet.y = 0;
        }
        if(Gdx.input.isKeyPressed(Keys.A)) position.x-=deltaTime*speed;
        if(Gdx.input.isKeyPressed(Keys.D)) position.x+=deltaTime*speed;

        if(position.x-(sprite.getWidth()*sprite.getScaleX()/2)<=0) position.x = (sprite.getWidth()*sprite.getScaleX()/2);
        if(position.x+(sprite.getWidth()*sprite.getScaleX()/2)>=Gdx.graphics.getWidth()) position.x = Gdx.graphics.getWidth()-(sprite.getWidth()*sprite.getScaleX()/2);

        positionBullet.y+=deltaTime*speedBullet;
    }
    public void Draw(SpriteBatch batch){
        Update(Gdx.graphics.getDeltaTime());
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
        spriteBullet.setPosition(positionBullet.x, positionBullet.y);
        spriteBullet.draw(batch);
    }
}
