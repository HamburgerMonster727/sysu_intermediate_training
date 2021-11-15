# Part3实验报告

## 要求

实现一个jumper类，实现在grid中jump

## 实现

仿照bug类进行编写。Jumper类继承Actor类，编写构造函数和默认构造函数

```java
/**
 * Constructs a red Jumper.
 */
public Jumper()
{
    setColor(Color.RED);
}

/**
 * Constructs a Jumper of a given color.
 * @param jumperColor the color for this jumper
 */
public Jumper(Color jumperColor)
{
    setColor(jumperColor);
}
```

对act方法进行重写

```java
/**
 * Moves if it can move, turns otherwise
 */
public void act()
{
    if (canMove()) 
        move();
    else 
        turn();
}
```

编写turn方法

```java
/**
 * Turns the Jumper 45 degrees to the right without changing its location.
 */
public void turn()
{
    setDirection(getDirection() + Location.HALF_RIGHT);
}
```

编写move方法，移动到当前方向的前两个格子，需要调用两次getAdjacentLocation方法

```java
/**
 * Moves the Jumper forward.
 */
public void move()
{
    Grid<Actor> gr = getGrid();
    if (gr == null) 
        return;
    Location loc = getLocation();
    Location next = (loc.getAdjacentLocation(getDirection())).getAdjacentLocation(getDirection());
    if (gr.isValid(next)) 
        moveTo(next);
    else 
        removeSelfFromGrid();
}
```

编写canMove方法，判断当前方向的前两个格子是否为空，是否为花朵，是否为Bug

```java
**
 * Tests whether this jumper can move forward into a location that is empty or
 * contains a flower or a bug.
 * @return true if this jumper can move.
 */
public boolean canMove()
{
    Grid<Actor> gr = getGrid();
    if (gr == null) 
        return false;
    Location loc = getLocation();
    Location next = (loc.getAdjacentLocation(getDirection())).getAdjacentLocation(getDirection());
    if (!gr.isValid(next)) 
        return false;
    Actor neighbor = gr.get(next);
    return (neighbor == null) || (neighbor instanceof Flower) || (neighbor instanceof Bug);
    // ok to move into empty location or onto flower or bug
    // not ok to move onto any other actor
}
```

## 运行

生成一个jumper，可以看到，实现了jumper的jump功能

![](https://gitee.com/liangguanxuan/md_pictures/raw/2173d8ab134c3991dd3f6d96451b6461a711f1f9/stage2/part3/2%20(1).png)

![](https://gitee.com/liangguanxuan/md_pictures/raw/2173d8ab134c3991dd3f6d96451b6461a711f1f9/stage2/part3/2%20(2).png)

## sonar-scanner

运行sonar-scanner，对代码进行测试，可以看到通过了测试

![](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage2/part3/3%20(2).png)

![](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage2/part3/3.png)