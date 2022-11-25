package net.libraries.slick.tests;
	
import net.libraries.slick.*;
import net.libraries.slick.AngelCodeFont;
import net.libraries.slick.AppGameContainer;
import net.libraries.slick.BasicGame;
import net.libraries.slick.Font;
import net.libraries.slick.GameContainer;
import net.libraries.slick.Graphics;
import net.libraries.slick.Image;
import net.libraries.slick.Input;
import net.libraries.slick.SlickException;

/**
 * A test of the font rendering capabilities
 *
 * @author kevin
 */
public class PureFontTest extends BasicGame {
	/** The font we're going to use to render */
	private Font font;
	/** The image */
	private Image image;
	
	/**
	 * Create a new test for font rendering
	 */
	public PureFontTest() {
		super("Hiero Font Test");
	}
	
	/**
	 * @see Game#init(GameContainer)
	 */
	public void init(GameContainer container) throws SlickException {
		image = new Image("testdata/sky.jpg");
		font = new AngelCodeFont("testdata/hiero.fnt","testdata/hiero.png");
	}

	/**
	 * @see BasicGame#render(GameContainer, Graphics)
	 */
	public void render(GameContainer container, Graphics g) {
		image.draw(0,0,800,600);
		font.drawString(100, 32, "On top of old smokey, all");
		font.drawString(100, 80, "covered with sand..");
	}

	/**
	 * @see BasicGame#update(GameContainer, int)
	 */
	public void update(GameContainer container, int delta) throws SlickException {
	}
	
	/**
	 * @see BasicGame#keyPressed(int, char)
	 */
	public void keyPressed(int key, char c) {
		if (key == Input.KEY_ESCAPE) {
			net.minecraftforge.fml.common.FMLCommonHandler.instance().exitJava(0, true);
		}
	}
	
	/** The container we're using */
	private static AppGameContainer container;
	
	/**
	 * Entry point to our test
	 * 
	 * @param argv The arguments passed in the test
	 */
	public static void main(String[] argv) {
		try {
			container = new AppGameContainer(new PureFontTest());
			container.setDisplayMode(800,600,false);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
