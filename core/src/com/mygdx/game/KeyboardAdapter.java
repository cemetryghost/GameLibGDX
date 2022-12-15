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
    private String dialogKey = "dialog";
    public static String getKeyValue = "";
    private TiledMapTileLayer collisionLayer;
    public static Vector3 direction = new Vector3();
    boolean collideX, collideY,
            wasCollideX, wasCollideY,
            wasCollideLeft, wasCollideRight,
            wasCollideTop, wasCollideBottom,
            xBlocked, yBlocked = false;
    public static boolean isDialog = false;
    public static float getPosX;
    public static float getPosY;
    private Sprite sprite;
    private Animation still, left, right, up, down;

    public KeyboardAdapter(Animation still, Animation left, Animation right, Animation up, Animation down, TiledMapTileLayer collisionLayer){
        super((TextureRegion) still.getKeyFrame(0));
        this.still = still;
        this.left = left;
        this.right = right;
        this.up = up;
        this.down = down;
        this.collisionLayer = collisionLayer;
    }

    @Override
    public void draw(Batch spriteBatch){
        update(Gdx.graphics.getDeltaTime());
        super.draw(spriteBatch);
    }

    public void update(float delta) {
        float oldX = getX(), oldY = getY(), tileWidth = Play.collisionLayer.getTileWidth(), tileHeight = Play.collisionLayer.getTileHeight();
        setX(getX() + velocity.x * delta);

        if (velocity.x < 0) {
            collideX = collidesLeft();
            wasCollideLeft = collideX;
        } else if (velocity.x > 0) {
            collideX = collidesRight();
            wasCollideRight = collideX;
        }

//        increment = collisionLayer.getTileWidth();
//        increment = getWidth() < increment ? getWidth() / 2 : increment / 2;

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
//
        animationTime += delta;
        setRegion((TextureRegion) (velocity.x < 0 ? left.getKeyFrame(animationTime) : velocity.x > 0 ? right.getKeyFrame(animationTime) : velocity.y > 0 ? down.getKeyFrame(animationTime) : velocity.y < 0 ? up.getKeyFrame(animationTime) :  still.getKeyFrame(animationTime)));
    }
    @Override
    public boolean keyDown(int keycode) {

        switch(keycode) {
            case Input.Keys.W:
                velocity.y += speed;
                animationTime = 0;
                break;
            case Input.Keys.S:
                velocity.y -= speed;
                animationTime = 0;
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
        switch (keycode) {
            case Input.Keys.W:
                animationTime = 0;
                if (!wasCollideTop){
                    velocity.y -= speed;
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

    private boolean isCellDialog(float x, float y) {
        TiledMapTileLayer.Cell cell = this.collisionLayer.getCell((int) (x /this.collisionLayer.getTileWidth()), (int) (y / this.collisionLayer.getTileHeight()));
        if(cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey(dialogKey)){
             getKeyValue = cell.getTile().getProperties().get(dialogKey, String.class);
        }
        return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey(dialogKey);
    }

    public String getKeyValue(){
        return getKeyValue;
    }

    public boolean collidesRight() {
        for(float step = 0; step < getHeight(); step += this.collisionLayer.getTileHeight() / 2) {
            if (isCellDialog(getX() + getWidth(), getY() + step)){
                isDialog = true;}
            if (isCellBlocked(getX() + getWidth(), getY() + step)){
                return true;}

        }
        isDialog = false;
        return false;
    }

    public boolean collidesLeft() {
        for(float step = 0; step < getHeight(); step += this.collisionLayer.getTileHeight() / 2) {
            if (isCellDialog(getX(), getY() + step))
                isDialog = true;
            if (isCellBlocked(getX(), getY() + step))
                return true;

        }
        isDialog = false;
        return false;
    }

    public boolean collidesTop() {
        for(float step = 0; step < getWidth(); step += this.collisionLayer.getTileWidth() / 2) {
            if (isCellDialog(getX() + step, getY() + getHeight()))
                 isDialog = true;
            if (isCellBlocked(getX() + step, getY() + getHeight()))
                return true;

        }
        isDialog = false;
        return false;
    }

    public boolean collidesBottom() {
        for(float step = 0; step < getWidth(); step += this.collisionLayer.getTileWidth() / 2) {
            if (isCellDialog(getX() + step, getY()))
                 isDialog = true;
            if (isCellBlocked(getX() + step, getY()))
                return true;

        }
        isDialog = false;
        return false;
    }



    public String getSkin(){
        skin = "character.png";
        if(leftPassed) skin = "HeroLeft.png";
        return skin;
    }
}
