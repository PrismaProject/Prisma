package net.libraries.slick.tests;

import net.libraries.slick.gui.AbstractComponent;
import net.libraries.slick.gui.ComponentListener;
import net.libraries.slick.gui.MouseOverArea;
import net.libraries.slick.gui.TextField;
import net.libraries.slick.util.Log;
import net.libraries.slick.AngelCodeFont;
import net.libraries.slick.AppGameContainer;
import net.libraries.slick.BasicGame;
import net.libraries.slick.Color;
import net.libraries.slick.Font;
import net.libraries.slick.GameContainer;
import net.libraries.slick.Graphics;
import net.libraries.slick.Image;
import net.libraries.slick.Input;
import net.libraries.slick.SlickException;

/**
 * A test for the GUI components available in Slick. Very simple stuff
 *
 * @author kevin
 */
public class GUITest extends BasicGame implements ComponentListener {
	/** The image being rendered */
	private Image image;
	/** The areas defined */
	private MouseOverArea[] areas = new MouseOverArea[4];
	/** The game container */
	private GameContainer container;
	/** The message to display */
	private String message = "Demo Menu System with stock images";
	/** The text field */
	private TextField field;
	/** The text field */
	private TextField field2;
	/** The background image */
	private Image background;
	/** The font used to render */
	private Font font;
	/** The container */
	private AppGameContainer app;
	
	/**
	 * Create a new test of GUI  rendering
	 */
	public GUITest() {
		super("GUI Test");
	}
	
	/**
	 * @see BasicGame#init(GameContainer)
	 */
	public void init(GameContainer container) throws SlickException {
		if (container instanceof AppGameContainer) {
			app = (AppGameContainer) container;
			app.setIcon("testdata/icon.tga");
		}
		
		font = new AngelCodeFont("testdata/demo2.fnt","testdata/demo2_00.tga");
		field = new TextField(container, font, 150,20,500,35, new ComponentListener() {
			public void componentActivated(AbstractComponent source) {
				message = "Entered1: "+field.getText();
				field2.setFocus(true);
			}
		});
		field2 = new TextField(container, font, 150,70,500,35,new ComponentListener() {
			public void componentActivated(AbstractComponent source) {
				message = "Entered2: "+field2.getText();
				field.setFocus(true);
			}
		});
		field2.setBorderColor(Color.red);
		
		this.container = container;
		
		image = new Image("testdata/logo.tga");
		background = new Image("testdata/dungeontiles.gif");
		container.setMouseCursor("testdata/cursor.tga", 0, 0);
		
		for (int i=0;i<4;i++) {
			areas[i] = new MouseOverArea(container, image, 300, 100 + (i*100), 200, 90, this);
			areas[i].setNormalColor(new Color(1,1,1,0.8f));
			areas[i].setMouseOverColor(new Color(1,1,1,0.9f));
		}
	}

	/**
	 * @see BasicGame#render(GameContainer, Graphics)
	 */
	public void render(GameContainer container, Graphics g) {
		background.draw(0, 0, 800, 500);
		
		for (int i=0;i<4;i++) {
			areas[i].render(container, g);
		}
		field.render(container, g);
		field2.render(container, g);
		
		g.setFont(font);
		g.drawString(message, 200, 550);
	}

	/**
	 * @see BasicGame#update(GameContainer, int)
	 */
	public void update(GameContainer container, int delta) {
	}

	/**
	 * @see BasicGame#keyPressed(int, char)
	 */
	public void keyPressed(int key, char c) {
		if (key == Input.KEY_ESCAPE) {
			net.minecraftforge.fml.common.FMLCommonHandler.instance().exitJava(0, true);
		}
		if (key == Input.KEY_F2) {
			app.setDefaultMouseCursor();
		}
		if (key == Input.KEY_F1) {
			if (app != null) {
				try {
					app.setDisplayMode(640,480,false);		
				} catch (SlickException e) {
					Log.error(e);
				}
			}
		}
	}
	
	/**
	 * Entry point to our test
	 * 
	 * @param argv The arguments passed to the test
	 */
	public static void main(String[] argv) {
		try {
			AppGameContainer container = new AppGameContainer(new GUITest());
			container.setDisplayMode(800,600,false);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see ComponentListener#componentActivated(AbstractComponent)
	 */
	public void componentActivated(AbstractComponent source) {
		System.out.println("ACTIVL : "+source);
		for (int i=0;i<4;i++) {
			if (source == areas[i]) {
				message = "Option "+(i+1)+" pressed!";
			}
		}
		if (source == field2) {
		}
	}
}
