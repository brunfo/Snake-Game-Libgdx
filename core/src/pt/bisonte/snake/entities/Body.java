package pt.bisonte.snake.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import pt.bisonte.snake.Game;

public class Body extends GameObject {


    private boolean rotateLeft;
    private boolean rotateRight;

    private float width;
    private float height;

    /**
     * Constructor for the head.
     */
    public Body() {


        //set random player start position
        setPosition(
                (MathUtils.random(Game.WIDTH/ Game.CELL_WIDTH))* Game.CELL_WIDTH-10,
                (MathUtils.random(Game.HEIGHT/Game.CELL_WIDTH))* Game.CELL_WIDTH-10);

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
     * Updates the head, positioning, facing angle, etc.
     * @param dt - game timer
     */
    @Override
    public void update(float dt) {

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

            sr.setColor(0,2,0,1);
            sr.begin(ShapeRenderer.ShapeType.Line);
            sr.rect (x-width/2+4,y-height/2+4,width-8, height-8);
            sr.end();

    }
}
