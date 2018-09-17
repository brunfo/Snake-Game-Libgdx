package pt.bisonte.snake.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class Wall extends GameObject {


    public Wall(float x, float y, float size) {

        //set WIDTH and HEIGHT
        width = height = size;

        setPosition(x, y);
    }

    public Wall(){}

    @Override
    public void update(float dt) {

    }

    @Override
    public void draw(ShapeRenderer sr) {
        sr.setColor(0, 0, 0.25f, 1);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.rect(x, y, width, height);
        sr.end();
        sr.setColor(0, 0, 2, 1);
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.rect(x, y, width, height);
        sr.end();

    }
}
