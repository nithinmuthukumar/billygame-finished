package com.mygdx.game.Choices;

import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.mygdx.game.Character;

import java.util.Arrays;
import java.util.TreeMap;

public class Story {
    public String name;
    public Character mc;
    public Question[] questions;
    public Story(String name,Character c,Question... questions){
        this.name=name;
        this.questions=questions;
        this.mc=c;
    }
}
