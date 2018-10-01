package pt.bisonte.snake.managers;

import pt.bisonte.snake.Game;
import pt.bisonte.snake.gamestates.*;


public class GameStateManager {

    private GameState gameState;



    /**
     * States of the game.
     */
    public enum State {
        MENU, PLAY, HIGHSCORES, OPTIONS, GAMEOVER
    }

    /**
     * Play mode options available.
     */
    public enum OptionKeys {
        SNAKE, PLAYER
    }

    public enum PlayMode{
        LEVEL_UP, INFINITE_TAIL
    }

    private static OptionKeys optionKeys;

    private static PlayMode playMode;

    private static boolean newGame;

    public GameStateManager() {
        optionKeys = OptionKeys.PLAYER;
        playMode= PlayMode.INFINITE_TAIL;
        setState(State.MENU);
    }

    public void setOptionsKeys(OptionKeys optionKeys) {
        GameStateManager.optionKeys = optionKeys;
    }

    public static OptionKeys getOptionsKeys() {
        return GameStateManager.optionKeys;
    }

    public static PlayMode getPlayMode() {
        return playMode;
    }

    public static void setPlayMode(PlayMode playMode) {
        GameStateManager.playMode = playMode;
    }

    /**
     * Sets new game state.
     *
     * @param state - MENU, PLAY, HIGHSCORES, OPTIONS, GAMEOVER.
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
                if (isNewGame())
                    gameState = new OptionsState(this);
                else
                    gameState = new PlayState(this);
                break;
            case HIGHSCORES:
                Game.setCameraPosition();
                gameState = new HighScoreState(this);
                break;
            case OPTIONS:
                Game.setCameraPosition();
                gameState = new OptionsState(this);

                break;
            case GAMEOVER:
                Game.setCameraPosition();
                gameState = new GameOverState(this);
                break;
        }
    }
    public static boolean isNewGame() {
        return newGame;
    }

    public static void startNewGame() {
        newGame=!newGame;
    }

    public void update(float dt) {
        gameState.update(dt);

    }

    public void draw() {
        gameState.draw();

    }
}
