package com.research.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input; // Use this to move to second screen
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

// This will be our starting screen then move to second screen
// Simply want to state the purpose of the simple research project,
// and then move on to the meat of it all

public class StartScreen extends ScreenAdapter {
    Research game;

public StartScreen(Research game) {
    this.game = game;
}

// following takes in user input when a key is down
@Override
public void show() {
    Gdx.input.setInputProcessor(new InputAdapter() {
        @Override
        public boolean keyDown(int keyRef) {
            if (keyRef == Input.Keys.SPACE) {
                // This will move us to our main screen that we will be on
                game.setScreen(new MainScreen(game));
            }
            return true;
        }
    });
}

// Following renders what our screen is showing
@Override
public void render(float delta) {
    // the color values range from 0-1 with f to indicate floats
    // glClearColor sets our color, then flClear draws it for us
    Gdx.gl.glClearColor(0.1f,0.15f, 0.25f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    game.batch.begin(); // this starts
    // the width and height refers to starting position of our text
    game.font.draw(game.batch, "This is our Start Screen",
            Gdx.graphics.getWidth() * 0.45f, // set how far width goes on screen
            Gdx.graphics.getHeight() * .75f); // set height for our text
    game.font.draw(game.batch, "Approach events & click on them them to answer questions!",
            Gdx.graphics.getWidth() * 0.4f,
            Gdx.graphics.getHeight() / 2);
    game.font.draw(game.batch, "Click on the circle when finished to go to the end!",
            Gdx.graphics.getWidth() * 0.4f,
            Gdx.graphics.getHeight() / 4);
    game.font.draw(game.batch, "Click Space to begin!",
            Gdx.graphics.getWidth() * 0.45f,
            Gdx.graphics.getHeight() / 5);
    game.batch.end();
}

// This function is used whenever its no longer main screen
// So simply want to stop gathering user input
@Override
public void hide() {
    Gdx.input.setInputProcessor(null); // stop gathering input
}

}
