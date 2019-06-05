package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import static com.mygdx.game.Globals.*;

public class MenuScreen implements Screen {
    private Stage stage;
    private Image image;
    SpriteBatch batch=new SpriteBatch();
    private Animation<TextureRegion> book;
    private float time;
    private boolean pressed=false;

    @Override
    public void show() {
        Label label=new Label("Character Switcheroo", style,"title");
        label.setPosition(225,500);
        final TextButton play=new TextButton("Play", style);
        play.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                pressed=true;
                Gdx.audio.newSound(Gdx.files.internal("flip.mp3")).play(1);
                play.setVisible(false);
            }
        });
        play.setPosition(325,250);
        stage=new Stage();
        book=new Animation(0.4f,TextureRegion.split(new Texture("books-open.png"),27,33)[0]);
        stage.addActor(label);

        stage.addActor(play);
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta) {
        batch.begin();
        if(pressed) {
            time += delta;

            batch.draw(book.getKeyFrame(time,true),0,0,27.3f*time*time*1.8f,33*time*time*1.8f);
        }
        batch.end();
        if(time>3.6f){
            game.setScreen(new SelectScreen());
        }


        stage.draw();

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
