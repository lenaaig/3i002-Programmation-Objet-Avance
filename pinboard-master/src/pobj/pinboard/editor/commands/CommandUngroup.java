package pobj.pinboard.editor.commands;

import java.util.List;

import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.editor.EditorInterface;

public class CommandUngroup implements Command{

	private EditorInterface editor;
	private ClipGroup toUngroup;
	public CommandUngroup(EditorInterface editor, ClipGroup group) {
		this.editor=editor;
		this.toUngroup= group;
	}
	
	
	@Override
	public void execute() {
		List<Clip> list = toUngroup.getClips();
		Board planche = editor.getBoard();
		planche.removeClip(toUngroup);
		for(Clip clip: list)
			planche.addClip(clip);
	}

	@Override
	public void undo() {
		List<Clip> list = toUngroup.getClips();
		Board planche = editor.getBoard();
		planche.addClip(toUngroup);
		for(Clip clip: list)
			planche.removeClip(clip);
	}

}
