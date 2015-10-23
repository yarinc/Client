package view;

import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;


/**
 * The Class MyGUIView is a class for playing a 3d maze in GUI application.
 */
public class MyGUIView extends BasicWindow implements View {
	
	private MazeBoard board;
	private Button displayButton;
	private Button hintButton;
	private Button solveButton;
	private String currentMaze;
	private Solution<Position> solution;
	private Timer timer;
	private TimerTask task;
	private KeyPress key;
	
	/**
	 * Instantiates a new MyGUIView object.
	 *
	 * @param title The window title
	 * @param width The window width
	 * @param height tThe window height
	 */
	public MyGUIView(String title, int width, int height) {
		super(title, width, height);
	}

	/* (non-Javadoc)
	 * @see view.View#start()
	 */
	@Override
	public void start() {
		this.run();
	}

	/* (non-Javadoc)
	 * @see view.View#PrintStringArray(java.lang.String[])
	 */
	@Override
	public void PrintStringArray(String[] string) {
		

	}
	
	/* (non-Javadoc)
	 * @see view.View#printMaze(algorithms.mazeGenerators.Maze3d)
	 */
	@Override
	public void printMaze(Maze3d maze) {
		//Set some maze parameters in the board.
		board.setFullMaze(maze);
		board.setPlayer(maze.getStartPosition());
		board.setStart(new Position(maze.getStartPosition()));
		board.setEnd(maze.getGoalPosition());
	}

	/* (non-Javadoc)
	 * @see view.View#printString(java.lang.String)
	 */
	@Override
	public void printString(String string) {
		//If the received string doesn't start with "Solution", Pop a relevant message.
		if(!string.startsWith("Solution")) {
			display.syncExec(new Runnable() {
				public void run() {
					MessageBox message = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
					message.setMessage(string);
					message.setText("Message");
					message.open();
				}
			});
		}
	}

	/* (non-Javadoc)
	 * @see view.View#PrintCrossMaze(int[][])
	 */
	@Override
	public void PrintCrossMaze(int[][] maze2d) {
		
	}

	/* (non-Javadoc)
	 * @see view.View#inputToPresenter(java.lang.String)
	 */
	@Override
	public void inputToPresenter(String line) {
		this.setChanged();
		this.notifyObservers(line);
	}

	/* (non-Javadoc)
	 * @see view.BasicWindow#initWidgets()
	 */
	@Override
	void initWidgets() {
		shell.setLayout(new GridLayout(2, false));
		//Listener for the shell. 
		shell.addListener(SWT.Close, new Listener() {
	        public void handleEvent(Event event) {
	        	//exit application when pressing the X button.
	        	inputToPresenter("exit");
	        	shell.dispose();
	        	System.exit(0);
	        }
	    });
		//Create a menu bar.
		Menu bar = new Menu (shell, SWT.BAR);
		shell.setMenuBar(bar);
		//Add "File" menuItem to the menu.
		MenuItem fileItem = new MenuItem (bar, SWT.CASCADE);
		fileItem.setText ("File");
		//Create a menu bar for "File".
		Menu submenu = new Menu (shell, SWT.DROP_DOWN );
		fileItem.setMenu (submenu);
		//Add "Open properties" to the "File" sub menu.
		MenuItem openPropertiesItem = new MenuItem (submenu, SWT.PUSH);
		openPropertiesItem.setText("&Open properties");
		//Listener for openProperties.
		openPropertiesItem.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				//Open file dialog to choose an XML file.
		   		FileDialog selectFile = new FileDialog(shell);
		   		String[] filterExt = {"*.xml"};
		   		selectFile.setFilterExtensions(filterExt);
		   		String file = selectFile.open();
		   		//Load the XML file.
		   		inputToPresenter("properties " + file);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		//Add "Exit" to the "File" sub menu.
		MenuItem exitItem = new MenuItem (submenu, SWT.PUSH);
		exitItem.setText("&Exit");
		//Listener for Exit.
		exitItem.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				//exit application when pressing the X button.
				inputToPresenter("exit");
				shell.dispose();
	        	System.exit(0);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		//Creating "Options" menuItem.
		MenuItem options = new MenuItem (bar, SWT.CASCADE);
		options.setText("Options");
		//Create Options sub menu and attach to the main menu.
		Menu subOptions = new Menu (shell, SWT.DROP_DOWN);
		options.setMenu(subOptions);
		//Add "Save" to the options menu.
		MenuItem saveItem = new MenuItem (subOptions, SWT.PUSH);
		saveItem.setText("&Save");
		//Listener for "Save"
		saveItem.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				//Open SaveMaze dialog and if the parameters aren't null - run the run the save command and enable the display button.
				SaveMaze save = new SaveMaze(shell);
				String parameters = save.open();
				if(parameters != null) {
					String command = "save maze " + parameters;
					inputToPresenter(command);
					displayButton.setEnabled(true);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		//Add Load" to the options menu.
		MenuItem loadItem = new MenuItem (subOptions, SWT.PUSH);
		loadItem.setText("&Load");
		//Listener for "Load"
		loadItem.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				//Open LoadMaze dialog and if the parameters aren't null - run the load command.
				LoadMaze load = new LoadMaze(shell);
				String parameters = load.open();
				if(parameters != null) {
					String command = "load maze " + parameters;
					inputToPresenter(command);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		//Add "Size On RAM" to the options menu.
		MenuItem SizeRAMItem = new MenuItem (subOptions, SWT.PUSH);
		SizeRAMItem.setText("&Size on RAM");
		//Listener for "Size On RAM"
		SizeRAMItem.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				//Open MazeInfo dialog and if the parameters aren't null - run the "Size On RAM" command 
				MazeInfo sizeRAM = new MazeInfo(shell);
				String parameters = sizeRAM.open();
				if (parameters != null) {
					String command = "maze size " + parameters;
					inputToPresenter(command);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		//Add "Size On Disk" to the options menu.
		MenuItem SizeDiskItem = new MenuItem (subOptions, SWT.PUSH);
		SizeDiskItem.setText("&Size on disk");
		//Listener for "Size On Disk"
		SizeDiskItem.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				//Open MazeInfo dialog and if the parameters aren't null - run the "Size On Disk" command 
				MazeInfo sizeDisk = new MazeInfo(shell);
				String parameter = sizeDisk.open();
				if(parameter != null) { 
					String command = "file size " + parameter;
					inputToPresenter(command);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		//Create Generate Button.
		Button generateButton=new Button(shell, SWT.PUSH);
		generateButton.setText("Generate");
		generateButton.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		//Listener for Generate button.
		generateButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				//Open GenerateInput Dialog and if parameters aren't null - run the Generate maze command.
				GenerateInput input = new GenerateInput(shell);
				String parameter = input.open();
				if (parameter != null) {
					String command = "generate 3d maze " + parameter;
					inputToPresenter(command);
					//Enable display button.
					displayButton.setEnabled(true);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		//Create the maze board object.
		board = new Maze2D(shell, SWT.BORDER);
		board.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,5));
		//Create display button.
		displayButton=new Button(shell, SWT.PUSH);
		displayButton.setText("Display");
		//Disable display button.
		displayButton.setEnabled(false);
		displayButton.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		//Listener for display button.
		displayButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				//Open MazeInfo dialog and if parameters aren't null - run the display command.
				MazeInfo maze = new MazeInfo(shell);
				String parameters = maze.open();
				if(parameters != null) {
					String command = "display " + parameters;
					currentMaze = parameters;
					inputToPresenter(command);
					//If key listener is not null - the maze is still listening to key activities.
					if(key != null)
						//Remove the Key Listener
						board.removeKeyListener(key);
					//Enable hint and solve buttons.
					hintButton.setEnabled(true);
					solveButton.setEnabled(true);
					//Paint and redraw the board.
					board.paint();
					board.redraw();
					//Create keyListener and Listen to key pressing.
					key = new KeyPress(board, hintButton, solveButton);
					board.addKeyListener(key);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		//Create hint button.
		hintButton = new Button(shell, SWT.PUSH);
		hintButton.setText("Hint");
		//Disable Hint button.
		hintButton.setEnabled(false);
		hintButton.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		//Listener for hint button.
		hintButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				//Send command to solve maze from the current location.
				String command = "from " + currentMaze + " " + board.player.getLocation().getX() + " " + board.player.getLocation().getY() + " " + board.player.getLocation().getZ();
				inputToPresenter(command);
				//Remove the first position from the solution(current position).
				solution.getSolution().remove(0);
				//Set the player to the first position in the solution.
				board.setCharacterPosition(solution.getSolution().get(0));
				//If player reached the end position, disable hint and solve buttons.
				if(board.player.getLocation().equals(board.end.getLocation())) {
					hintButton.setEnabled(false);
					solveButton.setEnabled(false);
				}
				//Redraw board.
				board.redraw();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		//Create solve button.
		solveButton = new Button(shell, SWT.PUSH);
		solveButton.setText("Solve");
		//disable sole button.
		solveButton.setEnabled(false);
		solveButton.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		//Listener for solve button.
		solveButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				//Disable hint solve generate and display button.
				hintButton.setEnabled(false);
				solveButton.setEnabled(false);
				generateButton.setEnabled(false);
				displayButton.setEnabled(false);
				////Send command to solve maze from the current location.
				String command = "from " + currentMaze + " " + board.player.getLocation().getX() + " " + board.player.getLocation().getY() + " " + board.player.getLocation().getZ();
				inputToPresenter(command);
				//Remove first Position from the solution.
				solution.getSolution().remove(0);
				//New timer task to move the player in the solution direction.
				timer=new Timer();
						task=new TimerTask() {
							@Override
							public void run() {
								display.syncExec(new Runnable() {
									@Override
									public void run() {
										//If player reached to the end point
										if(board.player.getLocation().equals(board.end.getLocation())) {
											//Cancel the timer task and enable generate and display buttons.
											task.cancel();
											timer.cancel();
											generateButton.setEnabled(true);
											displayButton.setEnabled(true);
										}
										else {
											//Move the player by one position, redraw the board and remove the position from the solution.
											board.setCharacterPosition(solution.getSolution().get(0));
											board.redraw();
											solution.getSolution().remove(0);
										}
									}
								});
							}
						};				
						timer.scheduleAtFixedRate(task, 0, 500);
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
	}

	/* (non-Javadoc)
	 * @see view.View#handleSolution(algorithms.search.Solution)
	 */
	@Override
	public void handleSolution(Solution<Position> solution) {
		this.solution = solution;
	}
	
}
