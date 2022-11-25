package net.libraries.slick.svg.inkscape;

import net.libraries.slick.geom.Transform;
import net.libraries.slick.svg.Diagram;
import net.libraries.slick.svg.Loader;
import net.libraries.slick.svg.ParsingException;
import org.w3c.dom.Element;

/**
 * TODO: Document this class
 *
 * @author kevin
 */
public class GroupProcessor implements ElementProcessor {

	/**
	 * @see ElementProcessor#handles(Element)
	 */
	public boolean handles(Element element) {
		if (element.getNodeName().equals("g")) {
			return true;
		}
		return false;
	}

	/**O
	 * @see ElementProcessor#process(Loader, Element, Diagram, Transform)
	 */
	public void process(Loader loader, Element element, Diagram diagram, Transform t) throws ParsingException {
		Transform transform = Util.getTransform(element);
		transform = new Transform(t, transform);
		
		loader.loadChildren(element, transform);
	}

}
