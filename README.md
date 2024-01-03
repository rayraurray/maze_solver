# java-maze-solver
Maze Solver that Implements several Search Algorithms to solve a parsed 2d maze.

## Instructions:

To run the Robot Navigation Problem, please download and extract the project folder into a secure location on your computer. For demonstration purposes, I will place the project folder in my C:/ drive.

Next, please search ‘cmd’ on your Windows taskbar or with any other way to search for programs on your computer and select the first application that appears to open up the Command Prompt.

After opening Command Prompt, please navigate to the project folder using the ‘cd’ command plus the directory path of the placement of the project folder. For the example explained above, where the project folder is in the C:/ drive.

One more command is needed before running the assignment’s solution: ‘cd src’. Enter the command to navigate to the location where the program is run.
 
To run the program, please input the command in this format: ‘search filename method’, where ‘search’ is the name of the batch file needed to run the project, ‘filename’ represents the .txt file that holds the navigation environment problem’s format, and ‘method’ indicating the search methods used to solve the problem, including  BFS (Breadth-First Search), DFS (Depth-First Search), GBFS (Greedy Best First Search), AS (A* Search), CUS1 (Custom Algorithm 1),  CUS2 (Custom Algorithm 2). Note that the sample navigation environment text file has already been included in the folder. The agent will reach all goals in the maze, the terminal will output the filename, the method used and the number of nodes explored, the path the agent took to reach the goals, and a visual representation of the navigation problem in another window will also be outputted.
 
To exit the program, simply exit the ‘Assignment 1’ window by clicking on the ‘X’ on the top right, or any other means.

## File Format: 

The problems are stored in simple text files with the following format:
- First line contains a pair of numbers [N,M] – the number of rows and the number of columns of the grid, enclosed in square brackets.
- Second line contains a pair of numbers (x1,y1)– the coordinates of the current location of the agent, the initial state.
- Third line contains a sequence of pairs of numbers separated by |; these are the coordinates of the goal states: (xG1,yG1) | (xG2,yG2) | … | (xGn,yGn), where n ≥ 1.
- The subsequent lines represent the locations of the walls: The tuple (x,y,w,h) indicates that the leftmost top corner of the wall occupies cell (x,y) with a width of w cells and a height of h cells.
- We will only be interested in search algorithms. Therefore, you can assume that the problem files will contain valid configurations. For instance, if N=5 and M = 11 then you don’t have to worry that the agent is initially located at coordinates (15, 3).
