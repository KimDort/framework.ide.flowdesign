package framework.editor.flowdesigner.editors;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;

import framework.editor.flowdesigner.editors.factories.NodeEditFactory;
import framework.editor.flowdesigner.entities.Node;

public class MainNodeEditor extends GraphicalEditor{
	
	private Node node;
	private ScalableFreeformRootEditPart scalableFreeFormRootEditorPart;
	
	public MainNodeEditor() {
		super();
		setEditDomain(new DefaultEditDomain(this));
	}
	
	
	@Override
	protected void configureGraphicalViewer() {
		System.out.println("[FWK] Init Graphical Viewer");
		super.configureGraphicalViewer();
		scalableFreeFormRootEditorPart = new ScalableFreeformRootEditPart();
		getGraphicalViewer().setRootEditPart(scalableFreeFormRootEditorPart);
		getGraphicalViewer().setEditPartFactory(new NodeEditFactory());
	}
	
	
	@Override
	protected void createActions() {
		super.createActions();
	}


	public GraphicalViewer getInnerGraphicalViewer() {
		return getGraphicalViewer();
	}

	public void recreateNode(List<Node> node) {
		getGraphicalViewer().setContents(node);
	}
	
	@Override
	protected void initializeGraphicalViewer() {
		
	}

	@Override
	public void doSave(IProgressMonitor arg0) {
		
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		super.init(site, input);
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public ScalableFreeformRootEditPart getScalableFreeFormRootEditorPart() {
		return scalableFreeFormRootEditorPart;
	}

	public void setScalableFreeFormRootEditorPart(ScalableFreeformRootEditPart scalableFreeFormRootEditorPart) {
		this.scalableFreeFormRootEditorPart = scalableFreeFormRootEditorPart;
	}
	
}
