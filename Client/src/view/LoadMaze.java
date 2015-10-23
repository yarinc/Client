package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * The Class LoadMaze open a dialog window for entering parameters for loading maze from a file.
 */
public class LoadMaze extends Dialog {
	
	private String name;
	private String file;
	
	  /**
  	 * Instantiates a new load maze window.
  	 *
  	 * @param parent The parent shell
  	 */
  	public LoadMaze(Shell parent) {
		  this(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	  }
	
	/**
	 * Instantiates a new load maze window.
	 *
	 * @param parent The parent shell
	 * @param style The style
	 */
	public LoadMaze(Shell parent, int style) {
		super(parent, style);
		  setText("Save Maze.");
	}
	
	/**
	 * Open the dialog window.
	 *
	 * @return the string
	 */
	public String open() {
		//Create the window.
		Shell parent = getParent();
		Shell shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setSize(200, 150);
		shell.setText(getText());
		shell.setLayout(new GridLayout(2, false));
		//Widget for maze name 
		Label nameLabel = new Label(shell, SWT.NULL);
		nameLabel.setText("Maze name: ");
		Text nameText = new Text(shell, SWT.SINGLE | SWT.BORDER);
		//Widget for selecting file to load.
		Button selectFile = new Button(shell, SWT.PUSH);
		GridData grid = new GridData();
		grid.horizontalSpan = 2;
		selectFile.setLayoutData(grid);
		selectFile.setText("Choose file");
		//Listener for the select file button
		selectFile.addSelectionListener(new SelectionListener() {
			
		  	@Override
		   	public void widgetSelected(SelectionEvent arg0) {
		  		//Open a fileDialog window and filer to "*.maz" format.
		  		FileDialog selectFile = new FileDialog(shell);
		   		String[] filterExt = {"*.maz"};
		   		selectFile.setFilterExtensions(filterExt);
		   		file = selectFile.open();
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
	    //Listener for the OK Button.
	    buttonOK.addSelectionListener(new SelectionListener() {
	   	@Override
	   	public void widgetSelected(SelectionEvent arg0) {
	   		//if maze name or file name is empty, pop relevant message. Else, dispose shell.
	   		name = nameText.getText();
	   		if(!name.equals("") && (!file.equals("")))
	   			shell.dispose();
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
	    //Listener for cancal button.
	    buttonCancel.addSelectionListener(new SelectionListener() {
	    	@Override
	    	public void widgetSelected(SelectionEvent arg0) {
	    		//Close shell.
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
	    //Keep shell open.
	    Display display = parent.getDisplay();
	    while (!shell.isDisposed()) {
	    	if (!display.readAndDispatch()) display.sleep();
	    }
	    //If name name and file name are not null, return command for the load action.
	    if((file != null) && (name != null)) 
	    	return file + " " + name;
	    //Else return null.
	    else
	    	return null;
	}
}
