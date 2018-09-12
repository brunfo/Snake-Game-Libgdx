package pt.bisonte.snake.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Fruit extends GameObject {

    private float removeTimer;
    private float removeTime;
    private boolean remove;
    private int score;


    public Fruit(float x, float y) {
        setPosition(x, y);
        removeTimer = 0;
        removeTime = 10;

        //set WIDTH and HEIGHT
        width = height = 16;

        score = 100;
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
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.rect(x, y, width, height);
        sr.end();

    }
}
