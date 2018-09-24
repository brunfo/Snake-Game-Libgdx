package pt.bisonte.snake.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import pt.bisonte.snake.Game;
import pt.bisonte.snake.level.LevelData;
import pt.bisonte.snake.level.LevelManager;
import pt.bisonte.snake.managers.FontManager;
import pt.bisonte.snake.managers.GameStateManager;
import pt.bisonte.snake.managers.Jukebox;

import java.util.ArrayList;

public class EditLevelState extends GameState {

    private LevelData level;


    private int currentLevel;
    private boolean isLoad;

    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;
    private BitmapFont titleFont;
    private BitmapFont subTitleFont;
    private BitmapFont font;

    /**
     * items of actions to do.
     */
    private String[] items = new String[]{
            "New",
            "Load",
            "Save",
            "Restart",
            "Back"};

    private int currentItem;

    public EditLevelState(GameStateManager gameStateManager) {
        super(gameStateManager);
        load();
    }

    /**
     * Loads from a file a level
     */
    private static void load() {
        LevelManager.listAll();
    }


    /**
     * Creates a new level board
     */
    private void newLevel() {

    }

    /**
     * Saves a to a file a level
     */
    private void saveAndReplace() {

    }

    /**
     * Checks if file exists width level details
     */
    private void exists() {

    }

    /**
     * Clear details
     */
    private void clear() {

    }



    /**
     * Selection items of the menu.
     */
    private void select() {
        if(!isLoad) {
            switch (currentItem) {
                case 0:
                    newLevel();
                    break;
                case 1:
                    isLoad = !isLoad;
                    load();
                    break;
                case 2:
                    saveAndReplace();
                    break;
                case 3:
                    clear();
                    break;
                case 4:
                    gameStateManager.setState(GameStateManager.State.MENU);
                    break;
            }
        }
        else{
            LevelManager.load(LevelManager.getLevel(currentLevel));
        }

    }

    @Override
    public void init() {
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        // set font
        titleFont = FontManager.INSTANCE.setFont(36);
        titleFont.setColor(Color.WHITE);

        subTitleFont = FontManager.INSTANCE.setFont(26);
        subTitleFont.setColor(Color.FIREBRICK);

        font = FontManager.INSTANCE.setFont(20);


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
        glyphLayout.setText(titleFont, MenuState.title);
        titleFont.draw(spriteBatch, glyphLayout, (Game.WIDTH - glyphLayout.width) / 2, Game.HEIGHT - 50);

        glyphLayout.setText(subTitleFont, "Level Editor");
        subTitleFont.draw(spriteBatch, glyphLayout, (Game.WIDTH - glyphLayout.width) / 2, Game.HEIGHT - 100);

        float row = Game.HEIGHT - 200;


        //draw options
        for (int i = 0; i < items.length; i++) {
            if (currentItem == i)
                if(isLoad)
                    font.setColor(Color.GREEN);
                else
                    font.setColor(Color.RED);
            else
                font.setColor(Color.WHITE);
            glyphLayout.setText(font, items[i]);
            font.draw(spriteBatch, glyphLayout, 50, row);
            row -= 30;
        }

        //only draw list of levels if is in load option
        row = Game.HEIGHT - 200;
        if (isLoad) {
            for (String level : LevelManager.getLevels()) {
                if (currentLevel == LevelManager.getLevels().indexOf(level) )
                    font.setColor(Color.RED);
                else
                    font.setColor(Color.WHITE);
                glyphLayout.setText(font, level);
                font.draw(spriteBatch, glyphLayout, 200, row);
                row -= 30;
            }
        }
        spriteBatch.end();
    }


    @Override
    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            if (currentItem > 0 && !isLoad) {
                currentItem--;
            }
            if (currentLevel > 0 && isLoad) {
                currentLevel--;
            }
            Jukebox.play("select");
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            if(currentItem < items.length - 1 && !isLoad) {
                currentItem++;
            }
            if(currentLevel < LevelManager.getLevels().size() - 1 && isLoad) {
                currentLevel++;
            }
            Jukebox.play("select");
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            select();
            Jukebox.play("accept");
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            if (isLoad)
                isLoad = !isLoad;
            else {
                currentItem = 4;
                select();
            }
            Jukebox.play("accept");
        }
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        shapeRenderer.dispose();
        titleFont.dispose();
        subTitleFont.dispose();
        font.dispose();
    }
}
