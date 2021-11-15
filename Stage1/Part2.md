## Part 1: Observing and Experimenting with GridWorld

------

### Step2 Exploring Actor State and Behavior

- Test the setDirection method with the following inputs and complete the table, giving the compass direction each input represents.

| Degrees | Compass Direction |
| ------- | ----------------- |
| 0       | North             |
| 45      | NorthEast         |
| 90      | East              |
| 135     | SouthEast         |
| 180     | South             |
| 225     | SouthWest         |
| 270     | West              |
| 315     | NorthWest         |
| 360     | North             |

- Move a bug to a different location using the moveTo method. In which directions can you move it? How far can you move it? What happens if you try to move the bug outside the grid?

moveTo方法可以将虫子移动到网格内任何一个合法的位置，并且运动方向不会改变。若尝试将bug移动到网格外面，会发生IllegalArgumentException错误。

- Change the color of a bug, a flower, and a rock. Which method did you use?

使用setColor方法

- Move a rock on top of a bug and then move the rock again. What happened to the bug?

将一个岩石移动到一个bug上面时，bug会被岩石覆盖，岩石再次移动，会发现之前位置的bug已经消失了。

### GUI Summary

------

| Mouse Action                                      | Keyboard Shortcut                                            | Result                                                       |
| ------------------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| Click on an empty location                        | Select empty location with cursor keys and press the Enter key | Shows the constructor menu                                   |
| Click on an occupied location                     | Select occupied location with cursor keys and press the Enter key | Shows the method menu                                        |
| Select the Location -> Delete menu item           | Press the Delete key                                         | Removes the occupant in the currently selected location from the grid |
| Click on the Step button                          | 鼠标选择“Step”，然后按空格键                                 | Calls act on each actor                                      |
| Click on the Run button                           | 鼠标选择“Run"，然后按空格键                                  | Starts run mode (in run mode, the action of the Step button is carried out repeatedly) |
| Click on the Stop button                          | 鼠标选择”Stop“，然后按空格键                                 | Stops run mode                                               |
| Adjust the Slow/Fast slider                       |                                                              | Changes speed of run mode                                    |
| Select the Location -> Zoom in/Zoom out menu item | Press the Ctrl+PgUp / Ctrl+PgDn keys                         | Zooms grid display in or out                                 |
| Adjust the scroll bars next to grid               | Move the location with the cursor keys                       | Scrolls to other parts of the grid (if the grid is too large to fit inside the window) |
| Select the World -> Set grid menu item            |                                                              | Changes between bounded and unbounded grids                  |
| Select the World -> Quit menu item                | Press the Ctrl+Q keys Q                                      | Quits GridWorld                                              |