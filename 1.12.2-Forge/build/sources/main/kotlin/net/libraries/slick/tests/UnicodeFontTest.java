
package net.libraries.slick.tests;

import java.io.IOException;

import net.libraries.slick.*;
import net.libraries.slick.font.effects.ColorEffect;
import net.libraries.slick.AppGameContainer;
import net.libraries.slick.BasicGame;
import net.libraries.slick.Color;
import net.libraries.slick.GameContainer;
import net.libraries.slick.Graphics;
import net.libraries.slick.Input;
import net.libraries.slick.SlickException;
import net.libraries.slick.UnicodeFont;

/**
 * A simple test of the unicode font functionality provided
 * 
 * @author Nathan Sweet <misc@n4te.com>
 */
public class UnicodeFontTest extends BasicGame {
	/** The font we're going to display in the test */
	private UnicodeFont unicodeFont;

	/**
	 * Create the simple font test
	 */
	public UnicodeFontTest() {
		super("Font Test");
	}

	/**
	 * @see BasicGame#init(GameContainer)
	 */
	public void init(GameContainer container) throws SlickException {
		container.setShowFPS(false);

		// unicodeFont = new UnicodeFont(Font.decode("Arial Unicode MS"), 25, false, false);
//		unicodeFont.setPaddingBottom(10);
//		unicodeFont.setPaddingRight(10);
//		unicodeFont.setPaddingAdvanceX(-10);
//		unicodeFont.getEffects().add(new ShadowEffect(java.awt.Color.black, 5, 5, 0.5f));
		unicodeFont.getEffects().add(new ColorEffect(java.awt.Color.white));

		// unicodeFont = new UnicodeFont("Arial", 25, false, false);
		// unicodeFont = new UnicodeFont("Everson Mono", 44, false, false);

		// font.addGlyphs(0, 255);
		// font.addGlyphs("~!@#$%^&*()");

		container.getGraphics().setBackground(Color.darkGray);
	}

	/**
	 * @see Game#render(GameContainer, Graphics)
	 */
	public void render(GameContainer container, Graphics g) {
		g.setColor(Color.white);

		String text = "This is UnicodeFont!\nIt rockz. Kerning: T,";
		unicodeFont.drawString(10, 33, text);
		// unicodeFont.drawString(10, 33, text, Color.red, 8, 19);
		g.setColor(Color.red);
		g.drawRect(10, 33, unicodeFont.getWidth(text), unicodeFont.getLineHeight());
		g.setColor(Color.blue);
		int yOffset = unicodeFont.getYOffset(text);
		g.drawRect(10, 33 + yOffset, unicodeFont.getWidth(text), unicodeFont.getHeight(text) - yOffset);

		// font.drawString(10, 73, "\u6880\u6881\u6882 (...) \u6883\u6884\u6885\u6886\u6887 hi?");

		unicodeFont.addGlyphs("~!@!#!#$%___--");
		// Cypriot Syllabary glyphs (Everson Mono font): \uD802\uDC02\uD802\uDC03\uD802\uDC12 == 0x10802, 0x10803, s0x10812
		// g.drawLine(0, container.getHeight() - 512, container.getWidth(), container.getHeight() - 512);
	}

	/**
	 * @see BasicGame#update(GameContainer, int)
	 */
	public void update (GameContainer container, int delta) throws SlickException {
		unicodeFont.loadGlyphs(1);
	}

	/**
	 * Entry point to our simple test
	 * 
	 * @param args The arguments supplied to the test
	 * @throws SlickException Indicates a failure loading or processing resources 
	 * @throws IOException Indicates a failure loading the font
	 */
	public static void main(String[] args) throws SlickException, IOException {
		Input.disableControllers();
		AppGameContainer container = new AppGameContainer(new UnicodeFontTest());
		container.setDisplayMode(512, 600, false);
		container.setTargetFrameRate(20);
		container.start();
	}
}
