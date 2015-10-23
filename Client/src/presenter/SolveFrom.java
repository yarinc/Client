package presenter;

import model.Model;
import view.View;

/**
 * The Class SolveFrom gets a maze name and a position
 * in the maze, and solve it from the given location.
 */
public class SolveFrom extends AbstractCommand implements Command {

	/**
	 * Instantiates a new SolveFrom object.
	 *
	 * @param view The view
	 * @param model The model
	 */
	public SolveFrom(View view, Model model) {
		super(view, model);
	}

	/* (non-Javadoc)
	 * @see presenter.Command#doCommand(java.lang.String[])
	 */
	@Override
	public void doCommand(String[] parameters) {
		model.solveFromPoint(parameters);
	}


}
