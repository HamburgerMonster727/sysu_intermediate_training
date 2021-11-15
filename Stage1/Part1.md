## Part 1: Observing and Experimenting with GridWorld

------

### Step1 Running the Demo （Stage1_1.md）

- Does the bug always move to a new location? Explain.

不会，当bug前面味岩石，边界，面具，或者另一个面对面的bug时，该bug会改变其运动的方向，当bug前面为花或者空白格子时，该bug不会改变其运动的反向。

- In which direction does the bug move?

bug会一直向前直线运动，直到遇到会使bug改变运动方向的条件后，bug会自动改变运动方向。

- What does the bug do if it does not move?

bug会原地顺时针旋转45度，改变运动的方向，直到可以向前直线运动。

- What does a bug leave behind when it moves?

bug移动后，原来的前一个格子会留下一个与bug颜色一样的花朵，并且这朵花会随着bug的运动，颜色不断地加深。

- What happens when the bug is at an edge of the grid? (Consider whether the bug is facing the edge as well as whether the bug is facing some other direction when answering this question.)

首先会原地顺时针旋转45度，然后当bug前面为岩石，边界，面具，或者另一个面对面的bug时，继续原地顺时针旋转45度，直到可以向前直线运动，然后向前运动。

- What happens when a bug has a rock in the location immediately in front of it?

bug会原地不断地顺时针旋转45度，直到面前没有障碍物，可以向前运动。

- Does a flower move?

花朵不会移动。

- What behavior does a flower have?

花朵会出线在bug运动的前一个位置，花朵的颜色会随着bug的运动，颜色不断地加深，当有bug走到花朵上，花朵会被bug覆盖。

- Does a rock move or have any other behavior?

岩石不会移动，没有任何行为，只会阻碍bug的运动。

- Can more than one actor (bug, flower, rock) be in the same location in the grid at the same time?

不可以，一个格子只能有一个actor存在