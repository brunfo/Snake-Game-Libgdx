package pt.bisonte.snake.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import pt.bisonte.snake.Game;
import pt.bisonte.snake.entities.Head;
import pt.bisonte.snake.entities.Tail;
import pt.bisonte.snake.managers.GameStateManager;

import java.util.ArrayList;
import java.util.List;

public class PlayState extends GameState {

    private ShapeRenderer sr;

    //set player
    private Head player;
    private List<Tail> tails;


    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
    }

    @Override
    public void init() {
        sr = new ShapeRenderer();

        tails = new ArrayList<Tail>();
        player = new Head(tails);

    }

    @Override
    public void update(float dt) {
        //get user input
        handleInput();

        //update player
        player.update(dt);

    }

    @Override
    public void draw() {
        sr.setProjectionMatrix(Game.camera.combined);
        player.draw(sr);

    }

    @Override
    public void handleInput() {
        player.setRotateLeft(Gdx.input.isKeyJustPressed(Input.Keys.LEFT));
        player.setRotateRight(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT));


    }

    @Override
    public void dispose() {
        sr.dispose();

    }
}
