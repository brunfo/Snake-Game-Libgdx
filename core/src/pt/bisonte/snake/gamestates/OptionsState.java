package pt.bisonte.snake.gamestates;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import pt.bisonte.snake.Game;
import pt.bisonte.snake.managers.FontManager;
import pt.bisonte.snake.managers.GameStateManager;
import pt.bisonte.snake.managers.Jukebox;

public class OptionsState extends GameState {


    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;
    private BitmapFont titleFont;
    private BitmapFont font;

    private int currentItem;
    private String[] menuItems;

    public OptionsState(GameStateManager gameStateManager) {
        super(gameStateManager);
    }


    @Override
    public void init() {
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        // set font
        titleFont = FontManager.setFont(36);
        titleFont.setColor(Color.WHITE);

        font = FontManager.setFont(20);

        menuItems = new String[]{"Player", "Snake"};

    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void draw() {
        spriteBatch.setProjectionMatrix(Game.camera.combined);
        shapeRenderer.setProjectionMatrix(Game.camera.combined);

        FontManager.centered(spriteBatch, titleFont, MenuState.title, Game.WIDTH / 2, Game.HEIGHT - 50);

        float row = Game.HEIGHT - 150;

        for (int i = 0; i < menuItems.length; i++) {
            row -= 30;

            if (currentItem == i)
                font.setColor(Color.RED);
            else
                font.setColor(Color.WHITE);
            FontManager.centered(spriteBatch, font, menuItems[i], Game.WIDTH / 2, row);
        }

    }

    @Override
    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && currentItem > 0) {
            currentItem--;
            Jukebox.play("select");
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && currentItem < menuItems.length - 1) {
            currentItem++;
            Jukebox.play("select");
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            select();
            Jukebox.play("accept");
        }
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        shapeRenderer.dispose();
        titleFont.dispose();
        font.dispose();

    }

    /**
     * Selection options of the game menu.
     */
    private void select() {
        // play
        switch (currentItem) {
            case 0:
                gameStateManager.setOptionsKeys(GameStateManager.OptionKeys.PLAYER);
                break;
            case 1:
                gameStateManager.setOptionsKeys(GameStateManager.OptionKeys.SNAKE);
                break;
        }
        Jukebox.play("accept");
        gameStateManager.setState(GameStateManager.State.MENU);

    }
}
