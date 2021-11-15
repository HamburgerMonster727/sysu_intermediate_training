import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * A <code>Jumper</code> is an actor that can jump.<br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class Jumper extends Actor
{
    /**
     * Constructs a red Jumper.
     */
    public Jumper()
    {
        setColor(Color.RED);
    }

    /**
     * Constructs a Jumper of a given color.
     * @param jumperColor the color for this jumper
     */
    public Jumper(Color jumperColor)
    {
        setColor(jumperColor);
    }

    /**
     * Moves if it can move, turns otherwise
     */
    public void act()
    {
        if (canMove()) 
            move();
        else 
            turn();
    }

    /**
     * Turns the Jumper 45 degrees to the right without changing its location.
     */
    public void turn()
    {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }

    /**
     * Moves the Jumper forward.
     */
    public void move()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null) 
            return;
        Location loc = getLocation();
        Location next = (loc.getAdjacentLocation(getDirection())).getAdjacentLocation(getDirection());
        if (gr.isValid(next)) 
            moveTo(next);
        else 
            removeSelfFromGrid();
    }

    /**
     * Tests whether this jumper can move forward into a location that is empty or
     * contains a flower or a bug.
     * @return true if this jumper can move.
     */
    public boolean canMove()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null) 
            return false;
        Location loc = getLocation();
        Location next = (loc.getAdjacentLocation(getDirection())).getAdjacentLocation(getDirection());
        if (!gr.isValid(next)) 
            return false;
        Actor neighbor = gr.get(next);
        return (neighbor == null) || (neighbor instanceof Flower) || (neighbor instanceof Bug);
        // ok to move into empty location or onto flower or bug
        // not ok to move onto any other actor
    }
}

