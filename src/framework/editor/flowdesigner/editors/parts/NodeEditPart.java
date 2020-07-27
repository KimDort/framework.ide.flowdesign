package framework.editor.flowdesigner.editors.parts;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import framework.editor.flowdesigner.entities.Node;
import framework.editor.flowdesigner.entities.graphicItem.anchor.NodeAnchor;
import framework.editor.flowdesigner.entities.graphicItem.node.NodeRectangleFigure;

public class NodeEditPart extends AbstractGraphicalEditPart {
	private Node node;
	private NodeRectangleFigure nodeFigure;

	@Override
	protected IFigure createFigure() {
		this.node = (Node) getModel();
		GraphicalViewer viewer = (GraphicalViewer) getViewer();
		NodeRectangleFigure figure = new NodeRectangleFigure(viewer);
		figure.setX(node.getxPosition());
		figure.setY(node.getyPosition());
		figure.setNodeType(node.getNodeType());
		node.setRectangleFigure(figure);

		// Create Anchor
		if (!node.getNodeType().equals("CLASS")) {
			NodeAnchor anchor = new NodeAnchor();
			anchor.setInAnchor(node.getRectangleFigure().getInAnchor());
			anchor.setOutAnchor(node.getParentNode().getRectangleFigure().getOutAnchor());
			
			node.addSourceAnchors(anchor);
		}

		this.nodeFigure = figure;
		return figure;
	}

	@Override
	protected void refreshVisuals() {
		System.out.println("[FWK] Node EditPart Refresh Visuals");
		this.nodeFigure.refresh();
	}

	@Override
	protected ConnectionEditPart createConnection(Object model) {
		NodeAnchorPart anchorPart = (NodeAnchorPart) getRoot().getViewer().getEditPartRegistry().get(model);
		if (anchorPart == null) {
			anchorPart = new NodeAnchorPart();
			anchorPart.setModel(model);
		}

		return anchorPart;
	}

	@Override
	protected List getModelSourceConnections() {
		List inAnchors = ((Node)getModel()).getSourceAnchors();
		return inAnchors;
	}
	
	@Override
	protected List getModelTargetConnections() {
		List outAnchors = ((Node)getModel()).getTargetAnchors();
		return outAnchors;
	}
	
	@Override
	protected void createEditPolicies() {
	}

}
