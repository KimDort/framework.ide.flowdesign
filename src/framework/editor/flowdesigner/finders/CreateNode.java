package framework.editor.flowdesigner.finders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import framework.editor.flowdesigner.entities.Constants;
import framework.editor.flowdesigner.entities.Node;
import framework.editor.flowdesigner.utils.NodeCompare;

public class CreateNode {
	private int index=1;
	private List<Node> nodeList = new ArrayList<Node>();
	
	public Node create(CompilationUnit unit) {
		Node rootNode = new Node();
		rootNode.setNodeType("CLASS");
		rootNode.setStartLine(1);
		rootNode.setEndLine(unit.getLineNumber(unit.getLength()));
		rootNode.setAstNode(unit);
		rootNode.setIndex(1);
		rootNode.setLevel(1);
		rootNode.setxPosition(Constants.RECTANGLE_X_OFFSET);
		rootNode.setyPosition(Constants.RECTANGLE_DEFAULT_HEIGHT);
		nodeList.add(rootNode);
		
		List<MethodDeclaration> methods = MethodFinder.find(unit, rootNode);
		
		for(MethodDeclaration method : methods) {
			Node methodNode = new Node();
			methodNode.setAstNode(method);
			methodNode.setParentNode(rootNode);
			methodNode.setNodeType("METHOD");
			methodNode.setLevel(2);
			methodNode.setStartLine(unit.getLineNumber(method.getStartPosition()));
			methodNode.setEndLine(unit.getLineNumber(method.getStartPosition()+method.getLength()));
			rootNode = StatementFinder.find(method, rootNode, methodNode, unit);
		}
		
		//Create Node Index
		rootNode = createIndex(rootNode);
		//Create Node Position
		rootNode = createPosition(rootNode);
		createList(rootNode);
		
		Collections.sort(nodeList, new NodeCompare());
		rootNode.setNodeList(nodeList);
		
		return rootNode;
	}

	private Node createIndex(Node node) {
		for(int i = 0; i < node.getChildren().size(); i++){
			this.index++;
			
			node.getChildren().get(i).setIndex(this.index);
			createIndex(node.getChildren().get(i));
		}
		return node;
	}
	
	private Node createPosition(Node node) {
		for(int i = 0; i < node.getChildren().size(); i++) {
			int x = node.getChildren().get(i).getLevel();
			int y = node.getChildren().get(i).getIndex();
			int xPosition = x * Constants.RECTANGLE_X_OFFSET;
			int yPosition = y * Constants.RECTANGLE_DEFAULT_HEIGHT;
			node.getChildren().get(i).setxPosition(xPosition);
			node.getChildren().get(i).setyPosition(yPosition);
			createPosition(node.getChildren().get(i));
		}
		return node;
	}
	
	private void createList(Node node) {
		for(Node child : node.getChildren()) {
			nodeList.add(child);
			createList(child);
		}
	}
}
