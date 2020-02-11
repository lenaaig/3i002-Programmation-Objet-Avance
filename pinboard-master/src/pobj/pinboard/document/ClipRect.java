package pobj.pinboard.document;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
/**
 * 
 * Represente un Rectangle
 *
 */
public class ClipRect extends AbstractClip{

	/**
	 * Constructeur d'une figure Rectangle
	 * @param left: double
	 * @param top: double
	 * @param right: double
	 * @param bottom: double
	 * @param color: Color
	 */
	public ClipRect(double left,double	top,double right,double bottom, Color color){
		setGeometry(left, top, right, bottom);
		setColor(color);
	}

	/**
	 * Dessine un Rectangle
	 */
	@Override
	public void draw(GraphicsContext ctx) {
		ctx.setFill(getColor());
		ctx.fillRect(getLeft(), getTop(), getRight()-getLeft(), getBottom()-getTop());	
	}
	
	/**
	 * Copie le Rectangle
	 * @return Clip (ClipRect)
	 */
	@Override
	public Clip copy() {
		return new ClipRect(getLeft(), getTop(), getRight(), getBottom(), getColor());
	}

}
