package com.mygdx.game.Choices;

public class Question {
    public String prompt;
    public Choice[] choices;
    public Question(String prompt, Choice... choices){
        this.choices=choices;
        this.prompt=prompt;

    }
}
