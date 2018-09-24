package pt.bisonte.snake.managers;

import pt.bisonte.snake.Game;
import pt.bisonte.snake.gamestates.*;


public class GameStateManager {

    private GameState gameState;

    /**
     * States of the game.
     */
    public enum State {
        MENU, PLAY, HIGHSCORES, GAMEOVER, EDITOR;
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
     *
     * @param state - MENU, PLAY, HIGHSCORES, GAMEOVER, EDITOR.
     */
    public void setState(State state) {
        if (gameState != null) {
            gameState.dispose();
        }

        Game.setCameraPosition();
        switch (state) {

            case MENU:
                gameState = new MenuState(this);
                break;
            case PLAY:
                gameState = new PlayState(this);
                break;
            case HIGHSCORES:
                gameState = new HighScoreState(this);
                break;
            case EDITOR:
                gameState = new EditLevelState(this);
                break;
            case GAMEOVER:
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
