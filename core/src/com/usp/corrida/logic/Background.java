package com.usp.corrida.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.usp.corrida.Core;
import com.usp.corrida.utils.Utils;

/**
 * Renderiza todo o cenário de fundo do jogo. Basta chamar a função render no ciclo de renderização.
 */
public class Background {
    public static final int MAX_CLOUD = 5;
    public static final int MAX_TREE = 10;

    private final Core core;

    private Texture texBackground;
    private Texture texTerrain;
    private Texture texTree;

    private TextureRegion texCloud;
    private TextureRegion texBgCloud;
    private TextureRegion texBgMountain;
    private TextureRegion texBgTop;
    private TextureRegion texTile1;
    private TextureRegion texTile2;

    private final Vector2[] cloudPosition = new Vector2[MAX_CLOUD];
    private final float[] treePositionX = new float[MAX_TREE];

    public Background(Core core){
        this.core = core;

        loadResources();
        resetBackground();
    }

    /**
     * Volta para o estado zero
     */
    public void resetBackground(){
        setupClouds();
        setupTrees();
    }

    /**
     * Carrega recursos gráficos do background
     */
    private void loadResources(){
        texBackground = new Texture(Gdx.files.internal("run/background.png"));
        texTerrain = new Texture(Gdx.files.internal("run/terrain.png"));
        texTree = new Texture(Gdx.files.internal("run/tree.png"));

        texCloud = new TextureRegion(texBackground, 0, 16, 16, 16);
        texBgCloud = new TextureRegion(texBackground, 16, 0, 160, 32);
        texBgMountain = new TextureRegion(texBackground, 16, 32, 160, 48);
        texBgTop = new TextureRegion(texBackground, 0, 0, 16, 16);

        texTile1 = new TextureRegion(texTerrain, 16, 0, 16, 16);
        texTile2 = new TextureRegion(texTerrain, 16, 16, 16, 16);
    }

    /**
     * Define a posição inicial das nuvens
     */
    private void setupClouds(){
        for(int i = 0; i < MAX_CLOUD; i++){
            cloudPosition[i] = new Vector2(0, 0);
            cloudPosition[i].x = core.rand.getIntRand(-16, (int)core.width);
            cloudPosition[i].y = core.rand.getIntRand(100, (int)core.height-64);
        }
    }

    /**
     * Define a posição incial das árvores
     */
    private void setupTrees(){
        for(int i = 0;i < MAX_TREE;i++){
            treePositionX[i] = core.rand.getIntRand(-48, (int)core.width*2);
        }
    }

    /**
     * Chamada logo no início da função render. É utilizada para atualizar tudo antes da renderização
     * @param delta Variação de tempo entre a chamada atual e a última chamada
     * @param offsetX Deslocamento da coordenada x
     */
    private void update(float delta, float offsetX){
        // Updating tree position
        for(int i = 0;i < MAX_TREE;i++){
            float posx = treePositionX[i]-offsetX;
            if (posx < -48) treePositionX[i] = offsetX+core.width+core.rand.getIntRand(0, (int)core.width);
        }
    }

    /**
     * Renderiza todo o cenário do jogo
     * @param delta Variação de tempo entre a chamada atual e a última chamada
     * @param offsetX Deslocamento da coordenada x
     */
    public void render(float delta, float offsetX){
        update(delta, offsetX);

        // Drawing clouds
        for(int i = 0; i < MAX_CLOUD; i++){
            core.batch.draw(texCloud, cloudPosition[i].x, cloudPosition[i].y);
        }

        // Drawing top bar
        int cnt1 = ((int)core.width-1)/16+1;
        for(int i = 0;i < cnt1;i++){
            core.batch.draw(texBgTop, 16*i, core.height-48);
        }
        core.batch.draw(core.res.texBlack, 0, core.height-32, core.width, 32);

        // Drawing background clouds
        int cnt3 = ((int)core.width-1)/160+1;
        float offset3 = Utils.fixFloat((offsetX/4f)%160);
        for(int i = 0;i < cnt3+1;i++){
            core.batch.draw(texBgCloud, 160*i - offset3, 64);
        }

        // Drawing mountain
        int cnt2 = ((int)core.width-1)/160+1;
        float offset2 = Utils.fixFloat((offsetX/2f)%160);
        for(int i = 0;i < cnt2+1;i++){
            core.batch.draw(texBgMountain, 160*i - offset2, 16);
        }

        // Drawing terrain
        int cnt4 = ((int)core.width-1)/16+1;
        float offset4 = Utils.fixFloat(offsetX%16);
        for(int i = 0;i < cnt4+1;i++){
            core.batch.draw(texTile1, 16*i-offset4, 16);
            core.batch.draw(texTile2, 16*i-offset4, 0);
        }

        // Drawing trees
        for(int i = 0;i < MAX_TREE;i++){
            core.batch.draw(texTree, treePositionX[i]-offsetX, 32);
        }
    }

    /**
     * Descarrega todos os recursos
     */
    public void dispose(){
        texBackground.dispose();
        texTerrain.dispose();
        texTree.dispose();
    }
}
