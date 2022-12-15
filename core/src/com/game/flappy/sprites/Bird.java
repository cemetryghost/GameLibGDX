package com.game.flappy.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird{
    public static final int MOVEMENT = 100;
    public static final int GRAVITY = -15;
    private Vector3 position;
    private Vector3 velosity;
    private Rectangle bounds;
    private Animation birdAnimation;
    private Texture texture;
    private Sound flap;

    public Bird(int x, int y){
        position = new Vector3(x, y, 0);
        velosity = new Vector3(0,0,0);
        texture = new Texture("Flappy/birdanimation.png");
        birdAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);
        bounds = new Rectangle(x, y, texture.getWidth() / 3, texture.getHeight());
        flap = Gdx.audio.newSound(Gdx.files.internal("Flappy/sfx_wing.ogg"));
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getBird() {
        return birdAnimation.getFrame();
    }

    public void update(float dt){
        birdAnimation.update(dt);

        if(position.y > 0) velosity.add(0, GRAVITY, 0);
        velosity.scl(dt);
        position.add(MOVEMENT * dt, velosity.y, 0);
        if(position.y < 80) position.y = 80;
        if(position.y > 376) position.y = 376;

        velosity.scl(1 / dt);
        bounds.setPosition(position.x, position.y);

    }

    public void jump(){
        velosity.y = 250;
        flap.play();
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose() {
        texture.dispose();
        flap.dispose();
    }
}