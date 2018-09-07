package pt.bisonte.snake.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import pt.bisonte.snake.Game;

public class Head extends GameObject {


    private boolean rotateLeft;
    private boolean rotateRight;

    private float width;
    private float height;

    private boolean dead;
    private boolean eat;

    private double score;

    /**
     * Constructor for the head.
     */
    public Head() {

        //set width and height
        width=height=Game.CELL_WIDTH;

        //initial orientation
        radians = (float) Math.PI / 2;

        //initial speed
        speed = width;


    }

    /**
     * Signs head to rotate left. Receives a boolean parameter from Game input keys.
     * It only sets turn if turning is not set already
     * @param rotateLeft
     */
    public void setRotateLeft(boolean rotateLeft) {
        if (!this.rotateLeft && !rotateRight)
            this.rotateLeft = rotateLeft;
    }

    /**
     * Signs head to rotate right. Receives a boolean parameter from Game input keys.
     * It only sets turn if turning is not set already
     * @param rotateRight
     */
    public void setRotateRight(boolean rotateRight) {
        if(!this.rotateRight && !rotateLeft)
            this.rotateRight = rotateRight;
    }

    /**
     * Set player dead
     * @param b
     */
    public void hit(boolean b){
        dead = b;
    }

    public boolean isDead(){ return dead;}

    /**
     * If true the player sums score
     * @param b true or false
     * @param score int score
     */
    public void eat(boolean b, int score){
        if(b) {
            this.score += score;
            eat = true;
        }
    }

    public boolean hasEat(){ return eat;}

     /**
     * Updates the head, positioning, facing angle, etc.
     * @param dt - game timer
     */
    @Override
    public void update(float dt) {

            eat = false;

            //rotating
            if (rotateLeft) {
                radians += (float) Math.PI / 2;
                rotateLeft=false;
            }
            if (rotateRight) {
                radians -= (float) Math.PI / 2;
                rotateRight = false;
            }


            //calculate direction
            dx = Math.round(Math.cos(radians)) * speed;
            dy = Math.round(Math.sin(radians)) * speed;

            //set new position
            x += dx;
            y += dy;

    }


    /**
     * Draws the shape.
     * @param sr - ShapeRenderer
     */
    @Override
    public void draw(ShapeRenderer sr) {
        sr.setColor(0, 0.25f, 0, 1);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.rect(x-width/2,y-height/2,width, height);
        sr.end();
        sr.setColor(0,2,0,1);
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.rect (x-width/2,y-height/2,width, height);
        sr.end();

            sr.setColor(0,0.1f,0,1);
            sr.begin(ShapeRenderer.ShapeType.Filled);
            sr.rect (x-width/2+4,y-height/2+4,width-8, height-8);
            sr.end();

    }
}
