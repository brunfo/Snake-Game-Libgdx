package pt.bisonte.snake.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public enum FontManager {

    INSTANCE;
    // não há necessidade de construtor privado
    // não há necessidade de getInstance, é só usar FontManager.INSTANCE

    public BitmapFont setFont(int size, Color color) {
	// set font
	FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(
		Gdx.files.internal("fonts/Hyperspace Bold.ttf"));

	FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
	// set parameters for the font
	fontParameter.size = size;
	//fontParameter.shadowOffsetX = 2;
	//fontParameter.shadowOffsetY = 2;
	//fontParameter.shadowColor = color;
	BitmapFont font = fontGenerator.generateFont(fontParameter);
	fontGenerator.dispose();
	return font;
    }

}
