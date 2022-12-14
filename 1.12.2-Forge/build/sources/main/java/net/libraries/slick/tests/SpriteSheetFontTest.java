package net.libraries.slick.tests;

import net.libraries.slick.*;
import net.libraries.slick.util.Log;
import net.libraries.slick.*;

/**
 * Tests the SpriteSheetFont.
 *
 * @author Onno Scheffers
 */
public class SpriteSheetFontTest extends BasicGame {
   /**
    * The font we're going to use to render
    */
   private Font font;

   /**
    * Create a new test for font rendering
    */
   public SpriteSheetFontTest() {
      super("SpriteSheetFont Test");
   }

   /**
    * @see Game#init(GameContainer)
    */
   public void init(GameContainer container) throws SlickException {
      SpriteSheet sheet = new SpriteSheet("testdata/spriteSheetFont.png", 32, 32);
      font = new SpriteSheetFont(sheet, ' ');
   }

   /**
    * @see BasicGame#render(GameContainer, Graphics)
    */
   public void render(GameContainer container, Graphics g) {
      g.setBackground(Color.gray);
      font.drawString(80, 5, "A FONT EXAMPLE", Color.red);
      font.drawString(100, 50, "A MORE COMPLETE LINE");
   }

   /**
    * @see BasicGame#update(GameContainer,int)
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
      if (key == Input.KEY_SPACE) {
         try {
            container.setDisplayMode(640, 480, false);
         } catch (SlickException e) {
            Log.error(e);
         }
      }
   }

   /**
    * The container we're using
    */
   private static AppGameContainer container;

   /**
    * Entry point to our test
    *
    * @param argv The arguments passed in the test
    */
   public static void main(String[] argv) {
      try {
         container = new AppGameContainer(new SpriteSheetFontTest());
         container.setDisplayMode(800, 600, false);
         container.start();
      } catch (SlickException e) {
         e.printStackTrace();
      }
   }
}
