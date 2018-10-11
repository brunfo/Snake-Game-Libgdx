package pt.bisonte.snake.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;
import java.util.Map;

public class Jukebox {
    private static Map<String, Sound> sounds;

    static {
        sounds = new HashMap<>();
    }

    public static void load(String path, String name) {
        Sound sound = Gdx.audio.newSound(Gdx.files.internal(path));
        sounds.put(name, sound);
    }

    public static void play(String name) {
        play(name, 1f);
    }

    /**
     * Plays a sound width volume control.
     *
     * @param name name of the referenced sound file.
     * @param volume volume of sound.
     */
    public static void play(String name, float volume) {
        sounds.get(name).play(volume);
    }

    public static void loop(String name) {
        sounds.get(name).loop();
    }

    public static void stop(String name) {
        sounds.get(name).stop();
    }

    public static void stopAll() {
        for (Sound s : sounds.values()) {
            s.stop();
        }
    }
}
