package framework.editor.flowdesigner.entities.graphicItem.node;

import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.Clickable;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalViewer;

import framework.editor.flowdesigner.entities.Constants;
import framework.editor.flowdesigner.entities.graphicItem.anchor.FixedAnchor;

public class NodeRectangleFigure extends Clickable{
	private FixedAnchor inAnchor;
	private FixedAnchor outAnchor;
	private String nodeType;
	private String nodeName;
	private int x;
	private int y;
	
	private RectangleFigure pointArea = new RectangleFigure();
	private RectangleFigure typeArea = new RectangleFigure();
	private RectangleFigure nameArea = new RectangleFigure();
	
	private Label pointAreaLabel = new Label();
	private Label typeAreaLabel = new Label();
	private Label nameAreaLabel = new Label();
	
	public NodeRectangleFigure(GraphicalViewer viewer) {
		setOpaque(true);
		
		//Set Default Border 
		Border rectangleBorder = new LineBorder(Constants.RECTANGLE_LINE_BORDER_COLOR, 1);
		//Set Custom Border
		//Border customBorder = new CustomBorder();
		
		//Set Rectangle Point Area : S
		pointArea.setBounds(new Rectangle(0, 0, Constants.RECTANGLE_POINT_WIDTH, Constants.RECTANGLE_DEFAULT_HEIGHT));
		pointArea.setBorder(rectangleBorder);
		//Set Rectangle Point Area : E
		
		//Set Rectangle Type Area : S
		typeArea.setBounds(new Rectangle(Constants.RECTANGLE_POINT_WIDTH, 0, Constants.RECTANGLE_TYPE_WIDTH, Constants.RECTANGLE_DEFAULT_HEIGHT));
		typeArea.setBorder(rectangleBorder);
		//Set Label 
		typeAreaLabel.setBackgroundColor(ColorConstants.white);
		typeAreaLabel.setBounds(new Rectangle(Constants.RECTANGLE_POINT_WIDTH, 0, Constants.RECTANGLE_TYPE_WIDTH, Constants.RECTANGLE_DEFAULT_HEIGHT));
		
		typeArea.add(typeAreaLabel);
		//Set Rectangle Type Area : E
		
		//Set Rectangle Name Area : S
		nameArea.setBounds(new Rectangle(Constants.RECTANGLE_POINT_WIDTH+Constants.RECTANGLE_TYPE_WIDTH, 0, Constants.RECTANGLE_NAME_WIDTH, Constants.RECTANGLE_DEFAULT_HEIGHT));
		nameArea.setBackgroundColor(Constants.RECTANGLE_NAME_AREA_COLOR);
		//Set Rectangle Name Area : E
		
		//Rectangle Add
		add(pointArea);
		add(typeArea);
		add(nameArea);
		
		//Set Anchor 
		inAnchor = new FixedAnchor(this);
		inAnchor.setPlace(new Point(0, 50));
		outAnchor = new FixedAnchor(this);
		outAnchor.setPlace(new Point(8, 100));
	}

	public void refresh() {
		typeAreaLabel.setText(this.nodeType);
		typeArea.setBackgroundColor(Constants.RECTANGLE_TYPE_AREA_COLOR(nodeType));
		setBounds(new Rectangle(this.x, this.y, Constants.RECTANGE_TOTAL_WIDTH, Constants.RECTANGLE_DEFAULT_HEIGHT));
	}
	
	public FixedAnchor getInAnchor() {
		return inAnchor;
	}

	public void setInAnchor(FixedAnchor inAnchor) {
		this.inAnchor = inAnchor;
	}

	public FixedAnchor getOutAnchor() {
		return outAnchor;
	}

	public void setOutAnchor(FixedAnchor outAnchor) {
		this.outAnchor = outAnchor;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = (int)((y*2)/1.5);
	}
	
	class CustomBorder extends AbstractBorder {
		@Override
		public boolean isOpaque() {
			return true;
		}

		@Override
		public Insets getInsets(IFigure arg0) {
			return new Insets(0, 0, 0, 0);
		}

		@Override
		public void paint(IFigure figure, Graphics graphics, Insets insets) {
			int width = 1;

			Rectangle rectangle = tempRect;
			tempRect.setBounds(getPaintRectangle(figure, insets));
			rectangle.width--;
			rectangle.height--;

			graphics.setLineWidth(width);

			// Top Left Corner
			PointList pointList = new PointList();
			pointList.addPoint(rectangle.getTopLeft());
			pointList.addPoint(rectangle.getTopRight());
			graphics.setForegroundColor(Constants.RECTANGLE_LINE_BORDER_COLOR);
			graphics.setLineWidth(1);
			graphics.drawPolygon(pointList);

			// Bottem Left Corner
			pointList = new PointList();
			pointList.addPoint(rectangle.getBottomLeft());
			pointList.addPoint(rectangle.getBottomRight());
			graphics.setForegroundColor(Constants.RECTANGLE_LINE_BORDER_COLOR);
			graphics.setLineWidth(1);
			graphics.drawPolygon(pointList);

			// Top Right Corner
			pointList = new PointList();
			pointList.addPoint(rectangle.getTopLeft());
			pointList.addPoint(rectangle.getBottomRight());
			graphics.setForegroundColor(Constants.RECTANGLE_LINE_BORDER_COLOR);
			graphics.setLineWidth(1);
			graphics.drawPolygon(pointList);

			// Bottem Right Corner
			pointList = new PointList();
			pointList.addPoint(rectangle.getTopRight());
			pointList.addPoint(rectangle.getBottomRight());
			graphics.setForegroundColor(Constants.RECTANGLE_LINE_BORDER_COLOR);
			graphics.setLineWidth(1);
			graphics.drawPolygon(pointList);
		}

	}
}
