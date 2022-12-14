package net.libraries.slick.tests;

import net.libraries.slick.particles.ParticleSystem;
import net.libraries.slick.particles.effects.FireEmitter;
import net.libraries.slick.AppGameContainer;
import net.libraries.slick.BasicGame;
import net.libraries.slick.GameContainer;
import net.libraries.slick.Graphics;
import net.libraries.slick.Image;
import net.libraries.slick.Input;
import net.libraries.slick.SlickException;

/**
 * A particle test using built in effects
 *
 * @author kevin
 */
public class ParticleTest extends BasicGame {
	/** The particle system running everything */
	private ParticleSystem system;
	/** The particle blending mode */
	private int mode = ParticleSystem.BLEND_COMBINE;
	
	/**
	 * Create a new test of graphics context rendering
	 */
	public ParticleTest() {
		super("Particle Test");
	}
	
	/**
	 * @see BasicGame#init(GameContainer)
	 */
	public void init(GameContainer container) throws SlickException {
		Image image = new Image("testdata/particle.tga", true);
		system = new ParticleSystem(image);
		
		system.addEmitter(new FireEmitter(400,300,45));
		system.addEmitter(new FireEmitter(200,300,60));
		system.addEmitter(new FireEmitter(600,300,30));
		
		//system.setUsePoints(true);
	}

	/**
	 * @see BasicGame#render(GameContainer, Graphics)
	 */
	public void render(GameContainer container, Graphics g) {
		for (int i=0;i<100;i++) {
			g.translate(1,1);
			system.render();
		}
		g.resetTransform();
		g.drawString("Press space to toggle blending mode", 200, 500);
		g.drawString("Particle Count: "+(system.getParticleCount()*100), 200, 520);
	}

	/**
	 * @see BasicGame#update(GameContainer, int)
	 */
	public void update(GameContainer container, int delta) {
		system.update(delta);
	}

	/**
	 * @see BasicGame#keyPressed(int, char)
	 */
	public void keyPressed(int key, char c) {
		if (key == Input.KEY_ESCAPE) {
			net.minecraftforge.fml.common.FMLCommonHandler.instance().exitJava(0, true);
		}
		if (key == Input.KEY_SPACE) {
			mode = ParticleSystem.BLEND_ADDITIVE == mode ? ParticleSystem.BLEND_COMBINE : ParticleSystem.BLEND_ADDITIVE;
			system.setBlendingMode(mode);
		}
	}
	
	/**
	 * Entry point to our test
	 * 
	 * @param argv The arguments passed to the test
	 */
	public static void main(String[] argv) {
		try {
			AppGameContainer container = new AppGameContainer(new ParticleTest());
			container.setDisplayMode(800,600,false);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
