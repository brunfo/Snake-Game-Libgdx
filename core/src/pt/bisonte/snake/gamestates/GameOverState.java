package pt.bisonte.snake.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import pt.bisonte.snake.Game;
import pt.bisonte.snake.managers.FontManager;
import pt.bisonte.snake.managers.GameFileManager;
import pt.bisonte.snake.managers.GameStateManager;

public class GameOverState extends GameState {

    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;

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
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        newHighScore = GameFileManager.gameData.isHighScore(GameFileManager.gameData.getTentativeScore());

        if (newHighScore) {
            newName = new char[]{'A', 'A', 'A'};
            currentChar = 0;
        }
        gameOverFont = FontManager.setFont(32);
        font = FontManager.setFont(20);

    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void draw() {
        spriteBatch.setProjectionMatrix(Game.camera.combined);

        FontManager.centered(spriteBatch, gameOverFont, "Game Over", Game.WIDTH / 2, 300);

        if (!newHighScore) {
            spriteBatch.end();
            return;
        }

        String str = "New High Score: " + GameFileManager.gameData.getTentativeScore();
        FontManager.centered(spriteBatch, font, str, Game.WIDTH / 2, 200);

        GlyphLayout layout = new GlyphLayout();
        layout.setText(font, "AAA");

        spriteBatch.begin();
        for (int i = 0; i < newName.length; i++) {
            font.draw(spriteBatch, Character.toString(newName[i]),
                    (Game.WIDTH - layout.width) / 2 + 14 * i,
                    150);
        }
        spriteBatch.end();

        shapeRenderer.begin(ShapeType.Line);
        shapeRenderer.line(
                Game.WIDTH / 2 - layout.width / 2 + 14 * currentChar,
                125,
                Game.WIDTH / 2 - layout.width / 2 + 10 + 14 * currentChar,
                125);
        shapeRenderer.end();

    }

    @Override
    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
            if (newHighScore) {
                GameFileManager.gameData.addHighScore(GameFileManager.gameData.getTentativeScore(),
                        new String(newName));
                GameFileManager.save();
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
        spriteBatch.dispose();
        shapeRenderer.dispose();
        gameOverFont.dispose();
        font.dispose();

    }

}
