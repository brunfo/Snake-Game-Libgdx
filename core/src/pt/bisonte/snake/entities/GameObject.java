package pt.bisonte.snake.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

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

    //shape(polygon
    protected float[] shapeX;
    protected float[] shapeY;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float[] getShapeX() {
        return shapeX;
    }

    public float[] getShapeY() {
        return shapeY;
    }

    /**
     * Sets coordinates for center of a body part.
     * @param x - width coordinate
     * @param y - eight coordinate
     */
    public void setPosition(float x, float y){
        this.x=x;
        this.y=y;
    }

    //public abstract void setShape();

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

}
