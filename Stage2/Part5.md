[TOC]

# Set 10

**The source code for the AbstractGrid class is in Appendix D.**

- Where is the isValid method specified? Which classes provide an implementation of this method?

回答：isValid方法在Grid类中声明，在BoundedGrid类和UnboundedGrid类中实现

```java
//@file/GridWorldCode/framework/info/gridworld/grid/Grid.java
//@line:50
boolean isValid(Location loc);

//@file/GridWorldCode/framework/info/gridworld/grid/BoundedGrid.java
//@line:60~64
public boolean isValid(Location loc)
{
    return 0 <= loc.getRow() && loc.getRow() < getNumRows()
            && 0 <= loc.getCol() && loc.getCol() < getNumCols();
}

//@file/GridWorldCode/framework/info/gridworld/grid/UnboundedGrid.java
//@line:53~56
public boolean isValid(Location loc)
{
    return true;
}
```

- Which AbstractGrid methods call the isValid method? Why don’t the other methods need to call it?

回答：getValidAdjacentLocations方法调用了isValid方法，其他方法只需要调用getValidAdjacentLocations方法即可，不需要再调用isValid方法

```java
//@file/GridWorldCode/framework/info/gridworld/grid/AbstractGrid.java
//@line:44~45
if (isValid(neighborLoc))
    locs.add(neighborLoc);
```

- Which methods of the Grid interface are called in the getNeighbors method? Which classes provide implementations of these methods?

回答：get方法和getOccupiedAdjacentLocations方法

```java
//@file/GridWorldCode/framework/info/gridworld/grid/AbstractGrid.java
//@line:31~32
for (Location neighborLoc : getOccupiedAdjacentLocations(loc))
    neighbors.add(get(neighborLoc));
```

get方法在BoundedGrid类和UnboundedGrid类中实现，getOccupiedAdjacentLocations方法在AbstractGrid类中实现。

```java
//@file:/GridWorldCode/framework/info/gridworld/grid/BoundedGrid.java
//@line:85~91
public E get(Location loc)
{
    if (!isValid(loc))
        throw new IllegalArgumentException("Location " + loc
                + " is not valid");
    return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
}

//@file:/GridWorldCode/framework/info/gridworld/grid/UnboundedGrid.java
//@line:66~71
public E get(Location loc)
{
    if (loc == null)
        throw new NullPointerException("loc == null");
    return occupantMap.get(loc);
}

//@file/GridWorldCode/framework/info/gridworld/grid/AbstractGrid.java
//@line:62~71
public ArrayList<Location> getOccupiedAdjacentLocations(Location loc)
{
    ArrayList<Location> locs = new ArrayList<Location>();
    for (Location neighborLoc : getValidAdjacentLocations(loc))
    {
        if (get(neighborLoc) != null)
            locs.add(neighborLoc);
    }
    return locs;
}
```

- Why must the get method, which returns an object of type E, be used in the getEmptyAdjacentLocations method when this method returns locations, not objects of type E?

回答：因为get方法如果没有对象，返回为null，因此get方法必须返回E类型的对象。getEmptyAjacentLocations方法使用get方法，判断返回结果是否为null，可以得到空格子的位置。

```java
//@file/GridWorldCode/framework/info/gridworld/grid/AbstractGrid.java
//@line: 51~60
public ArrayList<Location> getEmptyAdjacentLocations(Location loc)
{
    ArrayList<Location> locs = new ArrayList<Location>();
    for (Location neighborLoc : getValidAdjacentLocations(loc))
    {
        if (get(neighborLoc) == null)
            locs.add(neighborLoc);
    }
    return locs;
}
```

- What would be the effect of replacing the constant Location.HALF_RIGHT with Location.RIGHT in the two places where it occurs in the getValidAdjacentLocations method?

回答：检测方向从八个方位变为只检测前后左右四个方位

```java
//@file/GridWorldCode/framework/info/gridworld/grid/AbstractGrid.java
//@line:41
for (int i = 0; i < Location.FULL_CIRCLE / Location.HALF_RIGHT; i++)
```

# Set 11

**The source code for the BoundedGrid class is in Appendix D.**

- What ensures that a grid has at least one valid location?

回答：BoundedGrid的构造函数会检测row和col是否小于0，小于0不能进行构造

```java
//@file:/GridWorldCode/framework/info/gridworld/grid/BoundedGrid.java
//@line:39~46
public BoundedGrid(int rows, int cols)
{
    if (rows <= 0)
        throw new IllegalArgumentException("rows <= 0");
    if (cols <= 0)
        throw new IllegalArgumentException("cols <= 0");
    occupantArray = new Object[rows][cols];
}
```

- How is the number of columns in the grid determined by the getNumCols method? What assumption about the grid makes this possible?

回答：occupantArray[0].length。BoundedGrid根据构造函数，至少拥有一行一列

```java
//@file:/GridWorldCode/framework/info/gridworld/grid/BoundedGrid.java
//@line:53~58
public int getNumCols()
{
    // Note: according to the constructor precondition, numRows() > 0, so
    // theGrid[0] is non-null.
    return occupantArray[0].length;
}
```

- What are the requirements for a Location to be valid in a BoundedGrid?

回答：loc的row大于等于0，小于grid的row；loc的col大于等于0，小于grid的col

```java
//@file:/GridWorldCode/framework/info/gridworld/grid/BoundedGrid.java
//@line:60~64
public boolean isValid(Location loc)
{
    return 0 <= loc.getRow() && loc.getRow() < getNumRows()
            && 0 <= loc.getCol() && loc.getCol() < getNumCols();
}
```

**In the next four questions, let r = number of rows, c = number of columns, and n = number of occupied locations.**

- What type is returned by the getOccupiedLocations method? What is the time complexity (Big-Oh) for this method?

回答：返回类型为ArrayList<Location>，时间复杂度为O(r * c)

```java
//@file:/GridWorldCode/framework/info/gridworld/grid/BoundedGrid.java
//@line:66~83
public ArrayList<Location> getOccupiedLocations()
{
    ArrayList<Location> theLocations = new ArrayList<Location>();

    // Look at all grid locations.
    for (int r = 0; r < getNumRows(); r++)
    {
        for (int c = 0; c < getNumCols(); c++)
        {
            // If there's an object at this location, put it in the array.
            Location loc = new Location(r, c);
            if (get(loc) != null)
                theLocations.add(loc);
        }
    }

    return theLocations;
}
```

- What type is returned by the get method? What parameter is needed? What is the time complexity (Big-Oh) for this method?

回答：返回类型为E，时间复杂度为O(1)

```java
//@file:/GridWorldCode/framework/info/gridworld/grid/BoundedGrid.java
//@line:85~91
public E get(Location loc)
{
    if (!isValid(loc))
        throw new IllegalArgumentException("Location " + loc
                + " is not valid");
    return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
}
```

- What conditions may cause an exception to be thrown by the put method? What is the time complexity (Big-Oh) for this method?

回答：放入的位置不合法，或者放入的对象为空。时间复杂度为O(1)

```java
//@file:/GridWorldCode/framework/info/gridworld/grid/BoundedGrid.java
//@line:93~105
public E put(Location loc, E obj)
{
    if (!isValid(loc))
        throw new IllegalArgumentException("Location " + loc
                + " is not valid");
    if (obj == null)
        throw new NullPointerException("obj == null");

    // Add the object to the grid.
    E oldOccupant = get(loc);
    occupantArray[loc.getRow()][loc.getCol()] = obj;
    return oldOccupant;
}
```

- What type is returned by the remove method? What happens when an attempt is made to remove an item from an empty location? What is the time complexity (Big-Oh) for this method?

回答：返回类型为E；会丢出一个IllegalArgumentException；时间复杂度为O(1)

```java
//@file:/GridWorldCode/framework/info/gridworld/grid/BoundedGrid.java
//@line:107~117
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
```

- Based on the answers to questions 4, 5, 6, and 7, would you consider this an efficient implementation? Justify your answer.

回答：是的，多数方法的时间复杂度都为O(1)，只有getOccupiedLocations方法的时间复杂度是O(r * c)，已经十分高效

# Set 12

**The source code for the UnboundedGrid class is in Appendix D.**

- Which method must the Location class implement so that an instance of HashMap can be used for the map? What would be required of the Location class if a TreeMap were used instead? Does Location satisfy these requirements?

回答：使用hashCode()和equals(Object other)方法；需要使用compareTo(Object other)方法；位置满足需求

```java
//@file:/GridWorldCode/framework/info/gridworld/grid/Location.java
//@line:218~221
public int hashCode()
{
    return getRow() * 3737 + getCol();
}

//@line:205~212
public boolean equals(Object other)
{
    if (!(other instanceof Location))
        return false;

    Location otherLoc = (Location) other;
    return getRow() == otherLoc.getRow() && getCol() == otherLoc.getCol();
}

//@line:234~246
public int compareTo(Object other)
{
    Location otherLoc = (Location) other;
    if (getRow() < otherLoc.getRow())
        return -1;
    if (getRow() > otherLoc.getRow())
        return 1;
    if (getCol() < otherLoc.getCol())
        return -1;
    if (getCol() > otherLoc.getCol())
        return 1;
    return 0;
}
```

- Why are the checks for null included in the get, put, and remove methods? Why are no such checks included in the corresponding methods for the BoundedGrid?

回答：在BoundedGrid中，isValid方法检查位置是否不为null并且在边界内。但在unboundGrid类，使用HashMap方法，假定每个位置都有效，但null是不可接受的。与BoundedGrid不同，isValid方法在UnboundedGrid中永远返回true，因此有必要在get、put、remove方法中检查位置是否非空。

```java
//@file:/GridWorldCode/framework/info/gridworld/grid/UnBoundedGrid.java
//@line:53~56
public boolean isValid(Location loc)
{
    return true;
}
```

- What is the average time complexity (Big-Oh) for the three methods: get, put, and remove? What would it be if a TreeMap were used instead of a HashMap?

回答：HashMap的时间复杂度为O(1)，TreeMap的时间复杂度为O(logn)

- How would the behavior of this class differ, aside from time complexity, if a TreeMap were used instead of a HashMap?

回答：TreeMap会导致get,put,remove这些方法的时间复杂度变大，因为TreeMap需要遍历

- Could a map implementation be used for a bounded grid? What advantage, if any, would the two-dimensional array implementation that is used by the BoundedGrid class have over a map implementation?

回答：可以，如果使用HashMap，getOccupiedLocations方法的时间复杂度会变为O(n)，时间复杂度得到下降，get,put,remove方法的时间复杂度为O(1)。如果使用TreeMap，getOccupiedLocations方法的时间复杂度为O(n)，get,put,remove方法的时间复杂度为O(logn)。网格中使用二维数组存储也有优势，数组只需要存储对象，而map需要存储位置和对应的对象。

# Coding Exercises

2.Consider using a `HashMap` **or** `TreeMap` to implement the `SparseBoundedGrid`. How could you use the UnboundedGrid class to accomplish this task? Which methods of `UnboundedGrid` could be used without change?

回答：根据UnboundedGrid类的是实现，完成SparseBoundedGrid类。getOccupiedLocations，get，put，remove，方法不会改变。

Fill in the following chart to compare the expected Big-Oh efficiencies for each implementation of the `SparseBoundedGrid`.

Let r = number of rows, c = number of columns, and n = number of occupied locations

|            Methods             | `SparseGridNode` version | `LinkedList<OccupantInCol>` version | `HashMap` version | `TreeMap`  version |
| :----------------------------: | :----------------------: | :---------------------------------: | :---------------: | :----------------: |
|         `getNeighbors`         |           O(c)           |                O(c)                 |       O(1)        |      O(logn)       |
|  `getEmptyAdjacentLocations`   |           O(c)           |                O(c)                 |       O(1)        |      O(logn)       |
| `getOccupiedAdjacentLocations` |           O(c)           |                O(c)                 |       O(1)        |      O(logn)       |
|     `getOccupiedLocations`     |         O(r + n)         |              O(r + n)               |       O(n)        |        O(n)        |
|             `get`              |           O(c)           |                O(c)                 |       O(1)        |      O(logn)       |
|             `put`              |           O(c)           |                O(c)                 |       O(1)        |      O(logn)       |
|            `remove`            |           O(c)           |                O(c)                 |       O(1)        |      O(logn)       |

3.Consider an implementation of an unbounded grid in which all valid locations have non-negative row and column values. The constructor allocates a 16 x 16 array. When a call is made to the put method with a row or column index that is outside the current array bounds, double both array bounds until they are large enough, construct a new square array with those bounds, and place the existing occupants into the new array.

Implement the methods specified by the Grid interface using this data structure. What is the Big-Oh efficiency of the get method? What is the efficiency of the put method when the row and column index values are within the current array bounds? What is the efficiency when the array needs to be resized?

回答：

O(1)；

O(1)；

O(r * c)，r和c为原来的行和列。

