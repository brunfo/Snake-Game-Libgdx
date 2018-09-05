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

    private float moveTimer;
    private float moveTime; //move every x second

    //set player
    private Head player;
    private List<Tail> tails;


    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
    }

    @Override
    public void init() {
        sr = new ShapeRenderer();

        //Timers
        moveTimer = 0;
        moveTime = 0.5f;

        tails = new ArrayList<Tail>();
        player = new Head();

        //add 2 tails
        tails.add(new Tail(player.getPX(),player.getPY(), player.getDirection()));
        tails.add(new Tail(tails.get(0).getPX(),tails.get(0).getPY(),tails.get(0).getDirection()));
        tails.add(new Tail(tails.get(1).getPX(),tails.get(1).getPY(),tails.get(1).getDirection()));
        tails.add(new Tail(tails.get(2).getPX(),tails.get(2).getPY(),tails.get(2).getDirection()));
    }

    @Override
    public void update(float dt) {
        //get user input
        handleInput();

        moveTimer += dt;

        //only moves every time defined by moveTime
        if (moveTimer > moveTime) {

            moveTimer = 0; //reset timer

            //update tails
            //draw tail


            for (int i = tails.size() - 1; i >= 0; i--) {
                if (tails.size() == 0)
                    break;
                if (i == 0)
                    tails.get(i).setPosition(player.getX(), player.getY());
                else

                    tails.get(i).setPosition(tails.get(i - 1).getX(), tails.get(i - 1).getY());

            }

            for (Tail tail : tails) {
                tail.update(dt);
            }
            //update player
            player.update(dt);
        }

    }

    @Override
    public void draw() {
        sr.setProjectionMatrix(Game.camera.combined);
        player.draw(sr);


        //draw tail
        for (Tail tail:tails){
            tail.draw(sr);
        }

    }

    @Override
    public void handleInput() {
        player.setRotateLeft(Gdx.input.isKeyPressed(Input.Keys.LEFT));
        player.setRotateRight(Gdx.input.isKeyPressed(Input.Keys.RIGHT));


    }

    @Override
    public void dispose() {
        sr.dispose();

    }
}
