package presenter;

import model.Model;
import view.View;

/**
 * The Class LoadMaze gets a file and maze name
 * then decompressing the file and create maze with the name given.
 */
public class LoadMaze extends AbstractCommand implements Command {

	/**
	 * Instantiates a new LoadMaze object.
	 * @param view the view
	 * @param model the model
	 */
	public LoadMaze(View view, Model model) {
		super(view, model);
	}

	/* (non-Javadoc)
	 * @see controller.Command#doCommand(java.lang.String[])
	 */
	@Override
	public void doCommand(String[] parameters) {
		model.loadMaze(parameters);

	}

}
