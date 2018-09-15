package pt.bisonte.snake.level;


import pt.bisonte.snake.Game;
import pt.bisonte.snake.entities.Wall;

import java.util.ArrayList;

public class Level1 extends Level {


    public Level1() {
        super();
    }

    /**
     * Sets initial parameter of the level
     */
    @Override
    public void init() {
        this.columns = 20;
        this.rows = 20;
        this.gridCell = 15;
        level = 1;
        fruitToNextLevel = 15;

        //setup board
        Game.WIDTH = getColumns() * getGridCell();
        Game.HEIGHT = getRows() * getGridCell();

        Game.camera.position.set(Game.WIDTH / 2, Game.HEIGHT / 2, 0);
        Game.camera.update();

        setupBoard();

    }

    /**
     * Setup the board. Creates the walls.
     */
    private void setupBoard() {
        walls = new ArrayList<Wall>();
        for (int i = getGridCell(); i < Game.WIDTH - getGridCell(); i += getGridCell()) {
            walls.add(new Wall(0, i, getGridCell()));
            walls.add(new Wall(Game.WIDTH - getGridCell(), i, getGridCell()));
        }

        for (int i = 0; i < Game.HEIGHT; i += getGridCell()) {
            walls.add(new Wall(i, 0, getGridCell()));
            walls.add(new Wall(i, Game.HEIGHT - getGridCell(), getGridCell()));
        }
    }

}
