package com.game.snake.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.game.snake.actor.base.BaseActor;
import com.game.snake.actor.model.Body;
import com.game.snake.actor.model.Head;
import com.game.snake.asset.GfxAsset;
import com.game.snake.config.GameConfig;
import com.game.snake.type.DirectionType;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Snake extends BaseActor {

    private Texture texture;
    private LinkedList<Body> bodyList;
    private DirectionType directionType;
    private Head head;


    public Snake() {
        texture = new Texture(GfxAsset.BODY.getPath());
        bodyList = new LinkedList<>();
    }


    public void reset() {
//        Play.counter -= 1;
//        ((Game) Gdx.app.getApplicationListener()).setScreen(new Play());
//        bodyList.clear();

        for (int i = GameConfig.SNAKE_BODY_INIT - 1; i >= 0; i--) {
            Vector2 position = new Vector2(i, 0);
            extendBody(position);
        }

        directionType = DirectionType.RIGHT;
        head = new Head(bodyList.getFirst(), directionType);
    }

    public void move() {
        moveBody();
        moveHead();
    }


    public void crash() {
        for (int i = bodyList.size() - 1; i > 0; i--) {
            Body body = bodyList.get(i);
            body.fog();
        }
    }


    public List<Vector2> getAllBodyPositionList() {
        List<Vector2> vectorList = new ArrayList<>();
        for (Body body : bodyList) {
            vectorList.add(body.getPosition());
        }
        return vectorList;
    }


    public void updateDirection(DirectionType directionType) {
        this.directionType = directionType;
    }


    public boolean isFoodCollision(Vector2 foodPosition) {
        return getAllBodyPositionList().contains(foodPosition);
    }


    public boolean isBodyCollision() {
        return getOnlyBodyPositionList().contains(head.getPosition());
    }


    public void expand() {
        Body tail = bodyList.getLast();
        extendBody(tail.getPosition());
    }

    @Override
    public void render(SpriteBatch batch) {
        for (Body body : bodyList) {
            body.render(batch);
        }
    }

    @Override
    public void dispose() {
        texture.dispose();
    }


    private void extendBody(Vector2 position) {
        Body body = new Body(texture, position);
        bodyList.add(body);
    }


    private void moveBody() {
        for (int i = bodyList.size() - 1; i > 0; i--) {
            Body currentBody = bodyList.get(i);
            Body nextBody = bodyList.get(i - 1);
            currentBody.updatePosition(nextBody.getPosition());
        }
    }

    private void moveHead() {
        head.updateDirection(directionType);
    }


    private List<Vector2> getOnlyBodyPositionList() {
        List<Vector2> vectorList = getAllBodyPositionList();
        vectorList.remove(0);

        return vectorList;
    }
}