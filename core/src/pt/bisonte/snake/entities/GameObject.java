package pt.bisonte.snake.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import pt.bisonte.snake.Game;

public abstract class GameObject {
    //postition
    float x;
    float y;

    //vector
    float dx;
    float dy;

    //angle direction in radians
    float radians;

    float speed;

    //size
    float width;
    float height;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    /**
     * Sets coordinates for center of a body part.
     *
     * @param x - WIDTH coordinate
     * @param y - eight coordinate
     */
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Return true if coordinates x and y are the same,
     *
     * @param x  - point x
     * @param y- point y
     * @return true or false
     */
    public boolean contains(float x, float y) {
        return (getX() == x && getY() == y);
    }

    public abstract void update(float dt);

    public abstract void draw(ShapeRenderer sr);

    /**
     * When ends the game board, jumps to opposite side board.
     */

    public void wrap() {
        if (x < 0) {
            x = Game.WIDTH - width;
        }
        if (x > Game.WIDTH - width) {
            x = 0;
        }
        if (y < 0) {
            y = Game.HEIGHT - height;
        }
        if (y > Game.HEIGHT - height) {
            y = 0;
        }

    }

}
