package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.commands.Command;
import pobj.pinboard.editor.commands.CommandAdd;

public class ToolRect implements Tool{

	private double x_ini;
	private double y_ini;
	private double x_cour;
	private double y_cour;
	private boolean drag=false;
	
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		x_ini=x_cour=e.getX();
		y_ini=y_cour=e.getY();
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		drag=true;
		x_cour=e.getX();
		y_cour=e.getY();
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		drag=false;
		x_cour=e.getX();
		y_cour=e.getY();
		//Board planche=i.getBoard();
		if(x_cour>x_ini && y_cour>y_ini) {
			//planche.addClip(new ClipRect(x_ini, y_ini, x_cour, y_cour, i.getColor()));
			Command cmd = new CommandAdd(i,new ClipRect(x_ini, y_ini, x_cour, y_cour, i.getColor()) );
			cmd.execute();
		}else if(x_cour<x_ini && y_cour<y_ini) {
			//planche.addClip(new ClipRect(x_cour, y_cour, x_ini, y_ini, i.getColor()));
			Command cmd = new CommandAdd(i,new ClipRect(x_cour, y_cour, x_ini, y_ini, i.getColor()));
			cmd.execute();
		}else if(x_cour<x_ini && y_cour>y_ini) {
			//planche.addClip(new ClipRect(x_cour, y_ini, x_ini, y_cour, i.getColor()));
			Command cmd = new CommandAdd(i,new ClipRect(x_cour, y_ini, x_ini, y_cour, i.getColor()));
			cmd.execute();
		}else if(x_cour>x_ini && y_cour<y_ini) {
			//planche.addClip(new ClipRect(x_ini, y_cour, x_cour, y_ini, i.getColor()));
			Command cmd = new CommandAdd(i,new ClipRect(x_ini, y_cour, x_cour, y_ini, i.getColor()));
			cmd.execute();
		}
		
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		Board planche = i.getBoard();
		planche.draw(gc);
		if (drag==true) {
			if(x_cour>x_ini && y_cour>y_ini)
				gc.strokeRect(x_ini, y_ini, x_cour-x_ini, y_cour-y_ini);
			if(x_cour<x_ini && y_cour<y_ini)
				gc.strokeRect(x_cour, y_cour, x_ini-x_cour, y_ini-y_cour);
			if(x_cour<x_ini && y_cour>y_ini)
				gc.strokeRect(x_cour, y_ini, x_ini-x_cour, y_cour-y_ini);
			if(x_cour>x_ini && y_cour<y_ini)
				gc.strokeRect(x_ini, y_cour, x_cour-x_ini, y_ini-y_cour);
		}
	}

	@Override
	public String getName() {
		return "Filled rectangle tool";
	}

}