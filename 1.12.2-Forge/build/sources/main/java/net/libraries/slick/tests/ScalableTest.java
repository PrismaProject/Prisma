package net.libraries.slick.tests;

import net.libraries.slick.*;
import net.libraries.slick.AppGameContainer;
import net.libraries.slick.BasicGame;
import net.libraries.slick.Color;
import net.libraries.slick.GameContainer;
import net.libraries.slick.Graphics;
import net.libraries.slick.ScalableGame;
import net.libraries.slick.SlickException;

/**
 * A test for a scalable game
 *
 * @author kevin
 */
public class ScalableTest extends BasicGame {

	/**
	 * Simple test
	 */
	public ScalableTest() {
		super("Scalable Test For Widescreen");
	}
	
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
		g.setColor(new Color(0.4f,0.6f,0.8f));
		g.fillRect(0,0, 1024,568);
		g.setColor(Color.white);
		g.drawRect(5,5, 1024-10,568-10);
		
		g.setColor(Color.white);
		g.drawString(container.getInput().getMouseX()+","+container.getInput().getMouseY(), 10, 400);
		g.setColor(Color.red);
		g.fillOval(container.getInput().getMouseX()-10,container.getInput().getMouseY()-10,20,20);
	}
	
	/**
	 * Entry point to our test
	 * 
	 * @param argv The arguments to pass into the test
	 */
	public static void main(String[] argv) {
		// normal res
//		try {
//			AppGameContainer container = new AppGameContainer(new ScalableGame(new InputTest(),600,600));
//			container.setDisplayMode(600,600,false);
//			container.start();
//		} catch (SlickException e) {
//			e.printStackTrace();
//		}
		// smaller
//		try {
//			AppGameContainer container = new AppGameContainer(new ScalableGame(new InputTest(),600,600));
//			container.setDisplayMode(300,300,false);
//			container.start();
//		} catch (SlickException e) {
//			e.printStackTrace();
//		}
//		// bigger
//		try {
//			AppGameContainer container = new AppGameContainer(new ScalableGame(new InputTest(),600,600,true));
//			container.setDisplayMode(800,800,false);
//			container.start();
//		} catch (SlickException e) {
//			e.printStackTrace();
//		}

		// maintain aspect ratio
		try {
			ScalableGame game = new ScalableGame(new ScalableTest(),1024,568,true) {

				protected void renderOverlay(GameContainer container, Graphics g) {
					g.setColor(Color.white);
					g.drawString("Outside The Game", 350, 10);
					g.drawString(container.getInput().getMouseX()+","+container.getInput().getMouseY(), 400, 20);
				}
				
			};
			
			AppGameContainer container = new AppGameContainer(game);
			container.setDisplayMode(800,600,false);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
