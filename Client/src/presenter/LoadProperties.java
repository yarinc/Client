package presenter;

import model.Model;
import view.View;

/**
 * The Class LoadProperties gets a XML file path and loads it to the model.
 */
public class LoadProperties extends AbstractCommand implements Command {

	/**
	 * Instantiates a new LoadProperties object.
	 *
	 * @param view The view
	 * @param model The model
	 */
	public LoadProperties(View view, Model model) {
		super(view, model);
	}

	/* (non-Javadoc)
	 * @see presenter.Command#doCommand(java.lang.String[])
	 */
	@Override
	public void doCommand(String[] parameters) {	
		model.setProperties(parameters);
	}

}
