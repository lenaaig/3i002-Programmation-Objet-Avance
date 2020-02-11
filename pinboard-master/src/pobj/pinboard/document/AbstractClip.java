package pobj.pinboard.document;

import javafx.scene.paint.Color;

/**
 * 
 * Represente un element graphique
 *
 */
public abstract class AbstractClip implements Clip {
	private double left;
	private double top;
	private double right;
	private double bottom;
	private Color color;
	
	/**
	 * @return double
	 */
	@Override
	public double getTop() {
		return top;
	}
	/**
	 * @return double
	 */
	@Override
	public double getLeft() {
		return left;
	}
	
	/**
	 * @return double
	 */
	@Override
	public double getBottom() {
		return bottom;
	}

	/**
	 * @return double
	 */
	@Override
	public double getRight() {
		return right;
	}

	/**
	 * Modifie les parametres de l'element graphique selectionne
	 * @param left: double
	 * @param top: double
	 * @param right: double
	 * @param bottom: double
	 */
	@Override
	public void setGeometry(double left, double top, double right, double bottom) {
		this.left=left;
		this.top=top;
		this.right=right;
		this.bottom=bottom;
	}

	/**
	 * Deplace l'element graphique
	 * @param x: double
	 * @param y: double
	 */
	@Override
	public void move(double x, double y) {
		left+=x;
		right+=x;
		top+=y;
		bottom+=y;
		
	}

	/**
	 * Verifie si la position est dans l'element graphique
	 * @param x: double
	 * @param y: double
	 * @return true or false
	 */
	@Override
	public boolean isSelected(double x, double y) {
		if (x>=left && x<=right && y>=top && y<=bottom) return true;
		return false;
	}

	/**
	 * @param Color
	 */
	@Override
	public void setColor(Color c) {
		this.color=c;		
	}

	/**
	 * @return Color
	 */
	@Override
	public Color getColor() {
		return color;
	}

	
}
