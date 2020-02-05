package model;

import java.util.ArrayList;
import java.util.Random;

public class TesterModel extends ThreePlayerImpl {
  TesterModel(LocationState[][][] board) {
    super();
    this.board = copyBoard(board);
  }

  private Random rand = new Random();

  Move getWinInTwo(LocationState player1, LocationState player2) {
    for(Move move : getPossibleMoves(player1)) {
      System.out.println("Test move in getWinInTwoOuter " + player1 + " " + move.x + move.y + move.z + move.dir);
      move(move.x,move.y,move.z,move.dir,move.player);
      if(getWinningMove(player2) != null) {
        boolean moveWinsInTwo = true;
        for (Move secondMove : getPossibleMoves(player2)) {
          System.out.println("Test move in getWinInTwoInner " + player2 + " " + secondMove.x + secondMove.y + secondMove.x + secondMove.dir);
          move(secondMove.x, secondMove.y, secondMove.z, secondMove.dir, secondMove.player);
          if (getWinningMove(player1) == null) {
            moveWinsInTwo = false;
          }
          this.board = this.previousBoards.get(this.previousBoards.size() - 1);
          this.previousBoards.remove(this.previousBoards.size()-1);
        }
        if (moveWinsInTwo) {
          return move;
        }
      }
      reset();
    }
    return null;
  }

  Move getMoveWhereTheyDontWinInTwo(LocationState player1, LocationState player2) {
    for(Move move : getPossibleMoves(player1)) {
      System.out.println("Test move in getWhereDontWinInTwo " + player1 + " " + move.x + move.y + move.x + move.dir);
      move(move.x,move.y,move.z,move.dir,move.player);
      if(getWinInTwo(player2,player1) != null && getWinningMove(player2) != null) {
        return move;
      }
      reset();
    }
    return null;
  }

  //returns a move if player can win, o/w returns null
  Move getWinningMove(LocationState player) {
    for(Move move : getPossibleMoves(player)) {
      System.out.println("Test move in getWinningmove");
      move(move.x,move.y,move.z,move.dir,move.player);
      if (hasWon(player)) {
        return move;
      } else {
        reset();
      }
    }
    return null;
  }

  Move getBetterRandomMove(LocationState player1, LocationState player2) {
    ArrayList<Move> goodPotentialMoves = new ArrayList<>();
    ArrayList<Move> allPotentialMoves = new ArrayList<>();
    int maxDoubles = 0;
    for(Move move : getPossibleMoves(player1)) {
              allPotentialMoves.add(move);
              System.out.println("Test move in getBetterRandomMove");
              move(move.x, move.y, move.z, move.dir, move.player);
              if (getNumDoubles(player1) == maxDoubles && getNumDoubles(player2) == 0) {
                goodPotentialMoves.add(move);
              }
              else if (getNumDoubles(player1) > maxDoubles && getNumDoubles(player2) == 0) {
                goodPotentialMoves.clear();
                goodPotentialMoves.add(move);
              }
              reset();
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
    int maxDoubles = 1;
            for(Move move : getPossibleMoves(player1)) {
              System.out.println("Test move in getBetterDefendingMove");
              move(move.x, move.y, move.z, move.dir, move.player);
              if (getNumDoubles(player1) == maxDoubles && getNumDoubles(player2) == 0) {
                potentialMoves.add(move);
              } else if (getNumDoubles(player1) > maxDoubles && getNumDoubles(player2) == 0) {
                potentialMoves.clear();
                potentialMoves.add(move);
              }
              reset();
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

  //get a list of all possible moves for a given player
  private ArrayList<Move> getPossibleMoves(LocationState player) {
    ArrayList<Move> possibleMoves = new ArrayList<>();
    for(int i = 0; i < 3; i++) {
      for(int j = 0; j < 3; j++) {
        for(int k = 0; k < 3; k++) {
          for(Direction dir : Direction.values()) {
            if(this.isValidMove(i,j,k,dir)) {
              Move toAdd = new Move(i,j,k,dir,player);
              possibleMoves.add(toAdd);
            }
          }
        }
      }
    }
    return possibleMoves;
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

}
