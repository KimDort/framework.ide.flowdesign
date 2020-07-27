package framework.editor.flowdesigner.entities;

import org.eclipse.swt.graphics.Color;

public class Constants {
	
	//Rectangle Default Size Info
	public static final int RECTANGLE_DEFAULT_HEIGHT=34;
	public static final int RECTANGLE_POINT_WIDTH=20;
	public static final int RECTANGLE_TYPE_WIDTH=76;
	public static final int RECTANGLE_NAME_WIDTH=500;
	public static final int RECTANGE_TOTAL_WIDTH=RECTANGLE_POINT_WIDTH+RECTANGLE_TYPE_WIDTH+RECTANGLE_NAME_WIDTH;
	public static final int RECTANGLE_X_OFFSET=60;
	public static final int RECTANGLE_Y_OFFSET=20;
	
	//Rectangle Color Info
	public static final Color RECTANGLE_POINT_AREA_COLOR = new Color(null, 255, 255, 255);
	public static final Color RECTANGLE_NAME_AREA_COLOR = new Color(null, 220, 230, 242);
	public static final Color RECTANGLE_LINE_BORDER_COLOR = new Color(null, 155, 148, 104);
	
	//Text Color
	public static final Color DEFAULT_TEXT_COLOR = new Color(null, 155, 148, 104);
	
	//public static final Color RECTANGLE_TYPE_AREA_COLOR = new Color(null, 255, 255, 255);
	public static final Color RECTANGLE_TYPE_AREA_COLOR(String nodeType) {
		Color nodeColor=null;
		switch (nodeType) {
		case "METHOD":
			nodeColor = new Color(null, 144, 180, 227);
			break;
		case "IF":
			nodeColor = new Color(null, 250, 192, 144);
			break;
		case "ELSE_IF":
			nodeColor = new Color(null, 250, 192, 144);
			break;
		case "ELSE":
			nodeColor = new Color(null, 250, 192, 144);
			break;
		case "FOR":
			nodeColor = new Color(null, 230, 185, 184);
			break;
		case "WHILE":
			nodeColor = new Color(null, 230, 185, 184);
			break;
		case "SWITCH":
			nodeColor = new Color(null, 250, 192, 144);
			break;
		default:
			nodeColor = new Color(null, 195, 214, 155);
			break;
		}
		return nodeColor;
	}
}
