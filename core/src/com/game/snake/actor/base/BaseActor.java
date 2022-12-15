package com.game.snake.actor.base;

import com.badlogic.gdx.utils.Disposable;


public abstract class BaseActor implements Renderable, Disposable {

    private boolean isVisible = true;


    public boolean isVisible() {

        return isVisible;
    }


    public void setVisible(boolean visible) {

        isVisible = visible;
    }
}
