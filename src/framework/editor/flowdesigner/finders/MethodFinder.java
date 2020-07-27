package framework.editor.flowdesigner.finders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import framework.editor.flowdesigner.entities.Node;

public class MethodFinder extends ASTVisitor{
	private List<MethodDeclaration> methods = new ArrayList<>();
	
	public static List<MethodDeclaration> find(ASTNode astNode, Node node) {
		MethodFinder methodFinder = new MethodFinder();
		
		astNode.accept(methodFinder);
		
		return methodFinder.getMethods();
	}

	@Override
	public boolean visit(MethodDeclaration node) {
		methods.add(node);
		
		return false;
	}
	
	public List<MethodDeclaration> getMethods(){
		return Collections.unmodifiableList(methods);
	}
}
