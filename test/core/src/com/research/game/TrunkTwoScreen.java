package com.research.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

public class TrunkTwoScreen extends ScreenAdapter {
    Research game;

    public TrunkTwoScreen(Research game) {
        this.game = game;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyRef) {
                if (keyRef == Input.Keys.D) {
                    game.setScreen(new MainScreen(game));
                }
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f, 0.6f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.font.draw(game.batch, "What order do Queues work in?",
                Gdx.graphics.getWidth() * .45f,
                Gdx.graphics.getHeight() * .75f);
        game.font.draw(game.batch, "A: First in Last Out.",
                Gdx.graphics.getWidth() * .4f,
                Gdx.graphics.getHeight() * .6f);
        game.font.draw(game.batch, "B: Last in Last Out.",
                Gdx.graphics.getWidth() * .4f,
                Gdx.graphics.getHeight() * .5f);
        game.font.draw(game.batch, "C: Last in First Out!",
                Gdx.graphics.getWidth() * .4f,
                Gdx.graphics.getHeight() * .4f);
        game.font.draw(game.batch, "D: Fist in First Out!",
                Gdx.graphics.getWidth() * .4f,
                Gdx.graphics.getHeight() * .3f);
        game.batch.end();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
