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
import pt.bisonte.snake.entities.Apple;
import pt.bisonte.snake.entities.Player;
import pt.bisonte.snake.entities.Tail;
import pt.bisonte.snake.entities.Wall;
import pt.bisonte.snake.level.LevelManager;
import pt.bisonte.snake.managers.FontManager;
import pt.bisonte.snake.managers.GameFileManager;
import pt.bisonte.snake.managers.GameStateManager;
import pt.bisonte.snake.managers.Jukebox;

import java.util.ArrayList;
import java.util.List;


public class PlayState extends GameState {

    private SpriteBatch sb;
    private ShapeRenderer sr;
    private BitmapFont titleFont;
    private BitmapFont font;

    private float tempGameWidth;
    private float tempGameHeight;


    private Player player;
    private List<Tail> body;
    private Apple apple;

    //remaning lives and apples
    private List<Player> extraLives;
    private Apple remaningApples;


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

        player = new Player(LevelManager.getGrid());

        extraLives = new ArrayList<>();
        remaningApples = new Apple(0, -25, false);

        for (int i = 0; i < player.getLives(); i++) {
            Player newLive = new Player(15);
            extraLives.add(newLive);
        }

        resetBody();

    }

    /**
     * Resets the snake position.
     */
    private void resetBody() {
        player.reset();
        player.setPosition(
                (5 * LevelManager.getGrid()),
                (5 * LevelManager.getGrid())
        );

        body = new ArrayList<>();

        //add 2 body
        body.add(new Tail(player.getX(), player.getY() - LevelManager.getGrid(), LevelManager.getGrid()));
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
                //if player has eat a apple add a body part
                if (player.hasEat())
                    body.add(new Tail(0, 0, LevelManager.getGrid()));
                //sets new position of body parts
                for (int i = body.size() - 1; i >= 0; i--) {
                    if (i == 0)
                        body.get(i).setPosition(player.getX(), player.getY());
                    else
                        body.get(i).setPosition(body.get(i - 1).getX(), body.get(i - 1).getY());
                }
            }
            //update player
            player.update(dt);
            player.wrap();

            if (beat1)
                Jukebox.play("beat1");
            else
                Jukebox.play("beat2");
            beat1 = !beat1;


            if (player.isDead())
                if (player.getLives() < 0) {
                    GameFileManager.gameData.setTentativeScore((long) player.getScore());
                    gameStateManager.setState(GameStateManager.State.GAMEOVER);
                } else {
                    resetBody();
                    playTime = !playTime;
                }


        }
        //create apple
        if (apple == null) {
            float x;
            float y;
            boolean containsHead, containsFruit = false, containsWall = false;
            do {
                // first get the position random
                x = MathUtils.random(LevelManager.getColumns() - 1) * LevelManager.getGrid();
                y = MathUtils.random(LevelManager.getRows() - 1) * LevelManager.getGrid();

                // check if space is free
                containsHead = player.contains(x, y);

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

            apple = new Apple(x, y);
        } else {
            apple.update(dt);
            if (apple.shouldRemove())
                apple = null;
        }
    }

    /**
     * Check collision point to point intersection
     */
    private void checkCollision() {
        //player to tail
        for (Tail bodyPart : body) {
            if (bodyPart.contains(player.getX(), player.getY()))
                player.hit();
        }

        //player to walls
        for (Wall pWall : LevelManager.getWalls()) {
            if (pWall.contains(player.getX(), player.getY())) {
                player.hit();
            }
        }

        //player to apple
        if (apple != null) {
            if (player.eat(apple.contains(player.getX(), player.getY()), apple.getScore())) {
                if (player.fruitsAte() >= LevelManager.getFruitToNextLevel()) {
                    // if isn't bonus apple, decreases 10% time to update, increasing speed.
                    LevelManager.getNextLevel();
                    playTime = !playTime; //pause the game
                    resetBody();
                    moveTime = 0.25f; // reset speed
                    Jukebox.play("levelup");
                }
                //if fruits is bonus, then the update time increases 10%, decreasing speed.
                if (apple.isBonus()) {
                    Jukebox.play("bonus");
                    moveTime += moveTime * 0.10f;
                }
                // else, each 5 decrease update time, increasing speed.
                else if (player.fruitsAte() % 5 == 0) {
                    moveTime += moveTime * -0.10f;
                }
            }

            if (apple.shouldRemove() || player.isDead())
                apple = null;
        }

        updateExtraLives();

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


    /**
     * Updates the extra lives to the display
     */
    private void updateExtraLives() {
        if (player.isDead() && extraLives.size() > 0) {
            extraLives.remove(extraLives.size() - 1);
        }

        //updates the position whatever the board size
        int i = 0;
        for (Player extraLive : extraLives) {
            extraLive.setPosition(Game.WIDTH - 15 - i++ * 20, Game.HEIGHT + 5);
        }
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

        glyphLayout.setText(font, "Score: " + (int) player.getScore());
        font.draw(sb, glyphLayout, 0, Game.HEIGHT + 15);

        glyphLayout.setText(font, "Level: " + LevelManager.getLevelID());
        font.draw(sb, glyphLayout, Game.WIDTH - glyphLayout.width, -5);

        sb.end();

        for (Player extraLive : extraLives) {
            extraLive.draw(sr);
        }

        player.draw(sr);

        //draw body
        for (Tail bodyPart : body) {
            bodyPart.draw(sr);
        }
        if (apple != null)
            apple.draw(sr);

        if (!playTime) {
            sb.begin();
            glyphLayout.setText(font, "Hit space to continue ...");
            font.draw(sb, glyphLayout, (Game.WIDTH - glyphLayout.width) / 2, Game.HEIGHT / 2);
            sb.end();
        }

        remaningApples.draw(sr);

        sb.begin();
        glyphLayout.setText(font, "x " + (LevelManager.getFruitToNextLevel() - player.fruitsAte()));
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
        if (isPlayTime()) {
            //user preferences input keys
            switch (GameStateManager.optionKeys) {
                case SNAKE://snake perspective
                    player.setRotateLeft(Gdx.input.isKeyJustPressed(Input.Keys.LEFT));
                    player.setRotateRight(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT));
                    break;
                case PLAYER: //player perspective
                    player.setLeft(Gdx.input.isKeyJustPressed(Input.Keys.LEFT));
                    player.setRight(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT));
                    player.setUp(Gdx.input.isKeyJustPressed(Input.Keys.UP));
                    player.setDown(Gdx.input.isKeyJustPressed(Input.Keys.DOWN));
                    break;
            }
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
