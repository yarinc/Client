package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * The Class SaveMaze opens a dialog window for entering parameters for saving a maze.
 */
public class SaveMaze extends Dialog {

	private String name;
	private String file;
	private String path;
	
	  /**
  	 * Instantiates a new SaveMaze object.
  	 *
  	 * @param parent The parent shell
  	 */
  	public SaveMaze(Shell parent) {
		  // Pass the default styles here
		  this(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	  }
	
	/**
	 * Instantiates a new SaveMaze object.
	 *
	 * @param parent the parent shell
	 * @param style the style
	 */
	public SaveMaze(Shell parent, int style) {
		super(parent, style);
		  setText("Save Maze.");
	}
	 
 	/**
 	 * Open the dialog window.
 	 *
 	 * @return the string
 	 */
 	public String open() {
 		//Create the dialog window
		 Shell parent = getParent();
		 Shell shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		 shell.setSize(200, 200);
		 shell.setText(getText());
		 shell.setLayout(new GridLayout(2, false));
		 //Widget for entering the maze name.
		 Label nameLabel = new Label(shell, SWT.NULL);
		 nameLabel.setText("Name: ");
		 Text nameText = new Text(shell, SWT.SINGLE | SWT.BORDER);
		 //Widget to enter the file name.
		 Label fileLabel = new Label(shell, SWT.NULL);
		 fileLabel.setText("File name: ");
		 Text fileText = new Text(shell, SWT.SINGLE | SWT.BORDER);
		 //Button to choose the destination folder.
		 Button pathButton = new Button(shell, SWT.PUSH);
		 pathButton.setText("Choose folder");
		 GridData gridData = new GridData();
		 gridData.horizontalSpan = 2;
		 pathButton.setLayoutData(gridData);
		 //Listener for the path Button 
		 pathButton.addSelectionListener(new SelectionListener() {
			 //
		   	 @Override
		   	 public void widgetSelected(SelectionEvent arg0) {
		   		 //open a directory selection dialog.
		 		 DirectoryDialog dir = new DirectoryDialog(shell);
				 path = dir.open();
		   	 }
		   	 @Override
		   	 public void widgetDefaultSelected(SelectionEvent arg0) {}
		     });
		 //OK and cancel buttons.
		 Button buttonOK = new Button(shell, SWT.PUSH);
	     buttonOK.setText("Ok");
	     buttonOK.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
	     Button buttonCancel = new Button(shell, SWT.PUSH);
	     buttonCancel.setText("Cancel");
	     //Listener for OK button.
	     buttonOK.addSelectionListener(new SelectionListener() {
	   	 @Override
	   	 public void widgetSelected(SelectionEvent arg0) {
	   		 //Save parameters.
	   		 name = nameText.getText();
	   		 file = fileText.getText();
	   		 //If maze name or file name are not empty, close shell.
	   		 if(!name.equals("") && (!file.equals("")))
	   			 shell.close();
	   		 //Else, pop relevant message.
	   		 else { 
	   			 MessageBox enterName = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
	   			 enterName.setText("Error!");
	   			 enterName.setMessage("Missing parameters");
	   			 enterName.open();		
	   		 }
	   	 }
	   	 @Override
	   	 public void widgetDefaultSelected(SelectionEvent arg0) {}
	     });
	     //Listener for the cancel button.
	     buttonCancel.addSelectionListener(new SelectionListener() {
	    	 @Override
	    	 //Close shell.
	    	 public void widgetSelected(SelectionEvent arg0) {
	    		 shell.close();
	    	 }
	    	 @Override
	    	 public void widgetDefaultSelected(SelectionEvent arg0) {}
	     });
	          
	     shell.addListener(SWT.Traverse, new Listener() {
	    	 public void handleEvent(Event event) {
	    		 if(event.detail == SWT.TRAVERSE_ESCAPE)
	    			 event.doit = false;
	    	 }
	     });
	     //Open shell.
	     shell.open();
	     Display display = parent.getDisplay();
	     //Keep open.
	     while (!shell.isDisposed()) {
	    	 if (!display.readAndDispatch()) display.sleep();
	     }
	     //If maze name, path name, and file name are not null. Return the save command.
	     if((name != null) && (path != null) && (file != null))
	    	 return name + " " + path + "\\" + file + ".maz";
	     else 
	    	 //Else return null.
	    	 return null;
	 }
}
