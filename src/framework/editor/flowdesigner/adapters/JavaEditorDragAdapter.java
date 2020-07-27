package framework.editor.flowdesigner.adapters;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.swt.dnd.TransferData;

public class JavaEditorDragAdapter extends ViewerDropAdapter{

	public JavaEditorDragAdapter(Viewer viewer) {
		super(viewer);
	}

	@Override
	public boolean performDrop(Object arg0) {
		return true;
	}

	@Override
	public boolean validateDrop(Object arg0, int arg1, TransferData arg2) {
		return true;
	}

}
