package pt.bisonte.snake.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import pt.bisonte.snake.Game;
import pt.bisonte.snake.entities.Fruit;
import pt.bisonte.snake.entities.Head;
import pt.bisonte.snake.entities.Tail;
import pt.bisonte.snake.entities.Wall;
import pt.bisonte.snake.level.LevelManager;
import pt.bisonte.snake.managers.*;

import java.util.ArrayList;
import java.util.List;


public class PlayState extends GameState {

    private SpriteBatch sb;
    private ShapeRenderer sr;
    private BitmapFont titleFont;
    private BitmapFont font;

    private float tempGameWidth;
    private float tempGameHeight;


    private Head head;
    private List<Tail> body;
    private Fruit fruit;


    private float moveTimer;
    private float moveTime; //move every x second

    private boolean playTime;

    private boolean beat1;


    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
    }

    @Override
    public void init() {
        sr = new ShapeRenderer();
        sb = new SpriteBatch();
        titleFont = new BitmapFont();

        setupLevel();

        //Timers
        moveTimer = 0;
        moveTime = 0.25f;

        head = new Head(LevelManager.getGrid());

        resetBody();

    }

    /**
     * Resets the snake position.
     */
    private void resetBody() {
        head.reset();
        head.setPosition(
                (5 * LevelManager.getGrid()),
                (5 * LevelManager.getGrid())
        );

        body = new ArrayList<>();

        //add 2 body
        body.add(new Tail(head.getX(), head.getY() - LevelManager.getGrid(), LevelManager.getGrid()));
        body.add(new Tail(body.get(0).getX(), body.get(0).getY() - LevelManager.getGrid(), LevelManager.getGrid()));
        body.add(new Tail(body.get(1).getX(), body.get(1).getY() - LevelManager.getGrid(), LevelManager.getGrid()));
    }

    private boolean isPlayTime() {
        return playTime;
    }

    @Override
    public void update(float dt) {
        //get user input
        handleInput();


        moveTimer += dt;

        //if is paused doesn't update the rest
        if (!isPlayTime()) {
            return;
        }

        //only moves every time defined by moveTime
        if (moveTimer > moveTime) {
            moveTimer = 0; //reset timer

            checkCollision();

            //update body position
            if (body.size() != 0) {
                //if player has eat a fruit add a body part
                if (head.hasEat())
                    body.add(new Tail(0, 0, LevelManager.getGrid()));
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

            if(beat1)
                Jukebox.play("beat1");
            else
                Jukebox.play("beat2");
            beat1=!beat1;


            if (head.isDead())
                if (head.getLives() < 0) {
                    GameFileManager.gameData.setTentativeScore((long) head.getScore());
                    gameStateManager.setState(GameStateManager.State.GAMEOVER);
                } else {
                    resetBody();
                    playTime = !playTime;
                }


        }
        //create fruit
        if (fruit == null) {
            float x;
            float y;
            boolean containsHead, containsFruit = false, containsWall = false;
            do {
                //TODO the fruit should not spawn in the same position as any object
                // first get the position random
                x = MathUtils.random(LevelManager.getColumns() - 1) * LevelManager.getGrid();
                y = MathUtils.random(LevelManager.getRows() - 1) * LevelManager.getGrid();

                // check if space is free
                containsHead = head.contains(x, y);

                for (Tail bodyPart : body) {
                    containsFruit = bodyPart.contains(x, y);
                    if (containsFruit)
                        break; //if contains exit for loop
                }

                for (Wall pWall : LevelManager.getWalls()) {
                    containsWall = pWall.contains(x, y);
                    if (containsWall)
                        break;
                }

            } while (containsHead || containsFruit || containsWall);

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

        //head to walls
        for (Wall pWall : LevelManager.getWalls()) {
            if (pWall.contains(head.getX(), head.getY()))
                head.hit();
        }

        //head to fruit
        if (fruit != null) {
            if (head.eat(fruit.contains(head.getX(), head.getY()), fruit.getScore())) {
                if (head.fruitsAte() >= LevelManager.getFruitToNextLevel()) {
                    // if isn't bonus fruit, decreases 10% time to update, increasing speed.
                    LevelManager.getNextLevel();
                    playTime= !playTime; //pause the game
                    resetBody();
                    moveTime = 0.25f; // reset speed
                    Jukebox.play("levelup");
                }
                //if fruits is bonus, then the update time increases 10%, decreasing speed.
                if(fruit.isBonus()) {
                    Jukebox.play("bonus");
                    moveTime += moveTime * 0.10f;
                }
                // else, each 5 decrease update time, increasing speed.
                else if (head.fruitsAte()%5==0){
                    moveTime += moveTime * -0.10f;
                }
            }

            if (fruit.shouldRemove())
                fruit = null;
        }

    }

    /**
     * Format game board. Sets main class game WIDTH and HEIGHT,
     * according columns and rows times gridCell dimension.
     */
    private void setupLevel() {
        tempGameWidth = Game.WIDTH;
        tempGameHeight = Game.HEIGHT;

        LevelManager.getNextLevel();
    }

    @Override
    public void draw() {
        drawGrid();
        sr.setProjectionMatrix(Game.camera.combined);
        sb.setProjectionMatrix(Game.camera.combined);

        for (Wall pWall : LevelManager.getWalls()) {
            pWall.draw(sr);
        }

        GlyphLayout glyphLayout = new GlyphLayout();
        titleFont = FontManager.INSTANCE.setFont(40, new Color(0, 1, 1, 1));
        font = FontManager.INSTANCE.setFont(15);

        sb.begin();
        // Set text and font each time you want to calculate bounds.
        glyphLayout.setText(titleFont, MenuState.title);
        titleFont.draw(sb, glyphLayout, (Game.WIDTH - glyphLayout.width) / 2, Game.HEIGHT + 60);

        glyphLayout.setText(font, "Score: " + (int) head.getScore());
        font.draw(sb, glyphLayout, 0, Game.HEIGHT + 15);

        glyphLayout.setText(font, "Level: " + LevelManager.getLevelID());
        font.draw(sb, glyphLayout, Game.WIDTH - glyphLayout.width, -5);

        sb.end();

        for (int i = 0; i < head.getLives(); i++) {
            new Head(15).setPosition(Game.WIDTH - 15 - i * 20, Game.HEIGHT + 5).draw(sr);
        }


        head.draw(sr);

        //draw body
        for (Tail bodyPart : body) {
            bodyPart.draw(sr);
        }
        if (fruit != null)
            fruit.draw(sr);

        if (!playTime) {
            sb.begin();
            glyphLayout.setText(font, "Hit space to continue ...");
            font.draw(sb, glyphLayout, (Game.WIDTH - glyphLayout.width) / 2, Game.HEIGHT / 2);
            sb.end();
        }

        new Fruit(0, -25).draw(sr);
        sb.begin();
        glyphLayout.setText(font, "x " + (LevelManager.getFruitToNextLevel() - head.fruitsAte()));
        font.draw(sb, glyphLayout, 22, -12);
        sb.end();


    }

    /**
     * Draws a grid of the board.
     */
    private void drawGrid() {
        sr.setProjectionMatrix(Game.camera.combined);

        sr.setColor(0, 0, 0.35f, 1);
        sr.begin(ShapeRenderer.ShapeType.Line);
        for (int i = 0; i <= Game.WIDTH; i += LevelManager.getGrid()) {
            sr.line(i, 0, i, Game.HEIGHT);
        }
        for (int i = 0; i <= Game.HEIGHT; i += LevelManager.getGrid()) {
            sr.line(0, i, Game.WIDTH, i);
        }
        sr.end();
    }

    @Override
    public void handleInput() {
        //user preferences input keys
        switch (GameStateManager.optionKeys) {
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

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
            playTime = !playTime;

    }

    @Override
    public void dispose() {
        Game.WIDTH = tempGameWidth;
        Game.HEIGHT = tempGameHeight;
        Game.setCameraPosition();

        sr.dispose();
        sb.dispose();
        font.dispose();
        titleFont.dispose();

    }
}
