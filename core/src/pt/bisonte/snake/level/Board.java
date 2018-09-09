package pt.bisonte.snake.level;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import pt.bisonte.snake.Game;

public class Board {
    private float x;
    private float y;

    private int columns;
    private int rows;

    private float startX;
    private float startY;

    public Board(int columns, int rows){
        this.columns =columns;
        this.rows =rows;
        x=(Game.WIDTH-getWidth())/2;
        y=(Game.HEIGHT-getHeight())/2;

        //TODO change random position to level coordinate
        startX=MathUtils.random(getColumns());
        startY=MathUtils.random(getRows());
        startX=5;
        startY=8;


    }

    public float startX(){return startX;}

    public float startY(){return startY;}

    public float getWidth(){ return columns*Game.GRID_CELL;}

    public float getHeight(){return rows* Game.GRID_CELL;}

    public int getColumns(){return columns;}

    public int getRows(){return rows;}

    public float getX(){return x;}

    public float getY(){ return y;}

    public void draw(ShapeRenderer sr){
        sr.setColor(0,0,0.15f,1);
        sr.begin(ShapeRenderer.ShapeType.Line);
        for(int i=0;i<=columns; i++){
            float x1,x2,y1,y2;
            x1=x2=x+Game.GRID_CELL*i;
            y1=y;
            y2=y+getHeight();
            sr.line(x1,y1,x2,y2);
        }
        for (int i=0;i<=rows; i++){
            float x1,x2,y1,y2;
            x1=x;
            y1=y2=y+Game.GRID_CELL*i;
            x2=x+getWidth();
            sr.line(x1,y1,x2,y2);
        }
        sr.end();
    }

}
