package com.game.flappy.states;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.game.flappy.MyGame;
import com.game.flappy.sprites.Bird;
import com.game.flappy.sprites.Tube;
import com.mygdx.game.Play;

public class PlayState extends State{

    private Bird bird;
    private Texture bg;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;
    public static final int GROUND_Y_OFFSET = -30;
    public static final int TUBE_SPACING = 125;
    public static final int TUBE_COUNT = 4;
    public static int counter = 0;
    private static BitmapFont font;

    private Array<Tube> tubes;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 300);
        camera.setToOrtho(false, MyGame.WIDTH / 2, MyGame.HEIGHT / 2);
        bg = new Texture("Flappy/bg.png");
        ground = new Texture("Flappy/ground.png");

        groundPos1 = new Vector2(camera.position.x  - camera.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((camera.position.x - camera.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);

        tubes = new Array<Tube>();

        font = new BitmapFont();
        float c = (float)1.5;
        font.getData().setScale(c,c);
        font.setColor(Color.RED);

        for(int i = 1; i < TUBE_COUNT; i++){
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()) bird.jump();
    }

    @Override
    public void update(float dt){
        handleInput();
        updateGround();
        bird.update(dt);
        camera.position.x = bird.getPosition().x + 80;

        for(int i = 0; i < tubes.size; i++){

            Tube tube = tubes.get(i);
            if(camera.position.x - (camera.viewportWidth / 2) >
                    tube.getPosTopTube().x + tube.getTopTube().getWidth()){
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }

            if(tube.collides(bird.getBounds())) {
                counter = 0;
                gsm.set(new PlayState(gsm));
            }
            if(tube.isGood(bird.getBounds())) {
                counter++;
            }
            if(counter / 20 == 10){
                counter = 0;
                ((Game) Gdx.app.getApplicationListener()).setScreen(new Play());
                MyGame.music.stop();
            }
        }

        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg, camera.position.x - (camera.viewportWidth / 2), 0);
        sb.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);
        for(Tube tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getPosBotTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);

        font.draw(sb, String.valueOf(counter / 20), bird.getPosition().x + 10, bird.getPosition().y + 45);

        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        ground.dispose();
        for(Tube tube : tubes) tube.dispose();
    }

    private void updateGround(){
        if (camera.position.x - (camera.viewportWidth / 2) > groundPos1.x + ground.getWidth())
            groundPos1.add(ground.getWidth() * 2, 0);
        if (camera.position.x - (camera.viewportWidth / 2) > groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth() * 2, 0);
    }
}