package presenter;

import model.Model;
import view.View;

/**
 * The Class MazeSize gets a maze name
 * and sending the view its size on the RAM.
 */
public class MazeSize extends AbstractCommand implements Command {

	/**
	 * Instantiates a new MazeSize object.
	 * @param view the view
	 * @param model the model
	 */
	public MazeSize(View view, Model model) {
		super(view, model);
	}

	/* (non-Javadoc)
	 * @see controller.Command#doCommand(java.lang.String[])
	 */
	@Override
	public void doCommand(String[] parameters) {
		model.mazeSizeRAM(parameters);

	}

}
