package pt.bisonte.snake.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import pt.bisonte.snake.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=400;
		config.height=300;
		config.resizable=false;
		config.title="Snake";
		new LwjglApplication(new Game(), config);
	}
}
