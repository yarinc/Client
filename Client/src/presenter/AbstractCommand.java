package presenter;

import model.Model;
import view.View;

/**
 * The Class AbstractCommand is abstract class
 * that represent a command which implements Command interface. 
 */
public abstract class AbstractCommand {
	
	protected View view;
	protected Model model;
	protected String[] parameters;
	
	/**
	 * Instantiates a new abstractCommand object.
	 * @param view the view
	 * @param model the model
	 */
	public AbstractCommand(View view, Model model) { 
		this.view = view;
		this.model = model;
	}
	
	/**
	 * Sets the parameters.
	 * @param parameters the new parameters
	 */
	public void setParameters(String[] parameters) { 
		this.parameters = parameters; 
	}
}
