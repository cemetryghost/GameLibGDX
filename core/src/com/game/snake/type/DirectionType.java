package com.game.snake.type;


public enum DirectionType {
    LEFT, RIGHT, UP, DOWN;

    public boolean isOppositeDirection(DirectionType directionType) {
        if (this == LEFT && directionType == RIGHT) {
            return true;
        }
        if (this == RIGHT && directionType == LEFT) {
            return true;
        }
        if (this == UP && directionType == DOWN) {
            return true;
        }
        if (this == DOWN && directionType == UP) {
            return true;
        }

        return false;
    }
}
