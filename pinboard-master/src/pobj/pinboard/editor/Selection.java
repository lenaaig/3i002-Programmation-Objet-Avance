package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.document.ClipRect;

public class Selection {
	
	private ClipGroup liste;
	
	/**
	 * Constructeur de Selection
	 */
	public Selection() {
		liste=new ClipGroup();
	}
	
	/**
	 * Selection un seul clip
	 * @param board
	 * @param x
	 * @param y
	 */
	public void select (Board board, double x, double y) {
		clear();
		List<Clip> l = board.getContents();
		for(Clip clip:l) {
			if(clip.isSelected(x, y)) {
				liste.addClip(clip);
				break;
			}
		}
	}
	
	/**
	 * Selectionne plusieurs clips
	 * @param board
	 * @param x
	 * @param y
	 */
	public void toogleSelect(Board board, double x, double y) {
		List<Clip> l = board.getContents();
		for(Clip clip:l) {
			if(clip.isSelected(x, y)) {
				if(liste.contains(clip))
					liste.removeClip(clip);
				else
					liste.addClip(clip);
				break;
			}
		}
	}
	
	/**
	 * Vide la liste des clips selectionnés 
	 */
	public void clear() {
		liste.clear();
	}
	
	/**
	 * Retourne la liste des clips selectionés
	 * @return List <Clip>
	 */
	public List<Clip> getContents(){
		return liste.getClips();
	}
	
	public void drawFeedback(GraphicsContext gc) {
		List<Clip> list = liste.getClips();
		
			double top=list.get(0).getTop();
			double left=list.get(0).getLeft();
			double bottom=list.get(0).getBottom();
			double right=list.get(0).getRight();
			for(Clip c : list) {
				if(c.getTop()<top)
					top=c.getTop();
				if (c.getLeft()<left)
					left=c.getLeft();
				if (c.getBottom()>bottom)
					bottom=c.getBottom();
				if (c.getRight()>right)
					right=c.getRight();
			}
			gc.strokeRect(left, top, right-left, bottom-top);
	}
	
	
}
