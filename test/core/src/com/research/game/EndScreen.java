package com.research.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input; // Use this to move to second screen
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

public class EndScreen extends ScreenAdapter {
    Research game;

    public EndScreen(Research game) {
        this.game = game;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyRef) {
                if (keyRef == Input.Keys.ENTER) {
                    game.setScreen(new StartScreen(game));
                }
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // This following is essentially the same as Start Screen, but at the end
        game.batch.begin();
        game.font.draw(game.batch, "Good Job!",
                Gdx.graphics.getWidth() * .45f,
                Gdx.graphics.getHeight() / 2);
        game.font.draw(game.batch, "Press Enter to go back to Start Screen!",
                Gdx.graphics.getWidth() * .4f,
                Gdx.graphics.getHeight() / 4);
        game.batch.end();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
