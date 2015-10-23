package presenter;

import model.Model;
import view.View;

/**
 * The Class SaveMaze gets a maze name and a path
 * to a file and saving the maze to the disk.
 */
public class SaveMaze extends AbstractCommand implements Command {

	/**
	 * Instantiates a new SaveMaze object.
	 * @param view the view
	 * @param model the model
	 */
	public SaveMaze(View view, Model model) {
		super(view, model);
	}

	/* (non-Javadoc)
	 * @see controller.Command#doCommand(java.lang.String[])
	 */
	@Override
	public void doCommand(String[] parameters) {
		model.saveMaze(parameters);
	}

}
