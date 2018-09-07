package pt.bisonte.snake.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Fruit extends GameObject {

    private float removeTimer;
    private float removeTime;
    private boolean remove;
    private int score;


    public Fruit(float x, float y){
        setPosition(x,y);
        removeTimer=0;
        removeTime=10;

        //set width and height
        width=height=16;

        score=100;

    }

    public boolean shouldRemove(){ return remove;}

    public boolean contains(float x, float y){
        remove=super.contains(x,y);
        return remove;
    }

    public int getScore(){ return score;}

    @Override
    public void update(float dt) {
        removeTimer+=dt;
        if(removeTimer > removeTime){
            removeTimer=0;
            remove = true;
        }

    }

    @Override
    public void draw(ShapeRenderer sr) {
        sr.setColor(1,0,0,1);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.rect(x-width/2,y-height/2,width, height);
        sr.end();

    }
}
