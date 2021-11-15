# Part2实验报告

[TOC]

## CircleBug

### 要求

Write a class `CircleBug` that is identical to `BoxBug`, except that in the `act` method the `turn` method is called once instead of twice. How is its behavior different from a `BoxBug`?

### 实现

CircleBug和BoxBug只有turn一次和两次的区别，其他都相同

```java
public CircleBug(int radius) 
{
    steps = 0;
    sizeRadius = radius;
}

public void act() 
{
    if (steps < sizeRadius && canMove()) 
    {
        move();
        ++steps;
    } 
    else 
    {
        turn();
        steps = 0;
    }
}
```

CircleBugRunner添加一个半径为2的CircleBug，一个半径为1的CircleBug

```java
public static void main(String[] args)
{
    int x, y, radius;
    ActorWorld world = new ActorWorld();
    CircleBug newbug;

    x = 5;
    y = 0;
    radius = 2;
    newbug = new CircleBug(radius);
    world.add(new Location(x, y), newbug);

    x = 5;
    y = 2;
    radius = 1;
    newbug = new CircleBug(radius);
    world.add(new Location(x, y), newbug);

    world.show();
}
```

通过ant编译CircleBug和CircleBugRunner

![](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage2/part2/1%20(1).png)

可以看到，实现了bug的圆形运动，一个半径为1，一个半径为2

![](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage2/part2/1%20(2).png)

## SpiralBug

### 要求

Write a class `SpiralBug` that drops flowers in a spiral pattern. Hint: Imitate `BoxBug`, but adjust the side length when the bug turns. You may want to change the world to an UnboundedGrid to see the spiral pattern more clearly.

### 实现

当steps等于sideLength的时候，旋转90度，并且sideLength自增1

```java
public void act() 
{
    if (steps < sideLength && canMove()) 
    {
        move();
        ++steps;
    } 
    else 
    {
        turn();
        turn();
        steps = 0;
        ++sideLength;
    }
}
```

SprialBugRunner生成一个长度为3的SprialBug

```java
public static void main(String[] args)
{
    ActorWorld world = new ActorWorld();
    SpiralBug new_spiralbug = new SpiralBug(3);
    world.add(new Location(9, 9), new_spiralbug);

    world.show();
}
```

可以看到，实现了bug的Sprial移动，长度为3

![](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage2/part2/1%20(3).png)

## ZBug

### 要求

Write a class `ZBug` to implement bugs that move in a "Z" pattern, starting in the top left corner. After completing one "Z" pattern, a `ZBug` should stop moving. In any step, if a `ZBug` can't move and is still attempting to complete its "Z" pattern, the `ZBug` does not move and should not turn to start a new side. Supply the length of the "Z" as a parameter in the constructor. The following image shows a "Z" pattern of length 4. Hint: Notice that a `ZBug` needs to be facing east before beginning its "Z" pattern.

### 实现

生成一个bug，首先要原地顺时针旋转两次，面朝右边

```java
public ZBug(int lengthTemp) 
{
    steps = 0;
    sizeLength = lengthTemp;
    //bug turn right first
    turn();
    turn();
}
```

然后移动距离等于sizelength时，旋转三次

```java
private boolean turnThreeTimes() 
{
    return (steps == sizeLength);
}

if (turnThreeTimes()) 
{
    for (int i = 0; i < 3; ++i) 
    {
        turn();
    }
}
```

然后移动距离等于2 * sizeLength + 1时，旋转五次

```java
private boolean turnFiveTimes() 
{
    return (steps == 2 * sizeLength + 1);
}

else if (turnFiveTimes()) 
{
    for (int i = 0; i < 5; ++i) 
    {
        turn();
    }
}
```

最后移动距离大于等于3 * sizeLength + 2时，停止移动

```java
private boolean stopMove() 
{
	return (steps >= 3 * sizeLength + 2 || !canMove());
}

else if (stopMove()) 
{
    --steps;
} 
```

正常情况下进行向前移动

```java
else if (canMove()) 
{
    move();
}
++steps;
```

ZBugRunner生成一个长度为4的ZBug

```java
public static void main(String[] args)
{
    int x = 4, y = 4;
    ActorWorld world = new ActorWorld();
    ZBug newZbug = new ZBug(4);
    world.add(new Location(x, y), newZbug);
    world.show();
}
```

可以看到，实现了bug的Z型移动，长度为4

![](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage2/part2/1%20(4).png)

## DancingBug

### 要求

Write a class `DancingBug` that "dances" by making different turns before each move. The `DancingBug` constructor has an integer array as parameter. The integer entries in the array represent how many times the bug turns before it moves. For example, an array entry of 5 represents a turn of 225 degrees (recall one turn is 45 degrees). When a dancing bug acts, it should turn the number of times given by the current array entry, then act like a Bug. In the next move, it should use the next entry in the array. After carrying out the last turn in the array, it should start again with the initial array value so that the dancing bug continually repeats the same turning pattern.
The `DancingBugRunner` class should create an array and pass it as aparameter to the `DancingBug` constructor.

### 实现

要把一个保存有bug每次移动要旋转多少次的数组传递给DancingBug

```java
//turn times array of Dancing
private int[] turnTimes;

public DancingBug(int[] turnTemp) 
{
    turnTimes = new int[turnTemp.length];
    System.arraycopy(turnTemp, 0, turnTimes, 0, turnTemp.length);
    steps = 0;
}
```

按照传递进来的数组，进行移动，按照数组的顺序，依次完成一定次数的旋转，整个数组遍历完后，再次从头开始遍历数组。

```java
//count how many times turn in this round
private int count = 0;

public void act() 
{
    int index = steps % turnTimes.length;
    if (count < turnTimes[index]) 
    {
        turn();
        ++count;
    } 
    else 
    {
        if (canMove()) 
        {
            move();
            count = 0;
            ++steps;
        } 
        else 
        {
            turn();
        }
    }
}
```

DancingBugRunner生成一个turnTimes数组为[1, 2, 3, 4]的DancingBug

```java
public static void main(String[] args)
{
    ActorWorld world = new ActorWorld();
    int[] turnTimes = new int[4] ;
    for (int i = 0; i < 4; i++) 
    {
        turnTimes[i] = i + 1;
    }
    DancingBug newDancingbug = new DancingBug(turnTimes);
    world.add(new Location(6, 6), newDancingbug);
    world.show();
}
```

可以看到，bug依次旋转1，2，3，4次，然后重复运行此移动

![](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage2/part2/1%20(5).png)

![](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage2/part2/1%20(6).png)

![](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage2/part2/1%20(7).png)

![](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage2/part2/1%20(8).png)

![](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage2/part2/1%20(9).png)

## BoxBug

### 要求

Study the code for the `BoxBugRunner` class. Summarize the steps you would use to add another `BoxBug` actor to the grid.

### 实现

先实例化一个ActorWorld

```
ActorWorld world = new ActorWorld();
```

然后实例化一个Bug

```
BoxBug alice = new BoxBug(6);
```

设置Bug的属性

```
alice.setColor(Color.ORANGE);
```

往ActorWorld中添加Bug，设置添加的位置

```
world.add(new Location(7, 8), alice);
```

展示ActorWorld

```
world.show();
```

## sonar-scanner测试

可以看到，四个项目代码都通过了sonar-scanner测试

![](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage2/part2/10.png)