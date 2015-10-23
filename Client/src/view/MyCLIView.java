package view;

import java.util.Observable;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;


/**
 * The Class MyView communicate with the user.
 */
public class MyCLIView extends Observable implements View {

	/** The cli. */
	private CLI cli;
	
	/**
	 * Instantiates a new MyView object.
	 * @param cli the CLI object
	 */
	public MyCLIView(CLI cli) { 
		this.cli = cli;
	}
	
	/* (non-Javadoc)
	 * @see view.View#start()
	 */
	@Override
	public void start() {
		 cli.start();
	}

	/* (non-Javadoc)
	 * @see view.View#PrintStringArray(java.lang.String[])
	 */
	@Override
	public void PrintStringArray(String[] string) {
		for(String s:string)
			cli.getOut().println(s);
		cli.getOut().flush();
	}

	/* (non-Javadoc)
	 * @see view.View#printString(java.lang.String)
	 */
	@Override
	public void printString(String string) {
		cli.getOut().println(string); 
		cli.getOut().flush();
		if(string.equals("Goodbye"))
			System.exit(0);
	}

	/* (non-Javadoc)
	 * @see view.View#PrintCrossMaze(int[][])
	 */
	@Override
	public void PrintCrossMaze(int[][] maze2d) {
		int i,j;
		for(i=0;i<maze2d.length;i++){
			cli.getOut().print("[");
			for(j=0;j<maze2d[i].length;j++){
				cli.getOut().print(maze2d[i][j]);
					if(j != maze2d[i].length - 1)
						cli.getOut().print(", ");
				}
			cli.getOut().println("]");
		}
		cli.getOut().print("\n");
		cli.getOut().flush();
	}
	
	/* (non-Javadoc)
	 * @see view.View#inputToPresenter(java.lang.String)
	 */
	public void inputToPresenter(String line) {
		this.setChanged();
		this.notifyObservers(line);
	}

	/* (non-Javadoc)
	 * @see view.View#printMaze(algorithms.mazeGenerators.Maze3d)
	 */
	@Override
	public void printMaze(Maze3d maze) {
		cli.getOut().println(maze.toString()); 
		cli.getOut().flush();
	}

	/* (non-Javadoc)
	 * @see view.View#handleSolution(algorithms.search.Solution)
	 */
	@Override
	public void handleSolution(Solution<Position> solution) {
		cli.getOut().println(solution.toString()); 
		cli.getOut().flush();
	}
}
