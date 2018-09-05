package pt.bisonte.snake.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Tail extends GameObject {

    public Tail(){
        //initialize shape
        shapeX = new float[4];
        shapeY = new float[4];
    }


    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        setShape();
    }


    @Override
    public void setShape() {
        //draw a circle radio 8

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void draw(ShapeRenderer sr) {

    }
}
