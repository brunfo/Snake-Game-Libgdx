package pt.bisonte.snake.gamestates;

import pt.bisonte.snake.managers.GameStateManager;

public abstract class GameState {
    final GameStateManager gameStateManager;

    GameState(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
        init();
    }

    protected abstract void init();

    public abstract void update(float dt);

    public abstract void draw();

    public abstract void handleInput();

    public abstract void dispose();

}
