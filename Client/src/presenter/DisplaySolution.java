package presenter;

import model.Model;
import view.View;

/**
 * The Class DisplaySolution gets a maze name
 * and sending to the view the solution.
 */
public class DisplaySolution extends AbstractCommand implements Command {

	/**
	 * Instantiates a new DisplaySolution object.
	 * @param view the view
	 * @param model the model
	 */
	public DisplaySolution(View view, Model model) {
		super(view, model);
	}

	/* (non-Javadoc)
	 * @see controller.Command#doCommand(java.lang.String[])
	 */
	@Override
	public void doCommand(String[] parameters) {
		model.getSolution(parameters);

	}

}
