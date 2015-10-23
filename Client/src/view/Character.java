package view;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Color;

import algorithms.mazeGenerators.Position;

/**
 * The Class Character represent a location in the GUI maze.
 */
public class Character {
	
	private Position location;
		
	/**
	 * Instantiates a new character.
	 * @param position The location by a Position class
	 */
	public Character(Position position) {
		location = position;
	}
	
	/**
	 * Paint the location on them maze.
	 *
	 * @param e The event
	 * @param w the width
	 * @param h the height
	 * @param color The color
	 */
	public void paint(PaintEvent e,int w,int h, Color color){
		e.gc.setForeground(color);
		e.gc.setBackground(color);
		e.gc.fillOval(location.getX()*w,location.getZ()*h, w, h);
	}

	/**
	 * Gets the location.
	 *
	 * @return The location
	 */
	public Position getLocation() {
		return location;
	}
	
	/**
	 * Sets the location.
	 *
	 * @param place The new location
	 */
	public void SetLocation(Position place) { 
		location = place;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Character other = (Character) obj;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		return true;
	}
}
