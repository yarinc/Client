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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * The Class MazeInfo open a dialog for entering a maze name.
 */
public class MazeInfo extends Dialog {

	private String name;
	
	  /**
  	 * Instantiates a new MazeInfo object.
  	 *
  	 * @param parent The parent shell
  	 */
  	public MazeInfo(Shell parent) {
		  // Pass the default styles here
		  this(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	  }

	  /**
  	 * Instantiates a new MazeInfo object.
  	 *
  	 * @param parent The parent shell
  	 * @param style The style
  	 */
  	public MazeInfo(Shell parent, int style) {
		  // Let users override the default styles
		  super(parent, style);
		  setText("Maze Information");
	  }
	  
  	/**
  	 * Open the dialog window.
  	 *
  	 * @return The name name.
  	 */
  	public String open() {
  		//Create the window.
		Shell parent = getParent();
		Shell shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	    shell.setSize(100, 100);
	    shell.setText(getText());
	    shell.setLayout(new GridLayout(2, false));
	    //Widget to enter the maze name.
	    Label nameLabel = new Label(shell, SWT.NULL);
	    nameLabel.setText("Name: ");
	    Text text = new Text(shell, SWT.SINGLE | SWT.BORDER);
	    //Ok and cancel buttons.
	    Button buttonOK = new Button(shell, SWT.PUSH);
	    buttonOK.setText("Ok");
	    buttonOK.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
	    Button buttonCancel = new Button(shell, SWT.PUSH);
	    buttonCancel.setText("Cancel");
	    //Listener for the OK button.
	    buttonOK.addSelectionListener(new SelectionListener() {
	    	@Override
	    	public void widgetSelected(SelectionEvent arg0) {
	    		name = text.getText();
	    		//If the name is not empty, dispose shell.
	    		if(!name.equals(""))
	    			shell.dispose();
	    		//Else, pop relevant message.
	    		else { 
	    			MessageBox enterName = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
	    			enterName.setText("Error!");
	    			enterName.setMessage("Invalid name.");
	    			enterName.open();		
	    		}
	    	}
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
	    });
	    //Listener for the cancel button.
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
	    //Open window.
	    shell.open();
	    Display display = parent.getDisplay();
	    //closing window.
	    while (!shell.isDisposed()) {
	    	if (!display.readAndDispatch()) display.sleep();
	    }
	    //If name is not null, return it.
	    if (name != null)
	    	return name;
	    //Else return null.
	    else 
	    	return null;
  	}
}
