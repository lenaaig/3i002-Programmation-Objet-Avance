package pobj.pinboard.editor.tools;

import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.Selection;
import pobj.pinboard.editor.commands.Command;
import pobj.pinboard.editor.commands.CommandMove;

public class ToolSelection implements Tool {
	
	private double x_init;
	private double y_init;
	private double x;
	private double y;
	private double x_release;
	private double y_release;
	private Selection selection;
	
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		selection = i.getSelection();
		if(e.isShiftDown()) {
			selection.toogleSelect(i.getBoard(), e.getX(), e.getY());
		}else {
			selection.select(i.getBoard(), e.getX(), e.getY());
		}
		x_init=e.getX();
		y_init=e.getY();
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		x=e.getX();
		y=e.getY();
		List<Clip> select = selection.getContents();

		for (Clip c : select) {
			Command cmd = new CommandMove(i, c, x-x_init, y-y_init);
			i.getUndoStack().addCommand(cmd);
			cmd.execute();
			//c.move(x-x_init, y-y_init);
		}
		x_init=x;
		y_init=y;
		
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		/*List<Clip> select = selection.getContents();

		for (Clip c : select) {	
			Command cmd = new CommandMove(i, c, -(e.getX()-x_release), -(e.getY()-y_release));
			i.getUndoStack().addCommand(cmd);
		}*/
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		Board planche = i.getBoard();
		planche.draw(gc);
		selection.drawFeedback(gc);
	}

	@Override
	public String getName() {
		return "selection";
	}
		
}
