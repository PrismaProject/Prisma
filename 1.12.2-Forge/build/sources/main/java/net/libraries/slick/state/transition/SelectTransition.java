package net.libraries.slick.state.transition;

import net.libraries.slick.Color;
import net.libraries.slick.GameContainer;
import net.libraries.slick.Graphics;
import net.libraries.slick.SlickException;
import net.libraries.slick.opengl.renderer.Renderer;
import net.libraries.slick.opengl.renderer.SGL;
import net.libraries.slick.state.GameState;
import net.libraries.slick.state.StateBasedGame;

/**
 * A transition that moves to the next as though it was selected by some background menu. Note
 * this transition is provided as an example more than intended for use. The values contained 
 * are designed for 800x600 resolution.
 * 
 * This is an enter transition
 * 
 * @author kevin
 */
public class SelectTransition implements Transition {
	/** The renderer to use for all GL operations */
	protected static SGL GL = Renderer.get();
	
	/** The previous state */
	private GameState prev;
	/** True if the state has finished */
	private boolean finish;
	/** The background applied under the previous state if any */
	private Color background;

	/** The scale of the first state */
	private float scale1 = 1;
	/** The x coordinate to render the first state */
	private float xp1 = 0;
	/** The y coordinate to render the first state */
	private float yp1 = 0;
	/** The scale of the second state */
	private float scale2 = 0.4f;
	/** The x coordinate to render the second state */
	private float xp2 = 0;
	/** The y coordinate to render the second state */
	private float yp2 = 0;
	/** True if this transition has been initialised */
	private boolean init = false;
	
	/** True if the move back of the first state is complete */
	private boolean moveBackDone = false;
	/** The length of the pause between selection */
	private int pause = 300;
	
	/**
	 * Create a new transition
	 */
	public SelectTransition() {
		
	}

	/**
	 * Create a new transition
	 * 
	 * @param background The background colour to draw under the previous state
	 */
	public SelectTransition(Color background) {
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
		g.resetTransform();
		
		if (!moveBackDone) {
			g.translate(xp1,yp1);
			g.scale(scale1, scale1);
			g.setClip((int) xp1,(int) yp1,(int) (scale1*container.getWidth()),(int) (scale1*container.getHeight()));
			prev.render(container, game, g);
			g.resetTransform();
			g.clearClip();
		}
	}

	/**
	 * @see Transition#preRender(StateBasedGame, GameContainer, Graphics)
	 */
	public void preRender(StateBasedGame game, GameContainer container,
			Graphics g) throws SlickException {
		if (moveBackDone) {
			g.translate(xp1,yp1);
			g.scale(scale1, scale1);
			g.setClip((int) xp1,(int) yp1,(int) (scale1*container.getWidth()),(int) (scale1*container.getHeight()));
			prev.render(container, game, g);
			g.resetTransform();
			g.clearClip();
		}
		
		g.translate(xp2,yp2);
		g.scale(scale2, scale2);
		g.setClip((int) xp2,(int) yp2,(int) (scale2*container.getWidth()),(int) (scale2*container.getHeight()));
	}

	/**
	 * @see Transition#update(StateBasedGame, GameContainer, int)
	 */
	public void update(StateBasedGame game, GameContainer container, int delta)
			throws SlickException {
		if (!init) {
			init = true;
			xp2 = (container.getWidth()/2)+50;
			yp2 = (container.getHeight()/4);
		}
		
		if (!moveBackDone) {
			if (scale1 > 0.4f) {
				scale1 -= delta * 0.002f;
				if (scale1 <= 0.4f) {
					scale1 = 0.4f;
				}
				xp1 += delta * 0.3f;
				if (xp1 > 50) {
					xp1 = 50;
				}
				yp1 += delta * 0.5f;
				if (yp1 > (container.getHeight()/4)) {
					yp1 = (container.getHeight()/4);
				}
			} else {
				moveBackDone = true;
			}
		} else {
			pause -= delta;
			if (pause > 0) {
				return;
			}
			if (scale2 < 1) {
				scale2 += delta * 0.002f;
				if (scale2 >= 1) {
					scale2 = 1f;
				}
				xp2 -= delta * 1.5f;
				if (xp2 < 0) {
					xp2 = 0;
				}
				yp2 -= delta * 0.5f;
				if (yp2 < 0) {
					yp2 = 0;
				}
			} else {
				finish = true;
			}
		}
	}
}
