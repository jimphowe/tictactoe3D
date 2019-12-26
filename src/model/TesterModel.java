package model;

import java.util.ArrayList;

public class TesterModel extends ThreePlayerImpl {
  public TesterModel(LocationState[][][] board) {
    super();
    this.board = copyBoard(board);
  }

  //returns a move if player can win, o/w returns null
  Move getWinningMove(LocationState player) {
    for(int i = 0; i <= 2; i++) {
      for(int j = 0; j <= 2; j++) {
        for(int k = 0; k <= 2; k++) {
          for(Direction dir : Direction.values()) {
            if (isValidMove(i, j, k, dir)) {
              move(i, j, k, dir, player);
              if (hasWon(player)) {
                return new Move(i, j, k, dir, player);
              } else {
                reset();
              }
            }
          }
        }
      }
    }
    return null;
  }

  Move getBetterRandomMove(LocationState player1, LocationState player2) {
    Move result = null;
    int maxDoubles = 0;
    for(int i = 0; i <= 2; i++) {
      for(int j = 0; j <= 2; j++) {
        for(int k = 0; k <= 2; k++) {
          for(Direction dir : Direction.values()) {
            if(isValidMove(i,j,k,dir)) {
              LocationState[][][] oldBoard = copyBoard(this.board);
              move(i, j, k, dir, player1);
              if (getNumDoubles(player1) >= maxDoubles && getNumDoubles(player2) == 0) {
                result = new Move(i, j, k, dir, player1);
              }
              this.board = oldBoard;
            }
          }
        }
      }
    }
    return result;
  }

  //returns a move if player1 has a move which stops all opponents 2 in a rows and maximizes their
  //own two in a rows
  Move getBetterDefendingMove(LocationState player1, LocationState player2) {
    Move result = null;
    int maxDoubles = 0;
    for(int i = 0; i <= 2; i++) {
      for(int j = 0; j <= 2; j++) {
        for(int k = 0; k <= 2; k++) {
          for(Direction dir : Direction.values()) {
            if(isValidMove(i,j,k,dir)) {
              LocationState[][][] oldBoard = copyBoard(this.board);
              move(i, j, k, dir, player1);
              if (getNumDoubles(player1) >= maxDoubles && getNumDoubles(player2) == 0) {
                result = new Move(i, j, k, dir, player1);
              }
              this.board = oldBoard;
            }
          }
        }
      }
    }
    return result;
  }

  private int getNumDoubles(LocationState player) {
    int doubles = 0;
    for(ArrayList<BoardLocation> run : runs) {
      if((locationToState(run.get(0)).equals(player) && locationToState(run.get(1)).equals(player))
        || (locationToState(run.get(1)).equals(player) && locationToState(run.get(2)).equals(player))
        || (locationToState(run.get(0)).equals(player) && locationToState(run.get(2)).equals(player))) {
        doubles++;
      }
    }
    return doubles;
  }

  void reset() {
    if(previousBoards.size() > 0) {
      this.board = previousBoards.get(0);
      this.previousBoards = new ArrayList<>();
      this.previousBoards.add(copyBoard(this.board));
    }
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

}
