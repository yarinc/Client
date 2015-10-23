package presenter;

import model.Model;
import view.View;

/**
 * The Class Display gets a maze name and sending to the view
 * the maze.
 */
public class Display extends AbstractCommand implements Command {

	/**
	 * Instantiates a new Display object.
	 * @param view the view
	 * @param model the model
	 */
	public Display(View view, Model model) {
		super(view, model);
	}

	/* (non-Javadoc)
	 * @see controller.Command#doCommand(java.lang.String[])
	 */
	@Override
	public void doCommand(String[] parameters) {
		model.GetMaze(parameters);

	}
}
