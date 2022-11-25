package net.libraries.slick.tests;

import net.libraries.slick.AppGameContainer;
import net.libraries.slick.BasicGame;
import net.libraries.slick.GameContainer;
import net.libraries.slick.Graphics;
import net.libraries.slick.Image;
import net.libraries.slick.SlickException;

/**
 * A test for basic image rendering
 *
 * @author kevin
 */
public class ImageMemTest extends BasicGame {
	
	/**
	 * Create a new image rendering test
	 */
	public ImageMemTest() {
		super("Image Memory Test");
	}
	
	/**
	 * @see BasicGame#init(GameContainer)
	 */
	public void init(GameContainer container) throws SlickException {
		try {
            Image img = new Image(2400, 2400);
            img.getGraphics();
            img.destroy();
            img = new Image(2400, 2400);
            img.getGraphics();
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
	}

	/**
	 * @see BasicGame#render(GameContainer, Graphics)
	 */
	public void render(GameContainer container, Graphics g) {
	}

	/**
	 * @see BasicGame#update(GameContainer, int)
	 */
	public void update(GameContainer container, int delta) {
	}

	/**
	 * Entry point to our test
	 * 
	 * @param argv The arguments to pass into the test
	 */
	public static void main(String[] argv) {
		try {
			AppGameContainer container = new AppGameContainer(new ImageMemTest());
			container.setDisplayMode(800,600,false);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
