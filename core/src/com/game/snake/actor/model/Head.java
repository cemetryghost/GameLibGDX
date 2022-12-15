package com.game.snake.actor.model;

import com.badlogic.gdx.math.Vector2;
import com.game.snake.config.GameConfig;
import com.game.snake.type.DirectionType;


public class Head {

    public static final int SNAKE_MOVE_STEP = 1;

    private Body body;
    private DirectionType directionType;


    public Head(Body body, DirectionType directionType) {
        this.body = body;
        this.directionType = directionType;
    }


    public void updateDirection(DirectionType directionType) {
        if (!directionType.isOppositeDirection(this.directionType)) {
            this.directionType = directionType;
        }
        move();
        handleWallCollision();
    }


    public Vector2 getPosition() {
        return body.getPosition();
    }


    private void move() {
        Vector2 position = body.getPosition();

        switch (directionType) {
            case LEFT:
                body.updatePosition(new Vector2(position.x - SNAKE_MOVE_STEP, position.y));
                break;
            case RIGHT:
                body.updatePosition(new Vector2(position.x + SNAKE_MOVE_STEP, position.y));
                break;
            case UP:
                body.updatePosition(new Vector2(position.x, position.y + SNAKE_MOVE_STEP));
                break;
            case DOWN:
                body.updatePosition(new Vector2(position.x, position.y - SNAKE_MOVE_STEP));
                break;
        }
    }


    private void handleWallCollision() {
        if (body.getPositionX() >= GameConfig.BOARD_WIDTH) {
            body.updatePositionX(0);
        }
        if (body.getPositionX() < 0) {
            body.updatePositionX(GameConfig.BOARD_WIDTH - 1);
        }
        if (body.getPositionY() >= GameConfig.BOARD_HEIGHT) {
            body.updatePositionY(0);
        }
        if (body.getPositionY() < 0) {
            body.updatePositionY(GameConfig.BOARD_HEIGHT - 1);
        }
    }
}
