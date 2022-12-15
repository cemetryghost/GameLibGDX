package com.game.college;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Play;

import java.util.Iterator;

public class GameScreen implements Screen {
    final College game;
    Texture otchImg;
    Texture akvaImg;
    Texture rusCatcherImg;
    Texture nozsImg;
    Texture capImg;
    Texture[] collegeDropArray;
    Texture gameBackgroundImg;

    Sound catchSound;
    Music catchMusic;

    OrthographicCamera camera;
    SpriteBatch batch;

    Rectangle rusCatcher;
    Array<CollegeDrop> masDrops;

    long lastDropTime; //сколько времени прошло после упавшей капли
    int speed = 200;
    long time = 1000000000; //сколько по времени будет длиться ускорение
    int combo = 0;
    boolean isModeActive = false;

    Vector3 touchPos = new Vector3();

    public GameScreen(final College game) {
        this.game = game;
        otchImg = new Texture(Gdx.files.internal("college/otch.png"));
        akvaImg = new Texture(Gdx.files.internal("college/akva.png"));
        rusCatcherImg = new Texture("college/rus.png");
        nozsImg = new Texture(Gdx.files.internal("college/noz.png"));
        capImg = new Texture(Gdx.files.internal("college/cap.png"));

        gameBackgroundImg = new Texture(Gdx.files.internal("college/gameBackground.jpg"));

        collegeDropArray = new Texture[] {capImg, akvaImg, nozsImg, otchImg};

        catchSound = Gdx.audio.newSound(Gdx.files.internal("college/catchSound.ogg"));
        catchMusic = Gdx.audio.newMusic(Gdx.files.internal("college/Sweden.mp3"));

        batch = new SpriteBatch();

        rusCatcher = new Rectangle();
        rusCatcher.x = (float)Gdx.graphics.getWidth() / 2 - (float) rusCatcherImg.getWidth() / 2;
        rusCatcher.y = 215;
        rusCatcher.width = rusCatcherImg.getWidth();
        rusCatcher.height = 10;

        camera =new OrthographicCamera();
        camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());


        catchMusic.setLooping(true);
        catchMusic.play();

        masDrops = new Array<>();
        spawnmasdrop(); //рождение
    }
    private void spawnmasdrop() {
        Circle college = new Circle();
        int type = 0;
        if (isModeActive & speed > 200){
            speed-=10;                          //с каждым новом дропом скорость уменьшается после ускорения
        }
        if (isModeActive & time < 1000000000){
            time+=25000000;
        }
        college.x = MathUtils.random(0, Gdx.graphics.getWidth()- rusCatcherImg.getWidth() ); //генерирется откуда появится

        if(MathUtils.randomBoolean(0.25f)){           //рандомный тип
            type =0;
        }
        else if(MathUtils.randomBoolean(0.20f)){
            type = 1;
        }
        else if(MathUtils.randomBoolean(0.20f)){
            type = 2;
        }
        else if(MathUtils.randomBoolean(0.15f)){
            type = 3;
        }
        college.y = Gdx.graphics.getHeight(); //на какой высоте родится
        college.radius = 60;
        masDrops.add(new CollegeDrop(college, type));
        lastDropTime = TimeUtils.nanoTime();
    }




    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(gameBackgroundImg,0,0);
        batch.draw(rusCatcherImg, rusCatcher.x, rusCatcher.y-215);
        game.font.draw(batch, String.valueOf(combo), (float)Gdx.graphics.getWidth()/2,(float)Gdx.graphics.getHeight()/2 );
        if(combo >= 30){
            MyGdxGame.counter += 1;
            ((Game) Gdx.app.getApplicationListener()).setScreen(new Play());
        }
        for(CollegeDrop collegedrop: masDrops) {
            batch.draw(collegeDropArray[collegedrop.type], collegedrop.circle.x-collegedrop.circle.radius, collegedrop.circle.y-collegedrop.circle.radius);
        }
        batch.end();

        if(Gdx.input.isTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            rusCatcher.x = touchPos.x - (float) rusCatcherImg.getWidth()  / 2; //перемещение рюкзачка по нажатию
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)) rusCatcher.x -= 1000 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.D)) rusCatcher.x += 1000 * Gdx.graphics.getDeltaTime();
                                          //передвижение по кнопкам

        if(rusCatcher.x < 0) rusCatcher.x = 0;
        if(rusCatcher.x > Gdx.graphics.getWidth() - rusCatcherImg.getWidth() ) rusCatcher.x = Gdx.graphics.getWidth() - rusCatcherImg.getWidth() ;
                                   //ограничение на границу экрана

        if(TimeUtils.nanoTime() - lastDropTime > time) spawnmasdrop(); //создается новая капелька по истечению времени

        Iterator<CollegeDrop> iter = masDrops.iterator(); //перебор массива
        while(iter.hasNext()) {
            CollegeDrop coldrop = iter.next();
            coldrop.circle.y -= speed * Gdx.graphics.getDeltaTime(); //перевод в движение вниз капелек
            if(coldrop.circle.y + coldrop.circle.radius*2 < 0){
                iter.remove();                               //удаление пропущенных капелей
            }
            if(Intersector.overlaps(coldrop.circle, rusCatcher)) {
                catchSound.play();
                if(coldrop.type == 0){
                    combo+=3;
                }
                if(coldrop.type == 1){
                    speed = 600;
                    isModeActive = true;
                }
                iter.remove();
                if(coldrop.type == 2){
                    combo+=5;
                }
                if(coldrop.type == 3){
                    MyGdxGame.counter -= 1;
                    catchMusic.stop();
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new Play());
                    //сюда пихнуть закрытие
                }
            }
        }
    }
    public void show() {
        catchMusic.play();
    }

    class CollegeDrop {
        Circle circle;
        int type;

        public CollegeDrop(Circle circle, int type) {
            this.circle = circle;
            this.type = type;
        }
    }
    public void resize(int width, int height) {
    }
    public void hide() {
    }
    public void pause() {
    }
    public void resume() {
    }
    public void dispose() {
        otchImg.dispose();
        akvaImg.dispose();
        rusCatcherImg.dispose();
        nozsImg.dispose();
        capImg.dispose();

        catchSound.dispose();
        catchMusic.dispose();

        batch.dispose();
    }
}
