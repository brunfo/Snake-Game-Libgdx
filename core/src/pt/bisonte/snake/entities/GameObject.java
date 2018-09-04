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

    protected float rotationSpeed;

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

    public void setPosition(float x, float y){
        this.x=x;
        this.y=y;
    }

    public abstract void update(float dt);

    public abstract void draw(ShapeRenderer sr);

}
