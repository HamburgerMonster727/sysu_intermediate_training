# Part5实验报告

[TOC]

## SparseBoundedGrid

### 要求

Suppose that a program requires a very large bounded grid that contains very few objects and that the program frequently calls the getOccupiedLocations method (as, for example, ActorWorld). Create a class SparseBoundedGrid that uses a "sparse array" implementation. Your solution need not be a generic class; you may simply store occupants of type Object.

The "sparse array" is an array list of linked lists. Each linked list entry holds both a grid occupant and a column index. Each entry in the array list is a linked list or is null if that row is empty.

You may choose to implement the linked list in **one of two** ways. You can use raw list nodes.**Or** you can use a LinkedList<OccupantInCol> with a helper class.

### 实现

使用辅助类OccupantInCol来实现SparseBoundedGrid

```java
public class OccupantInCol 
{
    private Object occupant;
    private int col;
    OccupantInCol(Object obj, int colSize) 
    {
        this.occupant = obj;
        col = colSize;
    }
    public void setOccupant(Object obj) 
    {
        occupant = obj;
    }
    public Object getOccupant() 
    {
        return occupant;
    }
    public void setCol(int col)
    {
        this.col = col;
    }
    public int getCol() 
    {
        return col;
    }
}
```

SparseBoundedGrid构造函数，初始化一个二维的OccupantInCol链表

```java
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
```

还需要重写getOccupiedLocations，get，put，remove函数，把里面的数据结构全部换成OccupantInCol链表

可以看到，生成了一个11*11的SparseBoundedGrid，Grid可以正常运行

![](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage2/part5/1%20(3).png)

![](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage2/part5/1%20(2).png)

![](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage2/part5/1%20(1).png)

## SparseBoundedGrid2

### 要求

Consider using a `HashMap` **or** `TreeMap` to implement the `SparseBoundedGrid`. How could you use the UnboundedGrid class to accomplish this task? Which methods of `UnboundedGrid` could be used without change?

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

### 实现

使用HashMap实现SparseBoundedGrid2，构造函数中初始化一个HashMap

```java
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
```

还需要仿照UnboundedGrid类重写getOccupiedLocations，get，put，remove函数，把里面的数据结构全部换成HashMap

```java
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
```

可以看到，生成了一个11*11的SparseBoundedGrid2，Grid可以正常运行

![](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage2/part5/1%20(3).jpg)

![](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage2/part5/1%20(2).jpg)

![](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage2/part5/1%20(1).jpg)

## UnboundedGrid2

### 要求

Consider an implementation of an unbounded grid in which all valid locations have non-negative row and column values. The constructor allocates a 16 x 16 array. When a call is made to the put method with a row or column index that is outside the current array bounds, double both array bounds until they are large enough, construct a new square array with those bounds, and place the existing occupants into the new array.

Implement the methods specified by the Grid interface using this data structure. What is the Big-Oh efficiency of the get method? What is the efficiency of the put method when the row and column index values are within the current array bounds? What is the efficiency when the array needs to be resized?

回答：O(1)；O(1)；O(r * c)，r和c为原来的行和列。

### 实现

使用二维数组Object实现UnboundedGrid2，构造函数初始化一个16*16的二维数组

```java
public UnboundedGrid2() 
{
    occupantArray = new Object[16][16];
    gridSize = 16;
}
```

重写getOccupiedLocations，get，remove，数据结构都换成二维数组

```java
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

重写put方法，实现grid的扩展

```java
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
```

可以看到，生成了没有界限的UnboundedGrid2，Grid可以正常运行

![](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage2/part5/1%20(4).png)

![](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage2/part5/1%20(5).png)

![](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage2/part5/1%20(6).png)

## sonar-scanner测试

可以看到，三个项目的代码都通过了sonar-scanner测试

![](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage2/part5/1%20(7).png)

