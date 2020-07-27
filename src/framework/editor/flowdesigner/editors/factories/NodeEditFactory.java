package framework.editor.flowdesigner.editors.factories;

import java.util.ArrayList;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import framework.editor.flowdesigner.editors.parts.DefaultEditPart;
import framework.editor.flowdesigner.editors.parts.NodeEditPart;

public class NodeEditFactory implements EditPartFactory{
	
	@Override
	public EditPart createEditPart(EditPart editPart, Object obj) {
		//System.out.println("[FWK] EditPart="+editPart);
		EditPart returnEditPart=null;
		try {
			if(obj instanceof ArrayList) {
				returnEditPart = new DefaultEditPart();
				System.out.println("default part");
			}else {
				System.out.println("node edit part");
				returnEditPart = new NodeEditPart();
			}
			returnEditPart.setModel(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return returnEditPart;
	}
	
}
