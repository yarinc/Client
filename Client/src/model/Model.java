package model;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import presenter.MazeGenerator;
import presenter.Solve;

/**
 * The Interface Model.
 */
public interface Model {

	/**
	 * Generate an array of all file and folders on the given path.
	 *
	 * @param string the path
	 */
	void getDir(String string);
	
	/**
	 * Starting a thread that creates maze with the given parameters.
	 *
	 * @param parameters the parameters
	 * @param mazeGenerator the maze generator
	 */
	void CreateMaze(String[] parameters, MazeGenerator mazeGenerator);
	
	/**
	 * Generate maze with the given parameters.
	 *
	 * @param parameters the parameters
	 * @return the maze3d
	 */
	Maze3d MazeGen(String[] parameters);
	
	/**
	 * Selecting the maze from a HashMap of mazes.
	 * @param parameters the parameters
	 */
	void GetMaze(String[] parameters);
	
	/**
	 * Sending a crossed maze to a view.
	 * @param parameters the parameters
	 */
	void PrintCrossMaze(String[] parameters);
	
	/**
	 * Save a given maze to a given path.
	 * @param parameters the parameters
	 */
	void saveMaze(String[] parameters);
	
	/**
	 * Load a maze from a file.
	 * @param parameters the parameters
	 */
	void loadMaze(String[] parameters);
	
	/**
	 * Sending the size of a maze on the RAM to the view.
	 * @param parameters the parameters
	 */
	void mazeSizeRAM(String[] parameters);
	
	/**
	 * Sending the size of a maze on the disk to the view.
	 * @param parameters the parameters
	 */
	void mazeSizeFile(String[] parameters);
	
	/**
	 * Starting a thread that solves maze with the given algorithm in the parameters.
	 *
	 * @param parameters the parameters
	 * @param solve the solve
	 */
	void solveMaze(String[] parameters, Solve solve);
	
	/**
	 * Solve a maze with the given algorithm.
	 *
	 * @param parameters the parameters
	 * @return the solution
	 */
	Solution<Position> solutionGenerator(String[] parameters);
	
	/**
	 * Sending the solution to a maze to the view.
	 *
	 * @param parameters the parameters
	 */
	void getSolution(String[] parameters);
	
	/**
	 * Wait for threads to finish before exiting the CLI.
	 */
	void waitForThreads();

	/**
	 * Solve maze from a given position .
	 *
	 * @param parameters the parameters
	 */
	void solveFromPoint(String[] parameters);

	/**
	 * Sets the properties from an XML file.
	 *
	 * @param parameters the new properties
	 */
	void setProperties(String[] parameters);
}
