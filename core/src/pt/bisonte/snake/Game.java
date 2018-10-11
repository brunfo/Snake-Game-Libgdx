package pt.bisonte.snake;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import pt.bisonte.snake.managers.GameStateManager;
import pt.bisonte.snake.managers.Jukebox;

public class Game extends ApplicationAdapter {

    public static float WIDTH;
    public static float HEIGHT;

    public static OrthographicCamera camera;
    private static Vector3 cameraPosition; //Vector to save the original position of the camera

    private static GameStateManager gameStateManager;


    @Override
    public void create() {
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();

        camera = new OrthographicCamera(WIDTH, HEIGHT);
        camera.translate(WIDTH / 2, HEIGHT / 2);
        cameraPosition = camera.position.cpy();
        camera.update();

        loadSounds();
        gameStateManager = new GameStateManager();
    }

    /**
     * Sets the original position of the camera.
     */
    public static void setCameraPosition() {
        camera.position.set(cameraPosition);
        camera.update();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.1f, 0.5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameStateManager.update(Gdx.graphics.getDeltaTime());
        gameStateManager.draw();
    }

    @Override
    public void dispose() {
    }

    private void loadSounds(){
        Jukebox.MANAGER.load("sounds/itemback.wav", "select");
        Jukebox.MANAGER.load("sounds/itempick.wav", "accept");
        Jukebox.MANAGER.load("sounds/level-up.wav", "levelup");
        Jukebox.MANAGER.load("sounds/tick.wav", "bonus");
        Jukebox.MANAGER.load("sounds/Slide01.wav","slide1");
        Jukebox.MANAGER.load("sounds/Slide02.wav", "slide2");
        Jukebox.MANAGER.load("sounds/hiss2.wav", "hiss");

    }
}
