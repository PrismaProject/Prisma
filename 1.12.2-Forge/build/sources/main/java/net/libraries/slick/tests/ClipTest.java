package net.libraries.slick.tests;

import net.libraries.slick.*;
import net.libraries.slick.AppGameContainer;
import net.libraries.slick.BasicGame;
import net.libraries.slick.Color;
import net.libraries.slick.GameContainer;
import net.libraries.slick.Graphics;
import net.libraries.slick.Input;
import net.libraries.slick.SlickException;

/**
 * A test to demonstrate world clipping as opposed to screen clipping
 *
 * @author kevin
 */
public class ClipTest extends BasicGame {

	/** The current angle of rotation */
	private float ang = 0;
	/** True if we're showing world clipping */
	private boolean world;
	/** True if we're showing screen clipping */
	private boolean clip;
	
	/**
	 * Create a new tester for the clip plane based clipping
	 */
	public ClipTest() {
		super("Clip Test");
	}
	
	/**
	 * @see BasicGame#init(GameContainer)
	 */
	public void init(GameContainer container) throws SlickException {
	}

	/**
	 * @see BasicGame#update(GameContainer, int)
	 */
	public void update(GameContainer container, int delta)
			throws SlickException {
		ang += delta * 0.01f;
	}

	/**
	 * @see Game#render(GameContainer, Graphics)
	 */
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		g.setColor(Color.white);
		g.drawString("1 - No Clipping", 100, 10);
		g.drawString("2 - Screen Clipping", 100, 30);
		g.drawString("3 - World Clipping", 100, 50);
		
		if (world) {
			g.drawString("WORLD CLIPPING ENABLED", 200, 80);
		} 
		if (clip) {
			g.drawString("SCREEN CLIPPING ENABLED", 200, 80);
		}
		
		g.rotate(400, 400, ang);
		if (world) {
			g.setWorldClip(350,302,100,196);
		}
		if (clip) {
			g.setClip(350,302,100,196);
		}
		g.setColor(Color.red);
		g.fillOval(300,300,200,200);
		g.setColor(Color.blue);
		g.fillRect(390,200,20,400);
		
		g.clearClip();
		g.clearWorldClip();
	}

	/**
	 * @see BasicGame#keyPressed(int, char)
	 */
	public void keyPressed(int key, char c) {
		if (key == Input.KEY_1) {
			world = false;
			clip = false;
		}
		if (key == Input.KEY_2) {
			world = false;
			clip = true;
		}
		if (key == Input.KEY_3) {
			world = true;
			clip = false;
		}
	}
	
	/**
	 * Entry point to our test
	 * 
	 * @param argv The arguments to pass into the test
	 */
	public static void main(String[] argv) {
		try {
			AppGameContainer container = new AppGameContainer(new ClipTest());
			container.setDisplayMode(800,600,false);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
