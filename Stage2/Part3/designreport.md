# Design Report

- What will a jumper do if the location in front of it is empty, but the location two cells in front contains a flower or a rock?

回答：前一个格子为空，但前面第二个格子为rock时，需要调用turn方法，调整方向；若前面第二个格子为flower时，会直接跳到花朵上。

- What will a jumper do if the location two cells in front of the jumper is out of the grid?

回答：调用turn方法，调整方向。

- What will a jumper do if it is facing an edge of the grid?

回答：调用turn方法，调整方向。

- What will a jumper do if another actor (not a flower or a rock) is in the cell that is two cells in front of the jumper?

回答：调用turn方法，调整方向。

- What will a jumper do if it encounters another jumper in its path?

回答：如果这两个jumper需要跳到的位置不相同，则都可以jump；但若是两个jumper要跳的位置为同一个位置，则会一个jumper改变方向，一个jumper跳跃。

- Are there any other tests the jumper needs to make?

回答：若jumper前面一个格子不为空时，jumper会如何应对？