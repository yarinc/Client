package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Button;

import algorithms.mazeGenerators.Position;

/**
 * The Class KeyPress.
 * The class implements a KeyListener interface, in order to perform actions upon key presses.
 */
public class KeyPress implements KeyListener {
	
	private MazeBoard board;
	private Button hintButton;
	private Button solveButton;
	
	/**
	 * Instantiates a new key press object.
	 *
	 * @param board the board
	 * @param hintButton the hint button
	 * @param solveButton the solve button
	 */
	public KeyPress(MazeBoard board, Button hintButton, Button solveButton) {
		this.board = board;
		this.hintButton = hintButton;
		this.solveButton = solveButton;
	}

	/**
	 *  Invoked when a key has been pressed.
	 *
	 * @param e the e
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.keyCode == SWT.ARROW_UP) {
			//If the press key is arrow up, move player up and redraw board.
			board.moveUp();
			board.redraw();
			if(board.player.equals(board.end)) {
				//If the player reached to the end position, remove listener and disable buttons.
				board.removeKeyListener(this);
				hintButton.setEnabled(false);
				solveButton.setEnabled(false);
			}
		}
		
		else if(e.keyCode == SWT.ARROW_DOWN) {
			//If the press key is arrow down, move player down and redraw board.
			board.moveDown();
			board.redraw();
			if(board.player.equals(board.end)) {
				//If the player reached to the end position, remove listener and disable buttons.
				board.removeKeyListener(this);
				hintButton.setEnabled(false);
				solveButton.setEnabled(false);
			}
		}
		
		else if(e.keyCode == SWT.ARROW_LEFT) {
			//If the press key is arrow left, move player left and redraw board.
			board.moveLeft();
			board.redraw();
			if(board.player.equals(board.end)) {
				//If the player reached to the end position, remove listener and disable buttons.
				board.removeKeyListener(this);
				hintButton.setEnabled(false);
				solveButton.setEnabled(false);
			}
		}
		else if(e.keyCode == SWT.ARROW_RIGHT) {
			//If the press key is arrow right, move player right and redraw board.
			board.moveRight();
			board.redraw();
			if(board.player.equals(board.end)) {
				//If the player reached to the end position, remove listener and disable buttons.
				board.removeKeyListener(this);
				hintButton.setEnabled(false);
				solveButton.setEnabled(false);
			}
		}
		else if(e.keyCode == SWT.PAGE_UP) {
			if((board.player.getLocation().getY() < board.fullMaze.getBounds().getY()) && (board.fullMaze.getMazeValue(new Position(board.player.getLocation().getX(),board.player.getLocation().getY() + 1,board.player.getLocation().getZ())) == 0)) {
				//If the press key is page up and a higher level is available, move player one level up and redraw board.
				board.moveUpLvl();
				board.redraw();
				if(board.player.equals(board.end)) { 
					board.removeKeyListener(this);
					hintButton.setEnabled(false);
					solveButton.setEnabled(false);
				}
			}
		}
		else if(e.keyCode == SWT.PAGE_DOWN) {
			if((board.player.getLocation().getY() > 0) && (board.fullMaze.getMazeValue(new Position(board.player.getLocation().getX(),board.player.getLocation().getY() - 1,board.player.getLocation().getZ())) == 0)) {
				//If the press key is page down and a lower level is available, move player one level down and redraw board.
				board.moveDownLvl();
				board.redraw();
				if(board.player.equals(board.end)) {
					//If the player reached to the end position, remove listener and disable buttons.
					board.removeKeyListener(this);
					hintButton.setEnabled(false);
					solveButton.setEnabled(false);
				}
			}
		}
	}

	/**
	 * Invoked when a key has been released.
	 *
	 * @param arg0 the arg0
	 */
	@Override
	public void keyReleased(KeyEvent arg0) { }

}
