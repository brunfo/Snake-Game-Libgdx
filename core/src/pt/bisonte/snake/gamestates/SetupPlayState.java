package pt.bisonte.snake.gamestates;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import pt.bisonte.snake.Game;
import pt.bisonte.snake.level.Level;
import pt.bisonte.snake.level.Level1;
import pt.bisonte.snake.managers.GameStateManager;


/**
 * This class only deals width screen/board setup
 */
public abstract class SetupPlayState extends GameState {
    protected ShapeRenderer sr;
    protected Level level;

    private float tempGameWidth;
    private float tempGameHeight;


    public SetupPlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
    }

    @Override
    public void init() {
        sr = new ShapeRenderer();

        setupLevel();
    }

    /**
     * Format game board. Sets main class game WIDTH and HEIGHT,
     * according columns and rows times gridCell dimension
     */
    private void setupLevel(){
        level= new Level1();

        tempGameWidth = Game.WIDTH;
        tempGameHeight= Game.HEIGHT;

        Game.WIDTH = level.getColumns()*level.getGridCell();
        Game.HEIGHT = level.getRows()*level.getGridCell();

        Game.camera.translate(-(tempGameWidth-Game.WIDTH)/2  , -(tempGameHeight-Game.HEIGHT)/2);
        Game.camera.update();
    }

    @Override
    public abstract void update(float dt);

    @Override
    public void draw() {
        sr.setProjectionMatrix(Game.camera.combined);

        sr.setColor(0,0,0.25f,0);
        sr.begin(ShapeRenderer.ShapeType.Line);
        for(int i = 0; i<=Game.WIDTH; i+= level.getGridCell()){
            sr.line(i,0, i, Game.HEIGHT);
        }
        for(int i = 0; i<=Game.HEIGHT; i+= level.getGridCell()){
            sr.line(0, i, Game.WIDTH, i);
        }
        sr.end();
    }

    @Override
    public abstract void handleInput();

    @Override
    public void dispose() {
        sr.dispose();

        //revert formatted
        Game.camera.translate(+(tempGameWidth-Game.WIDTH)/2  , +(tempGameHeight-Game.HEIGHT)/2);
        Game.camera.update();
        Game.WIDTH =tempGameWidth ;
        Game.HEIGHT = tempGameHeight;
    }
}
