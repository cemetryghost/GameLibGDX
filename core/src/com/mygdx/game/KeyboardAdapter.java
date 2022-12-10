package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class KeyboardAdapter extends Sprite implements InputProcessor {
    boolean leftPassed;
    boolean rightPassed;
    boolean upPassed;
    boolean downPassed;
    String skin;
    public static Vector2 velocity = new Vector2();
    public float speed = 10 * 20,  gravity = 0, animationTime = 0, increment;
    private String blockedKey = "blocked";
    private TiledMapTileLayer collisionLayer;
    public static Vector3 direction = new Vector3();
    boolean collideX, collideY,
            wasCollideX, wasCollideY,
            wasCollideLeft, wasCollideRight,
            wasCollideTop, wasCollideBottom,
            xBlocked, yBlocked = false;
    public static float getPosX;
    public static float getPosY;
    private Sprite sprite;
    private Animation still, left, right;

    public KeyboardAdapter(Animation still, Animation left, Animation right, TiledMapTileLayer collisionLayer){
        super((TextureRegion) still.getKeyFrame(0));
        this.still = still;
        this.left = left;
        this.right = right;
        this.collisionLayer = collisionLayer;
        setSize(collisionLayer.getWidth() / 3, collisionLayer.getHeight() * 1.25f);
        //        super(sprite);
//        this.sprite = sprite;
//        getPosX = sprite.getX();
//        getPosY = sprite.getY();
//        this.collisionLayer = collisionLayer;
        // setSize(getWidth() , getHeight() );
    }

//    public void spriteUpdate(){
//       this.sprite = new Sprite(new TextureRegion(Character.boyAnimation.getFrame()));
//       Play.inputProcessor = new KeyboardAdapter(this.sprite, this.collisionLayer);
//    }

    @Override
    public void draw(Batch spriteBatch){
        update(Gdx.graphics.getDeltaTime());
        super.draw(spriteBatch);


//        super.draw(spriteBatch);
//        Play.camera.translate(getPosX, getPosY);
//        Play.character.render();
//        spriteBatch.draw(Character.boyAnimation.getFrame(), velocity.x, velocity.y);
//        update(Gdx.graphics.getDeltaTime());

    }

    public void update(float delta) {
        float oldX = getX(), oldY = getY(), tileWidth = Play.collisionLayer.getTileWidth(), tileHeight = Play.collisionLayer.getTileHeight();
        //boolean collisionX = false, collisionY = false;
        setX(getX() + velocity.x * delta);

        if (velocity.x < 0) {
            collideX = collidesLeft();
            wasCollideLeft = collideX;
        } else if (velocity.x > 0) {
            collideX = collidesRight();
            wasCollideRight = collideX;
        }

        increment = collisionLayer.getTileWidth();
        increment = getWidth() < increment ? getWidth() / 2 : increment / 2;

        if (collideX) {
            setX(oldX);
            velocity.x = 0;
            wasCollideX = true;
        }else {
            wasCollideX = false;
        }

        setY(getY() + velocity.y * delta);

        if (velocity.y < 0) {
            collideY = collidesBottom();
            wasCollideBottom = collideY;
        } else if (velocity.y > 0) {
            collideY = collidesTop();
            wasCollideTop = collideY;
        }
//
        increment = collisionLayer.getTileHeight();
        increment = getHeight() < increment ? getHeight() / 2 : increment / 2;

        if (collideY) {
            setY(oldY);
            velocity.y = 0;
            wasCollideY = true;
        } else {
            wasCollideY = false;
        }
//        float oldX = getX(), oldY = getY(), tileWidth = Play.collisionLayer.getTileWidth(), tileHeight = Play.collisionLayer.getTileHeight();
//        velocity.y -= gravity * delta;
//
//        // clamp velocity
//        if(velocity.y > speed)
//            velocity.y = speed;
//        else if(velocity.y < -speed)
//            velocity.y = -speed;
//
//        // save old position
//
//        boolean collisionX = false, collisionY = false;
//
//        // move on x
//        setX(getX() + velocity.x * delta);
//        if (velocity.x < 0) {
//            collideX = collidesLeft();
//            wasCollideLeft = collideX;
//        } else if (velocity.x > 0) {
//            collideX = collidesRight();
//            wasCollideRight = collideX;
//        }
//        // calculate the increment for step in #collidesLeft() and #collidesRight()
//        increment = collisionLayer.getTileWidth();
//        increment = getWidth() < increment ? getWidth() / 2 : increment / 2;
//
//        if (collideX) {
//            setX(oldX);
//            velocity.x = 0;
//            wasCollideX = true;
//        }else {
//            wasCollideX = false;
//        }
//
//        if(velocity.x < 0) // going left
//            collisionX = collidesLeft();
//        else if(velocity.x > 0) // going right
//            collisionX = collidesRight();
//
//        // react to x collision
//        if(collisionX) {
//            setX(oldX);
//            velocity.x = 0;
//        }
//
//        // move on y
//        setY(getY() + velocity.y * delta * 5f);
//
//        // calculate the increment for step in #collidesBottom() and #collidesTop()
//        increment = collisionLayer.getTileHeight();
//        increment = getHeight() < increment ? getHeight() / 2 : increment / 2;
//
//
//
//        // react to y collision
//        if(collisionY) {
//            setY(oldY);
//            velocity.y = 0;
//        }
        animationTime += delta;
        setRegion((TextureRegion) (velocity.x < 0 ? left.getKeyFrame(animationTime) : velocity.x > 0 ? right.getKeyFrame(animationTime) : still.getKeyFrame(animationTime)));
    }
    @Override
    public boolean keyDown(int keycode) {
//        if(keycode == Input.Keys.A) {
//            leftPassed = true;
//            velocity.x -= speed;
//            Play.boyOrin = "LEFT";
//            Play.character.getBoyLeft();
//            Character.counterAnimation = 0.0f;
//        }
//        if(keycode == Input.Keys.D) {
//            rightPassed = true;
//            velocity.x += speed;
//            Play.boyOrin = "RIGHT";
//            Play.character.getBoyRight();
//            Character.counterAnimation = 0.0f;
//        }
//        if(keycode == Input.Keys.W) {
//            upPassed = true;
//            velocity.y += speed;
//            Play.boyOrin = "UP";
//            Play.character.getBoyUp();
//            Character.counterAnimation = 0.0f;
//        }
//        if(keycode == Input.Keys.S) {
//            downPassed = true;
//            velocity.y -= speed;
//            Play.boyOrin = "DOWN";
//            Play.character.getBoyDown();
//            Character.counterAnimation = 0.0f;
//        }
//
//        return true;
//        switch (keycode) {
//            case Input.Keys.W:
//                velocity.y += speed;
//                getPosY = velocity.y / 50;
//
//                break;
//            case Input.Keys.S:
//                velocity.y -= speed;
//
//                break;
//            case Input.Keys.A:
//                velocity.x -= speed;
//
//                break;
//            case Input.Keys.D:
//                velocity.x += speed;
//
//                break;
//        }
//        return true;
        switch(keycode) {
            case Input.Keys.W:
                velocity.y += speed;
                getPosY = velocity.y / 50;

                break;
            case Input.Keys.S:
                velocity.y -= speed;
                break;
            case Input.Keys.A:
                velocity.x = -speed;
                animationTime = 0;
                break;
            case Input.Keys.D:
                velocity.x = speed;
                animationTime = 0;
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT) || Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) || Gdx.input.isKeyJustPressed(Input.Keys.UP) || Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
//            Character.counterAnimation = 0.0f; //Обнуление счетчика, когда меняется ориентация
        }
        switch (keycode) {
            case Input.Keys.W:
                animationTime = 0;
                if (!wasCollideTop){
                    velocity.y -= speed;
                    getPosY = velocity.y / 50;
                }
                wasCollideTop = false;
                if (!yBlocked){
                    velocity.x -= speed;
                }
                if (!yBlocked){
                    velocity.x += speed;
                }
                break;
            case Input.Keys.S:
                animationTime = 0;
                if(!wasCollideBottom) {
                    velocity.y += speed;
                }
                wasCollideBottom = false;
                if (!yBlocked){
                    velocity.x -= speed;
                }
                if (!yBlocked){
                    velocity.x += speed;
                }
                break;
            case Input.Keys.A:

                animationTime = 0;
                if(!wasCollideLeft) {
                    velocity.x += speed;
                }
                wasCollideLeft = false;
                if (!xBlocked){
                    velocity.y -= speed;
                }
                if (!xBlocked){
                    velocity.y += speed;
                }
                break;
            case Input.Keys.D:

                animationTime = 0;
                if(!wasCollideRight) {
                    velocity.x -= speed;

                }
                wasCollideRight = false;
                if (!xBlocked){
                    velocity.y -= speed;
                }
                if (!xBlocked){
                    velocity.y += speed;
                }
                break;
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    public Vector2 getDirection(){//камера
        update(Gdx.graphics.getDeltaTime());
        return velocity;
    }
    public Vector2 getMove(){//Персонаж
        return velocity;
    }

    public TiledMapTileLayer getCollisionLayer() {
        return collisionLayer;
    }

    private boolean isCellBlocked(float x, float y) {
        TiledMapTileLayer.Cell cell = this.collisionLayer.getCell((int) (x /this.collisionLayer.getTileWidth()), (int) (y / this.collisionLayer.getTileHeight()));
        return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey(blockedKey);
    }

    public boolean collidesRight() {
        for(float step = 0; step < getHeight(); step += this.collisionLayer.getTileHeight() / 2)
            if(isCellBlocked(getX() + getWidth(), getY() + step))
                return true;
        return false;
    }

    public boolean collidesLeft() {
        for(float step = 0; step < getHeight(); step += this.collisionLayer.getTileHeight() / 2)
            if(isCellBlocked(getX(), getY() + step))
                return true;
        return false;
    }

    public boolean collidesTop() {
        for(float step = 0; step < getWidth(); step += this.collisionLayer.getTileWidth() / 2)
            if(isCellBlocked(getX() + step, getY() + getHeight()))
                return true;
        return false;
    }

    public boolean collidesBottom() {
        for(float step = 0; step < getWidth(); step += this.collisionLayer.getTileWidth() / 2)
            if(isCellBlocked(getX() + step, getY()))
                return true;
        return false;
    }

    public String getSkin(){
        skin = "character.png";
        if(leftPassed) skin = "HeroLeft.png";
        return skin;
    }
}
