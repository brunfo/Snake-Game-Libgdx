package pt.bisonte.snake.level;


public class Level1 extends Level {

    public Level1() {
        super();
    }

    /**
     * Sets initial parameter of the level
     */
    @Override
    public void init() {
        this.columns=15;
        this.rows= 15;
        this.gridCell=15;
    }


}
