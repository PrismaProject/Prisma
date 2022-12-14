package net.libraries.slick.tests;

import net.libraries.slick.AppGameContainer;
import net.libraries.slick.BasicGame;
import net.libraries.slick.Color;
import net.libraries.slick.GameContainer;
import net.libraries.slick.Graphics;
import net.libraries.slick.Image;
import net.libraries.slick.Input;
import net.libraries.slick.SlickException;

/**
 * A test for image flashes
 *
 * @author kevin
 */
public class FlashTest extends BasicGame {
	/** The TGA image loaded */
	private Image image;
	/** True if the image is rendered flashed */
	private boolean flash;
	/** The container for the test */
	private GameContainer container;
	
	/**
	 * Create a new image rendering test
	 */
	public FlashTest() {
		super("Flash Test");
	}
	
	/**
	 * @see BasicGame#init(GameContainer)
	 */
	public void init(GameContainer container) throws SlickException {
		this.container = container;
		
		image = new Image("testdata/logo.tga");
	}

	/**
	 * @see BasicGame#render(GameContainer, Graphics)
	 */
	public void render(GameContainer container, Graphics g) {
		g.drawString("Press space to toggle",10,50);
		if (flash) {
			image.draw(100,100);
		} else {
			image.drawFlash(100,100,image.getWidth(), image.getHeight(), new Color(1,0,1f,1f));
		}
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
			AppGameContainer container = new AppGameContainer(new FlashTest());
			container.setDisplayMode(800,600,false);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see BasicGame#keyPressed(int, char)
	 */
	public void keyPressed(int key, char c) {
		if (key == Input.KEY_SPACE) {
			flash = !flash;
		}
		if (key == Input.KEY_ESCAPE) {
			container.exit();
		}
	}
}
