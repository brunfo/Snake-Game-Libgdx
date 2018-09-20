package pt.bisonte.snake.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class Apple extends GameObject {

    private float removeTimer;
    private float removeTime;
    private boolean remove;
    private int score;
    private boolean bonusApple; //this fruit decrease speed of the game by 10%


    public Apple(float x, float y) {
        setPosition(x, y);
        getRandomFruit();
        removeTimer = 0;
        removeTime = 10;

        //set WIDTH and HEIGHT
        width = height = 15;

        //sets the score, if bonus fruit it adds 100 points, else 10 points.
        score = bonusApple ? 100 : 10;
    }

    public Apple(float x, float y, boolean bonus){
        this(x,y);
        this.bonusApple= bonus;
    }

    /**
     * Check if the fruit i signed to be removed.
     *
     * @return - true or false.
     */
    public boolean shouldRemove() {
        return remove;
    }

    /**
     * Checks if a given coordinate is the same of the fruit. If it those, sets the fruit to be removed.
     *
     * @param x - point x
     * @param y - point y
     * @return - true or false.
     */
    public boolean contains(float x, float y) {
        remove = super.contains(x, y);
        return remove;
    }

    /**
     * Gets the score of the piece of fruit to sum to the player.
     *
     * @return - score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Randoms a bonus fruit width a chance of 10%.
     */
    private void getRandomFruit() {
        bonusApple = MathUtils.random(10) < 1;
    }

    /**
     * Returns true if this is a bonus fruit.
     *
     * @return - true or false.
     */
    public boolean isBonus() {
        return bonusApple;
    }

    @Override
    public void update(float dt) {
        removeTimer += dt;
        if (removeTimer > removeTime) {
            removeTimer = 0;
            remove = true;
        }

    }

    @Override
    public void draw(ShapeRenderer sr) {

        sr.setColor(1, 0, 0, 1);
        if (bonusApple)
            sr.setColor(255,255,0, 0);

        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.ellipse(x + width / 4, y, width / 2, height * 4 / 6);
        sr.ellipse(x + 2f * (width / 4), y, width / 2, height * 4 / 6);
        sr.setColor(0, 0.55f, 0, 1);
        sr.ellipse(x + width / 6, y + height / 2, width / 2, height * 2 / 6);
        sr.setColor(1, 1, 1, 1);
        sr.end();


    }
}
