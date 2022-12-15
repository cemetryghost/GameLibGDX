package com.game.snake.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.game.snake.actor.base.BaseActor;
import com.game.snake.asset.GfxAsset;
import com.game.snake.config.GameConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Food extends BaseActor {

    private Texture texture;
    private Sprite sprite;
    private Vector2 position;
    private Random random;


    public Food() {
        texture = new Texture(GfxAsset.FOOD.getPath());
        sprite = new Sprite(texture);
        sprite.setSize(GameConfig.FOOD_CELL_SIZE, GameConfig.FOOD_CELL_SIZE);
        random = new Random();
        setVisible(false);
    }


    public Vector2 getPosition() {
        return position;
    }


    public void reset(List<Vector2> bodyPositionList) {
        setVisible(true);
        position = generatePosition(bodyPositionList);
        float posX = GameConfig.FOOD_START_X + GameConfig.FOOD_CELL_SIZE * position.x;
        float posY = GameConfig.FOOD_START_Y + GameConfig.FOOD_CELL_SIZE * position.y;
        sprite.setPosition(posX, posY);
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        if (isVisible()) {
            sprite.draw(spriteBatch);
        }
    }

    @Override
    public void dispose() {
        texture.dispose();
    }


    private Vector2 generatePosition(List<Vector2> bodyPositionList) {
        List<Vector2> positionList = generateEvenPositionList();
        positionList.removeAll(bodyPositionList);
        Vector2 position = positionList.get(random.nextInt(positionList.size()));

        return position;
    }


    private List<Vector2> generateEvenPositionList() {
        List<Vector2> positionList = new ArrayList<>();

        for (int posX = 0; posX < GameConfig.BOARD_WIDTH; posX++) {
            for (int posY = 0; posY < GameConfig.BOARD_HEIGHT; posY++) {
                if ((posY + posX) % 2 == 0) {
                    positionList.add(new Vector2(posX, posY));
                }
            }
        }

        return positionList;
    }
}
