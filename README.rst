======================
Tetris Galaxy v1.0.4.1
======================
Introduction
------------
Tetris Galaxy is a video game inspired by Tetris. In Tetris Galaxy, blocks fall 
from all edges of the screen towards the center. Lines are cleared in concentric 
squares around the center.

Tetris Galaxy is a fun variation of Tetris that would be enjoyable to any Tetris fan.

Installation
------------
0. If you haven't already, install `Java SE 8 <https://www.java.com/en/>`_
1. Download the Tetris Galaxy directory
2. Run TetrisGalaxy.jar

Documentation
-------------
Full documentation can be found `here <http://student2.cs.appstate.edu/swansonmp/index.html>`_

Troubleshooting
---------------
* Tetris Galaxy isn't working!
	1. Ensure you have Java installed
	2. Ensure TetrisGalaxy.jar is placed in the same directory as all its folders and dependencies

FAQs
----
* Where is the pause menu? Where is the main menu?
	* Menus are the first thing on the todo list
* What is the inspiration for the project?
	* Tetris Galaxy is the result of a single "what if?" from a team of Tetris fans

Source Code
-----------
.. code:: java
	while (!g.startIt()) { wait(150); }
		g.passIn(board, activeSet, scoring, bag);
		try { gameLoop();}
		catch (Exception e) { g.end();}

The code for the game loop, located in :code:`Game.java`. The graphics driver :code:`g` is 
passed in game information from the previous iteration via the :code:`passIn` method.
Then, the :code:`gameLoop` function computes logic for the next game tick.

Additional source code for Tetris Galaxy can be found on `Github <https://github.com/swansonmp/tetrisgalaxy>`_

Support
-------
For additional support, contact [email]

License
-------
`Apache 2.0 <https://www.apache.org/licenses/LICENSE-2.0>`_
