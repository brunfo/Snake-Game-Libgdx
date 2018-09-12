package pt.bisonte.snake.entities;

import java.io.Serializable;

public class PlayerScore implements Serializable {
    /**
     * Serializable identifier version
     */
    private static final long serialVersionUID = 201809112000L;

    private String name;
    private long score;

    public PlayerScore(String name, long score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Sets the name of the player.
     *
     * @param name - Name
     */
    public void setName(String name) {
        this.name = name;

    }

    /**
     * Sets the score of the player.
     *
     * @param score - Score.
     */
    public void setScore(long score) {
        this.score = score;

    }

    /**
     * Gets the score of the player.
     *
     * @return - Score.
     */
    public long getScore() {
        return score;
    }

    /**
     * Gets the name of the player.
     *
     * @return - Name of the player.
     */
    public String getName() {
        return name;
    }

}
