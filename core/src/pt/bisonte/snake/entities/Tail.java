package pt.bisonte.snake.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import pt.bisonte.snake.Game;

public class Tail extends GameObject {

    public Tail(float x, float y) {

        //set WIDTH and HEIGHT
        width = height = Game.GRID_CELL;

        setPosition(x, y);
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void draw(ShapeRenderer sr) {
        sr.setColor(0, 0.25f, 0, 1);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.rect(x, y, width, height);
        sr.end();
        sr.setColor(0, 2, 0, 1);
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.rect(x, y, width, height);
        sr.end();

    }
}
