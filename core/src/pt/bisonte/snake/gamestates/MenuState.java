package pt.bisonte.snake.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import pt.bisonte.snake.Game;
import pt.bisonte.snake.managers.FontManager;
import pt.bisonte.snake.managers.GameFileManager;
import pt.bisonte.snake.managers.GameStateManager;
import pt.bisonte.snake.managers.Jukebox;

public class MenuState extends GameState {

    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;
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
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        // set font
        titleFont = FontManager.INSTANCE.setFont(36);
        titleFont.setColor(Color.WHITE);

        font = FontManager.INSTANCE.setFont(20);

        menuItems = new String[]{"Play", "Highscores", "Level Editor", "Quit"};

        GameFileManager.load();

    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void draw() {
        spriteBatch.setProjectionMatrix(Game.camera.combined);
        shapeRenderer.setProjectionMatrix(Game.camera.combined);


        spriteBatch.begin();
        GlyphLayout glyphLayout = new GlyphLayout();

        // Set text and font each time you want to calculate bounds.
        glyphLayout.setText(titleFont, title);
        titleFont.draw(spriteBatch, glyphLayout, (Game.WIDTH - glyphLayout.width) / 2, Game.HEIGHT - 50);

        float row = Game.HEIGHT - 150;

        for (int i = 0; i < menuItems.length; i++) {
            row -= 30;

            if (currentItem == i)
                font.setColor(Color.RED);
            else
                font.setColor(Color.WHITE);
            glyphLayout.setText(font, menuItems[i]);
            font.draw(spriteBatch, glyphLayout, (Game.WIDTH - glyphLayout.width) / 2, row);
        }

        spriteBatch.end();

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

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            currentItem=3;
            select();
        }
    }

    @Override
    public void dispose() {

    }

    /**
     * Selection options of the game menu.
     */
    private void select() {
        // play
        switch (currentItem) {
            case 0:
                gameStateManager.setState(GameStateManager.State.PLAY);
                break;
            case 1:
                gameStateManager.setState(GameStateManager.State.HIGHSCORES);
                break;
            case 2:
                gameStateManager.setState(GameStateManager.State.EDITOR);
                break;
            case 3:
                Gdx.app.exit();
                break;
        }

    }
}
