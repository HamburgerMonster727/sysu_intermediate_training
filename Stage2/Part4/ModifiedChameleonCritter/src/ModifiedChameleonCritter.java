import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.util.ArrayList;

import info.gridworld.actor.Rock;
import java.awt.Color;

/**
 * A <code>ModifiedChameleonCritter</code> if the list of actors to process is empty, 
 * the color of the ModifiedChameleonCritter will darken <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class ModifiedChameleonCritter extends Critter
{
    /**
     * Randomly selects a neighbor and changes this critter's color to be the
     * same as that neighbor's. If there are no neighbors, the color of the ModifiedChameleonCritter will darken.
     */
    public void processActors(ArrayList<Actor> actors)
    {
        int n = actors.size();
        if (n == 0) 
        {
            Color color = getColor();
            int red = (color.getRed() - 15) > 0 ? (color.getRed() - 15) : 0;
            int green = (color.getGreen() - 15) > 0 ? (color.getGreen() - 15) : 0;
            int blue = (color.getBlue() - 15) > 0 ? (color.getBlue() - 15) : 0;
            setColor(new Color(red, green, blue));
        }
        else
        {
            int r = (int) (Math.random() * n);
            Actor other = actors.get(r);
            setColor(other.getColor());
        }
    }

    /**
     * Turns towards the new location as it moves.
     */
    public void makeMove(Location loc)
    {
        setDirection(getLocation().getDirectionToward(loc));
        super.makeMove(loc);
    }
}



