package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ObjectMap;
import com.mygdx.game.Choices.Story;

import static com.mygdx.game.Globals.*;

public class StoryScreen implements Screen {
    private Stage stage;

    @Override
    public void show() {
        stage=new Stage();
        int x=100;
        int y=200;
        Label select=new Label("Select a story",style,"title");
        select.setPosition(225,500);
        stage.addActor(select);
        for(Story s: stories){
            if(player.character!=s.mc){
                TextButton button=new TextButton(s.name,style);
                button.addListener(new CClickListener<Story>(s){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        story=object;
                        game.setScreen(new PlayScreen());

                        super.clicked(event, x, y);
                    }
                });
                stage.addActor(button);
                button.setPosition(x,y);
                x+=200;

            }
        }
        Gdx.input.setInputProcessor(stage);

    }


    @Override
    public void render(float delta) {
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
