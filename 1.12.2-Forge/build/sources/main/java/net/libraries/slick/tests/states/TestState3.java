package net.libraries.slick.tests.states;

import net.libraries.slick.state.transition.FadeInTransition;
import net.libraries.slick.state.transition.FadeOutTransition;
import net.libraries.slick.AngelCodeFont;
import net.libraries.slick.Color;
import net.libraries.slick.Font;
import net.libraries.slick.GameContainer;
import net.libraries.slick.Graphics;
import net.libraries.slick.Input;
import net.libraries.slick.SlickException;
import net.libraries.slick.state.BasicGameState;
import net.libraries.slick.state.StateBasedGame;

/**
 * A simple test state to display an image and rotate it
 *
 * @author kevin
 */
public class TestState3 extends BasicGameState {
	/** The ID given to this state */
	public static final int ID = 3;
	/** The font to write the message with */
	private Font font;
	/** The menu options */
	private String[] options = new String[] {"Start Game","Credits","Highscores","Instructions","Exit"};
	/** The index of the selected option */
	private int selected;
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
		font = new AngelCodeFont("testdata/demo2.fnt","testdata/demo2_00.tga");
		this.game = game;
	}

	/**
	 * @see BasicGameState#render(GameContainer, StateBasedGame, Graphics)
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		g.setFont(font);
		g.setColor(Color.blue);
		g.drawString("This is State 3", 200, 50);
		g.setColor(Color.white);
		
		for (int i=0;i<options.length;i++) {
			g.drawString(options[i], 400 - (font.getWidth(options[i])/2), 200+(i*50));
			if (selected == i) {
				g.drawRect(200,190+(i*50),400,50);
			}
		}
	}

	/**
	 * @see BasicGameState#update(GameContainer, StateBasedGame, int)
	 */
	public void update(GameContainer container, StateBasedGame game, int delta) {
	}

	/**
	 * @see BasicGameState#keyReleased(int, char)
	 */
	public void keyReleased(int key, char c) {
		if (key == Input.KEY_DOWN) {
			selected++;
			if (selected >= options.length) {
				selected = 0;
			}
		}
		if (key == Input.KEY_UP) {
			selected--;
			if (selected < 0) {
				selected = options.length - 1;
			}
		}
		if (key == Input.KEY_1) {
			game.enterState(TestState1.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
		}
		if (key == Input.KEY_2) {
			game.enterState(TestState2.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
		}
	}

}
