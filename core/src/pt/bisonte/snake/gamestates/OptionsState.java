package pt.bisonte.snake.gamestates;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import pt.bisonte.snake.Game;
import pt.bisonte.snake.managers.Font;
import pt.bisonte.snake.managers.GameStateManager;
import pt.bisonte.snake.managers.Jukebox;

public class OptionsState extends GameState {


    private SpriteBatch batch;
    private ShapeRenderer renderer;
    private BitmapFont titleFont;
    private BitmapFont font;
    private BitmapFont info;

    private int currentItem;
    private String[] menuItems;

    public OptionsState(GameStateManager gameStateManager) {
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
        info = Font.MANAGER.set(15);

        String[] keysMenuItems = new String[]{"Player", "Snake"};
        String[] playMenuItems = new String[]{"Level UP", "Infinite Tail"};

        if (GameStateManager.isNewGame())
            menuItems = playMenuItems;
        else
            menuItems = keysMenuItems;

    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void draw() {
        batch.setProjectionMatrix(Game.camera.combined);
        renderer.setProjectionMatrix(Game.camera.combined);

        Font.MANAGER.centered(batch, titleFont, MenuState.title, Game.WIDTH / 2, Game.HEIGHT - 50);

        float row = Game.HEIGHT - 150;
        String str = "Chose your preferred play input keys";

        if (!GameStateManager.isNewGame()) {
            Font.MANAGER.centered(batch, info, str, Game.WIDTH / 2, row);
            row -= 50;
        }

        for (int i = 0; i < menuItems.length; i++) {
            row -= 30;

            if (currentItem == i)
                font.setColor(Color.RED);
            else
                font.setColor(Color.WHITE);
            Font.MANAGER.centered(batch, font, menuItems[i], Game.WIDTH / 2, row);
        }

        str = currentItem == 0 ? "Left, Right, Up and Down keys" : "Left or Right keys to rotate imputed direction";

        row -= 50;

        if (!GameStateManager.isNewGame()) {
            Font.MANAGER.centered(batch, info, str, Game.WIDTH / 2, row);
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

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            //revert newGame
            GameStateManager.startNewGame();

            Jukebox.MANAGER.play("accept");
            gameStateManager.setState(GameStateManager.State.MENU);
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
        if (GameStateManager.isNewGame()) {
            switch (currentItem) {
                case 0:
                    GameStateManager.setPlayMode(GameStateManager.PlayMode.LEVEL_UP);
                    break;
                case 1:
                    GameStateManager.setPlayMode(GameStateManager.PlayMode.INFINITE_TAIL);
                    break;
            }
            Jukebox.MANAGER.play("accept");
            GameStateManager.startNewGame();
            gameStateManager.setState(GameStateManager.State.PLAY);
        } else {
            switch (currentItem) {
                case 0:
                    gameStateManager.setOptionsKeys(GameStateManager.OptionKeys.PLAYER);
                    break;
                case 1:
                    gameStateManager.setOptionsKeys(GameStateManager.OptionKeys.SNAKE);
                    break;
            }
            Jukebox.MANAGER.play("accept");
            gameStateManager.setState(GameStateManager.State.MENU);
        }

    }
}
