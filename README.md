# MazeGeneratot

    The maze generator create a maze[x][y] with entrance, exit, traps and treasure. The maze ensures that you
    can take treasure and out from maze.
    
        1. To choose a maze size int method main(String[] args) in line 

            Maze maze = new Maze(x,y);

        set a x and y and run a class

        2. To change a difficulty of maze int line 

            private final int difficult = 10;

        If you increase a x you will add optional path.

    The maze generate pathes using Random class. Firstly it generates exit and entrance and generate a path.
    All generated pathes save into a massive. Secondly it generates a "difficult" numbers of optional pathes,
    then it generates a treasure and path to it. Finally it generate traps in the way you can pass through the maze. 
    
