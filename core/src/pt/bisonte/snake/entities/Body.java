package pt.bisonte.snake.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import pt.bisonte.snake.Game;

public class Body extends GameObject {


    private boolean rotateLeft;
    private boolean rotateRight;

    private float width;
    private float height;

    private boolean head; //required for draw head different to body parts


    private float pX; //previous X
    private float pY; //previous Y

    /**
     * Constructor for the head.
     */
    public Body() {

        //start position
        x = Game.WIDTH / 2;
        y = Game.HEIGHT / 2;

        //set width and height
        width=height=16;

        //initialize shape
        shapeX = new float[4];
        shapeY = new float[4];

        //initial orientation
        radians = (float) Math.PI / 2;

        //initial speed
        speed = width;

        //set previous position for the first tail
        pX = x;
        pY = y - height;

        head=true;

    }

    /**
     * Constructor for body parts.
     */
    public Body(float x, float y){
        this();
        this.x=x;
        this.y=y;

        //set previous position for the first tail
        pX = x;
        pY = y - height;

        head=false;
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
     * Get previous x coordinate.
     * @return pX coordinate.
     */
    @Deprecated
    public float getPX() {
        return pX;
    }

    /**
     * Get previous y coordinate.
     * @return pY coordinate.
     */
    @Deprecated
    public float getPY() {
        return pY;
    }

    /**
     * Check if this part is the head.
     * @return true or false.
     */
    private boolean isHead(){return head;}

    /**
     * Sets coordinates for center of a body part.
     * @param x - width coordinate
     * @param y - eight coordinate
     */
    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        setShape();
    }

    /**
     * Sets a polygon shape according from a angle.
     *
     *  - CURRENTLY THIS IS NOT USED IN THIS GAME -
     */
    @Override
    @Deprecated
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

    /**
     * Updates the body part, positioning, facing angle, etc.
     * @param dt - game timer
     */
    @Override
    public void update(float dt) {
        if(isHead()) {
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
    }


    /**
     * Draws the shape. If a body part is the head, is draw slightly different.
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
        if (isHead()){
            sr.setColor(0,2,0,1);
            sr.begin(ShapeRenderer.ShapeType.Line);
            sr.rect (x-width/2+4,y-height/2+4,width-8, height-8);
            sr.end();
        }
    }
}
