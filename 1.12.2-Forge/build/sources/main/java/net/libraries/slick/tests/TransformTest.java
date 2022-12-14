package net.libraries.slick.tests;

import net.libraries.slick.AppGameContainer;
import net.libraries.slick.BasicGame;
import net.libraries.slick.Color;
import net.libraries.slick.GameContainer;
import net.libraries.slick.Graphics;
import net.libraries.slick.Input;
import net.libraries.slick.SlickException;

/**
 * A test for transforming the graphics context
 *
 * @author kevin
 */
public class TransformTest extends BasicGame {
	/** The current scale applied to the graphics context */
	private float scale = 1;
	/** True if we should be scaling up */
	private boolean scaleUp;
	/** True if we should be scaling down */
	private boolean scaleDown;
	
	/**
	 * Create a new test of graphics context rendering
	 */
	public TransformTest() {
		super("Transform Test");
	}
	
	/**
	 * @see BasicGame#init(GameContainer)
	 */
	public void init(GameContainer container) throws SlickException {
		container.setTargetFrameRate(100);
	}

	/**
	 * @see BasicGame#render(GameContainer, Graphics)
	 */
	public void render(GameContainer contiainer, Graphics g) {
		g.translate(320,240);
		g.scale(scale, scale);

		g.setColor(Color.red);
		for (int x=0;x<10;x++) {
			for (int y=0;y<10;y++) {
				g.fillRect(-500+(x*100), -500+(y*100), 80, 80);
			}
		}
		
		g.setColor(new Color(1,1,1,0.5f));
		g.fillRect(-320,-240,640,480);
		g.setColor(Color.white);
		g.drawRect(-320,-240,640,480);
	}

	/**
	 * @see BasicGame#update(GameContainer, int)
	 */
	public void update(GameContainer container, int delta) {
		if (scaleUp) {
			scale += delta * 0.001f;
		}
		if (scaleDown) {
			scale -= delta * 0.001f;
		}
	}

	/**
	 * @see BasicGame#keyPressed(int, char)
	 */
	public void keyPressed(int key, char c) {
		if (key == Input.KEY_ESCAPE) {
			net.minecraftforge.fml.common.FMLCommonHandler.instance().exitJava(0, true);
		}
		if (key == Input.KEY_Q) {
			scaleUp = true;
		}
		if (key == Input.KEY_A) {
			scaleDown = true;
		}
	}

	/**
	 * @see BasicGame#keyReleased(int, char)
	 */
	public void keyReleased(int key, char c) {
		if (key == Input.KEY_Q) {
			scaleUp = false;
		}
		if (key == Input.KEY_A) {
			scaleDown = false;
		}
	}
	
	/**
	 * Entry point to our test
	 * 
	 * @param argv The arguments passed to the test
	 */
	public static void main(String[] argv) {
		try {
			AppGameContainer container = new AppGameContainer(new TransformTest());
			container.setDisplayMode(640,480,false);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
