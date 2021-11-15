import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;

import java.util.ArrayList;
import java.awt.Color;

/**
 * A <code>KingCrab</code>  A KingCrab gets the actors to be processed in 
 * the same way a CrabCritter does. A KingCrab causes each actor that it 
 * processes to move one location further away from the KingCrab. If the 
 * actor cannot move away, the KingCrab removes it from the grid.<br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class KingCrab extends CrabCritter 
{
    /**
     * If the actor cannot move away, the KingCrab 
     * removes it from the grid.
     */
    public void processActors(ArrayList<Actor> actors)
    {
        for (Actor actor : actors)
        {
        	if(!(actor instanceof KingCrab))
            {
                ArrayList<Location> loc = getGrid().getEmptyAdjacentLocations(actor.getLocation());
                if(loc.size() == 0 || actor instanceof Flower || actor instanceof Rock) 
                {
                    actor.removeSelfFromGrid();
                }
                else 
                {
                    int n = loc.size();
                    int r = (int) (Math.random() * n);
                    actor.moveTo(loc.get(r));
                }
            }
        }
    }
}
