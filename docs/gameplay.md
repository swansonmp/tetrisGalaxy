# Gameplay

## Starting the Game

1. Run TetrisGalaxy.jar

The play screen will appear automatically following two splash screens.

## Controls

* Move block: :code:`up`, :code:`down`, :code:`left`, :code:`right`
* Rotate left: :code:`z`
* Rotate right: :code:`x`
* Drop block: :code:`space`

## Gameplay

### Objective

Like Tetris, the objective of Tetris Galaxy is to clear lines to get the highest score possible.

### Block-falling

Blocks fall from the edges of the play area, starting at the top edge and changing clockwise for every fallen block.
Blocks will stop either on the red center block or on any other placed blocks.

### Line Clears

Lines are cleared in concentric squares around the center. Exterior blocks collapse inward on line clear.

Clearing multiple lines at once is worth more points than clearing lines individually. Clearing four lines at once is known as a *Galactic Tetris*.

### Other Maneuvers

The player can speed up falling blocks by holding arrow key corresponding to the fall direction.

For example, if the block is falling from the top of the screen, the player may hold :code:`down`
the speed up the falling block.

The player can drop falling blocks by pressing :code:`space`. A 'shadow' block displays a preview 
of where the block will land. Luckily, the player cannot drop blocks into empty space.

### End Conditions

The game ends when either...

* The player sends a block into empty space

* The player fills up the play area
