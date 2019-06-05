package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Choices.Choice;
import com.mygdx.game.Choices.Question;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;

import static com.mygdx.game.Globals.*;
public class PlayScreen implements Screen {
    OrthographicCamera camera;
    ShapeRenderer shapeRenderer;
    SpriteBatch batch;
    Stage stage;
    Path curPath;
    Array<Path> choicePaths;
    Array<Path> deadPaths;
    float deathA=1f;
    Array<Path> oldPaths;
    Group group;
    int index;
    float time;
    TypingLabel label;

    @Override
    public void show() {
        Music music=Gdx.audio.newMusic(Gdx.files.internal(story.mc.toString()+"/song.mp3"));
        music.play();
        index=0;
        time=0;

        stage=new Stage();
        shapeRenderer=new ShapeRenderer();
        camera=new OrthographicCamera();
        camera.setToOrtho(false);
        oldPaths=new Array<Path>();
        choicePaths=new Array<Path>();
        deadPaths=new Array<Path>();
        curPath=new Path(new Vector2());
        batch=new SpriteBatch();
        player.setCurPath(curPath);
        group=new Group();
        addChoicePaths();

        Gdx.input.setInputProcessor(stage);
        stage.addActor(group);


    }

    private void addChoicePaths() {
        if(index>=story.questions.length){
            game.setScreen(new GameOverScreen());
            return;
        }
        Choice[] choices =story.questions[index].choices;
        for(int i=0;i<choices.length;i++){
            choicePaths.add(new Path(player.getCurPath().getEnd()));
        }

    }

    @Override
    public void render(float delta) {
        camera.position.x=player.getX();
        camera.position.y=player.getY();

        camera.update();
        player.update(delta);



        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1,1,1,deathA);
        for(Path path: deadPaths){
            path.draw(shapeRenderer);
        }
        curPath.draw(shapeRenderer);
        for(Path path:oldPaths){
            path.draw(shapeRenderer);
        }
        for(Path path:choicePaths){
            path.draw(shapeRenderer);
        }
        if(!deadPaths.isEmpty()){
            deathA-=0.01f;
        }
        if(deathA<=0){
            deadPaths.clear();
            deathA=1;
        }

        shapeRenderer.setColor(1,1,1,1);
        shapeRenderer.end();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        player.draw(batch);
        batch.end();
        if(!group.hasChildren()&&player.isIdle()){
            Question question=story.questions[index];
            label=new TypingLabel(story.questions[index].prompt,style);
            label.setPosition(150,500);
            stage.addActor(label);
            int x=200;
            int y=400;

            for(int i=0;i<question.choices.length;i++){
                TypingLabel option=new TypingLabel((i+1)+". "+question.choices[i].choice,style);
                /*option.addListener(new CClickListener<Integer>(i){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        group.clearChildren();
                        oldPaths.add(curPath);
                        curPath=choicePaths.removeIndex(object);


                        player.setCurPath(curPath);
                        deadPaths.addAll(choicePaths);
                        choicePaths.clear();
                        index+=1;
                        addChoicePaths();



                        super.clicked(event, x, y);
                    }
                });

                 */
                group.addActor(option);

                option.setPosition(x,y);
                y-=100;

            }



        }
        stage.act();
        stage.draw();
        if(group.hasChildren()){
            time+=delta;
            if(time>5){
                for(int i=0;i<story.questions[index].choices.length;i++) {
                    if (story.questions[index].choices[i].selector == player.character) {
                        Label answer=new Label((i+1)+".",style,"title");
                        answer.setPosition(400,400);
                        group.addActor(answer);





                    }

                }

            }
            if(time>10){
                group.clearChildren();
                stage.clear();
                stage.addActor(group);

                oldPaths.add(curPath);
                for(int i=0;i<story.questions[index].choices.length;i++){
                    if(story.questions[index].choices[i].selector==player.character){
                        curPath=choicePaths.removeIndex(i);


                    }


                }
                time=0;


                player.setCurPath(curPath);
                deadPaths.addAll(choicePaths);
                choicePaths.clear();
                index+=1;
                addChoicePaths();

            }
        }





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
