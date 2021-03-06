import info.gridworld.grid.Grid;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * A <code>SparseBoundedGrid2</code> is a rectangular grid with a finite number of
 * rows and columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class SparseBoundedGrid2<E> extends AbstractGrid<E> {
    private Map<Location, E> occupantArray;// the HaspMap storing grid elements
    private int rows; 
    private int columns;
    
    /**
     * Constructs an empty bounded grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * @param rows number of rows in BoundedGrid
     * @param cols number of columns in BoundedGrid
     */
    public SparseBoundedGrid2(int rows, int cols) 
    {
        if (rows <= 0) 
            throw new IllegalArgumentException("rows <= 0");    
        if (cols <= 0) 
            throw new IllegalArgumentException("cols <= 0");
        this.rows = rows;
        this.columns = cols;
        occupantArray = new HashMap<Location, E>();
    }

    public int getNumRows() 
    {
        return rows;
    }

    public int getNumCols() 
    {
        return columns;
    }

    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    public ArrayList<Location> getOccupiedLocations() 
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (Location key : occupantArray.keySet()) 
        {  
            theLocations.add(key);
        }  

        return theLocations;
    }

    public E get(Location loc) 
    {
        if (!isValid(loc)) 
          throw new IllegalArgumentException("Location " + loc
                        + " is not valid");
        return (E) occupantArray.get(loc); // unavoidable warning
    }

    public E put(Location loc, E obj)
    {
        if (!isValid(loc))
        throw new IllegalArgumentException("Location " + loc
                + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");
              
        // Add the object to the grid.
        E oldOccupant = get(loc);
        occupantArray.put(loc, obj);
        return oldOccupant;
    }

    public E remove(Location loc)
    {
        if (!isValid(loc)) 
          throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        
        // Remove the object from the grid.
        E r = get(loc);
        occupantArray.remove(loc);
        return r;
    }
}
