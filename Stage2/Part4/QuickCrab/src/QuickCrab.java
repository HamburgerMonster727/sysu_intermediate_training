import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * A <code>QuickCrab</code> A QuickCrab processes actors the same way a 
 * CrabCritter does.A QuickCrab moves to one of the two locations,
 * randomly selected, that are two spaces to its right or left,
 * if that location and the intervening location are both empty. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class QuickCrab extends CrabCritter 
{
    /**
     * @return list of empty locations immediately to the right and to the left
     */
    public ArrayList<Location> getMoveLocations() 
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        int[] dirs = { Location.LEFT, Location.RIGHT }; 
        for (Location loc : getLocationsInDirections(dirs)) 
            if (getGrid().get(loc) == null) 
                locs.add(loc);

        if (locs.size() == 0) 
            return super.getMoveLocations();

        return locs;
    }

    /**
     * Finds the valid adjacent locations of this critter in different
     * directions.
     * @param directions - an array of directions (which are relative to the
     * current direction)
     * @return a set of valid locations that are neighbors of the current
     * location in the given directions
     */
    public ArrayList<Location> getLocationsInDirections(int[] directions)
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid gr = getGrid();
        Location loc = getLocation();

        for (int d : directions)
        {
            Location neighborLoc1 = loc.getAdjacentLocation(getDirection() + d);
            if (gr.isValid(neighborLoc1) && getGrid().get(neighborLoc1) == null) 
            {
                Location neightborLoc2 = neighborLoc1.getAdjacentLocation(getDirection() + d);
                if (gr.isValid(neightborLoc2)) 
                    locs.add(neightborLoc2);
            }
        }

        return locs;
    }
}