package pt.bisonte.snake.managers;

import pt.bisonte.snake.gamestates.*;

public class GameStateManager {

    private GameState gameState;

    public enum State{
        MENU, PLAY, HIGHSCORES, GAMEOVER
    };

    public GameStateManager(){
        setState(State.MENU);
    }

    public void setState(State state){
        if(gameState !=null){
            gameState.dispose();
        }

        switch(state){
            case MENU:
                gameState = new MenuState(this);
                break;
            case PLAY:
                gameState = new PlayState(this);
                break;
            case HIGHSCORES:
                gameState = new HighScoreState(this);
                break;
            case GAMEOVER:
                gameState = new GameOverState(this);
                break;
        }
    }

    public void update(float dt){
        gameState.update(dt);

    }

    public void draw(){
        gameState.draw();

    }
}
