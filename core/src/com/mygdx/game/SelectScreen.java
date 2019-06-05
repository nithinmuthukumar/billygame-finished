package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.GLOnlyTextureData;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

import static com.mygdx.game.Globals.*;

public class SelectScreen implements Screen {
    private Stage stage;
    private Array<Sprite> sprites=new Array<Sprite>();
    SpriteBatch batch=new SpriteBatch();





    @Override
    public void show() {
        stage=new Stage();
        Label l=new Label("Select a character", style,"title");
        l.setPosition(225,500);
        stage.addActor(l);
        for(int i=0;i<Character.values().length;i++){
            Character c=Character.values()[i];
            TextButton t=new TextButton(c.toString(), style);
            Sprite s=new Sprite(new Texture(c.toString()+"/IDLE.png"));
            s.setPosition(i*200+50,150);
            sprites.add(s);

            t.addListener(new CClickListener<Character>(c){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    player=new Player(object,createAnimation(object));
                    game.setScreen(new StoryScreen());


                }
            });
            t.setPosition(i*200,200);
            stage.addActor(t);
        }
        Gdx.input.setInputProcessor(stage);

    }
    public ObjectMap<State,Animation<TextureRegion>> createAnimation(Character c) {

        ObjectMap<State, Animation<TextureRegion>> animations=new ObjectMap<State, Animation<TextureRegion>>();
        Texture t=new Texture(c.toString()+"/IDLE.png");
        animations.put(State.WALK,new Animation(0.1f,TextureRegion.split(new Texture(c.toString()+"/WALK.png"),t.getWidth(),t.getHeight())[0]));
        animations.put(State.IDLE,new Animation(0.1f,TextureRegion.split(new Texture(c.toString()+"/IDLE.png"),t.getWidth(),t.getHeight())[0]));
        return animations;
    }

    @Override
    public void render(float delta) {
        stage.draw();
        batch.begin();
        for(Sprite s:sprites){
            s.draw(batch);
        }

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
