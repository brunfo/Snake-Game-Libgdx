package pt.bisonte.snake.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public enum FontManager {

    INSTANCE;
    // no need to use private constructor
    // no need to use getInstance, just use FontManager.INSTANCE

    /**
     * Formats a font.
     * @param size - size of the font.
     * @return formatted bitmap font.
     */
    public BitmapFont setFont(int size) {

        return setFont(size, new Color(1, 1, 1, 1));
    }

    /**
     * Formats a font.
     * @param size - size of the font.
     * @param color - color of the font.
     * @return formatted bitmap font.
     */
    public BitmapFont setFont(int size, Color color) {
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

}
