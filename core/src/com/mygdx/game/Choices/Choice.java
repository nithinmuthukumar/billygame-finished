package com.mygdx.game.Choices;

import com.mygdx.game.Character;

public class Choice {
    public Character selector;
    public String choice;
    public Choice(Character c,String choice) {
        this.selector = c;
        this.choice = choice;

    }
}
