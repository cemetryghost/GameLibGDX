package com.game.snake.asset;

public enum FontAsset {

    PIXEL("pixel.ttf");

    FontAsset(String path) {
        this.path = PREFIX + path;
    }

    public String getPath() {
        return path;
    }

    private static final String PREFIX = "Snake/font/";
    private String path;
}
