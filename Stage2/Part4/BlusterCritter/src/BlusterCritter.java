import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.Rock;

import java.util.ArrayList;
import java.awt.Color;

/**
 * A <code>ChameleonKid</code> A BlusterCritter looks at all of the neighbors 
 * within two steps of its current location. It counts the number of critters 
 * in those locations. If there are fewer than c critters, the BlusterCritter's 
 * color gets brighter. If there are c or more critters, the BlusterCritter's 
 * color darkens. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class BlusterCritter extends Critter 
{
    private int courage;

    /**
     * Constructs a BlusterCritter with a given courage
     * @param courageSize the courage
     */
    public BlusterCritter(int courageSize) {
        this.courage = courageSize;
    }

    /**
     * A BlusterCritter gets the actors which are within 2 steps the BlusterCritter
     * @return a list of actors occupying these locations
     */
    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        Grid<Actor> grid = getGrid();
        if (grid == null) 
        {
            return actors;
        }

        int row = getLocation().getRow();
        int col = getLocation().getCol();
        for (int i = col - 2;  i <= col + 2; i++) 
        {
            for (int j = row - 2; j <= row + 2; j++) 
            {
                if (!(i == col && j == row)) 
                {
                    Location location = new Location(j, i);
                    if (grid.isValid(location)) 
                    {
                        Actor actor = grid.get(location);
                        if (actor != null) 
                            actors.add(actor);
                    }
                }
            }
        }
        return actors;
    }
    
    /**
     * If the number of actors bigger than the courage of BlusterCritter,
     * the color of the ModifiedChameleonCritter will darken, otherwise, 
     * it will brighter.
     */
    public void processActors(ArrayList<Actor> actors)
    {
        if (actors.size() < courage) 
        {
            Color color = getColor();
            int red = (color.getRed() + 15) < 255 ? (color.getRed() + 15) : 255;
            int green = (color.getGreen() + 15) < 255 ? (color.getGreen() + 15) : 255;
            int blue = (color.getBlue() + 15) < 255 ? (color.getBlue() + 15) : 255;
            setColor(new Color(red, green, blue));
        } 
        else 
        {
            Color color = getColor();
            int red = (color.getRed() - 15) > 0 ? (color.getRed() - 15) : 0;
            int green = (color.getGreen() - 15) > 0 ? (color.getGreen() - 15) : 0;
            int blue = (color.getBlue() - 15) > 0 ? (color.getBlue() - 15) : 0;
            setColor(new Color(red, green, blue));
        }
    }
}

