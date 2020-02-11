package pobj.pinboard.document;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * 
 * Class representant une Ellipse
 *
 */
public class ClipEllipse extends AbstractClip {
	/**
	 * Constructeur d'une figure Ellipse
	 * @param left: double
	 * @param top: double
	 * @param right: double
	 * @param bottom: double
	 * @param color: Color
	 */
	public ClipEllipse(double left,double top,double right,double bottom, Color color){
		setGeometry(left, top, right, bottom);
		setColor(color);
	}
	
	/**
	 * Verifie si la position est dans l'Ellipse
	 * @param x: double
	 * @param y: double
	 * @return true or false
	 */
	@Override
	public boolean isSelected(double x, double y) {
		double cx=(getLeft()+getRight())/2;
		double cy=(getTop()+getBottom())/2;
		double rx=(getRight()-getLeft())/2;
		double ry=(getBottom()-getTop())/2;
		if (Math.pow(((x-cx)/rx),2) + Math.pow(((y-cy)/ry),2) <=1)
			return true;
		return false;
	}
	
	/**
	 * Dessine l'Ellipse
	 */
	@Override
	public void draw(GraphicsContext ctx) {
		ctx.setFill(getColor());
		ctx.fillOval(getLeft(), getTop(), getRight()-getLeft(), getBottom()-getTop());
	}
	
	/**
	 * Copie l'Ellipse
	 * @return Clip (ClipEllipse)
	 */
	@Override
	public Clip copy() {
		return new ClipEllipse(getLeft(), getTop(), getRight(), getBottom(), getColor());
	}

}
