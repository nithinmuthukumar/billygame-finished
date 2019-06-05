package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ObjectMap;
import com.mygdx.game.Character;
public class Player extends Sprite {

    public Character character;
    private ObjectMap<State,Animation<TextureRegion>> animations;
    private State state=State.WALK;
    private float aniTime;
    private Path curPath;
    private int progress;


    public Player(Character c, ObjectMap<State, Animation<TextureRegion>> animations) {
        super(animations.get(State.IDLE).getKeyFrames()[0]);
        this.animations=animations;
        aniTime=0;
        progress=0;
        this.character=c;

    }
    public void update(float deltaTime) {

        if(progress>=curPath.points.length){

            state=State.IDLE;
            //change state to standing
        }


        else {
            Vector2 goal=curPath.points[progress];
            state = State.WALK;

            progress+=1;

            setPosition(goal.x,goal.y);


        }
        setRegion(animations.get(state).getKeyFrame(aniTime, true));
        aniTime+=deltaTime;





    }


    public Path getCurPath() {
        return curPath;
    }

    public void setCurPath(Path path){
        progress=0;
        this.curPath=path;

    }

    public boolean isIdle() {
        return state.equals(State.IDLE);
    }
}
