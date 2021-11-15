import info.gridworld.grid.Grid;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.util.*;

/**
 * An <code>UnBoundedGrid2</code> is a rectangular grid with an unbounded number of rows and
 * columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class UnboundedGrid2<E> extends AbstractGrid<E> 
{
    private Object[][] occupantArray;
    private int gridSize;

    /**
     * Constructs an empty unbounded grid.
     */
    public UnboundedGrid2() 
    {
        occupantArray = new Object[16][16];
        gridSize = 16;
    }

    public int getNumRows()
    {
        return -1;
    }

    public int getNumCols()
    {
        return -1;
    }

    public boolean isValid(Location loc)
    {
        return loc.getRow() >= 0 && loc.getCol() >= 0;
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();
        // Look at all grid locations.
        for (int r = 0; r < gridSize; r++) 
        {
            for (int c = 0; c < gridSize; c++) 
            { 
                // If there's an object at this location, put it in the array.
                Location loc = new Location(r, c);
                if (get(loc) != null) 
                    theLocations.add(loc);
            }
        }

        return theLocations;
    }

    public E get(Location loc)
    {
        if (loc == null) 
            throw new IllegalArgumentException("loc == null");

        if (loc.getRow() >= gridSize || loc.getCol() >= gridSize) 
            return null;

        return (E) occupantArray[loc.getRow()][loc.getCol()]; 
    }

    public E put(Location loc, E obj) 
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                      + " is not valid"); 
        if (obj == null) 
            throw new IllegalArgumentException("obj == null");

        // Extend grid.
        int size = gridSize;
        while (loc.getRow() >= gridSize || loc.getCol() >= gridSize)
        {
            gridSize *= 2;
        } 
        if (gridSize != size) 
        {
            Object[][] array = new Object[gridSize][gridSize];
            for (int i = 0; i < size; i++) 
            {
                System.arraycopy(occupantArray[i], 0, array[i], 0, size);
            }
            occupantArray = array;
        }

        // Add the object to the grid.
        E oldOccupant = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }

    public E remove(Location loc)
    {
        if (!isValid(loc))
          throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
            
        // Remove the object from the grid.
        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }
}

