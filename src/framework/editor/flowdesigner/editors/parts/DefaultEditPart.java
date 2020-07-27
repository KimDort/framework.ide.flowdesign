package framework.editor.flowdesigner.editors.parts;

import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.swt.graphics.Color;

import framework.editor.flowdesigner.entities.Node;

public class DefaultEditPart extends AbstractGraphicalEditPart {

	@Override
	protected IFigure createFigure() {
		Figure f = new FreeformLayer();
		MarginBorder marginBorder = new MarginBorder(new Insets(0, 0, 40, 80));
		FreeformLayout freeformLayout = new FreeformLayout();
		f.setBorder(marginBorder);
		f.setBackgroundColor(new Color(null, 255, 255, 255));
		f.setLayoutManager(freeformLayout);
		f.setOpaque(true);

		return f;
	}

	@Override
	protected void createEditPolicies() {
	}

	@Override
	protected List getModelChildren() {
		List<Node> children = (List<Node>) getModel();
		return children;
	}
}
