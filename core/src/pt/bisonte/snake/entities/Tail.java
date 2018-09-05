package pt.bisonte.snake.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import pt.bisonte.snake.Game;

public class Tail extends GameObject {

    private float pX; //previous X
    private float pY; //previous Y

    public Tail(float x, float y, float radians){
        //initialize shape
        shapeX = new float[6];
        shapeY = new float[6];

        //set initial position
        this.x=x;
        this.y=y;

        this.radians = radians;

        //set previous position for the first tail
        pX= x;
        pY= y-10;
    }

    public float getPX() {
        return pX;
    }

    public float getPY() {
        return pY;
    }

    public float getDirection(){return radians;}

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        setShape();
    }


    @Override
    public void setShape() {
        int radio = 6;

        shapeX[0] = x + MathUtils.cos(radians + 3.1415f/6) * radio;
        shapeY[0] = y + MathUtils.sin(radians + 3.1415f/6) * radio;

        shapeX[1] = x + MathUtils.cos(radians + 3.1415f/2) * radio;
        shapeY[1] = y + MathUtils.sin(radians + 3.1415f/2) * radio;

        shapeX[2] = x + MathUtils.cos(radians + 5*3.1415f/6) * radio;
        shapeY[2] = y + MathUtils.sin(radians + 5*3.1415f/6) * radio;

        shapeX[3] = x + MathUtils.cos(radians + 7*3.1415f/6) * radio;
        shapeY[3] = y + MathUtils.sin(radians + 7*3.1415f/6) * radio;

        shapeX[4] = x + MathUtils.cos(radians + 3*3.1415f/2) * radio;
        shapeY[4] = y + MathUtils.sin(radians + 3*3.1415f/2) * radio;

        shapeX[5] = x + MathUtils.cos(radians + 11*3.1415f/6) * radio;
        shapeY[5] = y + MathUtils.sin(radians + 11*3.1415f/6) * radio;

    }

    @Override
    public void update(float dt) {
        //set shape for player
        setShape();

    }

    @Override
    public void draw(ShapeRenderer sr) {
        sr.setColor(1, 1, 0, 1);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        //draw a line point to point (initialy form first to last)
        for (int i = 0, j = shapeX.length - 1; i < shapeX.length; j = i++) {
            sr.rectLine(shapeX[i], shapeY[i], shapeX[j], shapeY[j],1.5f);
        }
        sr.end();

    }
}
