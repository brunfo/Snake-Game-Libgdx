package pt.bisonte.snake.level;

public abstract class Level {
    protected int rows;
    protected int columns;
    protected int gridCell;

    protected int level;



    protected Level(){
        init();
    }

    public abstract void init();

    public int getRows(){ return rows;}

    public int getColumns(){return columns;}

    public int  getGridCell(){return gridCell;}

    public int getLevel(){ return level;}

}
