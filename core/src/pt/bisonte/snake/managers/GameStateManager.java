package pt.bisonte.snake.managers;

import pt.bisonte.snake.Game;
import pt.bisonte.snake.gamestates.*;


public class GameStateManager {

    private GameState gameState;

    /**
     * States of the game.
     */
    public enum State {
        MENU, PLAY, HIGHSCORES, GAMEOVER
    }

    /**
     * Play mode options available.
     */
    public enum OptionKeys {
        SNAKE, PLAYER
    }

    public static OptionKeys optionKeys;


    public GameStateManager() {
        optionKeys = OptionKeys.PLAYER;
        setState(State.MENU);
    }

    /**
     * Sets new game state.
     * @param state - MENU, PLAY, HIGHSCORES, GAMEOVER.
     */
    public void setState(State state) {
        if (gameState != null) {
            gameState.dispose();
        }

        switch (state) {
            case MENU:
                Game.setCameraPosition();
                gameState = new MenuState(this);
                break;
            case PLAY:
                Game.setCameraPosition();
                gameState = new PlayState(this);
                break;
            case HIGHSCORES:
                Game.setCameraPosition();
                gameState = new HighScoreState(this);
                break;
            case GAMEOVER:
                Game.setCameraPosition();
                gameState = new GameOverState(this);
                break;
        }
    }

    public void update(float dt) {
        gameState.update(dt);

    }

    public void draw() {
        gameState.draw();

    }
}
