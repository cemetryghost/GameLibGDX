package com.game.snake.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.game.snake.actor.Food;
import com.game.snake.actor.Snake;
import com.game.snake.config.GameConfig;
import com.game.snake.game.ScoreCollector;
import com.game.snake.hud.ScoreHUD;
import com.game.snake.input.DirectionListener;
import com.game.snake.input.InputHandler;
import com.game.snake.object.Board;
import com.game.snake.toast.ToastTimerListener;
import com.game.snake.type.DirectionType;

import pl.sly.game.snake4sony.input.SwipeHandler;
import pl.sly.game.snake4sony.manager.ToastManager;


public class GameState implements ToastTimerListener, DirectionListener, Disposable {

    private Board board;
    private Snake snake;
    private Food food;
    private ScoreCollector scoreCollector;
    private ScoreHUD scoreHud;
    private ToastManager toastManager;
    private float timeDelta;
    private boolean isRunning;


    public GameState() {
        board = new Board();
        snake = new Snake();
        food = new Food();
        toastManager = new ToastManager();
        scoreCollector = new ScoreCollector();
        scoreHud = new ScoreHUD(scoreCollector);
        toastManager.showStartToast(this);
        setupInputProcessor();
    }

    public void update(float delta) {
        if (isRunning) {
            moveSnake(delta);
            handleCollision();
        }
        toastManager.update(delta);
    }

    public void stop() {
        gameStop();
    }

    public void renderGame(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        board.render(shapeRenderer);
        shapeRenderer.end();

        spriteBatch.begin();
        snake.render(spriteBatch);
        food.render(spriteBatch);
        scoreHud.render(spriteBatch);
        toastManager.render(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void onLeft() {
        snake.updateDirection(DirectionType.LEFT);
    }

    @Override
    public void onRight() {
        snake.updateDirection(DirectionType.RIGHT);
    }

    @Override
    public void onUp() {
        snake.updateDirection(DirectionType.UP);
    }

    @Override
    public void onDown() {
        snake.updateDirection(DirectionType.DOWN);
    }

    @Override
    public void dispose() {
        snake.dispose();
        food.dispose();
        scoreHud.dispose();
        toastManager.dispose();
    }

    @Override
    public void onTimeOut() {
        resetGame();
    }

    private void moveSnake(float delta) {
        timeDelta -= delta;
        if (timeDelta <= 0) {
            snake.move();
            timeDelta = GameConfig.SNAKE_DELAY_TIME;
        }
    }

    private void handleCollision() {
        if (snake.isFoodCollision(food.getPosition())) {
            snake.expand();
            resetFood();
            scoreCollector.addScore();
        }

        if (snake.isBodyCollision()) {
            snake.crash();
            gameStop();
            toastManager.showStartToast(this);
        }
    }

    private void resetFood() {
        food.reset(snake.getAllBodyPositionList());
    }

    private void gameStart() {
        isRunning = true;
    }

    private void gameStop() {
        isRunning = false;
    }

    private void resetGame() {
        toastManager.hideStartToast();
        snake.reset();
        scoreCollector.reset();
        resetFood();
        gameStart();
    }

    private void setupInputProcessor() {
        InputProcessor swipeHandler = new SwipeHandler(this);
        InputProcessor inputHandler = new InputHandler(this);
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(swipeHandler);
        inputMultiplexer.addProcessor(inputHandler);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }
}
