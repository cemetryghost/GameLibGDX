package com.game.snake.object;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.game.snake.asset.ColorAsset;
import com.game.snake.config.GameConfig;

import java.util.ArrayList;
import java.util.List;


public class Board {

    public void render(ShapeRenderer shapeRenderer) {
        renderBoardFrame(shapeRenderer);
        renderBoard(shapeRenderer);
    }


    private void renderBoardFrame(ShapeRenderer shapeRenderer) {

        shapeRenderer.setColor(ColorAsset.BOARD_FRAME_OUT);
        shapeRenderer.rect(
                GameConfig.BOARD_FRAME_OUT_X,
                GameConfig.BOARD_FRAME_OUT_Y,
                GameConfig.BOARD_FRAME_OUT_WIDTH,
                GameConfig.BOARD_FRAME_OUT_HEIGHT);
        shapeRenderer.setColor(ColorAsset.BOARD_FRAME_IN);
        shapeRenderer.rect(
                GameConfig.BOARD_FRAME_IN_X,
                GameConfig.BOARD_FRAME_IN_Y,
                GameConfig.BOARD_FRAME_IN_WIDTH,
                GameConfig.BOARD_FRAME_IN_HEIGHT);
    }

    private void renderBoard(ShapeRenderer shapeRenderer) {
        List<Vector2> boardList = generateBoard();
        for (Vector2 position : boardList) {
            if (isEven(position)) {
                shapeRenderer.setColor(ColorAsset.BOARD_CHECKER_GREEN_LIGHT);
            } else {
                shapeRenderer.setColor(ColorAsset.BOARD_CHECKER_GREEN_DARK);
            }
            renderSquare(shapeRenderer, position);
        }
    }
    private void renderSquare(ShapeRenderer shapeRenderer, Vector2 position) {
        float drawPosX = GameConfig.BOARD_X + position.x * GameConfig.BOARD_CELL_SIZE;
        float drawPosY = GameConfig.BOARD_Y + position.y * GameConfig.BOARD_CELL_SIZE;
        shapeRenderer.rect(drawPosX, drawPosY, GameConfig.BOARD_CELL_SIZE, GameConfig.BOARD_CELL_SIZE);
    }

    private boolean isEven(Vector2 vector) {
        return (vector.x + vector.y) % 2 == 0;
    }

    private static List<Vector2> generateBoard() {
        List<Vector2> boardList = new ArrayList<>();

        for (int posX = 0; posX < GameConfig.BOARD_WIDTH; posX++) {
            for (int posY = 0; posY < GameConfig.BOARD_HEIGHT; posY++) {
                boardList.add(new Vector2(posX, posY));
            }
        }

        return boardList;
    }
}
