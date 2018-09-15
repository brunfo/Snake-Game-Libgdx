package pt.bisonte.snake.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class Fruit extends GameObject {

    private float removeTimer;
    private float removeTime;
    private boolean remove;
    private int score;
    private boolean bonusFruit; //this fruit decrease speed of the game by 10%


    public Fruit(float x, float y) {
        setPosition(x, y);
        getRandomFruit();
        removeTimer = 0;
        removeTime = 10;

        //set WIDTH and HEIGHT
        width = height = 15;

        //sets the score, if bonus fruit it adds 100 points, else 10 points.
        score = bonusFruit ? 100 : 10;
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
        bonusFruit = MathUtils.random(10) < 1;
    }

    /**
     * Returns true if this is a bonus fruit.
     *
     * @return - true or false.
     */
    public boolean isBonus() {
        return bonusFruit;
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
        sr.setColor(0.55f, 0, 0, 1);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.rect(x, y, width, height);
        sr.end();

        sr.setColor(1, 0, 0, 1);
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.rect(x, y, width, height);
        sr.end();

        if (bonusFruit) {
            sr.setColor(255, 0, 128, 0);
            sr.begin(ShapeRenderer.ShapeType.Filled);
            sr.rect(x + 3, y + 3, width - 6, height - 6);
            sr.end();
        }

    }
}
