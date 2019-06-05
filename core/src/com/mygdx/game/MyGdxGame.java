package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.Choices.Choice;
import com.mygdx.game.Choices.Question;
import com.mygdx.game.Choices.Story;

import java.util.Arrays;

import static com.mygdx.game.Character.*;
import static com.mygdx.game.Globals.*;


public class MyGdxGame extends Game {


	@Override
	public void create () {

		AssetManager manager=new AssetManager();
		manager.load("flat-earth/skin/flat-earth-ui.json", Skin.class);
		manager.finishLoading();
		style=manager.get("flat-earth/skin/flat-earth-ui.json", Skin.class);
		game=this;
		Story storyMacbeth=new Story("Macbeth",Macbeth,
				new Question(
						"Three witches appear in front of you…\nThey prophesize that you will become king.",
						new Choice(Nicholson,"A little desire awakens within you but you hold it in"),
						new Choice(Billy,"Believe the prophecy\nbut do nothing"),
						new Choice(Winston,"Realize that this would\nmean the death of the king")),
				new Question(
						"Lady Macbeth argues with you and tries to convince you\nthat murdering Duncan is the correct path",
						new Choice(Billy,"Comply with her because you have no masculinity to begin with."),
						new Choice(Nicholson,"Dismiss her because your principles and honour are at stake."),
						new Choice(Winston,"Get your masculinity torn to shreds.")),
				new Question(
						"You are about to kill Duncan",
						new Choice(Billy,"Wimp out and Lady Macbeth has to do it for you."),
						new Choice(Nicholson,"Follow through by making yourself\n" +
								"believe that it is for the good of the nation"),
						new Choice(Winston,"Not being able to commit the murder for\n" +
								"personal gain because of how immoral it is")),
				new Question(
						"You are deciding on what to do with Banquo and Fleance",
						new Choice(Billy,"Believe that it is inevitable and do nothing."),
						new Choice(Nicholson,"Hatch a plot to kill them to protect what you have done so far."),
						new Choice(Winston,"Leap for the first chance to kill them.")),
				new Question(
						"You see the witches' prophecy coming true",
						new Choice(Nicholson,"What have I done"),
						new Choice(Billy,"Accept death believing it was coming anyway"),
						new Choice(Winston,"Fight to the death"))
		);
		Story slaughterHouseFive=new Story("Slaughterhouse\n5",Billy,
				new Question(
						"You are stranded along with the three musketeers",
						new Choice(Nicholson,"Fight valiantly and become their leader"),
						new Choice(Winston,"Follow them to wherever they go"),
						new Choice(Billy,"Give up and try to have them leave you to die")),
				new Question(
						"You arrive at the British Camp",
						new Choice(Nicholson,"Join the British in their dignified ways to the point\n" +
								"where they think you were British in a past life."),
						new Choice(Winston,"Be rebellious and try to find a way out."),
						new Choice(Macbeth,"Lead the Americans and try to raise their morale.")),
				new Question(
						"Tralfamadorians kidnap you",
						new Choice(Winston,"Go with the flow"),
						//winston is a closet rebel
						new Choice(Macbeth,"Make the Tralfamadorians make you believe\n" +
								"that you are meant for something more than this."),
						new Choice(Nicholson,"Realize that you are going crazy.")),
						//the other two believe in superstition. I could never see Nicholson believing in superstition
				new Question("Paul Lazzaro is about to shoot you",
						new Choice(Winston,"Try to survive."),
						new Choice(Billy, "Accept your fate and just stand there."),
						new Choice(Macbeth,"Dodge the bullet and try to behead him."),
						new Choice(Nicholson,"Try to stop him."))

		);
		Story bridgeOnTheRiverKwai=new Story("Bridge On\nThe River Kwai",Nicholson,
				new Question("You are given the chance to escape",
						new Choice(Winston,"Try to escape."),
						new Choice(Macbeth,"Stay and try to make the best of your situation."),
						new Choice(Billy,"Accept your fate and stay.")),
				new Question("Saito requires you to work",
						new Choice(Billy, "Comply"),
						new Choice(Winston,"Fight Back"),
						new Choice(Macbeth,"Try to maintain your leadership")
						),
				new Question("Saito puts you in the heat box",
						new Choice(Macbeth,"Last a few days but give in once the guy convinces you"),
						new Choice(Nicholson,"Hold out"),
						new Choice(Billy,"Give in immediately"),
						new Choice(Winston,"Try to maintain your resolve but lose it quickly.")),
				new Question("You discover someone trying to blow up your bridge",
						new Choice(Macbeth,"Try to destroy the plot that is trying to harm your creation."),
						new Choice(Winston,"Realize that they are your allies and keep quiet."),
						new Choice(Billy,"Decide that everything happens for a reason\nand it is not your place to interfere.")

		));
		stories=new Story[]{storyMacbeth,slaughterHouseFive,bridgeOnTheRiverKwai};







		setScreen(new MenuScreen());


	}


	@Override
	public void render () {
		Gdx.gl.glClearColor(0.3f, 0.2f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		super.render();
	}

	@Override
	public void dispose () {
	}
}
