package com.research.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
//use this for our random number to place trunks
import com.badlogic.gdx.math.MathUtils;


public class MainScreen extends ScreenAdapter {
    World world;
    Research game;
    OrthographicCamera camera;
    ShapeRenderer shapeRenderer;
    SpriteBatch batch;
    Texture background;
    Texture trunk;
    Texture trunk2;
    Box2DDebugRenderer debugRenderer;

    // Set the starting positions for our player control movement
    // Want postiont to start bottom center, and then from these we will use them for
    // movement later on.
    float circleRad = 75;
    float circleX = Gdx.graphics.getWidth() / 2;
    float circleY = Gdx.graphics.getHeight() / 10;

    // These right here will be used later for assigning, our events a random position
    // Dont do this in render or else it constantly reassigns them
    // cause its constantly rendering
    float trunk_1_positionX = MathUtils.random(1200);
    float trunk_1_positionY = MathUtils.random(720);
    float trunk_2_positionX = MathUtils.random(1200);
    float trunk_2_positionY = MathUtils.random(720);
    float trunk_1_area = 100;
    float trunk_2_area = 100;


    public MainScreen(Research game) {
        this.game = game;
        shapeRenderer = new ShapeRenderer();
        // for our main screen we are going to set a background image.
        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("Background.png"));
        // we will randomly place these trunk images
        // Also could have made them each their own class, but for our needs
        // Doing it here suffices
        trunk = new Texture(Gdx.files.internal("Trunk_Image.png"));
        trunk2 = new Texture(Gdx.files.internal("Trunk_Image_2.png"));
        world = new World(new Vector2(0, -10), true);
        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(50, 25);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            // this override replaces our keydown method with touchdown
            @Override
            public boolean touchDown(int x, int y, int pointer, int button) {

                Vector3 touchedPoint = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                camera.unproject(touchedPoint);

                int renderY = Gdx.graphics.getHeight() - y;
                if (Vector2.dst(circleX, circleY, x, renderY) < circleRad) {
                    game.setScreen(new EndScreen(game));
                }
                else if (Vector2.dst(circleX, circleY, x, renderY) < trunk_1_area) {
                    game.setScreen(new TrunkOneScreen(game));
                } else {
                    // (Vector2.dst(circleX, circleY, x, renderY) < trunk_2_area)
                    game.setScreen(new TrunkTwoScreen(game));
                }
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {

        // If it hits our boundraies need to not go any further
        // stop all movement. Currently has some overlap, but thats
        // mainly due to the fact that getting size of objects in relation
        // to stated can be a bit tricky (assets used vs stated)
        if (circleX < 0 ) {
            circleX = 0;
        } else if (circleY < 0) {
            circleY = 0;
        } else if ( circleX > Gdx.graphics.getWidth()) {
            circleX = Gdx.graphics.getWidth();
        } else if (circleY > Gdx.graphics.getHeight()) {
            circleY = Gdx.graphics.getHeight();
        }

        // Assign standard movement to wasd keys
        if(Gdx.input.isKeyPressed(Input.Keys.W)){ circleY++; } // move up
        else if(Gdx.input.isKeyPressed(Input.Keys.S)){ circleY--; } // move down
        if(Gdx.input.isKeyPressed(Input.Keys.A)){ circleX--; } // move left
        else if(Gdx.input.isKeyPressed(Input.Keys.D)){ circleX++; } // move right

        // the image used in assets was set to our desktop launchers size,
        // and should expand with it if you manually extend the window,
        // However if it doesnt, the color's here should match close enough
        Gdx.gl.glClearColor(0.15f, 0.15f, .15f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // this tells any background images what position to start from.
        // So if we have other images we can designate specific start points
        // Want our background image to be everything so start from 0,0 and go out
        game.batch.begin();
        game.batch.draw(background, 0, 0);
        // sets our trunks at two different random positions of each other
        game.batch.draw(trunk, trunk_1_positionX, trunk_1_positionY);
        game.batch.draw(trunk2, trunk_2_positionX, trunk_2_positionY);
        game.batch.end();

        // this is where we render in the object we will be moving, so its color and size
        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        game.shapeRenderer.setColor(0.7f, 0.2f, 0.1f, 1);
        // first two are starting position, next two are width and height
        game.shapeRenderer.circle(circleX, circleY, 75);
        game.shapeRenderer.end();

    }
    @Override
        public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
