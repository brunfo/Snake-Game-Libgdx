package pt.bisonte.snake.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import pt.bisonte.snake.entities.Player;
import pt.bisonte.snake.entities.Wall;
import pt.bisonte.snake.managers.GameStateManager;

import java.io.*;
import java.util.List;


public class LevelManager {

    private static LevelData level;

    public static void getNext() {

        //alter drawLevel to test new or replaced level.
        boolean drawLevel = false;
        if(drawLevel)
            save();

        String filename;
        if (level == null)
            filename = "levels/level1.dat";
        else
            filename="levels/level" + (getID()+1) + ".dat";
        load(filename);
    }

    /**
     * Used to save level details.
     */
    private static void save(){
        level= new LevelData();
        level.save();
        save(level);
    }

    public static int getRows() {
        return level.getRows();
    }

    public static int getColumns() {
        return level.getColumns();
    }

    public static int getGrid() {
        return level.getGridCell();
    }

    public static int getID() {
        return level.getID();
    }

    public static int getFruitToNextLevel() {
        if (GameStateManager.getPlayMode()==GameStateManager.PlayMode.INFINITE_TAIL)
            return 1000;
        return level.getFruitToNextLevel();
    }

    public static List<Wall> getWalls() {
        return level.getWalls();
    }


    /**
     * Saves a file with level data.
     */
    private static void save(LevelData level) {
        try {
            //preparing json
            Json json = new Json();
            json.setElementType(LevelData.class, "walls", Wall.class);

            //preparing file and outputstream
            FileOutputStream fileOut= new FileOutputStream("levels/level" + getID() + ".dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            //writing
            out.writeObject(json.toJson(level));

            out.flush();
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
            Gdx.app.exit();
        }

    }


    /**
     * Loads file width saved high scores.
     */
    private static void load(String filename) {
        try {
            if (!saveFileExists(filename)) {
               // if (level==null)
                    save();
                return;
            }
            //preparing json
            Json json = new Json();
            json.setElementType(LevelData.class, "walls", Wall.class);

            //preparing file and inputstream
            FileInputStream fileIn= new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            //reading
            level = json.fromJson(LevelData.class, in.readObject().toString());

            in.close();
            fileIn.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            Gdx.app.exit();
        }
        level.init();
    }

    /**
     * Checks if the file exists.
     *
     * @return true or false.
     */
    private static boolean saveFileExists(String filename) {
        File f = new File(filename);
        return f.exists();
    }


    /**
     * Grants that LevelManager is reset without a stored level in memory.
     */
    public static void reset() {
        level=null;
    }

    public static float getStartX() {
        return level.getStartx();
    }

    public static float getStartY() {
        return level.getStartY();
    }

    public static Player.Facing getFacing() {
        return level.getFacing();
    }
}
