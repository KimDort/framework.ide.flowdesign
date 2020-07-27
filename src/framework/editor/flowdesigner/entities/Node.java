package framework.editor.flowdesigner.entities;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;

import framework.editor.flowdesigner.entities.graphicItem.anchor.NodeAnchor;
import framework.editor.flowdesigner.entities.graphicItem.node.NodeRectangleFigure;

public class Node{
	private int startLine;
	private int endLine;
	private CompilationUnit unit;
	
	private ASTNode astNode;
	private Node parentNode;
	private List<Node> children = new ArrayList<Node>();
	private List<Node> nodeList;
	
	private String NodeType;
	
	//Node Position Info
	private int index;
	private int level;
	private int xPosition;
	private int yPosition;

	//Rectangle Item
	private NodeRectangleFigure rectangleFigure;
	
	//Anchor
	private List<NodeAnchor> sourceAnchors = new ArrayList<NodeAnchor>();
	private List<NodeAnchor> targetAnchors = new ArrayList<NodeAnchor>();
	
	public ASTNode getAstNode() {
		return astNode;
	}

	public void setAstNode(ASTNode astNode) {
		this.astNode = astNode;
	}

	public Node getParentNode() {
		return parentNode;
	}

	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}

	public int getStartLine() {
		return startLine;
	}

	public void setStartLine(int startLine) {
		this.startLine = startLine;
	}

	public int getEndLine() {
		return endLine;
	}

	public void setEndLine(int endLine) {
		this.endLine = endLine;
	}

	public List<Node> getChildren() {
		return children;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}

	public String getNodeType() {
		return NodeType;
	}

	public void setNodeType(String nodeType) {
		NodeType = nodeType;
	}
	
	public CompilationUnit getUnit() {
		return unit;
	}

	public void setUnit(CompilationUnit unit) {
		this.unit = unit;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public int getxPosition() {
		return xPosition;
	}

	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}

	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public List<Node> getNodeList() {
		return nodeList;
	}

	public void setNodeList(List<Node> nodeList) {
		this.nodeList = nodeList;
	}
	
	public List<NodeAnchor> getSourceAnchors() {
		return sourceAnchors;
	}

	public void setSourceAnchors(List<NodeAnchor> sourceAnchors) {
		this.sourceAnchors = sourceAnchors;
	}
	
	public void addSourceAnchors(NodeAnchor nodeAnchor) {
		this.sourceAnchors.add(nodeAnchor);
	}

	public List<NodeAnchor> getTargetAnchors() {
		return targetAnchors;
	}

	public void setTargetAnchors(List<NodeAnchor> targetAnchors) {
		this.targetAnchors = targetAnchors;
	}

	public void addTargetAnchor(NodeAnchor nodeAnchor) {
		this.targetAnchors.add(nodeAnchor);
	}
	public NodeRectangleFigure getRectangleFigure() {
		return rectangleFigure;
	}

	public void setRectangleFigure(NodeRectangleFigure rectangleFigure) {
		this.rectangleFigure = rectangleFigure;
	}

	@Override
	public String toString() {
		return "Node[NodeType="+NodeType
				+", level="+level
				+", startLine="+startLine
				+", endLine="+endLine
				+", index="+index
				+", yPosition="+yPosition
				+", xPosition="+xPosition
				+", children="+children
				+"]";
	}
	
}
