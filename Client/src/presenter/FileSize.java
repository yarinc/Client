package presenter;

import model.Model;
import view.View;

/**
 * The Class FileSize gets a maze name
 * and sending the view its size on the disk.
 */
public class FileSize extends AbstractCommand implements Command {

	/**
	 * Instantiates a new FileSize object.
	 * @param view the view
	 * @param model the model
	 */
	public FileSize(View view, Model model) {
		super(view, model);
	}

	/* (non-Javadoc)
	 * @see controller.Command#doCommand(java.lang.String[])
	 */
	@Override
	public void doCommand(String[] parameters) {
		model.mazeSizeFile(parameters);

	}

}
