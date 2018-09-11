package pt.bisonte.snake.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import pt.bisonte.snake.Game;

public abstract class GameObject {
    //postition
    protected float x;
    protected float y;

    //vector
    protected float dx;
    protected float dy;

    //angle direction in radians
    protected float radians;

    protected float speed;

    //size
    protected float width;
    protected float height;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    /**
     * Sets coordinates for center of a body part.
     * @param x - WIDTH coordinate
     * @param y - eight coordinate
     * @return - Returns this Game Object, allowing to chain methods.
     */
    public GameObject setPosition(float x, float y){
        this.x=x;
        this.y=y;
        return this;
    }

    /**
     * Return true if coordinates x and y are the same,
     * @param x - point x
     * @param y- point y
     * @return true or false
     */
    public boolean contains(float x, float y){
        if(getX()==x && getY()==y) {
              return true;
        }
        return false;
    }

    public abstract void update(float dt);

    public abstract void draw(ShapeRenderer sr);

    // when end the screen it jumps to opposite side screen
    public void wrap() {
        if (x < 0) {
            x = Game.WIDTH-width;
        }
        if (x > Game.WIDTH-width) {
            x = 0;
        }
        if (y < 0){
            y = Game.HEIGHT-height;
        }
        if (y > Game.HEIGHT-height ) {
            y = 0;
        }

    }

}
