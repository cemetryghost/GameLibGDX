package com.game.snake.toast;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class ToastTimer extends Toast {

    private static final float DELAY = 1.0f;
    private static final int COUNT_INIT = 3;

    private float timeDelta = DELAY;
    private int count = COUNT_INIT;
    private ToastTimerListener toastTimerListener;

    public ToastTimer(BitmapFont bitmapFont) {
        super(bitmapFont);
    }


    public void setToastTimerListener(ToastTimerListener toastTimerListener) {
        this.toastTimerListener = toastTimerListener;
    }

    public void update(float delta) {
        timeDelta -= delta;
        if (timeDelta <= 0) {
            if (count <= 1) {
                count = COUNT_INIT;
                if (toastTimerListener != null) {
                    toastTimerListener.onTimeOut();
                }
            } else {
                count -= 1;
            }
            timeDelta = DELAY;
        }
        updateMessage(String.valueOf(count));
    }
}
