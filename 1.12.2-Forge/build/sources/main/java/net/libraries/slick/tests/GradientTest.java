package net.libraries.slick.tests;

import net.libraries.slick.fills.GradientFill;
import net.libraries.slick.geom.Polygon;
import net.libraries.slick.geom.Rectangle;
import net.libraries.slick.geom.RoundedRectangle;
import net.libraries.slick.opengl.renderer.Renderer;
import net.libraries.slick.AppGameContainer;
import net.libraries.slick.BasicGame;
import net.libraries.slick.Color;
import net.libraries.slick.GameContainer;
import net.libraries.slick.Graphics;
import net.libraries.slick.Input;
import net.libraries.slick.SlickException;

/**
 * A test for gradient fill on polygons
 *
 * @author kevin
 */
public class GradientTest extends BasicGame {
	/** The container for the test */
	private GameContainer container;
	/** The paint we'll use */
	private GradientFill gradient;
	/** The paint we'll use */
	private GradientFill gradient2;
	/** The paint we'll use */
	private GradientFill gradient4;
	/** The shape to render */
	private Rectangle rect;
	/** The shape to render */
	private Rectangle center;
	/** The shape to render */
	private RoundedRectangle round;
	/** The shape to render */
	private RoundedRectangle round2;
	/** The shape to render */
	private Polygon poly;
	/** The angle of rotation */
	private float ang;
	
	/**
	 * Create a new gradient test
	 */
	public GradientTest() {
		super("Gradient Test");
	}
	
	/**
	 * @see BasicGame#init(GameContainer)
	 */
	public void init(GameContainer container) throws SlickException {
		this.container = container;
	
		rect = new Rectangle(400,100,200,150);
		round = new RoundedRectangle(150,100,200,150,50);
		round2 = new RoundedRectangle(150,300,200,150,50);
		center = new Rectangle(350,250,100,100);
		
		poly = new Polygon();
		poly.addPoint(400,350);
		poly.addPoint(550,320);
		poly.addPoint(600,380);
		poly.addPoint(620,450);
		poly.addPoint(500,450);
		
		gradient = new GradientFill(0,-75, Color.red,0,75,Color.yellow,true);
		gradient2 = new GradientFill(0,-75,Color.blue,0,75,Color.white,true);
		gradient4 = new GradientFill(-50,-40,Color.green,50,40,Color.cyan,true);
	}

	/**
	 * @see BasicGame#render(GameContainer, Graphics)
	 */
	public void render(GameContainer container, Graphics g) {
		
		g.rotate(400, 300, ang);
		g.fill(rect, gradient);
		g.fill(round, gradient);
		g.fill(poly, gradient2);
		g.fill(center, gradient4);

		g.setAntiAlias(true);
		g.setLineWidth(10);
		g.draw(round2, gradient2);
		g.setLineWidth(2);
		g.draw(poly, gradient);
		g.setAntiAlias(false);
		
		g.fill(center, gradient4);
		g.setAntiAlias(true);
		g.setColor(Color.black);
		g.draw(center);
		g.setAntiAlias(false);
	}

	/**
	 * @see BasicGame#update(GameContainer, int)
	 */
	public void update(GameContainer container, int delta) {
		ang += (delta * 0.01f);
	}

	/**
	 * Entry point to our test
	 * 
	 * @param argv The arguments to pass into the test
	 */
	public static void main(String[] argv) {
		try {
			Renderer.setRenderer(Renderer.VERTEX_ARRAY_RENDERER);
			
			AppGameContainer container = new AppGameContainer(new GradientTest());
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
		if (key == Input.KEY_ESCAPE) {
			container.exit();
		}
	}
}
