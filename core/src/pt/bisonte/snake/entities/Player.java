package pt.bisonte.snake.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Player extends GameObject {

    public enum Facing {
        UP, DOWN, LEFT, RIGHT
    }

    private Facing facing;

    private boolean rotateLeft;
    private boolean rotateRight;

    private boolean dead;
    private boolean eat;

    private double score;
    private int lives;

    private int fruitAte;


    /**
     * Constructor for the player.
     */
    public Player(int size) {

        //set WIDTH and HEIGHT
        width = height = size;

        //initial speed
        speed = width;

        lives = 3;

        reset();
    }

    /**
     * Reset previous orientation
     */
    public void reset() {
        //initial orientation
        radians = (float) Math.PI / 2;
        facing = Facing.UP;

        dead = false;
    }

    /**
     * Resets to determinate position width orientation.
     * @param x Coordinate
     * @param y Coordinate
     * @param facing Facing to..
     */
    public void resetToPosition(float x, float y, Facing facing){
        //reset rotating keys to prevent start width a rotating position
        rotateLeft=false;
        rotateRight=false;

        setPosition(x,y);
        this.facing=facing;
        switch(facing){
            case UP:
                radians =(float) Math.PI /2;
                break;
            case DOWN:
                radians = (float) Math.PI *3/2;
                break;
            case RIGHT:
                radians = (float) Math.PI;
                break;
            case LEFT:
                radians=(float)Math.PI * 2;
        }

        dead = false;
    }

    /**
     * Signs player to rotate left. Receives a boolean parameter from Game input keys.
     * It only sets turn if turning is not set already
     *
     * @param rotateLeft - true or false.
     */
    public boolean setRotateLeft(boolean rotateLeft) {
        if (!this.rotateLeft && !rotateRight) {
            this.rotateLeft = rotateLeft;
            return true;
        }
        return false;
    }

    /**
     * Signs player to rotate right. Receives a boolean parameter from Game input keys.
     * It only sets turn if turning is not set already
     *
     * @param rotateRight - true or false.
     */
    public boolean setRotateRight(boolean rotateRight) {
        if (!this.rotateRight && !rotateLeft) {
            this.rotateRight = rotateRight;
            return true;
        }
        return false;
    }

    /**
     * Handles Left key, if facing fruitToNextLevel, rotates left, if facing down, rotates right.
     *
     * @param b - true or false.
     */
    public void setLeft(boolean b) {
        switch (facing) {
            case UP:
                if (setRotateLeft(b) && b)
                    facing = Facing.LEFT;
                break;
            case DOWN:
                if (setRotateRight(b) && b)
                    facing = Facing.LEFT;
                break;
        }
    }

    /**
     * Handles Right key, if facing fruitToNextLevel, rotates left, if facing down, rotates right.
     *
     * @param b - true or false.
     */
    public void setRight(boolean b) {
        switch (facing) {
            case DOWN:
                if (setRotateLeft(b) && b)
                    facing = Facing.RIGHT;
                break;
            case UP:
                if (setRotateRight(b) && b)
                    facing = Facing.RIGHT;
        }
    }

    /**
     * Handles Up key, if facing right, rotates left, if facing left, rotates right.
     *
     * @param b - true or false.
     */
    public void setUp(boolean b) {
        switch (facing) {
            case RIGHT:
                if (setRotateLeft(b) && b)
                    facing = Facing.UP;
                break;
            case LEFT:
                if (setRotateRight(b) && b)
                    facing = Facing.UP;
                break;
        }
    }


    /**
     * Handles Up key, if facing left, rotates left, if facing right, rotates right.
     *
     * @param b - true or false.
     */
    public void setDown(boolean b) {
        switch (facing) {
            case LEFT:
                if (setRotateLeft(b) && b)
                    facing = Facing.DOWN;
                break;
            case RIGHT:
                if (setRotateRight(b) && b)
                    facing = Facing.DOWN;
                break;
        }
    }

    /**
     * Set player dead
     */
    public void hit() {
        dead = !dead;
        lives -= 1;
    }

    /**
     * Checks if player is dead.
     *
     * @return - true or false.
     */
    public boolean isDead() {
        return dead;
    }

    /**
     * If true the player sums score
     *
     * @param b     true or false
     * @param score int score
     */
    public boolean eat(boolean b, int score) {
        if (b) {
            this.score += score;
            fruitAte++;
            eat = true;
        }
        return b;
    }

    /**
     * Gets the number of fruits ate.
     * @return - number os fruits.
     */
    public int fruitsAte(){ return fruitAte; }

    /**
     * Checks if the snake has ate.
     *
     * @return true or false.
     */
    public boolean hasEat() {
        return eat;
    }

    /**
     * Gets the score of the player.
     *
     * @return score.
     */
    public double getScore() {
        return score;
    }

    /**
     * Gets remaining extra lives
     *
     * @return number of extra lives
     */
    public int getLives() {
        return lives;
    }

    /**
     * Updates the player, positioning, facing angle, etc.
     *
     * @param dt - game timer
     */
    @Override
    public void update(float dt) {

        eat = false;

        //rotating
        if (rotateLeft) {
            radians += (float) Math.PI / 2;
            rotateLeft = false;
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
     *
     * @param sr - ShapeRenderer
     */
    @Override
    public void draw(ShapeRenderer sr) {
        sr.setColor(0, 0.15f, 0, 1);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.rect(x, y, width, height);
        sr.end();
        sr.setColor(0, 1f, 0, 0.5f);
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.rect(x, y, width, height);
        sr.end();

        sr.setColor(0, 0.45f, 0, 1);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.rect(x + 4, y + 4, width - 8, height - 8);
        sr.end();
        sr.end();

    }
}
