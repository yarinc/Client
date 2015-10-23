package presenter;

import model.Model;
import view.View;

/**
 * The Class Dir gets a path and sending to the view
 * the list of file and folder.
 */
public class Dir extends AbstractCommand implements Command {
	
	/**
	 * Instantiates a new Dir object.
	 * @param view the view
	 * @param model the model
	 */
	public Dir(View view, Model model) {
		super(view, model);
	}

	/* (non-Javadoc)
	 * @see controller.Command#doCommand(java.lang.String[])
	 */
	@Override
	public void doCommand(String[] parameters) {
		model.getDir(parameters[0]);
	}
}
