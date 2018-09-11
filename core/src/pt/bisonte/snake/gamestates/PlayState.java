package pt.bisonte.snake.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import pt.bisonte.snake.Game;
import pt.bisonte.snake.entities.Fruit;
import pt.bisonte.snake.entities.Head;
import pt.bisonte.snake.entities.Tail;
import pt.bisonte.snake.managers.FontManager;
import pt.bisonte.snake.managers.GameStateManager;

import java.util.ArrayList;
import java.util.List;


public class PlayState extends SetupPlayState {

    private SpriteBatch sb;
    private BitmapFont titleFont;
    private BitmapFont font;


    private Head head;
    private List<Tail> body;
    private Fruit fruit;

    private float moveTimer;
    private float moveTime; //move every x second


    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
    }

    @Override
    public void init() {
        super.init();

        sb = new SpriteBatch();
        titleFont = new BitmapFont();

        //Timers
        moveTimer = 0;
        moveTime = 0.25f;

        head = new Head(level.getGridCell());

        resetBody();

    }

    private void resetBody() {
        head.reset();
        head.setPosition(
                (5 * level.getGridCell()),
                (5 * level.getGridCell())
        );


        body = new ArrayList<Tail>();

        //add 2 body
        body.add(new Tail(head.getX(), head.getY()- level.getGridCell()));
        body.add(new Tail(body.get(0).getX(), body.get(0).getY() - level.getGridCell()));
        body.add(new Tail(body.get(1).getX(), body.get(1).getY() - level.getGridCell()));
    }

    @Override
    public void update(float dt) {
        //get user input
        handleInput();


        moveTimer += dt;

        //only moves every time defined by moveTime
        if (moveTimer > moveTime) {
            moveTimer = 0; //reset timer

            checkCollision();

            //update body position
            if (body.size() != 0) {
                //if player has eat a fruit add a body part
                if (head.hasEat())
                    body.add(new Tail(0, 0));
                //sets new position of body parts
                for (int i = body.size() - 1; i >= 0; i--) {
                    if (i == 0)
                        body.get(i).setPosition(head.getX(), head.getY());
                    else
                        body.get(i).setPosition(body.get(i - 1).getX(), body.get(i - 1).getY());
                }
            }
            //update head
            head.update(dt);
            head.wrap();

            if (head.isDead())
                resetBody();



        }
            //create fruit
            if (fruit == null) {
                float x;
                float y;
                boolean contains ;
                do {
                    //TODO the fruit should not spawn in the same position as any object
                    // first get the position random
                    x = MathUtils.random(level.getColumns() - 1) * level.getGridCell();
                    y = MathUtils.random(level.getRows() - 1) * level.getGridCell();

                    // check if space is free
                    contains  = head.contains(x, y);

                    for (Tail bodyPart : body) {
                        contains  = bodyPart.contains(x, y);
                        if(contains)
                            break; //if contains exit for loop
                    }

                } while (contains);

                fruit = new Fruit(x, y);
            } else {
                fruit.update(dt);
                if (fruit.shouldRemove())
                    fruit = null;
            }

    }

    /**
     * Check collision point to point intersection
     */
    private void checkCollision() {
        //head to tail
        for (Tail bodyPart : body) {
            if (bodyPart.contains(head.getX(), head.getY()))
                head.hit();

        }
        //head to fruit
        if (fruit != null) {
            head.eat(fruit.contains(head.getX(), head.getY()), fruit.getScore());
            if (fruit.shouldRemove())
                fruit = null;
        }

    }

    @Override
    public void draw() {
        super.draw();
        sr.setProjectionMatrix(Game.camera.combined);
        sb.setProjectionMatrix(Game.camera.combined);

        
        GlyphLayout glyphLayout = new GlyphLayout();
        titleFont = FontManager.INSTANCE.setFont(40, new Color(0,1,1,1));
        font= FontManager.INSTANCE.setFont(15, new Color(1,1,1,1));

        sb.begin();
        // Set text and font each time you want to calculate bounds.
        glyphLayout.setText(titleFont, "SNAKE");
        titleFont.draw(sb, glyphLayout, (Game.WIDTH - glyphLayout.width) / 2, Game.HEIGHT + 60);

        glyphLayout.setText(font, "Score: " + (int) head.getScore());
        font.draw(sb,glyphLayout, 0, Game.HEIGHT + 15);

        glyphLayout.setText(font, "Level: " + (int) level.getLevel());
        font.draw(sb,glyphLayout, Game.WIDTH-glyphLayout.width, - 5);

        sb.end();

        for (int i= 0; i<head.getLives(); i++){
            new Head(15).setPosition(Game.WIDTH-15-i*20, Game.HEIGHT +5).draw(sr);
        }


        head.draw(sr);

        //draw body
        for (Tail bodyPart : body) {
            bodyPart.draw(sr);
        }
        if (fruit != null)
            fruit.draw(sr);


    }

    @Override
    public void handleInput() {
        //user preferences input keys
        switch (Game.gameStateManager.optionKeys) {
            case SNAKE://snake perspective
                head.setRotateLeft(Gdx.input.isKeyJustPressed(Input.Keys.LEFT));
                head.setRotateRight(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT));
                break;
            case PLAYER: //player perspective
                head.setLeft(Gdx.input.isKeyJustPressed(Input.Keys.LEFT));
                head.setRight(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT));
                head.setUp(Gdx.input.isKeyJustPressed(Input.Keys.UP));
                head.setDown(Gdx.input.isKeyJustPressed(Input.Keys.DOWN));
                break;
        }

    }

    @Override
    public void dispose() {
        sr.dispose();

    }
}
