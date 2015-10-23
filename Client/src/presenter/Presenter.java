package presenter;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import model.Model;
import view.View;

/**
 * The Class Presenter.
 */
public class Presenter implements Observer {

	private Model model;
	private View ui;
	private HashMap<String,Command> map;

	/**
	 * Instantiates a new presenter.
	 *
	 * @param ui the ui
	 * @param model the model
	 */
	public Presenter(View ui, Model model) {
		this.model = model;
		this.ui = ui;
		map = new HashMap<String,Command>();
		map.put("dir", new Dir(this.ui,this.model));
		map.put("generate", new MazeGenerator(this.ui,this.model));
		map.put("display", new Display(this.ui,this.model));
		map.put("cross", new DisplayCross(this.ui,this.model));
		map.put("save", new SaveMaze(this.ui,this.model));
		map.put("load", new LoadMaze(this.ui,this.model));
		map.put("maze", new MazeSize(this.ui,this.model));
		map.put("file", new FileSize(this.ui,this.model));
		map.put("solve", new Solve(this.ui,this.model));
		map.put("solution", new DisplaySolution(this.ui,this.model));
		map.put("properties", new LoadProperties(this.ui,this.model));
		map.put("from", new SolveFrom(this.ui,this.model));
		map.put("exit", new Exit(this.ui,this.model));
	}
	
	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object obj) {
		if (o == model) { 
			if(obj instanceof String) 
				ui.printString((String)obj);
			else if(obj instanceof String[])
				ui.PrintStringArray((String[])obj);
			else if(obj instanceof int[][]) 
				ui.PrintCrossMaze((int[][])obj);
			else if(obj instanceof Maze3d)
				ui.printMaze((Maze3d)obj);
			else if(obj instanceof Solution<?>)
				ui.handleSolution((Solution<Position>)obj);
		}
		else if(o == ui) {
			this.manipulateInput((String)obj);
		}
		
	}
	
	/**
	 * Activate command.
	 *
	 * @param string the string
	 * @param parameters the parameters
	 */
	public void activateCommand(String string, String[] parameters) {
		try { 
			map.get(string).doCommand(parameters);
		} catch(NullPointerException e)	{
			
			ui.printString("Invalid object");
		}
	}
	
	/**
	 * Manipulate input.
	 *
	 * @param line the line
	 */
	/* (non-Javadoc)
	 * @see controller.Controller#manipulateInput(java.lang.String)
	 */
	public void manipulateInput(String line) {
		//If the input is not 'exit' and it contain one word than command not found.
		if((line.split(" ").length <= 1) && (!line.equals("exit")))
			ui.printString("Command not found");
		else {
			//Separate input by space
			String command[] = line.split(" ");
			//Validate the input by 'validateCommand' method.
			switch (command[0]) { 
			case "dir" : validateCommand(line, "dir", 4, 1);
						break;
			case "generate" : validateCommand(line, "generate", 17, 4);
						break;
			case "save" : validateCommand(line, "save", 10, 2);
						break;
			case "load" : validateCommand(line, "load", 10, 2);
						break;
			case "maze" : validateCommand(line, "maze", 10, 1);
						break;
			case "file" : validateCommand(line, "file", 10, 1);
						break;
			case "solve" : validateCommand(line, "solve", 6, 1);
						break;
			case "from" : validateCommand(line, "from",5, 4);
						break;
			case "display" : displayCommands(line, command);
						break;
			case "properties" : validateCommand(line, "properties",11, 1);
						break;
			case "exit" : validateCommand(line, "exit", 4, 1);
						break;
			default : ui.printString("Command not found");
			}
		}
	}
	
	/**
	 * Validate command checks that the input is a defined command.
	 * @param input the input from the user
	 * @param command the command identifier on the HashMap
	 * @param commandLength the command length
	 * @param numOfParameters the number of parameters
	 */
	public void validateCommand(String input, String command, int commandLength, int numOfParameters){
		String[] parameters;
		//If input starts with the command identifier.
		if((input.startsWith(command) || (input.startsWith("display " + command)))) {
			//Extract the parameters and validate their number.
			try {
				parameters = (input.substring(commandLength)).split(" ");
			//If parameter number is OK - activate the desired command.
			if(parameters.length == numOfParameters)
				activateCommand(command, parameters);
			//Else - send error.
			else
				ui.printString("Parameters error");
			} catch (IndexOutOfBoundsException e) { 
				ui.printString("Please check command.");
			}
		}
	}
	
	/**
	 * Display commands used to separate between all the 'display'
	 * commands and decide which command to initiate.
	 * @param input the input from the user
	 * @param splittedCommand the splitted command
	 */
	public void displayCommands(String input, String[] splittedCommand) {
		//If the second word is 'cross' and it's length is more then 2 - validate the display command.
		if((splittedCommand[1].equals("cross")) && (splittedCommand.length > 2)) 
			validateCommand(input, "cross", 25, 4);
		//If the length is 2 - validate the display command.
		else if(splittedCommand.length == 2)
			validateCommand(input, "display", 8, 1);
		//If the second word is 'solution' and it's length is 3 - validate the display command.
		else if ((splittedCommand[1].equals("solution")) && (splittedCommand.length == 3)) 
			validateCommand(input, "solution", 17, 1);
		//Else - print command not found.
		else
			ui.printString("Command not found.");
	}
}
