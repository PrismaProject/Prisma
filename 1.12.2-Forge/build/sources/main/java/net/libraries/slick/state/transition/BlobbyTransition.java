package net.libraries.slick.state.transition;

import java.util.ArrayList;

import net.libraries.slick.Color;
import net.libraries.slick.GameContainer;
import net.libraries.slick.Graphics;
import net.libraries.slick.SlickException;
import net.libraries.slick.opengl.renderer.Renderer;
import net.libraries.slick.opengl.renderer.SGL;
import net.libraries.slick.state.GameState;
import net.libraries.slick.state.StateBasedGame;
import net.libraries.slick.util.MaskUtil;

/**
 * A transition that causes the previous state to rotate and scale down into
 * the new state.
 * 
 * This is an enter transition
 * 
 * @author kevin
 */
public class BlobbyTransition implements Transition {
	/** The renderer to use for all GL operations */
	protected static SGL GL = Renderer.get();
	
	/** The previous state */
	private GameState prev;
	/** True if the state has finished */
	private boolean finish;
	/** The background applied under the previous state if any */
	private Color background;
	/** ArrayList blobs */
	private ArrayList blobs = new ArrayList();
	/** The time it will run for */
	private int timer = 1000;
	/** The number of blobs to create */
	private int blobCount = 10;
	
	/**
	 * Create a new transition
	 */
	public BlobbyTransition() {
		
	}

	/**
	 * Create a new transition
	 * 
	 * @param background The background colour to draw under the previous state
	 */
	public BlobbyTransition(Color background) {
		this.background = background;
	}
	
	/**
	 * @see Transition#init(GameState, GameState)
	 */
	public void init(GameState firstState, GameState secondState) {
		prev = secondState;
	}

	/**
	 * @see Transition#isComplete()
	 */
	public boolean isComplete() {
		return finish;
	}

	/**
	 * @see Transition#postRender(StateBasedGame, GameContainer, Graphics)
	 */
	public void postRender(StateBasedGame game, GameContainer container, Graphics g) throws SlickException {
		MaskUtil.resetMask();
	}

	/**
	 * @see Transition#preRender(StateBasedGame, GameContainer, Graphics)
	 */
	public void preRender(StateBasedGame game, GameContainer container,
			Graphics g) throws SlickException {
		prev.render(container, game, g);
		
		MaskUtil.defineMask();
		for (int i=0;i<blobs.size();i++) {
			((Blob) blobs.get(i)).render(g);
		}
		MaskUtil.finishDefineMask();

		MaskUtil.drawOnMask();
		if (background != null) {
			Color c = g.getColor();
			g.setColor(background);
			g.fillRect(0,0,container.getWidth(),container.getHeight());
			g.setColor(c);
		}
	}

	/**
	 * @see Transition#update(StateBasedGame, GameContainer, int)
	 */
	public void update(StateBasedGame game, GameContainer container, int delta)
			throws SlickException {
		if (blobs.size() == 0) {
			for (int i=0;i<blobCount;i++) {
				blobs.add(new Blob(container));
			}
		}
		
		for (int i=0;i<blobs.size();i++) {
			((Blob) blobs.get(i)).update(delta);
		}
		
		timer -= delta;
		if (timer < 0) {
			finish = true;
		}
	}

	/** 
	 * A blob to show the new state
	 * 
	 * @author kevin
	 */
	private class Blob {
		/** The x coordinate of the centre of this blob */
		private float x;
		/** The y coordinate of the centre of this blob */
		private float y;
		/** The speed at which this blob grows */
		private float growSpeed;
		/** The radius of this blob */
		private float rad;
		
		/**
		 * Create a new blob
		 * 
		 * @param container The container for dimensions
		 */
		public Blob(GameContainer container) {
			x = (float) (Math.random() * container.getWidth());
			y = (float) (Math.random() * container.getWidth());
			growSpeed = (float) (1f + (Math.random() * 1f));
		}
		
		/**
		 * Update the blob 
		 * 
		 * @param delta The change in time in milliseconds
		 */
		public void update(int delta) {
			rad += growSpeed * delta * 0.4f;
		}
		
		/**
		 * Render the blob - i.e. the mask
		 * 
		 * @param g The grphics context on which the mask should be drawn
		 */
		public void render(Graphics g) {
			g.fillOval(x-rad,y-rad,rad*2,rad*2);
		}
	}
}
