package pt.bisonte.snake.entities;

import java.io.Serializable;

public class PlayerScore implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 201809112000L;

    private String name;
    private long score;

    public PlayerScore(String name, long score) {
        this.name = name;
        this.score = score;
    }

    public void setName(String name) {
        this.name = name;

    }

    public void setScore(long score) {
        this.score = score;

    }

    public long getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

}
