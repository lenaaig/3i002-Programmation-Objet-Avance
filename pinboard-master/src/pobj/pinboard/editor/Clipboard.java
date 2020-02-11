package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipGroup;

/**
 * 
 * Class representant un presspapier
 *
 */
public class Clipboard {
	
	private ClipGroup copies;
	private static Clipboard pressep=new Clipboard();
	private List<ClipboardListener> cliplistener;
	
	private Clipboard() {
		copies=new ClipGroup();
		cliplistener=new ArrayList<>();
	}
	
	/**
	 * Retourne le presse papier
	 * @return Clipboard
	 */
	public static Clipboard getInstance() {
		return pressep;
	}
	
	/**
	 * Copie les elements de clips dans le presse papier
	 * @param clips
	 */
	public void copyToClipboard(List<Clip> clips) {
		for (Clip c : clips) {
			copies.addClip(c.copy());
		}
		changement();
	}
	
	
	/**
	 * Retourne une copie des elements du presse papier
	 * @return List<Clip>
	 */
	public List<Clip> copyFromClipboard(){
		List<Clip> copy= new ArrayList<>();
		for(Clip c: copies.getClips()) {
			copy.add(c.copy());
		}
		changement();
		return copy;

	}
	
	
	/**
	 * Supprime tout les elements de la liste
	 */
	public void clear() {
		copies.clear();
		changement();
	}
	
	/**
	 * Verifie si la liste est vide ou pas
	 * @return true or false
	 */
	public boolean isEmpty() {
		return copies.isEmpty();
	}

	/**
	 * Ajouter un observeur
	 * @param listener: ClipboardListener
	 */
	public void addListener(ClipboardListener listener) {
		 cliplistener.add(listener);
	}
	
	/**
	 * Supprimer un observeur
	 * @param listener: ClipboardListener
	 */
	public void removeListener(ClipboardListener listener) {
		cliplistener.remove(listener);
	}
	
	/**
	 * Notifier tout les observeurs
	 */
	private void changement() {
		for (ClipboardListener cl : cliplistener)
			cl.clipboardChanged();
	}
	
}
