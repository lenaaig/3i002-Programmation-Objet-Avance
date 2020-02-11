package pobj.pinboard.document;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class ClipGroup extends AbstractClip implements Composite{
	private List<Clip> groupe;

	public ClipGroup () {
		groupe= new ArrayList<>();
	}

	@Override
	public double getTop() {
		double top= groupe.get(0).getTop();
	
		for(Clip clip: groupe) {
			if(clip.getTop()< top)
				top=clip.getTop();
			
		}
		return top;
	}
	
	@Override
	public double getLeft() {
		double left= groupe.get(0).getLeft();
		
		for(Clip clip: groupe) {
			if(clip.getLeft()< left)
				left=clip.getLeft();
			
		}
		return left;
	}
	
	@Override
	public double getBottom() {
		double bottom= groupe.get(0).getBottom();
		
		for(Clip clip: groupe) {
			if(clip.getBottom()> bottom)
				bottom=clip.getBottom();
			
		}
		return bottom;	
	}

	@Override
	public double getRight() {
		double right= groupe.get(0).getRight();
		
		for(Clip clip: groupe) {
			if(clip.getRight()> right)
				right=clip.getRight();
			
		}
		return right;	
	}
	
	
	@Override
	public List<Clip> getClips() {
		return groupe;
	}

	@Override
	public void addClip(Clip toAdd) {
		groupe.add(toAdd);
	}

	@Override
	public void removeClip(Clip toRemove) {
		groupe.remove(toRemove);
	}

	@Override
	public void draw(GraphicsContext ctx) {
		for(Clip clip: groupe) {
			clip.draw(ctx);
		}
		double top=groupe.get(0).getTop();
		double left=groupe.get(0).getLeft();
		double bottom=groupe.get(0).getBottom();
		double right=groupe.get(0).getRight();
		for(Clip c : groupe) {
			if(c.getTop()<top)
				top=c.getTop();
			if (c.getLeft()<left)
				left=c.getLeft();
			if (c.getBottom()>bottom)
				bottom=c.getBottom();
			if (c.getRight()>right)
				right=c.getRight();
		}
		ctx.strokeRect(left, top, right-left, bottom-top);
	}
	
	@Override
	public void setGeometry(double left, double top, double right, double bottom) {
		move(right-left,bottom-top);
	}

	@Override
	public Clip copy() {
		ClipGroup copy= new ClipGroup();
		for(Clip clip: groupe)
			copy.addClip(clip.copy());
		return copy;
	}
	
	@Override
	public void move(double x, double y) {
		for(Clip clip: groupe) {
			clip.move(x,y);
		}
		
	}

	/**
	 * Verifie si la liste contient l'element
	 * @param clip
	 * @return true or false
	 */
	public boolean contains(Clip clip) {
		return groupe.contains(clip);
	}

	/**
	 * Supprime tout les elements de la liste  
	 */
	public void clear() {
		groupe.clear();		
	}

	/**
	 * Verifie si la liste est vide
	 * @return true or false
	 */
	public boolean isEmpty() {
		return groupe.isEmpty();
	}

	@Override
	public boolean isSelected(double x, double y) {
		double top=groupe.get(0).getTop();
		double left=groupe.get(0).getLeft();
		double bottom=groupe.get(0).getBottom();
		double right=groupe.get(0).getRight();
		for(Clip c : groupe) {
			if(c.getTop()<top)
				top=c.getTop();
			if (c.getLeft()<left)
				left=c.getLeft();
			if (c.getBottom()>bottom)
				bottom=c.getBottom();
			if (c.getRight()>right)
				right=c.getRight();
		}
		if (x>=left && x<=right && y>=top && y<=bottom) return true;
		return false;
	}
}
