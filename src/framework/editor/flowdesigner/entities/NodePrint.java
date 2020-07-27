package framework.editor.flowdesigner.entities;

public class NodePrint {
	public static void print(Node node, int count) {
		for(Node child : node.getChildren()) {
			System.out.println("["+count+"]"+getBlank(count) + child);
			print(child, count++);
		}
	}
	
	public static String getBlank(int count) {
		StringBuffer blankStr = new StringBuffer();
		for(int i = 0; i < count; i++) {
			blankStr.append(" ");
		}
		
		return blankStr.toString();
	}
}
