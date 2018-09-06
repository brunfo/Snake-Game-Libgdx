package pt.bisonte.snake;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.bisonte.snake.managers.GameStateManager;

public class Game extends ApplicationAdapter {

	public static int WIDTH;
	public static int HEIGHT;
	public static int CELL_WIDTH;

	public static OrthographicCamera camera;

	public GameStateManager gameStateManager;


	@Override
	public void create () {
	WIDTH = Gdx.graphics.getWidth();
	HEIGHT = Gdx.graphics.getHeight();
	CELL_WIDTH=20;

	camera = new OrthographicCamera(WIDTH, HEIGHT);
	camera.translate(WIDTH /2, HEIGHT/2);
	camera.update();

	gameStateManager= new GameStateManager();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.0f, 0.45f, 0.0f, 0.5f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		gameStateManager.update(Gdx.graphics.getDeltaTime());
		gameStateManager.draw();

	}
	
	@Override
	public void dispose () {
	}
}
