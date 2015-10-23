package presenter;

import java.util.concurrent.Callable;

import algorithms.mazeGenerators.Maze3d;
import model.Model;
import view.View;

/**
 * The Class MazeGenerator generate a new maze on a different thread.
 */
public class MazeGenerator extends AbstractCommand implements Command, Callable<Maze3d> {

	/**
	 * Instantiates a new MazeGenerator object.
	 * @param view the view
	 * @param model the model
	 */
	public MazeGenerator(View view, Model model) {
		super(view, model);
		
	}

	/* (non-Javadoc)
	 * @see controller.Command#doCommand(java.lang.String[])
	 */
	@Override
	public void doCommand(String[] parameters) {
		model.CreateMaze(parameters, this);
	}

	/* (non-Javadoc)
	 * @see java.util.concurrent.Callable#call()
	 */
	@Override
	public Maze3d call() throws Exception {
		return model.MazeGen(parameters);
	}
}
