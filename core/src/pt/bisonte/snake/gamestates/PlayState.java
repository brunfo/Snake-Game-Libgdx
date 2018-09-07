package pt.bisonte.snake.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import pt.bisonte.snake.Game;
import pt.bisonte.snake.entities.Head;
import pt.bisonte.snake.entities.Fruit;
import pt.bisonte.snake.entities.Tail;
import pt.bisonte.snake.managers.GameStateManager;

import java.util.ArrayList;
import java.util.List;

public class PlayState extends GameState {

    private Head head;
    private List<Tail> body;
    private Fruit fruit;

    private ShapeRenderer sr;

    private float moveTimer;
    private float moveTime; //move every x second

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
    }

    @Override
    public void init() {
        sr = new ShapeRenderer();

        //Timers
        moveTimer = 0;
        moveTime = 0.5f;

        head = new Head();

        resetBody();


    }

    private void resetBody(){
        head.setPosition(
                (MathUtils.random(Game.WIDTH/ Game.CELL_WIDTH))* Game.CELL_WIDTH-10,
                (MathUtils.random(Game.HEIGHT/Game.CELL_WIDTH))* Game.CELL_WIDTH-10
        );

        body = new ArrayList<Tail>();

        //add 2 body
        body.add(new Tail(head.getX(), head.getY()-Game.CELL_WIDTH));
        body.add(new Tail(body.get(0).getX(), body.get(0).getY()-Game.CELL_WIDTH));
        body.add(new Tail(body.get(1).getX(), body.get(1).getY()-Game.CELL_WIDTH));
        body.add(new Tail(body.get(2).getX(), body.get(2).getY()-Game.CELL_WIDTH));
    }

    @Override
    public void update(float dt) {
        //get user input
        handleInput();

        checkCollision();

        //create fruit
        if (fruit == null) {
            float x;
            float y;
            float distance;
            do {
                //TODO the fruit should not spawn in the same position as any object
                // first get the position random
                x = MathUtils.random(Game.WIDTH/Game.CELL_WIDTH)*Game.CELL_WIDTH-10;
                y = MathUtils.random(Game.HEIGHT/Game.CELL_WIDTH)*Game.CELL_WIDTH-10;

                //
                float dx = x - head.getX();
                float dy = y - head.getY();

                // calculates distance
                distance = (float) Math.sqrt(dx * dx + dy * dy);
            } while (distance < 10);

            fruit = new Fruit(x, y);
        }
        else{
            fruit.update(dt);
            if(fruit.shouldRemove())
                fruit=null;
        }

        moveTimer += dt;

        //only moves every time defined by moveTime
        if (moveTimer > moveTime) {

            moveTimer = 0; //reset timer

            //update body position
            if (body.size() != 0) {
                //if player has eat a fruit add a body part
                if(head.hasEat())
                    body.add(new Tail(0,0));
                //sets new position of body parts
                for (int i = body.size() - 1; i >= 0; i--) {
                    if (i == 0)
                        body.get(i).setPosition(head.getX(), head.getY());
                    else
                        body.get(i).setPosition(body.get(i - 1).getX(), body.get(i - 1).getY());
                }
            }

            if(head.isDead())
                resetBody();

            //update head
            head.update(dt);
        }
    }

    /**
     * Check collision point to point intersection
     */
    private void checkCollision(){
        //head to tail
        for (Tail bodyPart: body){
            head.hit(bodyPart.contains(head.getX(), head.getY()));
        }

        //head to fruit
        if(fruit!=null) {
            head.eat(fruit.contains(head.getX(), head.getY()), fruit.getScore());
            if (fruit.shouldRemove())
                fruit = null;
        }

    }

    /**
     * Auxiliary develop matrix
     */
    private void drawMatrix(){
        sr.setColor(0,0,1,1);
        sr.begin(ShapeRenderer.ShapeType.Line);
        for (int i=0; i<Game.WIDTH+1;i+=20){
            sr.line(i,Game.HEIGHT, i, 0);
        }
        for (int i=0; i<Game.HEIGHT+1;i+=20){
            sr.line(Game.WIDTH,i, 0,i);
        }
    }

    @Override
    public void draw() {
        sr.setProjectionMatrix(Game.camera.combined);

        drawMatrix();

        sr.end();
        head.draw(sr);

        //draw body
        for (Tail bodyPart : body) {
            bodyPart.draw(sr);
        }

        if(fruit!=null)
            fruit.draw(sr);

    }

    @Override
    public void handleInput() {
        head.setRotateLeft(Gdx.input.isKeyJustPressed(Input.Keys.LEFT));
        head.setRotateRight(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT));
    }

    @Override
    public void dispose() {
        sr.dispose();

    }
}
