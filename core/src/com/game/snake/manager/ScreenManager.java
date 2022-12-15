package com.game.snake.manager;

import com.badlogic.gdx.utils.Disposable;
import com.game.snake.Snake4Sony;
import com.game.snake.screen.GameScreen;
import com.game.snake.screen.SplashScreen;

public class ScreenManager implements Disposable {

    private Snake4Sony snake4Sony;


    public ScreenManager(Snake4Sony snake4Sony) {
        this.snake4Sony = snake4Sony;
    }

    public void changeScreen(ScreenType screenType) {
        dispose();
        switch (screenType) {
            case SPLASH:
                snake4Sony.setScreen(new SplashScreen(this));
                break;
            case GAME:
                snake4Sony.setScreen(new GameScreen());
                break;
        }
    }

    @Override
    public void dispose() {
        if (snake4Sony.getScreen() != null) {
            snake4Sony.getScreen().dispose();
        }
    }

    public enum ScreenType {
        SPLASH, GAME
    }
}
