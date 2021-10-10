package com.research.game;

import com.badlogic.gdx.Game; // Game extends application adapter
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

// Should normally run games from desktop
// Simply build, run, select desktop
// Will have basics setup in our Research
// and then each screen handle own events

public class Research extends Game {
	ShapeRenderer shapeRenderer;
	SpriteBatch batch;
	Texture img;
	BitmapFont font;
	
	@Override
	public void create () {
		shapeRenderer = new ShapeRenderer();
		batch = new SpriteBatch();
		font = new BitmapFont();
		// We will start on our StartScreen for obvious reasons
		setScreen(new StartScreen(this));
	}

	// dont require a render, because as stated above each screen
	// will render what it needs to

	@Override
	public void dispose () {
		shapeRenderer.dispose();
		batch.dispose();
		img.dispose();
	}
}
