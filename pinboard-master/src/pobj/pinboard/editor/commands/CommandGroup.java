package pobj.pinboard.editor.commands;

import java.util.List;

import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.editor.EditorInterface;

public class CommandGroup implements Command{

	private EditorInterface editor;
	private List<Clip> toGroup;
	private ClipGroup groupe;
	
	public CommandGroup(EditorInterface editor, List<Clip> toGroup) {
		this.editor=editor;
		this.toGroup= toGroup;
		groupe = new ClipGroup();
	}

	
	
	@Override
	public void execute() {
		Board planche = editor.getBoard();
		for(Clip clip: toGroup){
			groupe.addClip(clip);
			planche.removeClip(clip);
		}
		editor.getBoard().addClip(groupe);
	}

	@Override
	public void undo() {
		editor.getBoard().removeClip(groupe);
		editor.getBoard().addClip(toGroup);
		groupe = new ClipGroup();
	}

}
