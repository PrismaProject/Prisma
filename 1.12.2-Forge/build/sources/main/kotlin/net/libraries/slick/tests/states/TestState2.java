package net.libraries.slick.tests.states;

import net.libraries.slick.state.transition.FadeInTransition;
import net.libraries.slick.state.transition.FadeOutTransition;
import net.libraries.slick.AngelCodeFont;
import net.libraries.slick.Color;
import net.libraries.slick.Font;
import net.libraries.slick.GameContainer;
import net.libraries.slick.Graphics;
import net.libraries.slick.Image;
import net.libraries.slick.Input;
import net.libraries.slick.SlickException;
import net.libraries.slick.state.BasicGameState;
import net.libraries.slick.state.StateBasedGame;

/**
 * A simple test state to display an image and rotate it
 *
 * @author kevin
 */
public class TestState2 extends BasicGameState {
	/** The ID given to this state */
	public static final int ID = 2;
	/** The font to write the message with */
	private Font font;
	/** The image to be display */
	private Image image;
	/** The angle we'll rotate by */
	private float ang;
	/** The game holding this state */
	private StateBasedGame game;
	
	/**
	 * @see BasicGameState#getID()
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @see BasicGameState#init(GameContainer, StateBasedGame)
	 */
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.game = game;
		font = new AngelCodeFont("testdata/demo2.fnt","testdata/demo2_00.tga");
		image = new Image("testdata/logo.tga");
	}

	/**
	 * @see BasicGameState#render(GameContainer, StateBasedGame, Graphics)
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		g.setFont(font);
		g.setColor(Color.green);
		g.drawString("This is State 2", 200, 50);
		
		g.rotate(400,300,ang);
		g.drawImage(image,400-(image.getWidth()/2),300-(image.getHeight()/2));
	}

	/**
	 * @see BasicGameState#update(GameContainer, StateBasedGame, int)
	 */
	public void update(GameContainer container, StateBasedGame game, int delta) {
		ang += delta * 0.1f;
	}
	
	/**
	 * @see BasicGameState#keyReleased(int, char)
	 */
	public void keyReleased(int key, char c) {
		if (key == Input.KEY_1) {
			game.enterState(TestState1.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
		}
		if (key == Input.KEY_3) {
			game.enterState(TestState3.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
		}
	}
}
