package view;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Position;

/**
 * The Class Maze2D responsible for drawing the maze and performing basic actions.
 */
public class Maze2D extends MazeBoard{

	 /**
 	 * Instantiates a new maze2D object.
 	 *
 	 * @param parent The parent shell
 	 * @param style The style
 	 */
 	public Maze2D(Composite parent,int style){
	        super(parent, style);
	 }
	 
	 /* (non-Javadoc)
 	 * @see view.MazeBoard#paint()
 	 */
 	public void paint() {
	    //Set a white background.
	    setBackground(new Color(null, 255, 255, 255));
	    //Adding a paint listener
	    addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				//Setting background color.
				e.gc.setForeground(new Color(null,0,0,0));
				e.gc.setBackground(new Color(null,0,0,0));
				//Getting the 2d maze by the player location.
				mazeData = fullMaze.getCrossSectionByY(player.getLocation().getY());
				//Getting the proportional size of the maze.
				int width=getSize().x;
				int height=getSize().y;
				int w=width/mazeData.length;
				int h=height/mazeData[0].length;
				//Drawing walls.
				   for(int i=0;i<mazeData.length;i++)
					      for(int j=0;j<mazeData[i].length;j++){
					          int x=i*w;
					          int y=j*h;
					          if(mazeData[i][j]!=0)
					              e.gc.fillRectangle(x,y,w,h);
					      }
				//Painting the player, end point and start point
				player.paint(e, w,h, new Color(null,255,0,0));
				if(player.getLocation().getY() == start.getLocation().getY())
					start.paint(e, w, h, new Color(null,0,255,0));
				if(player.getLocation().getY() == end.getLocation().getY())
					end.paint(e, w, h, new Color(null,0,0,255));
				//Pop image when player reached to the end point.
				if(player.equals(end)) {
					Image image = new Image(getDisplay(), "resource\\win.jpg");
					e.gc.drawImage(image,0,0, 236,334,(width-236)/2, (height-334)/2,236,334);
				}
			}
	    });
	 }

	/* (non-Javadoc)
	 * @see view.MazeBoard#setCharacterPosition(algorithms.mazeGenerators.Position)
	 */
	@Override
	public void setCharacterPosition(Position place) {
		player.SetLocation(place);
	}

	/* (non-Javadoc)
	 * @see view.MazeBoard#moveUp()
	 */
	@Override
	public void moveUp() {
		if ((player.getLocation().getZ() > 0) && (mazeData[player.getLocation().getX()][player.getLocation().getZ() - 1] == 0))
			player.getLocation().setZ(player.getLocation().getZ() - 1);
	}

	/* (non-Javadoc)
	 * @see view.MazeBoard#moveDown()
	 */
	@Override
	public void moveDown() {
		if ((player.getLocation().getZ() < (mazeData[player.getLocation().getX()].length) - 1) && (mazeData[player.getLocation().getX()][player.getLocation().getZ() + 1] == 0))
			player.getLocation().setZ(player.getLocation().getZ() + 1);
	}

	/* (non-Javadoc)
	 * @see view.MazeBoard#moveLeft()
	 */
	@Override
	public void moveLeft() {
		if ((player.getLocation().getX() > 0) && (mazeData[player.getLocation().getX() - 1][player.getLocation().getZ()] == 0))
			player.getLocation().setX(player.getLocation().getX() - 1);
	}

	/* (non-Javadoc)
	 * @see view.MazeBoard#moveRight()
	 */
	@Override
	public void moveRight() {
		if ((player.getLocation().getX() < (mazeData.length) - 1) && (mazeData[player.getLocation().getX() + 1][player.getLocation().getZ()] == 0)) 
			player.getLocation().setX(player.getLocation().getX() + 1);
	}

	/* (non-Javadoc)
	 * @see view.MazeBoard#moveUpLvl()
	 */
	@Override
	public void moveUpLvl() {
		player.getLocation().setY(player.getLocation().getY() + 1);
	}

	/* (non-Javadoc)
	 * @see view.MazeBoard#moveDownLvl()
	 */
	@Override
	public void moveDownLvl() {
		player.getLocation().setY(player.getLocation().getY() - 1);

	}
}
