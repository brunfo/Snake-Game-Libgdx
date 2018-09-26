package pt.bisonte.snake.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * Starting with version 1.5 of Java, the best approach used for defining Singleton types
 * is with the use of Enum types, as mentioned by Joshua Bloch in his book
 * "Effective Java Second Revised Edition".
 * <p>
 * No need to use private constructor.
 * No need to use getInstance, just use FontManager.INSTANCE
 */
public enum FontManager {

    INSTANCE;


    /**
     * Formats a font.
     *
     * @param size - size of the font.
     * @return formatted bitmap font.
     */
    public static BitmapFont setFont(int size) {

        return setFont(size, new Color(1, 1, 1, 1));
    }

    /**
     * Formats a font.
     *
     * @param size  - size of the font.
     * @param color - color of the font.
     * @return formatted bitmap font.
     */
    public static BitmapFont setFont(int size, Color color) {
        // set font
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(
                Gdx.files.internal("fonts/Hyperspace Bold.ttf"));

        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        // set parameters for the font
        fontParameter.size = size;
        fontParameter.color = color;
        BitmapFont font = fontGenerator.generateFont(fontParameter);
        fontGenerator.dispose();
        return font;
    }

    /**
     * Displays left-justify a message in assigned coordinates.
     *
     * @param sb      SpriteBatch
     * @param font    Font configuration.
     * @param message Message to display
     * @param width   Assigned horizontal
     * @param height  Assigned vertical
     */
    public static void left(SpriteBatch sb, BitmapFont font, String message, float width, float height) {
        GlyphLayout layout = new GlyphLayout();
        sb.begin();
        layout.setText(font, message);
        font.draw(sb, layout, width, height);
        sb.end();
    }

    /**
     * Displays right-justify a message in assigned coordinates.
     *
     * @param sb      SpriteBatch
     * @param font    Font configuration.
     * @param message Message to display
     * @param width   Assigned horizontal
     * @param height  Assigned vertical
     */
    public static void right(SpriteBatch sb, BitmapFont font, String message, float width, float height) {
        GlyphLayout layout = new GlyphLayout();
        sb.begin();
        layout.setText(font, message);
        font.draw(sb, layout, width - layout.width, height);
        sb.end();
    }

    /**
     * Displays centered a message in assigned coordinates.
     *
     * @param sb      SpriteBatch
     * @param font    Font configuration.
     * @param message Message to display
     * @param width   Assigned horizontal
     * @param height  Assigned vertical
     */
    public static void centered(SpriteBatch sb, BitmapFont font, String message, float width, float height) {
        GlyphLayout layout = new GlyphLayout();
        sb.begin();
        layout.setText(font, message);
        font.draw(sb, layout, width - layout.width / 2, height);
        sb.end();
    }

}
