1.What is the role of the instance variable sideLength?

回答：变量sideLength定义了bug在框内任何一个方向可以移动的最大步数。

```java
//@file:/GridWorldCode/projects/boxBug/BoxBug.java
//@line:28
private int sideLength;
//@line: 45~49
if (steps < sideLength && canMove())
{
    move();
    steps++;
}
```

2.What is the role of the instance variable steps?

回答：变量steps定义了bug在当前方向移动的步数。

```java
//@file:/GridWorldCode/projects/boxBug/BoxBug.java
//@line:27
private int steps;
//@line: 43~56
public void act()
{
    if (steps < sideLength && canMove())
    {
        move();
        steps++;
    }
    else
    {
        turn();
        turn();
        steps = 0;
    }
}
```

3.Why is the turn method called twice when steps becomes equal to sideLength?

回答：因为bug每次只能旋转45度，steps等于sideLength时，bug需要旋转90度才能继续前进，所以需要调用两次turn。

```java
//@file:/GridWorldCode/projects/boxBug/BoxBug.java
//@line: 45~55
if (steps < sideLength && canMove())
{
    move();
    steps++;
}
else
{
    turn();
    turn();
    steps = 0;
}
```

4.Why can the move method be called in the BoxBug class when there is no move method in the BoxBug code?

回答：因为BoxBug类继承Bug类，而Bug类中定义了move()函数，所以BoxBug类继承了Bug类中的move()函数，可以调用move()函数。

```java
//@file:/GridWorldCode/projects/boxBug/BoxBug.java
//@line:25
public class BoxBug extends Bug

//@file:/GridWorldCode/framework/info/gridworld/actor/Bug.java
//@line:71
public void move()
```

5.After a BoxBug is constructed, will the size of its square pattern always be the same? Why or why not?

回答：一样。因为BoxBug的构造函数中，定义了sideLength = length，一旦BoxBug被构造，就会调用构造函数。

```java
//@file:/GridWorldCode/projects/boxBug/BoxBug.java
//@line:34~38
public BoxBug(int length)
{
    steps = 0;
    sideLength = length;
}
```

6.Can the path a BoxBug travels ever change? Why or why not?

回答：bug的路线会发生改变。canMove()函数判断是否还能继续前进，假如bug前方有一个岩石，bug就需要改变方向，bug的路线就会发生改变。

```java
//@file:/GridWorldCode/projects/boxBug/BoxBug.java
//@line: 45~55
if (steps < sideLength && canMove())
{
    move();
    steps++;
}
else
{
    turn();
    turn();
    steps = 0;
}
```

7.When will the value of steps be zero?

回答：BoxBug构造函数初始化时，steps设置为0；steps等于sideLength时，steps设置为0；bug前方有障碍物，不能前进时，steps设置为0；

```java
//@file:/GridWorldCode/projects/boxBug/BoxBug.java
//@line:34~38
public BoxBug(int length)
{
    steps = 0;
    sideLength = length;
}

//@line: 45~55
if (steps < sideLength && canMove())
{
    move();
    steps++;
}
else
{
    turn();
    turn();
    steps = 0;
}
```

