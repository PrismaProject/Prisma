package net.libraries.slick.state.transition;

import net.libraries.slick.GameContainer;
import net.libraries.slick.Graphics;
import net.libraries.slick.SlickException;
import net.libraries.slick.state.GameState;
import net.libraries.slick.state.StateBasedGame;

/**
 * A transition that has no effect and instantly finishes. Used as a utility for the people
 * not using transitions
 *
 * @author kevin
 */
public class EmptyTransition implements Transition {

	/**
	 * @see Transition#isComplete()
	 */
	public boolean isComplete() {
		return true;
	}


	/**
	 * @see Transition#postRender(StateBasedGame, GameContainer, Graphics)
	 */
	public void postRender(StateBasedGame game, GameContainer container, Graphics g) throws SlickException {
		// no op
	}

	/**
	 * @see Transition#preRender(StateBasedGame, GameContainer, Graphics)
	 */
	public void preRender(StateBasedGame game, GameContainer container, Graphics g) throws SlickException {
		// no op
	}

	/**
	 * @see Transition#update(StateBasedGame, GameContainer, int)
	 */
	public void update(StateBasedGame game, GameContainer container, int delta) throws SlickException {
		// no op
	}


	public void init(GameState firstState, GameState secondState) {
		// TODO Auto-generated method stub
		
	}
}
