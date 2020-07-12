package model;

import java.util.ArrayList;
import java.util.Arrays;

abstract public class TicTacToeModelImpl implements TicTacToeModel {

  LocationState[][][] board;

  ArrayList<LocationState[][][]> previousBoards;

  ArrayList<ArrayList<BoardLocation>> runs;

  TicTacToeModelImpl() {
    // creates a list of all possible runs in the game

    //front top left
    ArrayList<BoardLocation> a1 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 0, 0), new BoardLocation(0, 0, 1), new BoardLocation(0, 0, 2)));
    ArrayList<BoardLocation> a2 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 0, 0), new BoardLocation(0, 1, 0), new BoardLocation(0, 2, 0)));
    ArrayList<BoardLocation> a3 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 0, 0), new BoardLocation(1, 0, 0), new BoardLocation(2, 0, 0)));

    //back top right
    ArrayList<BoardLocation> a4 = new ArrayList<>(Arrays.asList(new BoardLocation(2, 2, 0), new BoardLocation(1, 2, 0), new BoardLocation(0, 2, 0)));
    ArrayList<BoardLocation> a5 = new ArrayList<>(Arrays.asList(new BoardLocation(2, 2, 0), new BoardLocation(2, 1, 0), new BoardLocation(2, 0, 0)));
    ArrayList<BoardLocation> a6 = new ArrayList<>(Arrays.asList(new BoardLocation(2, 2, 0), new BoardLocation(2, 2, 1), new BoardLocation(2, 2, 2)));

    //back bottom left
    ArrayList<BoardLocation> a7 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 2, 2), new BoardLocation(0, 1, 2), new BoardLocation(0, 0, 2)));
    ArrayList<BoardLocation> a8 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 2, 2), new BoardLocation(1, 2, 2), new BoardLocation(2, 2, 2)));
    ArrayList<BoardLocation> a9 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 2, 2), new BoardLocation(0, 2, 1), new BoardLocation(0, 2, 0)));

    // front bottom right
    ArrayList<BoardLocation> a10 = new ArrayList<>(Arrays.asList(new BoardLocation(2, 0, 2), new BoardLocation(2, 0, 1), new BoardLocation(2, 0, 0)));
    ArrayList<BoardLocation> a11 = new ArrayList<>(Arrays.asList(new BoardLocation(2, 0, 2), new BoardLocation(1, 0, 2), new BoardLocation(0, 0, 2)));
    ArrayList<BoardLocation> a12 = new ArrayList<>(Arrays.asList(new BoardLocation(2, 0, 2), new BoardLocation(2, 1, 2), new BoardLocation(2, 2, 2)));

    // faces

    //front
    ArrayList<BoardLocation> a13 =new ArrayList<>(Arrays.asList(new BoardLocation(0, 0, 0), new BoardLocation(1, 0, 1), new BoardLocation(2, 0, 2)));
    ArrayList<BoardLocation> a14 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 0, 2), new BoardLocation(1, 0, 1), new BoardLocation(2, 0, 0)));
    ArrayList<BoardLocation> a15 = new ArrayList<>(Arrays.asList(new BoardLocation(1, 0, 0), new BoardLocation(1, 0, 1), new BoardLocation(1, 0, 2)));
    ArrayList<BoardLocation> a16 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 0, 1), new BoardLocation(1, 0, 1), new BoardLocation(2, 0, 1)));

    //top
    ArrayList<BoardLocation> a17 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 0, 0), new BoardLocation(1, 1, 0), new BoardLocation(2, 2, 0)));
    ArrayList<BoardLocation> a18 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 2, 0), new BoardLocation(1, 1, 0), new BoardLocation(2, 0, 0)));
    ArrayList<BoardLocation> a19 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 1, 0), new BoardLocation(1, 1, 0), new BoardLocation(2, 1, 0)));
    ArrayList<BoardLocation> a20 = new ArrayList<>(Arrays.asList(new BoardLocation(1, 2, 0), new BoardLocation(1, 1, 0), new BoardLocation(1, 0, 0)));

    //left
    ArrayList<BoardLocation> a21 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 0, 0), new BoardLocation(0, 1, 1), new BoardLocation(0, 2, 2)));
    ArrayList<BoardLocation> a22 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 0, 2), new BoardLocation(0, 1, 1), new BoardLocation(0, 2, 0)));
    ArrayList<BoardLocation> a23 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 0, 1), new BoardLocation(0, 1, 1), new BoardLocation(0, 2, 1)));
    ArrayList<BoardLocation> a24 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 1, 0), new BoardLocation(0, 1, 1), new BoardLocation(0, 1, 2)));

    //back
    ArrayList<BoardLocation> a25 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 2, 2), new BoardLocation(1, 2, 1), new BoardLocation(2, 2, 0)));
    ArrayList<BoardLocation> a26 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 2, 0), new BoardLocation(1, 2, 1), new BoardLocation(2, 2, 2)));
    ArrayList<BoardLocation> a27 = new ArrayList<>(Arrays.asList(new BoardLocation(1, 2, 0), new BoardLocation(1, 2, 1), new BoardLocation(1, 2, 2)));
    ArrayList<BoardLocation> a28 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 2, 1), new BoardLocation(1, 2, 1), new BoardLocation(2, 2, 1)));

    //right
    ArrayList<BoardLocation> a29 = new ArrayList<>(Arrays.asList(new BoardLocation(2, 0, 2), new BoardLocation(2, 1, 1), new BoardLocation(2, 2, 0)));
    ArrayList<BoardLocation> a30 = new ArrayList<>(Arrays.asList(new BoardLocation(2, 0, 0), new BoardLocation(2, 1, 1), new BoardLocation(2, 2, 2)));
    ArrayList<BoardLocation> a31 = new ArrayList<>(Arrays.asList(new BoardLocation(2, 0, 1), new BoardLocation(2, 1, 1), new BoardLocation(2, 2, 1)));
    ArrayList<BoardLocation> a32 = new ArrayList<>(Arrays.asList(new BoardLocation(2, 1, 0), new BoardLocation(2, 1, 1), new BoardLocation(2, 1, 2)));

    //bottom
    ArrayList<BoardLocation> a33 = new ArrayList<>(Arrays.asList(new BoardLocation(2, 0, 2), new BoardLocation(1, 1, 2), new BoardLocation(0, 2, 2)));
    ArrayList<BoardLocation> a34 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 0, 2), new BoardLocation(1, 1, 2), new BoardLocation(2, 2, 2)));
    ArrayList<BoardLocation> a35 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 1, 2), new BoardLocation(1, 1, 2), new BoardLocation(2, 1, 2)));
    ArrayList<BoardLocation> a36 = new ArrayList<>(Arrays.asList(new BoardLocation(1, 0, 2), new BoardLocation(1, 1, 2), new BoardLocation(1, 2, 2)));

    // inner

    //diagonals (corners)
    ArrayList<BoardLocation> a37 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 0, 0), new BoardLocation(1, 1, 1), new BoardLocation(2, 2, 2)));
    ArrayList<BoardLocation> a38 = new ArrayList<>(Arrays.asList(new BoardLocation(2, 0, 0), new BoardLocation(1, 1, 1), new BoardLocation(0, 2, 2)));
    ArrayList<BoardLocation> a39 = new ArrayList<>(Arrays.asList(new BoardLocation(2, 2, 0), new BoardLocation(1, 1, 1), new BoardLocation(0, 0, 2)));
    ArrayList<BoardLocation> a40 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 2, 0), new BoardLocation(1, 1, 1), new BoardLocation(2, 0, 2)));

    //diagonals (edges)
    ArrayList<BoardLocation> a41 = new ArrayList<>(Arrays.asList(new BoardLocation(1, 0, 0), new BoardLocation(1, 1, 1), new BoardLocation(1, 2, 2)));
    ArrayList<BoardLocation> a42 = new ArrayList<>(Arrays.asList(new BoardLocation(2, 1, 0), new BoardLocation(1, 1, 1), new BoardLocation(0, 1, 2)));
    ArrayList<BoardLocation> a43 = new ArrayList<>(Arrays.asList(new BoardLocation(1, 2, 0), new BoardLocation(1, 1, 1), new BoardLocation(1, 0, 2)));
    ArrayList<BoardLocation> a44 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 1, 0), new BoardLocation(1, 1, 1), new BoardLocation(2, 1, 2)));
    ArrayList<BoardLocation> a45 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 0, 1), new BoardLocation(1, 1, 1), new BoardLocation(2, 2, 1)));
    ArrayList<BoardLocation> a46 = new ArrayList<>(Arrays.asList(new BoardLocation(2, 0, 1), new BoardLocation(1, 1, 1), new BoardLocation(0, 2, 1)));

    //middles
    ArrayList<BoardLocation> a47 = new ArrayList<>(Arrays.asList(new BoardLocation(1, 1, 0), new BoardLocation(1, 1, 1), new BoardLocation(1, 1, 2)));
    ArrayList<BoardLocation> a48 = new ArrayList<>(Arrays.asList(new BoardLocation(1, 0, 1), new BoardLocation(1, 1, 1), new BoardLocation(1, 2, 1)));
    ArrayList<BoardLocation> a49 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 1, 1), new BoardLocation(1, 1, 1), new BoardLocation(2, 1, 1)));

    runs = new ArrayList<>(Arrays.asList(a1, a2, a3, a4, a5, a6, a7, a8,
            a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21, a22, a23, a24, a25, a26, a27,
            a28, a29, a30, a31, a32, a33, a34, a35, a36, a37, a38, a39, a40, a41, a42, a43, a44,
            a45, a46, a47, a48, a49));

    // creates the board
    this.board = makeBoard();

    // initializes the board history
    this.previousBoards = new ArrayList<>();
  }

  @Override
  public void move(int x, int y, int z, Direction direction, LocationState player) throws IllegalArgumentException {
    if (!isValidMove(x, y, z, direction)) {
      throw new IllegalArgumentException("The spot chosen must either be empty or be able to push "
              + "other balls forward without pushing one out\n" + getGameState() + x + " " + y + " " + z + " " + direction);
    }
    else {
      //before move is made, state is saved
      previousBoards.add(copyBoard(this.board));
      if (board[x][y][z] == LocationState.EMPTY) {
        board[x][y][z] = player;
      }
      else {
        switch (direction) {
          case BACK:
            if (board[x][y+1][z] == LocationState.EMPTY) {
              board[x][y+1][z] = board[x][y][z];
              board[x][y][z] = player;
            }
            else {
              board[x][y+2][z] = board[x][y+1][z];
              board[x][y+1][z] = board[x][y][z];
              board[x][y][z] = player;
            }
            break;
          case FRONT:
            if (board[x][y-1][z] == LocationState.EMPTY) {
              board[x][y-1][z] = board[x][y][z];
              board[x][y][z] = player;
            }
            else {
              board[x][y-2][z] = board[x][y-1][z];
              board[x][y-1][z] = board[x][y][z];
              board[x][y][z] = player;
            }
            break;
          case RIGHT:
            if (board[x+1][y][z] == LocationState.EMPTY) {
              board[x+1][y][z] = board[x][y][z];
              board[x][y][z] = player;
            }
            else {
              board[x+2][y][z] = board[x+1][y][z];
              board[x+1][y][z] = board[x][y][z];
              board[x][y][z] = player;
            }
            break;
          case LEFT:
            if (board[x-1][y][z] == LocationState.EMPTY) {
              board[x-1][y][z] = board[x][y][z];
              board[x][y][z] = player;
            }
            else {
              board[x-2][y][z] = board[x-1][y][z];
              board[x-1][y][z] = board[x][y][z];
              board[x][y][z] = player;
            }
            break;
          case DOWN:
            if (board[x][y][z+1] == LocationState.EMPTY) {
              board[x][y][z+1] = board[x][y][z];
              board[x][y][z] = player;
            }
            else {
              board[x][y][z+2] = board[x][y][z+1];
              board[x][y][z+1] = board[x][y][z];
              board[x][y][z] = player;
            }
            break;
          case UP:
            if (board[x][y][z-1] == LocationState.EMPTY) {
              board[x][y][z-1] = board[x][y][z];
              board[x][y][z] = player;
            }
            else {
              board[x][y][z-2] = board[x][y][z-1];
              board[x][y][z-1] = board[x][y][z];
              board[x][y][z] = player;
            }
            break;
          default:
            return;
        }
      }
    }
    if(isGameOver()) {
      System.out.print("Game over! Player " + player.toString() + " wins!\n\n");
    }
  }

  //for debugging
  void printHistory() {
    int i = 1;
    for(LocationState[][][] oldBoard : previousBoards) {
      System.out.print("Board: " + i + oldBoard[0][0][0] + "\n\n");
      i++;
    }
  }

  @Override
  public boolean isGameOver() {
    if(hasWon(LocationState.RED) || hasWon(LocationState.WHITE) || hasWon(LocationState.BLACK)) {
      return true;
    }
    for (int x = 0; x < 3; x++) {
      for (int y = 0; y < 3; y++) {
        for (int z = 0; z < 3; z++) {
          for (Direction dir : Direction.values()) {
            if (isValidMove(x, y, z, dir)) {
              return false;
            }
          }
        }
      }
    }
    return true;
  }

  boolean hasWon(LocationState player) {
    for(ArrayList<BoardLocation> run : runs) {
      if (locationToState(run.get(0)).equals(player) && locationToState(run.get(1)).equals(player)
              && locationToState(run.get(2)).equals(player)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String getGameState() {
    String gameState = "+----------------------\n";
    gameState += "| ∖ " + board[0][2][0].toString() + "  " + board[1][2][0].toString() + "  " + board[2][2][0].toString() + " ∖\n";
    gameState += "|   ∖                     ∖\n";
    gameState += "|     ∖ " + board[0][1][0].toString() + "  " + board[1][1][0].toString() + "  " + board[2][1][0].toString() + " ∖\n";
    gameState += "|       ∖                     ∖\n";
    gameState += "|         ∖ " + board[0][0][0].toString() + "  " + board[1][0][0].toString() + "  " + board[2][0][0].toString() + " ∖\n";
    gameState += "|          ---------------------|\n";
    gameState += "|   " + board[0][2][1].toString() + " |" + board[1][2][1].toString() + "  " + board[2][2][1].toString() + "         |\n";
    gameState += "|         |                     |\n";
    gameState += "|       " + board[0][1][1].toString() + "  " + board[1][1][1].toString() + "  " + board[2][1][1].toString() + "     |\n";
    gameState += "|         |                     |\n";
    gameState += "|         | " + board[0][0][1].toString() + "  " + board[1][0][1].toString() + "  " + board[2][0][1].toString() + " |\n";
    gameState += "|         |                     |\n";
    gameState += " ∖ " + board[0][2][2].toString() + "  " + board[1][2][2].toString() + "  " + board[2][2][2].toString() + "          |\n";
    gameState += "   ∖      |                     |\n";
    gameState += "     ∖ " + board[0][1][2].toString() + "  " + board[1][1][2].toString() + "  " + board[2][1][2].toString() + "      |\n";
    gameState += "       ∖  |                     |\n";
    gameState += "         ∖| " + board[0][0][2].toString() + "  " + board[1][0][2].toString() + "  " + board[2][0][2].toString() + " |\n";
    gameState += "           ---------------------+\n\n";
    return gameState;
  }

  private static LocationState[][][] makeBoard() {
    int size = 3;
    LocationState[][][] board = new LocationState[size][size][size];
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        for (int k = 0; k < size; k++) {
          board[i][j][k] = LocationState.EMPTY;
        }
      }
    }
    return board;
  }

  private static boolean positionOutOfBounds(int x, int y, int z) {
    return !(x >= 0 && x <= 2 && y >= 0 && y <= 2 && z >= 0 && z <= 2);
  }

  boolean isValidMove(int x, int y, int z, Direction direction) {
    switch (direction) {
      case UP:
        if (z == 2) {
          return !(positionOutOfBounds(x, y, z))
                  && (this.board[x][y][z] == LocationState.EMPTY || this.board[x][y][z-1] == LocationState.EMPTY || this.board[x][y][z-2] == LocationState.EMPTY);
        }
        else {
          return false;
        }
      case DOWN:
        if (z == 0) {
          return !(positionOutOfBounds(x, y, z))
                  && (this.board[x][y][z] == LocationState.EMPTY || this.board[x][y][z+1] == LocationState.EMPTY || this.board[x][y][z+2] == LocationState.EMPTY);
        }
        else {
          return false;
        }
      case LEFT:
        if (x == 2) {
          return !(positionOutOfBounds(x, y, z))
                  && (this.board[x][y][z] == LocationState.EMPTY || this.board[x-1][y][z] == LocationState.EMPTY || this.board[x-2][y][z] == LocationState.EMPTY);
        }
        else {
          return false;
        }
      case RIGHT:
        if (x == 0) {
          return !(positionOutOfBounds(x, y, z))
                  && (this.board[x][y][z] == LocationState.EMPTY || this.board[x+1][y][z] == LocationState.EMPTY || this.board[x+2][y][z] == LocationState.EMPTY);
        }
        else {
          return false;
        }
      case FRONT:
        if (y == 2) {
          return !(positionOutOfBounds(x, y, z))
                  && (this.board[x][y][z] == LocationState.EMPTY || this.board[x][y-1][z] == LocationState.EMPTY || this.board[x][y-2][z] == LocationState.EMPTY);
        }
        else {
          return false;
        }
      case BACK:
        if (y == 0) {
          return !(positionOutOfBounds(x, y, z))
                  && (this.board[x][y][z] == LocationState.EMPTY || this.board[x][y+1][z] == LocationState.EMPTY || this.board[x][y+2][z] == LocationState.EMPTY);
        }
        else {
          return false;
        }
      default:
        return true;
    }
  }

  LocationState locationToState(BoardLocation location) {
    return board[location.getX()][location.getY()][location.getZ()];
  }

  int countColor(LocationState color) {
    int count = 0;
    for(int i = 0; i < 3; i++) {
      for(int j = 0; j < 3; j++) {
        for(int k = 0; k < 3; k++) {
          if(board[i][j][k] == color) {
            count++;
          }
        }
      }
    }
    return count;
  }

  @Override
  public void undo() {
    if(previousBoards.size() > 0) {
      this.board = previousBoards.get(previousBoards.size()-1);
      previousBoards.remove(previousBoards.size()-1);
    }
  }

  void reset() {
    if(previousBoards.size() > 0) {
      this.board = previousBoards.get(0);
      this.previousBoards = new ArrayList<>();
      this.previousBoards.add(copyBoard(this.board));
    }
  }

  LocationState[][][] copyBoard(LocationState[][][] board) {
    LocationState[][][] copy = new LocationState[3][3][3];
    for (int i = 0; i <= 2; i++) {
      for(int j = 0; j <= 2; j++) {
        System.arraycopy(board[i][j], 0, copy[i][j], 0, 3);
      }
    }
    return copy;
  }
}