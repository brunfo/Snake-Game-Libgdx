package pt.bisonte.snake.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.bisonte.snake.Game;
import pt.bisonte.snake.entities.PlayerScore;
import pt.bisonte.snake.managers.*;

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
        font = Font.MANAGER.set(20);

        GameFile.MANAGER.load();
        highScores = GameFile.MANAGER.gameData.getHighScores();
    }

    @Override
    public void update(float dt) {

        handleInput();
    }

    @Override
    public void draw() {
        spriteBatch.setProjectionMatrix(Game.camera.combined);

        Font.MANAGER.centered(spriteBatch, font, "High Scores", Game.WIDTH / 2, Game.HEIGHT - 30);

        float row = Game.HEIGHT - 55;

        for (int i = 0; i < highScores.length; i++) {
            row -= 30;
            String s = String.format("%2d. %5s %s", i + 1, highScores[i].getScore(), highScores[i].getName());
            Font.MANAGER.centered(spriteBatch, font, s, Game.WIDTH / 2, row);
        }
    }

    @Override
    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Keys.ENTER) || Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
            Jukebox.MANAGER.play("accept");
            gameStateManager.setState(GameStateManager.State.MENU);
        }
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        font.dispose();

    }

}
