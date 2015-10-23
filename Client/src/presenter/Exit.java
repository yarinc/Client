package presenter;

import model.Model;
import view.View;

/**
 * The Class Exit waiting for all threads
 * to finish and exit the program.
 */
public class Exit extends AbstractCommand implements Command {

	/**
	 * Instantiates a new Exit object.
	 * @param view the view
	 * @param model the model
	 */
	public Exit(View view, Model model) {
		super(view, model);
	}

	/* (non-Javadoc)
	 * @see controller.Command#doCommand(java.lang.String[])
	 */
	@Override
	public void doCommand(String[] parameters) {
		model.waitForThreads();
	}

}
