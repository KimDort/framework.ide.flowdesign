package framework.editor.flowdesigner.finders;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.Statement;

import framework.editor.flowdesigner.entities.Node;

public class StatementFinder extends ASTVisitor{
	List<Statement> statements = new ArrayList<Statement>();
	List<Node> methodNodes = new ArrayList<Node>();
	
	private CompilationUnit unit;
	private Node parentNode;
	private int parentLevel;
	
	public static Node find(ASTNode astNode, Node parentNode, Node node, CompilationUnit unit) {
		StatementFinder stFinder = new StatementFinder();
		node.setParentNode(parentNode);
		parentNode.getChildren().add(node);
		
		stFinder.setParentLevel(node.getLevel());
		stFinder.setParentNode(node);
		stFinder.setUnit(unit);
		astNode.accept(stFinder);
		
		return parentNode;
	}

	@Override
	public boolean visit(Block node) {
		List<Statement> blockStatements = node.statements();
		for(Statement statement : blockStatements) {
			int parentNodeLevel = parentLevel;
			parentNodeLevel++;
			
			if(statement instanceof IfStatement) {
				Node newNode = new Node();
				newNode.setAstNode(statement);
				newNode.setParentNode(parentNode);
				newNode.setNodeType("IF");
				newNode.setStartLine(unit.getLineNumber(statement.getStartPosition()));
				newNode.setEndLine(unit.getLineNumber(statement.getStartPosition()+statement.getLength()));
				newNode.setLevel(parentNodeLevel);
				find(statement, newNode.getParentNode(), newNode, this.unit);
			}
			if(statement instanceof ForStatement) {
				Node newNode = new Node();
				newNode.setAstNode(statement);
				newNode.setParentNode(parentNode);
				newNode.setNodeType("FOR");
				newNode.setStartLine(unit.getLineNumber(statement.getStartPosition()));
				newNode.setEndLine(unit.getLineNumber(statement.getStartPosition()+statement.getLength()));
				newNode.setLevel(parentNodeLevel);
				find(statement, newNode.getParentNode(), newNode, this.unit);
			}
			if(statement instanceof EnhancedForStatement) {
				Node newNode = new Node();
				newNode.setAstNode(statement);
				newNode.setParentNode(parentNode);
				newNode.setNodeType("FOR");
				newNode.setStartLine(unit.getLineNumber(statement.getStartPosition()));
				newNode.setEndLine(unit.getLineNumber(statement.getStartPosition()+statement.getLength()));
				newNode.setLevel(parentNodeLevel);
				find(statement, newNode.getParentNode(), newNode, this.unit);
			}
		}
		return false;
	}

	
	public int getParentLevel() {
		return parentLevel;
	}

	public void setParentLevel(int parentLevel) {
		this.parentLevel = parentLevel;
	}

	public Node getParentNode() {
		return parentNode;
	}

	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}

	public CompilationUnit getUnit() {
		return unit;
	}

	public void setUnit(CompilationUnit unit) {
		this.unit = unit;
	}
	
}
