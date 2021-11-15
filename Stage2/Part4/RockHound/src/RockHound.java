import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;

import java.util.ArrayList;

/**
 * A <code>RockHound</code> A RockHound gets the actors to be processed 
 * in the same way as a Critter. It removes any rocks in that list from the grid. 
 * A RockHound moves like a Critter. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */ 
public class RockHound extends Critter 
{
    /**
     * Remove rocks.
     */
    public void processActors(ArrayList<Actor> actors) 
    {
        for (Actor act : actors) 
        {
            if (act instanceof Rock) 
                act.removeSelfFromGrid();
        }
    }
}

