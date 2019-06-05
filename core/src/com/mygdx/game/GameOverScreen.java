package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class GameOverScreen implements Screen {
    private SpriteBatch batch=new SpriteBatch();
    private Texture theEnd=new Texture("the end.jpg");
    private Animation<TextureRegion> book;
    float time=0;
    boolean end=false;
    @Override
    public void show() {
        book=new Animation(0.5f, TextureRegion.split(new Texture("books-close.png"),27,33)[0]);

    }

    @Override
    public void render(float delta) {


        batch.begin();
        if(!end) {

            batch.draw(theEnd, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }
        time+=delta;
        if(time>2&&end!=true){
            time=0;
            end=true;

        }
        if(end){
            batch.draw(book.getKeyFrame(time,false),0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        }
        time+=delta;
        batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
