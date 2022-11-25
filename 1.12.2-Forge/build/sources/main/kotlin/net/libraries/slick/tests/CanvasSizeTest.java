package net.libraries.slick.tests;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import net.libraries.slick.*;
import net.libraries.slick.util.Log;
import net.libraries.slick.BasicGame;
import net.libraries.slick.CanvasGameContainer;
import net.libraries.slick.GameContainer;
import net.libraries.slick.Graphics;
import net.libraries.slick.SlickException;

/**
 * Quick test to confirm canvas size is reported correctly
 * 
 * @author kevin
 */
public class CanvasSizeTest extends BasicGame {
	
	/**
	 * Create test
	 */
	public CanvasSizeTest() {
		super("Test");
	}

	/**
	 * @see BasicGame#init(GameContainer)
	 */
	public void init(GameContainer container) throws SlickException {
		System.out.println(container.getWidth() + ", " + container.getHeight());
	}

	/**
	 * @see Game#render(GameContainer, Graphics)
	 */
	public void render(GameContainer container, Graphics g)
			throws SlickException {
	}

	/**
	 * @see BasicGame#update(GameContainer, int)
	 */
	public void update(GameContainer container, int delta)
			throws SlickException {
	}

	/**
	 * Entry point to the test
	 * 
	 * @param args The command line arguments passed in (none honoured)
	 */
	public static void main(String[] args) {
		try {
			CanvasGameContainer container = new CanvasGameContainer(
					new CanvasSizeTest());
			container.setSize(640,480);
			Frame frame = new Frame("Test");
			frame.setLayout(new GridLayout(1,2));
			frame.add(container);
			frame.pack();
			frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					net.minecraftforge.fml.common.FMLCommonHandler.instance().exitJava(0, true);
				}
			});
			frame.setVisible(true);
	
			container.start();
		} catch (Exception e) {
			Log.error(e);
		}
	}
}
