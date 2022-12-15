package pl.sly.game.snake4sony.input;

import com.badlogic.gdx.input.GestureDetector;
import com.game.snake.input.DirectionListener;


public class SwipeHandler extends GestureDetector {


    public SwipeHandler(DirectionListener directionListener) {
        super(new SwipeGestureAdapter(directionListener));
    }

    private static class SwipeGestureAdapter extends GestureAdapter {

        private DirectionListener directionListener;

        
        public SwipeGestureAdapter(DirectionListener directionListener) {
            this.directionListener = directionListener;
        }

        @Override
        public boolean fling(float velocityX, float velocityY, int button) {
            if (Math.abs(velocityX) > Math.abs(velocityY)) {
                if (velocityX > 0) {
                    directionListener.onRight();
                } else {
                    directionListener.onLeft();
                }
            } else {
                if (velocityY > 0) {
                    directionListener.onDown();
                } else {
                    directionListener.onUp();
                }
            }
            return super.fling(velocityX, velocityY, button);
        }
    }
}
