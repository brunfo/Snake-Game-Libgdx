package pt.bisonte.snake.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Head extends GameObject {

     private enum Facing{
        UP, DOWN, LEFT, RIGHT
    };

    private Facing facing;


    private boolean rotateLeft;
    private boolean rotateRight;


    private boolean dead;
    private boolean eat;

    private double score;
    private int lives;

    /**
     * Constructor for the head.
     */
    public Head(int size) {

        //set WIDTH and HEIGHT
        width=height=size;

        //initial speed
        speed = width;

        lives = 3;

        reset();
    }

    /**
     * Reset previous orientation
     */
    public void reset(){
        //initial orientation
        radians = (float) Math.PI / 2;
        facing=Facing.UP;

        dead=false;
    }

    /**
     * Signs head to rotate left. Receives a boolean parameter from Game input keys.
     * It only sets turn if turning is not set already
     * @param rotateLeft
     */
    public boolean setRotateLeft(boolean rotateLeft) {
        if (!this.rotateLeft && !rotateRight) {
            this.rotateLeft = rotateLeft;
            return true;
        }
        return false;
    }

    /**
     * Signs head to rotate right. Receives a boolean parameter from Game input keys.
     * It only sets turn if turning is not set already
     * @param rotateRight
     */
    public boolean setRotateRight(boolean rotateRight) {
        if(!this.rotateRight && !rotateLeft) {
            this.rotateRight = rotateRight;
            return true;
        }
        return false;
    }

    /**
     * Handles Left key, if facing up, rotates left, if facing down, rotates right.
     * @param b true or false
     */
    public void setLeft(boolean b){
            switch (facing){
                case UP:
                    if(setRotateLeft(b) && b)
                        facing = Facing.LEFT;
                    break;
                case DOWN:
                    if(setRotateRight(b) && b)
                        facing = Facing.LEFT;
                    break;
            }
    }

    /**
     * Handles Right key, if facing up, rotates left, if facing down, rotates right.
     * @param b true or false
     */
    public void setRight(boolean b){
        switch (facing){
            case DOWN:
                if(setRotateLeft(b) && b)
                    facing=Facing.RIGHT;
                break;
            case UP:
                if(setRotateRight(b) && b)
                    facing=Facing.RIGHT;
        }

    }

    /**
     * Handles Up key, if facing right, rotates left, if facing left, rotates right.
     * @param b true or false
     */
    public void setUp(boolean b){
        switch (facing){
            case RIGHT:
                if(setRotateLeft(b) && b)
                    facing=Facing.UP;
                break;
            case LEFT:
                if(setRotateRight(b) && b)
                    facing=Facing.UP;
                break;
        }
    }


    /**
     * Handles Up key, if facing left, rotates left, if facing right, rotates right.
     * @param b true or false
     */
    public void setDown(boolean b){
        switch(facing ){
            case LEFT:
                if(setRotateLeft(b) && b)
                    facing=Facing.DOWN;
                break;
            case RIGHT:
                if(setRotateRight(b) && b)
                    facing=Facing.DOWN;
                break;
        }
    }
    /**
     * Set player dead
     */
    public void hit(){
        dead = !dead;
        lives-=1;
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


    public double getScore() { return score;};

    public int getLives(){ return lives;}

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
        sr.rect(x,y,width, height);
        sr.end();
        sr.setColor(0,2,0,1);
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.rect (x,y,width, height);
        sr.end();

        sr.setColor(0,0.1f,0,1);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.rect (x+4,y+4,width-8, height-8);
        sr.end();
        sr.setColor(1,0,0,1);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.circle(x,y,2);
        sr.end();

    }
}
