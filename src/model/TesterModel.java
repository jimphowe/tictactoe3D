package model;

import java.util.ArrayList;
import java.util.Random;

public class TesterModel extends ThreePlayerImpl {
  TesterModel(LocationState[][][] board) {
    super();
    this.board = copyBoard(board);
  }

  private Random rand = new Random();


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
    ArrayList<Move> goodPotentialMoves = new ArrayList<>();
    ArrayList<Move> allPotentialMoves = new ArrayList<>();
    int maxDoubles = 0;
    for(int i = 0; i <= 2; i++) {
      for(int j = 0; j <= 2; j++) {
        for(int k = 0; k <= 2; k++) {
          for(Direction dir : Direction.values()) {
            if(isValidMove(i,j,k,dir)) {
              allPotentialMoves.add(new Move(i,j,k,dir,player1));
              LocationState[][][] oldBoard = copyBoard(this.board);
              move(i, j, k, dir, player1);
              if (getNumDoubles(player1) == maxDoubles && getNumDoubles(player2) == 0) {
                goodPotentialMoves.add(new Move(i, j, k, dir, player1));
              }
              else if (getNumDoubles(player1) > maxDoubles && getNumDoubles(player2) == 0) {
                goodPotentialMoves.clear();
                goodPotentialMoves.add(new Move(i,j,k,dir,player1));
              }
              this.board = oldBoard;
            }
          }
        }
      }
    }
    if(goodPotentialMoves.size() != 0) {
      return goodPotentialMoves.get(rand.nextInt(goodPotentialMoves.size()));
    }
    else {
      return allPotentialMoves.get(rand.nextInt(allPotentialMoves.size()));
    }
  }

  //returns a move if player1 has a move which stops all opponents 2 in a rows and maximizes their
  //own two in a rows
  Move getBetterDefendingMove(LocationState player1, LocationState player2) {
    ArrayList<Move> potentialMoves = new ArrayList<>();
    int maxDoubles = 0;
    for(int i = 0; i <= 2; i++) {
      for(int j = 0; j <= 2; j++) {
        for(int k = 0; k <= 2; k++) {
          for(Direction dir : Direction.values()) {
            if(isValidMove(i,j,k,dir)) {
              LocationState[][][] oldBoard = copyBoard(this.board);
              move(i, j, k, dir, player1);
              if (getNumDoubles(player1) == maxDoubles && getNumDoubles(player2) == 0) {
                potentialMoves.add(new Move(i, j, k, dir, player1));
              }
              else if (getNumDoubles(player1) > maxDoubles && getNumDoubles(player2) == 0) {
                potentialMoves.clear();
                potentialMoves.add(new Move(i,j,k,dir,player1));
              }
              this.board = oldBoard;
            }
          }
        }
      }
    }
    if(potentialMoves.size() != 0) {
      return potentialMoves.get(rand.nextInt(potentialMoves.size()));
    }
    else {
      return null;
    }
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

  @Override
  public boolean isGameOver() {
    return false;
  }

}
