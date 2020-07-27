package framework.editor.flowdesigner.editors.parts;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ManhattanConnectionRouter;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.swt.SWT;

import framework.editor.flowdesigner.entities.Constants;
import framework.editor.flowdesigner.entities.graphicItem.anchor.NodeAnchor;

public class NodeAnchorPart extends AbstractConnectionEditPart{

	@Override
	protected IFigure createFigure() {
		PolylineConnection connection = new PolylineConnection();
		connection.setConnectionRouter(new ManhattanConnectionRouter());
		connection.setLineStyle(SWT.LINE_DOT);
		connection.setBackgroundColor(Constants.DEFAULT_TEXT_COLOR);
		return connection;
	}

	@Override
	protected void refreshVisuals() {
		NodeAnchor anchor = (NodeAnchor) getModel();
		Connection connection = getConnectionFigure();
		connection.setSourceAnchor(anchor.getOutAnchor());
		connection.setTargetAnchor(anchor.getInAnchor());
		
		super.refreshVisuals();
	}
	
	@Override
	protected void createEditPolicies() {
		
	}

	@Override
	protected ConnectionAnchor getSourceConnectionAnchor() {
		return super.getSourceConnectionAnchor();
	}
}
