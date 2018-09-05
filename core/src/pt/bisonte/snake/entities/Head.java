package pt.bisonte.snake.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import pt.bisonte.snake.Game;

public class Head extends GameObject {


    private boolean rotateLeft;
    private boolean rotateRight;


    private float pX; //previous X
    private float pY; //previous Y

    public Head() {

        //start position
        x = Game.WIDTH / 2;
        y = Game.HEIGHT / 2;


        //initialize shape
        shapeX = new float[4];
        shapeY = new float[4];

        //initial orientation
        radians = (float) Math.PI / 2;

        //inital speed
        speed = 10;

        //set previous position for the first tail
        pX = x;
        pY = y - speed;

    }

    public void setRotateLeft(boolean rotateLeft) {
        this.rotateLeft = rotateLeft;
    }

    public void setRotateRight(boolean rotateRight) {
        this.rotateRight = rotateRight;
    }

    public float getPX() {
        return pX;
    }

    public float getPY() {
        return pY;
    }

    public float getDirection() {
        return radians;
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        setShape();
    }

    @Override
    public void setShape() {
        shapeX[0] = x + MathUtils.cos(radians) * 8;
        shapeY[0] = y + MathUtils.sin(radians) * 8;

        shapeX[1] = x + MathUtils.cos(radians - 3.1415f / 2) * 4;
        shapeY[1] = y + MathUtils.sin(radians - 3.1415f / 2) * 4;

        shapeX[2] = x + MathUtils.cos(radians + 3.1415f) * 4;
        shapeY[2] = y + MathUtils.sin(radians + 3.1415f) * 4;

        shapeX[3] = x + MathUtils.cos(radians + 3.1415f / 2) * 4;
        shapeY[3] = y + MathUtils.sin(radians + 3.1415f / 2) * 4;
    }

    @Override
    public void update(float dt) {
        //rotating
        if (rotateLeft)
            radians += (float) Math.PI / 2;
        if (rotateRight)
            radians -= (float) Math.PI / 2;


        //calculate direction
        dx = Math.round(Math.cos(radians)) * speed;
        dy = Math.round(Math.sin(radians)) * speed;

        //set new position
        x += dx;
        y += dy;


        //set shape for player
        setShape();
    }

    @Override
    public void draw(ShapeRenderer sr) {
        sr.setColor(1, 0, 0, 1);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        //draw a line point to point (initialy form first to last)
        for (int i = 0, j = shapeX.length - 1; i < shapeX.length; j = i++) {
            sr.rectLine(shapeX[i], shapeY[i], shapeX[j], shapeY[j], 2);
        }
        sr.end();
    }
}
