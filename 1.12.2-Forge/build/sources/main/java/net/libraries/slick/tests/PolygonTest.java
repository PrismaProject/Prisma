package net.libraries.slick.tests;

import net.libraries.slick.*;
import net.libraries.slick.geom.Polygon;
import net.libraries.slick.AppGameContainer;
import net.libraries.slick.BasicGame;
import net.libraries.slick.Color;
import net.libraries.slick.GameContainer;
import net.libraries.slick.Graphics;
import net.libraries.slick.SlickException;

/**
 * A test for polygon collision
 *
 * @author kevin
 */
public class PolygonTest extends BasicGame {
	/** The polygon we're going to test against */
	private Polygon poly;
	/** True if the mouse is in the polygon */
	private boolean in;
	/** The y offset */
	private float y;
	
	/**
	 * Create the test
	 */
	public PolygonTest() {
		super("Polygon Test");
	}

	/**
	 * @see BasicGame#init(GameContainer)
	 */
	public void init(GameContainer container) throws SlickException {
		poly = new Polygon();
		poly.addPoint(300, 100);
		poly.addPoint(320, 200);
		poly.addPoint(350, 210);
		poly.addPoint(280, 250);
		poly.addPoint(300, 200);
		poly.addPoint(240, 150);
		
	}

	/**
	 * @see BasicGame#update(GameContainer, int)
	 */
	public void update(GameContainer container, int delta) throws SlickException {
		in = poly.contains(container.getInput().getMouseX(), container.getInput().getMouseY());
		
		poly.setCenterY(0);
	}

	/**
	 * @see Game#render(GameContainer, Graphics)
	 */
	public void render(GameContainer container, Graphics g) throws SlickException {
		if (in) {
			g.setColor(Color.red);
			g.fill(poly);
		}
		g.setColor(Color.yellow);
		g.fillOval(poly.getCenterX()-3, poly.getCenterY()-3, 6, 6);
		g.setColor(Color.white);
		g.draw(poly);
	}

	/**
	 * Entry point into our test
	 * 
	 * @param argv The arguments passed on the command line
	 */
	public static void main(String[] argv) {
		try {
			AppGameContainer container = new AppGameContainer(new PolygonTest(), 640, 480, false);
			container.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
