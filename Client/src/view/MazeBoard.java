package view;

import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;

/**
 * The Class MazeBoard.
 */
public abstract class MazeBoard extends Canvas{
	
	protected Maze3d fullMaze;
	protected int[][] mazeData;
	protected Character player;
	protected Character start;
	protected Character end;
	
	/**
	 * Instantiates a new maze board object.
	 *
	 * @param parent The parent composite
	 * @param style The style
	 */
	public MazeBoard(Composite parent, int style) {
		super(parent, style);
	}
	
	/**
	 * Sets the 3d maze.
	 * @param maze The new full maze
	 */
	public void setFullMaze(Maze3d maze){
		this.fullMaze = maze;
	}
	
	/**
	 * Sets the 2d maze.
	 *
	 * @param maze The new 2d maze
	 */
	public void setMazeData(int[][] maze){
		this.mazeData = maze;
	}
	
	/**
	 * Sets the player in a Position.
	 *
	 * @param player the new player
	 */
	public void setPlayer(Position player){
		this.player = new Character(player);
	}
	
	/**
	 * Sets the start point.
	 *
	 * @param start The start point
	 */
	public void setStart(Position start){
		this.start = new Character(start);
	}
	
	/**
	 * Sets the end point.
	 *
	 * @param end the end point
	 */
	public void setEnd(Position end){
		this.end = new Character(end);
	}
	
	/**
	 * Paint the board.
	 */
	public abstract void paint();
	
	/**
	 * Sets the character position.
	 *
	 * @param place The new character position
	 */
	public abstract void setCharacterPosition(Position place);

	/**
	 * Move player up.
	 */
	public abstract void moveUp();

	/**
	 * Move player down.
	 */
	public abstract void moveDown();

	/**
	 * Move player left.
	 */
	public abstract void moveLeft();

	/**
	 * Move player right.
	 */
	public abstract void moveRight();

	/**
	 * Move player one level up.
	 */
	public abstract void moveUpLvl();
	
	/**
	 * Move player one level down.
	 */
	public abstract void moveDownLvl();

}