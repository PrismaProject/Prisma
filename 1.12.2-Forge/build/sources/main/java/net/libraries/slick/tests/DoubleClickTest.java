package net.libraries.slick.tests;

import net.libraries.slick.*;
import net.libraries.slick.AppGameContainer;
import net.libraries.slick.BasicGame;
import net.libraries.slick.GameContainer;
import net.libraries.slick.Graphics;
import net.libraries.slick.SlickException;

/**
 * The double click testing
 * 
 * @author kevin
 */
public class DoubleClickTest extends BasicGame {

	/**
	 * Create the test game
	 */
	public DoubleClickTest() {
		super("Double Click Test");
	}

	/** The test message to display */
	private String message = "Click or Double Click";
	
	/**
	 * @see BasicGame#init(GameContainer)
	 */
	public void init(GameContainer container) throws SlickException {
	}

	/**
	 * @see BasicGame#update(GameContainer, int)
	 */
	public void update(GameContainer container, int delta) throws SlickException {
	}

	/**
	 * @see Game#render(GameContainer, Graphics)
	 */
	public void render(GameContainer container, Graphics g) throws SlickException {
		g.drawString(message, 100, 100);
	}
	
	/**
	 * Entry point to our test
	 * 
	 * @param argv The arguments to pass into the test, not used here
	 */
	public static void main(String[] argv) {
		try {
			AppGameContainer container = new AppGameContainer(new DoubleClickTest());
			container.setDisplayMode(800,600,false);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see BasicGame#mouseClicked(int, int, int, int)
	 */
	public void mouseClicked(int button, int x, int y, int clickCount) {
		if (clickCount == 1) {
			message = "Single Click: "+button+" "+x+","+y;
		}
		if (clickCount == 2) {
			message = "Double Click: "+button+" "+x+","+y;
		}
	}
}
