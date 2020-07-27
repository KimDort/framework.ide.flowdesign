package framework.editor.flowdesigner.utils;

import java.util.Comparator;

import framework.editor.flowdesigner.entities.Node;

public class NodeCompare implements Comparator<Node>{

	@Override
	public int compare(Node n1, Node n2) {
		return n1.getIndex() > n2.getIndex() ? 1 :n1.getIndex() < n2.getIndex()? -1 : 0;
	}

}
