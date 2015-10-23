package presenter;

/**
 * The Interface Command.
 */
public interface Command {
	
	/**
	 * Starting the command.
	 * @param parameters the parameters of the command
	 */
	void doCommand(String[] parameters);
	
	/**
	 * Sets the parameters of a command.
	 * @param parameters the new parameters
	 */
	void setParameters(String[] parameters);
}
