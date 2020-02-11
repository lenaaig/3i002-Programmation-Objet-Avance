package pobj.pinboard.editor.commands;


import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;

public class CommandMove implements Command{
	private EditorInterface editor;
	private Clip toMove;
	private double x, y;

	public CommandMove(EditorInterface editor, Clip toMove, double x, double y) {
		this.editor=editor;
		this.toMove= toMove;
		this.x=x;
		this.y=y;
	}
 
	@Override
	public void execute() {
		toMove.move(x, y);
	}

	@Override
	public void undo() {
		toMove.move(-x, -y);

	}

}
