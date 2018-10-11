package pt.bisonte.snake.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import pt.bisonte.snake.Game;
import pt.bisonte.snake.managers.Font;
import pt.bisonte.snake.managers.GameFile;
import pt.bisonte.snake.managers.GameStateManager;

public class GameOverState extends GameState {

    private SpriteBatch batch;
    private ShapeRenderer renderer;

    private BitmapFont gameOverFont;
    private BitmapFont font;

    private boolean newHighScore;
    // Arcade style to set name
    private char[] newName;
    private int currentChar;

    public GameOverState(GameStateManager gameStateManager) {
        super(gameStateManager);

    }

    @Override
    public void init() {
        batch = gameStateManager.batch;
        renderer = gameStateManager.renderer;

        newHighScore = GameFile.MANAGER.gameData.isHighScore(GameFile.MANAGER.gameData.getTentativeScore());

        if (newHighScore) {
            newName = new char[]{'A', 'A', 'A'};
            currentChar = 0;
        }
        gameOverFont = Font.MANAGER.set(32);
        font = Font.MANAGER.set(20);

    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void draw() {
        batch.setProjectionMatrix(Game.camera.combined);

        Font.MANAGER.centered(batch, gameOverFont, "Game Over", Game.WIDTH / 2, 300);

        if (!newHighScore) {
            return;
        }

        String str = "New High Score: " + GameFile.MANAGER.gameData.getTentativeScore();
        Font.MANAGER.centered(batch, font, str, Game.WIDTH / 2, 200);

        GlyphLayout layout = new GlyphLayout();
        layout.setText(font, "AAA");

        batch.begin();
        for (int i = 0; i < newName.length; i++) {
            font.draw(batch, Character.toString(newName[i]),
                    (Game.WIDTH - layout.width) / 2 + 14 * i,
                    150);
        }
        batch.end();

        renderer.begin(ShapeType.Line);
        renderer.line(
                Game.WIDTH / 2 - layout.width / 2 + 14 * currentChar,
                125,
                Game.WIDTH / 2 - layout.width / 2 + 10 + 14 * currentChar,
                125);
        renderer.end();

    }

    @Override
    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
            if (newHighScore) {
                GameFile.MANAGER.gameData.addHighScore(GameFile.MANAGER.gameData.getTentativeScore(),
                        new String(newName));
                GameFile.MANAGER.save();
            }
            gameStateManager.setState(GameStateManager.State.MENU);
        }

        // prevents accessing keys if isn't new highscore
        if (newHighScore) {

            if (Gdx.input.isKeyJustPressed(Keys.UP)) {
                if (newName[currentChar] == ' ') {
                    newName[currentChar] = 'Z';
                } else {
                    newName[currentChar]--;
                    if (newName[currentChar] < 'A')
                        newName[currentChar] = ' ';
                }
            }

            if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
                if (newName[currentChar] == ' ') {
                    newName[currentChar] = 'A';
                } else {
                    newName[currentChar]++;
                    if (newName[currentChar] > 'Z')
                        newName[currentChar] = ' ';
                }
            }

            if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
                if (currentChar < newName.length - 1)
                    currentChar++;
            }

            if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
                if (currentChar > 0)
                    currentChar--;
            }
        }

    }

    @Override
    public void dispose() {
        //dispose of objects is manipulated by the Game class
    }

}
