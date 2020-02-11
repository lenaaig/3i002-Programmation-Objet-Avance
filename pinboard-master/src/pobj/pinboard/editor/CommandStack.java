package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.editor.commands.Command;

/**
 * 
 * Pile de Commandes annulables 
 *
 */
public class CommandStack {
	private List<Command> undo;
	private List<Command> redo;
	
	
	public CommandStack() {
		undo = new ArrayList<Command>();
		redo = new ArrayList<Command>();
	}
	
	/**
	 * Verifie si la liste undo est vide
	 * @return true or false
	 */
	public boolean isUndoEmpty() {
		return undo.isEmpty();
	}

	
	/**
	 * Verifie si la liste redo est vide
	 * @return true or false
	 */
	public boolean isRedoEmpty() {
		return redo.isEmpty();
	}

	
	/**
	 * Ajouter une commande
	 * @param cmd: Command
	 */
	public void addCommand(Command cmd) {
		undo.add(cmd);
		redo.clear();
	}

	/**
	 * dépile la dernière commande de la pile undo, exécute sa méthode undo, et empile la commande dans la pile redo
	 */
	public void undo() {
		if(!isUndoEmpty()) {
			Command cmd = undo.get(undo.size()-1);
			cmd.undo();
			redo.add(cmd);
			undo.remove(undo.size()-1);
		}
	}

	/**
	 * dépile la dernière commande de la pile redo, exécute sa méthode redo, et empile la commande dans la pile undo
	 */
	public void redo() {
		if(!isRedoEmpty()) {
			Command cmd = redo.get(redo.size()-1);
			cmd.execute();
			undo.add(cmd);
			redo.remove(redo.size()-1);
		}
	}


}
