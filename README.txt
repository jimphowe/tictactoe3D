Welcome to a text based TicTacToe! In 3 dimensions!


To run the game:

1. Download the JAR file to a local directory

2. Run the game with the command: java -jar [filepath-of-jar]




The rules are as follows:


The board is three dimensional (3 by 3 by 3) and contains 3 different colors of pieces
(red, black, and white).

The first player to get three in a row along any run, including diagonals, wins the game.

Unlike normal tic tac toe, in this game pieces are "pushed" as opposed to "placed".

A move can involve "pushing" a piece reachable from the outside (all except the middle piece), or
placing a piece if the selected spot is empty.



----------------------------How to play-------------------------------


Example Board:
+----------------------
| ∖   _      _      _   ∖
|   ∖                     ∖
|     ∖   _      _    BLACK ∖            <------ Top Layer
|       ∖                     ∖
|         ∖  RED   WHITE  BLACK ∖
|          ---------------------|
|     _   |BLACK  BLACK         |
|         |                     |
|         _      _      _       |        <------ Middle Layer
|         |                     |
|         |   _    BLACK    _   |
|         |                     |
 ∖ BLACK  BLACK  BLACK          |
   ∖      |                     |
     ∖   _      _      _        |        <------ Bottom Layer
       ∖  |                     |
         ∖| BLACK    _      _   |
           ---------------------+


Board with coordinates:
+----------------------
| ∖ (0,2,0)(1,2,0)(2,2,0)∖
|   ∖                      ∖
|     ∖ (0,1,0)(1,1,0)(2,1,0)∖
|       ∖                     ∖
|         ∖(0,0,0)(1,0,0)(2,0,0)∖
|          ---------------------|
|  (0,2,1)(1,2,1)(2,2,1)        |
|         |                     |
|       (0,1,1)(1,1,1)(2,1,1)   |
|         |                     |
|         |(0,0,1)(1,0,1)(2,0,1)|
|         |                     |
 ∖ (0,2,2)(1,2,2)(2,2,2)        |
   ∖      |                     |
     ∖  (0,1,2)(1,1,2)(2,1,2)   |
       ∖  |                     |
         ∖|(0,0,2)(1,0,2)(2,0,2)|
           ---------------------+


At any point, type 'q' to quit, or 'u' to undo the last move.

First select the game mode (1, 2, or 3 players), and any other setup as prompted. Play then cycles
between the players until one player wins, or no more moves are possible.


A valid move is of the form [pos pos pos direction]

Where pos is one of:
    0
    1
    2

Direction is one of(any case):
    UP
    DOWN / D
    LEFT / L
    RIGHT / R
    FRONT / F
    BACK / B

The first pos value is left(0) to right(2)
The second pos value is front(0) to back(2)
The third pos value is top(0) to bottom(2)

So if you want to place a piece in the top left front position and push the piece currently there
(if any) to the right, your input would read '0 0 0 Right'. You must specify a direction even if
there is no piece in that location.

On a given players turn, type in all four of these parameters in the correct order, then hit enter
to submit the move.

A move must also be physically possible. Examples of impossible moves are:

1. Choosing a position on the bottom of the board, and try to push "down". Similarly, trying to
   place a piece in the center location (1,1,1). All moves must be done from an outer location.
   Imagine physically pushing a marble in the specified way.
2. Trying to push "right" on a piece which has two pieces behind it, this would push a piece out
   of the board and is not allowed.


-------------------------------GameModes-----------------------------

1 player:

Play against the computer(white) as red, you can go first or second. Black balls will be filled in
at random and are neutral.

Choose whether you want to go first or second, and how challenging you want the game to be.


2 player:

Play against a friend. Black balls will be filled in at random and are neutral.


3 player:

Play against two other people, the board will start empty.


Puzzle mode! (BETA):

In this mode you will be given a list of puzzle to choose from, asking you to win in 1 move, prevent a win, or more difficult tasks.
At any point you can type q to exit the current puzzle, and then q again to exit puzzle mode. To complete the puzzle type in a move as
normal, such as "0 1 0 RIGHT", and the game will tell you whether your move is correct or not.

In the future this mode will show you why your incorrect guesses are incorrect, by showing the computers next move.



            _                 _
           (_)               | |
  ___ _ __  _  ___  _   _    | |
 / _ \ '_ \| |/ _ \| | | |   | |
|  __/ | | | | (_) | |_| |   | |
 \___|_| |_| |\___/ \__, |   |_|
          _/ |       __/ |    _
         |__/       |___/    |_|



--------IN PROGRESS--------


Competitive mode (plans)

This is a 2 player competitive game mode.

As set up, you will pick the number of games played, and a time limit. P1 has a large advantage, so these series
will have an even number of rounds. Winning is the person who wins the most games, but if there's a
tie, which is likely at a high level, the winning is the one who played the least moves as P1 and the
most moves as P2 (P2moves - P1moves).

This rewards very accurate play. Some games might be lost, but there might be a single move which forces
the game to continue one more round. Similarly, sometimes a game can be won many ways, but one move wins in
2 moves while the rest win in 3, this system rewards finding those moves.

Finally, you have to balance how long you spend looking for the best move at different stages, because you
only have a set amount of time to make all your moves in a game.

ENJOY!
