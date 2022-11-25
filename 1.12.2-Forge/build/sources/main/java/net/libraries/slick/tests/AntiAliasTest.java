package net.libraries.slick.tests;

import net.libraries.slick.*;
import net.libraries.slick.AppGameContainer;
import net.libraries.slick.BasicGame;
import net.libraries.slick.Color;
import net.libraries.slick.GameContainer;
import net.libraries.slick.Graphics;
import net.libraries.slick.SlickException;

/**
 * Test to view the effects of antialiasing on cirles
 *
 * @author kevin
 */
public class AntiAliasTest extends BasicGame {

	/**
	 * Create the test
	 */
	public AntiAliasTest() {
		super("AntiAlias Test");
	}

	/**
	 * @see BasicGame#init(GameContainer)
	 */
	public void init(GameContainer container) throws SlickException {
		container.getGraphics().setBackground(Color.green);
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
		g.setAntiAlias(true);
		g.setColor(Color.red);
		g.drawOval(100,100,100,100);
		g.fillOval(300,100,100,100);
		g.setAntiAlias(false);
		g.setColor(Color.red);
		g.drawOval(100,300,100,100);
		g.fillOval(300,300,100,100);
	}

	/**
	 * Entry point to our test
	 * 
	 * @param argv The arguments passed to the test
	 */
	public static void main(String[] argv) {
		try {
			AppGameContainer container = new AppGameContainer(new AntiAliasTest());
			container.setDisplayMode(800,600,false);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
