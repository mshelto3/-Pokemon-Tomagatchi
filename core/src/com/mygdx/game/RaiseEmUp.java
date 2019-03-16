package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class RaiseEmUp extends Game {
	SpriteBatch batch;


	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new StartMenu(this));
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
