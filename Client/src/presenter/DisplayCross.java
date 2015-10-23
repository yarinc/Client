package presenter;

import model.Model;
import view.View;

/**
 * The Class DisplayCross gets a cross section (X,Y,Z) with the cross number and a maze name
 * and sending to the view the 2D maze.
 */
public class DisplayCross extends AbstractCommand implements Command {

	/**
	 * Instantiates a new DisplayCross object.
	 * @param view the view
	 * @param model the model
	 */
	public DisplayCross(View view, Model model) {
		super(view, model);
	}

	/* (non-Javadoc)
	 * @see controller.Command#doCommand(java.lang.String[])
	 */
	@Override
	public void doCommand(String[] parameters) {
		model.PrintCrossMaze(parameters);
	}

}
