package pt.bisonte.snake.managers;

import pt.bisonte.snake.entities.PlayerScore;

import java.io.Serializable;

public class GameData implements Serializable {
    /**
     * Serializable identifier version
     */
    private static final long serialVersionUID = 201809111959L;

    private final int MAX_SCORES = 10;

    private final PlayerScore[] highScores;

    private long tentativeScore;

    GameData() {
        highScores = new PlayerScore[MAX_SCORES];

    }

    /**
     * Setups empty high scores table.
     */

    void init() {
        for (int i = 0; i < MAX_SCORES; i++) {
            highScores[i] = new PlayerScore("---", 0);
        }
    }

    /**
     * Gets the high score table.
     *
     * @return - array of high scores.
     */
    public PlayerScore[] getHighScores() {
        return highScores;
    }

    /**
     * Gets the tentative score to set in the table of high scores.
     *
     * @return - score
     */
    public long getTentativeScore() {
        return tentativeScore;
    }

    /**
     * Sets the tentative score to set in the table of high scores.
     *
     * @param l - score to attempt to insert int the high score table.
     */
    public void setTentativeScore(long l) {
        this.tentativeScore = l;
    }

    /**
     * Checks if the score is bigger then the minor recorded score
     *
     * @param score - score
     * @return true or false.
     */
    public boolean isHighScore(long score) {
        return score > highScores[MAX_SCORES - 1].getScore();
    }

    /**
     * Substitute the minor score for new.
     *
     * @param newScore - new high score
     * @param newName - name of the player.
     */
    public void addHighScore(long newScore, String newName) {
        if (isHighScore(newScore)) {
            highScores[MAX_SCORES - 1].setScore(newScore);
            highScores[MAX_SCORES - 1].setName(newName);
            sortHighScore();
        }
    }

    /**
     * Sorts the high scores.
     */
    private void sortHighScore() {
        for (int i = 0; i < MAX_SCORES; i++) {
            long score = highScores[i].getScore();
            String name = highScores[i].getName();

            int j;
            for (j = i - 1; j >= 0 && highScores[j].getScore() < score; j--) {
                highScores[j + 1].setScore(highScores[j].getScore());
                highScores[j + 1].setName(highScores[j].getName());

            }
            highScores[j + 1].setScore(score);
            highScores[j + 1].setName(name);
        }

    }

}
