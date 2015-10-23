package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The Class CLI starts in a new thread a CLI for the user.
 */
public class CLI implements Runnable {
	
	private BufferedReader in; 
	private PrintWriter out;
	private View view;
	
	/**
	 * Gets in.
	 * @return the in
	 */
	public BufferedReader getIn() {
		return in;
	}
	
	/**
	 * Sets the view.
	 * @param view the new view
	 */
	public void setView(View view) { 
		this.view = view;
	}
	
	/**
	 * Gets out.
	 * @return the out
	 */
	public PrintWriter getOut() {
		return out;
	}
	
	/**
	 * Instantiates a new CLI object.
	 * @param in the in object
	 * @param out the out object
	 */
	public CLI(BufferedReader in, PrintWriter out) { 
		this.in = in;
		this.out = out;
	}
	
	/**
	 * Start the new thread.
	 */
	public void start() { 
		new Thread(this).start();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		String line;
		try {
			//Getting the input from the BufferdReader as long as it's not 'exit'.
			while (!(line = in.readLine()).equals("exit")) {
				//sending the input to the presenter.
				view.inputToPresenter(line);
			}
			//'exit' has entered - sending it to the presenter.
			view.inputToPresenter(line);
		} catch (IOException e) {
			//In case an exception throws - send relevant message.
			view.printString("I/O error occurred.");
		}
	}
}
