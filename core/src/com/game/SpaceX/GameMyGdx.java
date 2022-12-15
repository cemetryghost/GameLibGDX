package com.game.SpaceX;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Play;

public class GameMyGdx extends ApplicationAdapter implements Screen {
	SpriteBatch batch;
	Texture img;
	Texture imgBullet;
	Texture imgAlien;
	Player player;
	Aliens[] aliens;
	int NumWidthAliens = 11;
	int NumHeightAliens = 5;
	int spacingAliens = 40;
	int minXAliens;
	int minYAliens;
	int maxXAliens;
	int maxYAliens;
	int directionAliens = 1;
	float speedAliens = 100;
	Vector2 offsetAliens;
	@Override
	public void create () {

	}
	int amountAliveAliens = 0;
	int counter = 0;


	@Override
	public void show() {
		offsetAliens = Vector2.Zero;
		batch = new SpriteBatch();
		img = new Texture("game/player.png");
		imgBullet = new Texture("game/bullet.png");
		imgAlien = new Texture("game/aliens.png");
		player = new Player(img, imgBullet,Color.PURPLE);
		aliens = new Aliens[NumWidthAliens * NumHeightAliens];
		int i = 0;
		for(int y = 0; y< NumHeightAliens; y++)
		{
			for(int x = 0; x< NumWidthAliens; x++)
			{
				Vector2 position = new Vector2(x* spacingAliens,y* spacingAliens);
				position.x+=Gdx.graphics.getWidth()/2;
				position.y+=Gdx.graphics.getHeight();
				position.x-=(NumWidthAliens /2)* spacingAliens;
				position.y-=(NumHeightAliens)* spacingAliens;
				aliens[i] = new Aliens(position, imgAlien,Color.PURPLE);
				i++;
			}
		}
	}

	@Override
	public void render(float delta) {
		float deltaTime = Gdx.graphics.getDeltaTime();
		ScreenUtils.clear(0, 0, 0, 1);

		batch.begin();
		player.Draw(batch);
		if(counter == 55)	((Game) Gdx.app.getApplicationListener()).setScreen(new Play());
		for(int i = 0;i<aliens.length;i++)
		{
			if(aliens[i].Alive)
			{
				if(player.spriteBullet.getBoundingRectangle().overlaps(aliens[i].sprite.getBoundingRectangle()))
				{
					player.positionBullet.y = 10000;
					aliens[i].Alive = false;
					counter++;
					break;
				}
			}
		}
		minXAliens = 10000;
		minYAliens = 10000;
		maxXAliens = 0;
		maxYAliens = 0;
		amountAliveAliens = 0;
		for(int i = 0;i<aliens.length;i++)
		{
			if(aliens[i].Alive)
			{
				int IndexX = i% NumWidthAliens;
				int IndexY = i/ NumWidthAliens;
				if(IndexX> maxXAliens) maxXAliens = IndexX;
				if(IndexX< minXAliens) minXAliens = IndexX;
				if(IndexY> maxYAliens) maxYAliens = IndexY;
				if(IndexY< minYAliens) minYAliens = IndexY;
				amountAliveAliens++;
			}
		}
		if(amountAliveAliens == 0)
		{
			for(int i = 0;i<aliens.length;i++)
			{
				aliens[i].Alive = true;
			}
			offsetAliens =  new Vector2(0,0);
			batch.end();
			speedAliens = 100;
			return;
		}
		offsetAliens.x+= directionAliens *deltaTime* speedAliens;
		if(aliens[maxXAliens].position.x>=Gdx.graphics.getWidth())
		{
			directionAliens = -1;
			offsetAliens.y-=aliens[0].sprite.getHeight()*aliens[0].sprite.getScaleY()*0.25f;
			speedAliens +=3;
		}
		if(aliens[minXAliens].position.x<=0)
		{
			directionAliens = 1;
			offsetAliens.y-=aliens[0].sprite.getHeight()*aliens[0].sprite.getScaleY()*0.25f;
			speedAliens +=3;
		}
		if(aliens[minYAliens].position.y<=0)
		{
			Gdx.app.exit();
		}
		for(int i = 0;i<aliens.length;i++)
		{
			aliens[i].position = new Vector2(aliens[i].positionInitial.x+ offsetAliens.x,aliens[i].positionInitial.y+ offsetAliens.y);
			if(aliens[i].Alive)
			{
				aliens[i].Draw(batch);
				if(aliens[i].sprite.getBoundingRectangle().overlaps(player.sprite.getBoundingRectangle()))
				{
					Gdx.app.exit();
				}

			}
		}
		batch.end();
	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
