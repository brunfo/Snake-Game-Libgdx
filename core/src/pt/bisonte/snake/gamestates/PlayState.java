package pt.bisonte.snake.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import pt.bisonte.snake.Game;
import pt.bisonte.snake.entities.Body;
import pt.bisonte.snake.managers.GameStateManager;

import java.util.ArrayList;
import java.util.List;

public class PlayState extends GameState {

    private ShapeRenderer sr;

    private float moveTimer;
    private float moveTime; //move every x second

    //set head
    private Body head;
    private List<Body> body;


    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
    }

    @Override
    public void init() {
        sr = new ShapeRenderer();

        //Timers
        moveTimer = 0;
        moveTime = 0.5f;

        head = new Body();
        body = new ArrayList<Body>();

        //add 2 body
        body.add(new Body(head.getPX(), head.getPY()));
        body.add(new Body(body.get(0).getPX(), body.get(0).getPY()));
        body.add(new Body(body.get(1).getPX(), body.get(1).getPY()));
        body.add(new Body(body.get(2).getPX(), body.get(2).getPY()));
    }

    @Override
    public void update(float dt) {
        //get user input
        handleInput();

        moveTimer += dt;

        //only moves every time defined by moveTime
        if (moveTimer > moveTime) {

            moveTimer = 0; //reset timer

            //set body
            if (body.size() != 0) {
                for (int i = body.size() - 1; i >= 0; i--) {
                    //for (int i = 0; i <body.size(); i++) {
                    if (i == 0)
                        body.get(i).setPosition(head.getX(), head.getY());
                    else

                        body.get(i).setPosition(body.get(i - 1).getX(), body.get(i - 1).getY());

                }
            }

            for (Body bodyPart : body) {
                bodyPart.update(dt);
            }
            //update head
            head.update(dt);
        }
    }

    @Override
    public void draw() {
        sr.setProjectionMatrix(Game.camera.combined);
        head.draw(sr);

        //draw body
        for (Body bodyPart: body){
            bodyPart.draw(sr);
        }

    }

    @Override
    public void handleInput() {
        head.setRotateLeft(Gdx.input.isKeyPressed(Input.Keys.LEFT));
        head.setRotateRight(Gdx.input.isKeyPressed(Input.Keys.RIGHT));
    }

    @Override
    public void dispose() {
        sr.dispose();

    }
}
