package com.game.snake.asset;


public enum GfxAsset {

    BODY("body.png"),
    FOOD("food.png");

    GfxAsset(String path) {
        this.path = PREFIX + path;
    }

    public String getPath() {
        return path;
    }

    private static final String PREFIX = "Snake/gfx/";
    private String path;
}
