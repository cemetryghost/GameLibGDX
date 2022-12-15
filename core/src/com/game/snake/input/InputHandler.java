package com.game.snake.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;


public class InputHandler implements InputProcessor {

    private DirectionListener directionListener;


    public InputHandler(DirectionListener directionListener) {
        this.directionListener = directionListener;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                directionListener.onUp();
                break;
            case Input.Keys.DOWN:
                directionListener.onDown();
                break;
            case Input.Keys.LEFT:
                directionListener.onLeft();
                break;
            case Input.Keys.RIGHT:
                directionListener.onRight();
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
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

}