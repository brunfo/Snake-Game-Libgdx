package pt.bisonte.snake.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.bisonte.snake.Game;
import pt.bisonte.snake.entities.PlayerScore;
import pt.bisonte.snake.managers.FontManager;
import pt.bisonte.snake.managers.GameFileManager;
import pt.bisonte.snake.managers.GameStateManager;

public class HighScoreState extends GameState {

    private SpriteBatch spriteBatch;
    private PlayerScore[] highScores;
    private BitmapFont font;

    public HighScoreState(GameStateManager gameStateManager) {
        super(gameStateManager);
    }

    @Override
    public void init() {
        spriteBatch = new SpriteBatch();
        font = FontManager.INSTANCE.setFont(20);

        GameFileManager.load();
        highScores = GameFileManager.gameData.getHighScores();
    }

    @Override
    public void update(float dt) {

        handleInput();
    }

    @Override
    public void draw() {
        spriteBatch.setProjectionMatrix(Game.camera.combined);

        spriteBatch.begin();
        GlyphLayout glyphLayout = new GlyphLayout();

        // Set text and font each time you want to calculate bounds.
        glyphLayout.setText(font, "High Scores");
        font.draw(spriteBatch, glyphLayout, (Game.WIDTH - glyphLayout.width) / 2, Game.HEIGHT - 30);

        float row = Game.HEIGHT - 55;

        for (int i = 0; i < highScores.length; i++) {
            row -= 30;
            String s = String.format("%2d. %5s %s", i + 1, highScores[i].getScore(), highScores[i].getName());
            glyphLayout.setText(font, s);
            font.draw(spriteBatch, glyphLayout, (Game.WIDTH - glyphLayout.width) / 2, row);
        }

        spriteBatch.end();

    }

    @Override
    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Keys.ENTER) || Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
            gameStateManager.setState(GameStateManager.State.MENU);

        }
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        font.dispose();

    }

}
