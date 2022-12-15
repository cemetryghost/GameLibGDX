package pl.sly.game.snake4sony.manager;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.game.snake.factory.BitmapFontFactory;
import com.game.snake.toast.Toast;
import com.game.snake.toast.ToastTimer;
import com.game.snake.toast.ToastTimerListener;


public class ToastManager implements Disposable {

    private static final String MESSAGE_START_IN = "START IN:";
    private static final int MARGIN_Y_TOAST_MESSAGE = 10;
    private static final int MARGIN_Y_TOAST_TIMER_MESSAGE = 5;

    private BitmapFontFactory bitmapFontFactory;
    private Toast toastMessage;
    private ToastTimer toastTimer;

    public ToastManager() {
        bitmapFontFactory = new BitmapFontFactory();
    }

    public void showStartToast(ToastTimerListener toastTimerListener) {
        buildStartToast();
        buildTimerToast(toastTimerListener);
    }

    public void hideStartToast() {
        toastMessage = null;
        toastTimer = null;
    }

    public void update(float delta) {
        if (toastTimer != null) {
            toastTimer.update(delta);
        }
    }

    public void render(SpriteBatch spriteBatch) {
        if (toastMessage != null) {
            toastMessage.render(spriteBatch);
        }

        if (toastTimer != null) {
            toastTimer.render(spriteBatch);
        }
    }

    @Override
    public void dispose() {
        if (toastMessage != null) {
            toastMessage.dispose();
        }

        if (toastTimer != null) {
            toastTimer.dispose();
        }
    }


    private void buildStartToast() {
        BitmapFont bitmapFont = bitmapFontFactory.createFont(BitmapFontFactory.FontType.MESSAGE);
        toastMessage = new Toast(bitmapFont, MESSAGE_START_IN);
        toastMessage.setPositionY(Toast.PositionY.CENTER_ABOVE);
        toastMessage.setMarginY(MARGIN_Y_TOAST_MESSAGE);
    }


    private void buildTimerToast(ToastTimerListener toastTimerListener) {
        BitmapFont bitmapFont = bitmapFontFactory.createFont(BitmapFontFactory.FontType.MESSAGE);
        toastTimer = new ToastTimer(bitmapFont);
        toastTimer.setPositionY(Toast.PositionY.CENTER_BELOW);
        toastTimer.setToastTimerListener(toastTimerListener);
        toastTimer.setMarginY(MARGIN_Y_TOAST_TIMER_MESSAGE);
    }
}
