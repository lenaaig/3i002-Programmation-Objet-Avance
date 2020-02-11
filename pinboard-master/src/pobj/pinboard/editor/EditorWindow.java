package pobj.pinboard.editor;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.editor.commands.Command;
import pobj.pinboard.editor.commands.CommandAdd;
import pobj.pinboard.editor.commands.CommandGroup;
import pobj.pinboard.editor.commands.CommandMove;
import pobj.pinboard.editor.commands.CommandUngroup;
import pobj.pinboard.editor.tools.Tool;
import pobj.pinboard.editor.tools.ToolEllipse;
import pobj.pinboard.editor.tools.ToolRect;
import pobj.pinboard.editor.tools.ToolSelection;

/**
 * 
 * Class representant la fenete d'édition
 *
 */
public class EditorWindow implements EditorInterface, ClipboardListener{

	private Board planche;
	private Tool tool;
	private Selection selection;
	private Clipboard presspap;
	private MenuItem paste;
	private EditorWindow editor;
	private Color paint= Color.CADETBLUE;
	private ClipGroup groupe;
	private CommandStack stack;
	/**
	 * Creation d'une fenetre d'édition
	 * @param stage
	 */
	public EditorWindow(Stage stage) {
		planche= new Board();			
		tool= new ToolRect();
		selection=new Selection();
		presspap = Clipboard.getInstance();
		presspap.addListener(this);
		editor =this;
		groupe = new ClipGroup();
		stack= new CommandStack();
		
		//Zone de dessin
		Canvas canvas = new Canvas(800, 600);		
		
		//BARRE DE MENU 
		//File
		MenuItem newf = new MenuItem("New");
		newf.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				new EditorWindow(new Stage());
			}
		});
		
		MenuItem close = new MenuItem("Close");
		close.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				presspap.removeListener(editor);
				stage.close();
			}
		});

		Menu file= new Menu("File");
		file.getItems().addAll(newf,close);
		
		//Edit

		MenuItem copy = new MenuItem("Copy");
		copy.setOnAction((e)->{
				presspap.copyToClipboard(selection.getContents());
		});
		
		paste = new MenuItem("Paste");
		paste.setOnAction((e)->{
				Command cmd= new CommandAdd(editor, presspap.copyFromClipboard());
				stack.addCommand(cmd);
				cmd.execute();
				//planche.addClip(presspap.copyFromClipboard());
				planche.draw(canvas.getGraphicsContext2D());
		});
		
		MenuItem delete = new MenuItem("Delete");
		delete.setOnAction((e)->{
				for(Clip clip: selection.getContents()) {
					planche.removeClip(clip);
				}
				Clipboard.getInstance().clear();
				planche.draw(canvas.getGraphicsContext2D());
		});
		
		
		MenuItem group = new MenuItem("Group");
		group.setOnAction((e)->{
			/*for(Clip clip: selection.getContents()) {
				groupe.addClip(clip);
				planche.removeClip(clip);
			}*/
			Command cmd = new CommandGroup(editor, selection.getContents());
			stack.addCommand(cmd);
			cmd.execute();
			Clipboard.getInstance().clear();
			//planche.addClip(groupe);
		});
		
		MenuItem ungroup = new MenuItem("Ungroup");
		ungroup.setOnAction((e)->{
			/*planche.removeClip(groupe);
			for(Clip clip: groupe.getClips()) {
				planche.addClip(clip);
			}
			groupe=new ClipGroup();*/
			for(Clip clip: selection.getContents()) {
				if(clip instanceof ClipGroup) {
					Command cmd = new CommandUngroup(editor, (ClipGroup) clip);
					stack.addCommand(cmd);
					cmd.execute();
				}	
			}
			
			Clipboard.getInstance().clear();
			planche.draw(canvas.getGraphicsContext2D());
			
		});
		
		MenuItem undo = new MenuItem("Undo");
		undo.setOnAction((e)->{
			stack.undo();
		});
		
		MenuItem redo = new MenuItem("Redo");
		redo.setOnAction((e)->{
			stack.redo();			
		});
		
		
		Menu edit= new Menu("Edit");
		edit.getItems().addAll(copy, paste, delete, group, ungroup, undo, redo);
		
		Menu tools= new Menu("Tools");
		
		MenuBar menubar= new MenuBar(file, edit, tools);
		
		//Barre des bouttons
		Button rect=new Button("Rect");
		Button ellipse=new Button("Ellipse");
		Button img=new Button("Img...");
		Button select=new Button("Select");
		
		//Color:
		
		final ColorPicker color = new ColorPicker();
		color.setValue(paint);
		color.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){
				canvas.getGraphicsContext2D().setFill(color.getValue());	
				paint = color.getValue();
			}
		});
		color.getStyleClass().add("slip-button");

		ToolBar toolbar= new ToolBar(rect, ellipse, img, select, color);
		
		
		//Séparateur
		Separator separator=new Separator();
		
		//Barre de statut
		Label label=new Label();
		
		
		rect.setOnAction((e)->{tool= new ToolRect();
		label.setText( tool.getName());});

		ellipse.setOnAction((e)->{tool= new ToolEllipse(); 
		label.setText(tool.getName());});
		
		select.setOnAction((e)->{tool=  new ToolSelection(); 
		label.setText(tool.getName());});
		
		//Permet de deplacer les elements
		canvas.setOnMousePressed((e)->{tool.press(this, e);
		tool.drawFeedback(this, canvas.getGraphicsContext2D());});
		
		canvas.setOnMouseDragged((e)->{
			tool.drag(this, e);
			tool.drawFeedback(this, canvas.getGraphicsContext2D());
		});
		
		canvas.setOnMouseReleased((e)->{tool.release(this, e);
		tool.drawFeedback(this, canvas.getGraphicsContext2D());});
		
		
		
		
		stage.setTitle("Fenetre");
		VBox vbox=new VBox();
		vbox.getChildren().addAll(menubar,toolbar,canvas, separator, label);
		stage.setScene(new javafx.scene.Scene(vbox));
		planche.draw(canvas.getGraphicsContext2D());
		stage.show();	
	}

	@Override
	public Board getBoard() {
		return planche;
	}

	@Override
	public Selection getSelection() {
		return selection;
	}

	@Override
	public CommandStack getUndoStack() {
		return stack;
	}

	@Override
	public void clipboardChanged() {
		if(presspap.isEmpty())
			paste.setDisable(true);
		else
			paste.setDisable(false);
	}

	@Override
	public Color getColor() {
		return paint;
	}
		
	
}
