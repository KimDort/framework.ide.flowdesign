package framework.editor.flowdesigner.entities.graphicItem.anchor;

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;

public class FixedAnchor extends AbstractConnectionAnchor{
	private Point place;
	
	public FixedAnchor(IFigure figure) {
		super(figure);
	}
	
	@Override
	public Point getLocation(Point point) {
		Rectangle r = getOwner().getBounds();
		int x = r.x + place.x * r.width / 100;
		int y = r.y + place.y * r.height / 100;
		Point p = new PrecisionPoint(x, y);
		getOwner().translateToAbsolute(p);
		
		return p;
	}

	public Point getPlace() {
		return place;
	}

	public void setPlace(Point place) {
		this.place = place;
	}
	
}
