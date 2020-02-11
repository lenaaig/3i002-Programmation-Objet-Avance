package pobj.pinboard.document;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * 
 * Class representant la planche de dessin
 *
 */
public class Board {
	private List<Clip> planche;
	
	/**
	 * Creation d'une planche de dessin
	 */
	public Board() {
		planche= new ArrayList<>();
	}
	
	/**
	 * Retourne la planche
	 * @return List<Clip>
	 */
	public List<Clip> getContents(){
		return planche;
	}
	
	/**
	 * Ajoute un element graphique
	 * @param clip: Clip or List<Clip>
	 */
	public void addClip(Clip clip) {
		planche.add(clip);
	}
	
	/**
	 * Ajoute des elements graphiques
	 * @param clip: Clip or List<Clip>
	 */
	public void addClip(List<Clip> clip) {
		for (Clip c : clip) {
			planche.add(c);
		}
	}
	
	/**
	 * Retire un element graphique
	 * @param clip: Clip or List<Clip>
	 */
	public void removeClip(Clip clip) {
		if (planche.contains(clip))
			planche.remove(clip);
	}
	
	/**
	 * Retire des elements graphiques
	 * @param clip: Clip or List<Clip>
	 */
	public void removeClip(List<Clip> clip) {
		for (Clip c : clip) {
			if (planche.contains(c))
				planche.remove(c);
		}
	}
	
	/**
	 * Dessine tout les elements graphiques de la planche
	 * @param gc: GraphicsContext
	 */
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.fillRect(0,0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
		for (Clip c : planche) {
			c.draw(gc);
		}
	}
	
}
