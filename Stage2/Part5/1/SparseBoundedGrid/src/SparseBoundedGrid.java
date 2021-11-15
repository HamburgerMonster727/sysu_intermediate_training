import info.gridworld.grid.Grid;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A <code>SparseBoundedGrid</code> is a rectangular grid with a finite number of
 * rows and columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class SparseBoundedGrid<E> extends AbstractGrid<E> 
{
    private ArrayList<LinkedList<OccupantInCol>> occupantArray;// the LinkedList storing grid elements
    private int rows; 
    private int columns;
    
    /**
     * Constructs an empty bounded grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * @param rows number of rows in BoundedGrid
     * @param cols number of columns in BoundedGrid
     */
    public SparseBoundedGrid(int rows, int cols) 
    {
        if (rows <= 0) 
            throw new IllegalArgumentException("rows <= 0");    
        if (cols <= 0) 
            throw new IllegalArgumentException("cols <= 0");
        this.rows = rows;
        this.columns = cols;
        occupantArray = new ArrayList<LinkedList<OccupantInCol>>(rows);
        for (int i = 0; i < rows; i++) 
        {
            LinkedList<OccupantInCol> tmp = new LinkedList<OccupantInCol>();
            occupantArray.add(tmp);
        }
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
        for (int r = 0; r < rows; r++) 
        {
            for (int c = 0; c < occupantArray.get(r).size(); c++) 
            {
                OccupantInCol ob = occupantArray.get(r).get(c);
                Location loc = new Location(r, ob.getCol());
                theLocations.add(loc);
            }
        }

        return theLocations;
    }

    public E get(Location loc) 
    {
        if (!isValid(loc)) 
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
 
        LinkedList<OccupantInCol> row = occupantArray.get(loc.getRow());
        if (row.size() == 0) 
        {
            return null;
        }
        else 
        {
            for (int i = 0; i < row.size(); i++) 
            {
                if (row.get(i).getCol() == loc.getCol()) 
                {
                    return (E) row.get(i).getOccupant();
                }
            }
        }
        return null;
    }

    public E put(Location loc, E obj)
    {
        if (!isValid(loc)) 
            throw new IllegalArgumentException("Location " + loc
                      + " is not valid");

        if (obj == null) 
            throw new IllegalArgumentException("obj == null");

        // Add the object to the grid.
        E oldOccupant = get(loc);
        occupantArray.get(loc.getRow()).add(new OccupantInCol(obj, loc.getCol()));
        return oldOccupant;
    }

    public E remove(Location loc)
    {
        if (!isValid(loc)) 
            throw new IllegalArgumentException("Location " + loc
                      + " is not valid");

        // Remove the object from the grid.
        E r = get(loc);
        LinkedList<OccupantInCol> list = occupantArray.get(loc.getRow());
        if (list.size() != 0) 
        {
            for (int i = 0; i< list.size(); i++)
            {
                if (list.get(i).getCol() == loc.getCol()) 
                {
                    list.remove(i);
                }
            }
        }
        return r;
    }
}
