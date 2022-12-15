package com.usp.corrida;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Cuida de todos os recursos compartilhados, permitindo seu uso em mais de um objeto
 */
public class Resources {
    public static final int MAX_SPRITES = 11;

    public BitmapFont font20;
    public BitmapFont font32;

    public Texture texBlack;
    public Texture texTextbox;
    public Texture texHurt;

    public Texture[] texSprite = new Texture[MAX_SPRITES];
    public int[] SPRITE_WIDTH = new int[MAX_SPRITES];
    public int[] SPRITE_HEIGHT = new int[MAX_SPRITES];
    public int[] SPRITE_FRAMES = new int[MAX_SPRITES];

    public static Music mscBackground;
    public static Sound sndGameover;
    public Sound sndHit;
    public Sound sndMiss;

    /**
     * Carrega todos os recursos
     */
    public Resources(){
        font20 = new BitmapFont(Gdx.files.internal("run/font/clacon20.fnt"));
        font32 = new BitmapFont(Gdx.files.internal("run/font/clacon32.fnt"));

        texBlack = new Texture(Gdx.files.internal("run/black.png"));
        texTextbox = new Texture(Gdx.files.internal("run/textbox.png"));
        texHurt = new Texture(Gdx.files.internal("run/hurt.png"));

        for(int i = 0;i < MAX_SPRITES;i++){
            texSprite[i] = new Texture(Gdx.files.internal("run/sprites/"+i+".png"));
        }

        SPRITE_WIDTH[0] = 32; SPRITE_HEIGHT[0] = 32; SPRITE_FRAMES[0] = 4;
        SPRITE_WIDTH[1] = 16; SPRITE_HEIGHT[1] = 16; SPRITE_FRAMES[1] = 2;
        SPRITE_WIDTH[2] = 16; SPRITE_HEIGHT[2] = 32; SPRITE_FRAMES[2] = 2;
        SPRITE_WIDTH[3] = 16; SPRITE_HEIGHT[3] = 16; SPRITE_FRAMES[3] = 4;
        SPRITE_WIDTH[4] = 16; SPRITE_HEIGHT[4] = 32; SPRITE_FRAMES[4] = 4;
        SPRITE_WIDTH[5] = 16; SPRITE_HEIGHT[5] = 16; SPRITE_FRAMES[5] = 4;
        SPRITE_WIDTH[6] = 16; SPRITE_HEIGHT[6] = 16; SPRITE_FRAMES[6] = 2;
        SPRITE_WIDTH[7] = 16; SPRITE_HEIGHT[7] = 32; SPRITE_FRAMES[7] = 2;
        SPRITE_WIDTH[8] = 32; SPRITE_HEIGHT[8] = 32; SPRITE_FRAMES[8] = 2;
        SPRITE_WIDTH[9] = 16; SPRITE_HEIGHT[9] = 16; SPRITE_FRAMES[9] = 4;
        SPRITE_WIDTH[10] = 16; SPRITE_HEIGHT[10] = 16; SPRITE_FRAMES[10] = 6;

        mscBackground = Gdx.audio.newMusic(Gdx.files.internal("run/music/one.mp3"));
        mscBackground.setLooping(true);
        mscBackground.setVolume(0.5f);

        sndGameover = Gdx.audio.newSound(Gdx.files.internal("run/sound/gameover.wav"));
        sndHit = Gdx.audio.newSound(Gdx.files.internal("run/sound/hit.ogg"));
        sndMiss = Gdx.audio.newSound(Gdx.files.internal("run/sound/miss.wav"));
    }

    /**
     * Descarrega todos os recursos
     */
    public void dispose(){
        font20.dispose();
        font32.dispose();
        texBlack.dispose();
        texTextbox.dispose();
        texHurt.dispose();

        for(int i = 0;i < MAX_SPRITES;i++) {
            texSprite[i].dispose();
        }

        mscBackground.dispose();
        sndGameover.dispose();
        sndHit.dispose();
        sndMiss.dispose();
    }
}
