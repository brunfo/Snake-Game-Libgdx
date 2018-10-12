package pt.bisonte.snake.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import pt.bisonte.snake.Game;
import pt.bisonte.snake.managers.Font;
import pt.bisonte.snake.managers.GameFile;
import pt.bisonte.snake.managers.GameStateManager;
import pt.bisonte.snake.managers.Jukebox;

public class MenuState extends GameState {

    private SpriteBatch batch;
    private ShapeRenderer renderer;
    private BitmapFont titleFont;
    private BitmapFont font;

    static final String title = "SNAKE Game";

    private int currentItem;
    private String[] menuItems;


    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
    }


    @Override
    public void init() {
        batch = gameStateManager.batch;
        renderer = gameStateManager.renderer;

        // set font
        titleFont = Font.MANAGER.set(36);
        titleFont.setColor(Color.WHITE);

        font = Font.MANAGER.set(20);

        menuItems = new String[]{"Play", "Highscores", "Options", "Quit"};

        GameFile.MANAGER.load();

    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void draw() {
        batch.setProjectionMatrix(Game.camera.combined);
        renderer.setProjectionMatrix(Game.camera.combined);

        Font.MANAGER.centered(batch, titleFont, title, Game.WIDTH / 2, Game.HEIGHT - 50);

        float row = Game.HEIGHT - 150;

        for (int i = 0; i < menuItems.length; i++) {
            row -= 30;

            font.setColor(currentItem == i ? Color.RED : Color.WHITE);

            Font.MANAGER.centered(batch, font, menuItems[i], Game.WIDTH / 2, row);
        }

    }

    @Override
    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && currentItem > 0) {
            currentItem--;
            Jukebox.MANAGER.play("select");
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && currentItem < menuItems.length - 1) {
            currentItem++;
            Jukebox.MANAGER.play("select");
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            select();
            Jukebox.MANAGER.play("accept");
        }
    }

    @Override
    public void dispose() {
        //dispose of objects is manipulated by the Game class
    }

    /**
     * Selection options of the game menu.
     */
    private void select() {
        // play
        switch (currentItem) {
            case 0:
                GameStateManager.startNewGame();
                gameStateManager.setState(GameStateManager.State.PLAY);
                break;
            case 1:
                gameStateManager.setState(GameStateManager.State.HIGHSCORES);
                break;
            case 2:
                gameStateManager.setState(GameStateManager.State.OPTIONS);
                break;
            case 3:
                Gdx.app.exit();
                break;
        }

    }
}
