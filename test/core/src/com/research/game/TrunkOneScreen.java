package com.research.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

public class TrunkOneScreen extends ScreenAdapter {
    Research game;

    public TrunkOneScreen(Research game) {
        this.game = game;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyRef) {
                if (keyRef == Input.Keys.B) {
                    game.setScreen(new MainScreen(game));
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
        game.font.draw(game.batch, "Please select the option listing difference of arrys vs linked list!",
                Gdx.graphics.getWidth() * .45f,
                Gdx.graphics.getHeight() * .75f);
        game.font.draw(game.batch, "A: Arrays use sequential access, linked list use random access.",
                Gdx.graphics.getWidth() * .4f,
                Gdx.graphics.getHeight() * .6f);
        game.font.draw(game.batch, "B: Arrays use random access, linked list use sequential access.",
                Gdx.graphics.getWidth() * .4f,
                Gdx.graphics.getHeight() * .5f);
        game.font.draw(game.batch, "C: They work the same!",
                Gdx.graphics.getWidth() * .4f,
                Gdx.graphics.getHeight() * .4f);
        game.batch.end();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
