package pt.bisonte.snake.managers;

import com.badlogic.gdx.Gdx;

import java.io.*;

/**
 * Singleton Game File Manager
 */
public enum GameFile {
    MANAGER;

    public GameData gameData;
    private final String filename = "highscores.dat";

    /**
     * Saves a file with game high scores.
     */
    public void save() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(gameData);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            Gdx.app.exit();
        }

    }

    /**
     * Loads file width saved high scores.
     */
    public void load() {
        try {
            if (!saveFileExists()) {
                init();
                return;
            }
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
            gameData = (GameData) in.readObject();
            in.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            Gdx.app.exit();
        }
    }

    /**
     * Checks if the file exists.
     *
     * @return true or false.
     */
    private boolean saveFileExists() {
        File f = new File(filename);
        return f.exists();
    }


    private void init() {
        gameData = new GameData();
        gameData.init();
        save();
    }

}