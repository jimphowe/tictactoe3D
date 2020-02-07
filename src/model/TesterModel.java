package model;

import java.util.ArrayList;
import java.util.Random;

public class TesterModel extends ThreePlayerImpl {
  TesterModel(LocationState[][][] board) {
    super();
    this.board = copyBoard(board);
  }

  private Random rand = new Random();

  void printarray(ArrayList<Move> moves) {
    for(Move move : moves) {
      System.out.println(move.x + " " + move.y + " " + move.z + " " + move.dir.toString());
    }
  }

  Move getWinInTwo(LocationState player1, LocationState player2) {
    //loop through possible computer moves
    for(Move move : getPossibleMoves(player1)) {
      //try the move
      move(move.x,move.y,move.z,move.dir,move.player);
      //if other play cant win after move is made
      if(getWinningMove(player2) == null) {
        boolean moveWinsInTwo = true;
        //then loop through moves that other player could make
        for (Move secondMove : getPossibleMoves(player2)) {
          //make the move
          move(secondMove.x, secondMove.y, secondMove.z, secondMove.dir, secondMove.player);
          //and if we cant win after they make a certain move, our first wasn't win in two
          if (getWinningMove(player1) == null) {
            moveWinsInTwo = false;
          }
          this.board = this.previousBoards.get(this.previousBoards.size() - 1);
          this.previousBoards.remove(this.previousBoards.size()-1);
          //reset();
          //move(move.x,move.y,move.z,move.dir,move.player);
        }
        //if still true, we won next move no matter what, so is was winintwo
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
      move(move.x,move.y,move.z,move.dir,move.player);
      if(getWinInTwo(player2,player1) != null && getWinningMove(player2) != null) {
        return move;
      }
      this.board = this.previousBoards.get(this.previousBoards.size() - 1);
      this.previousBoards.remove(this.previousBoards.size()-1);
    }
    return null;
  }

  //returns a move if player can win, o/w returns null
  Move getWinningMove(LocationState player) {
    for(Move move : getPossibleMoves(player)) {
      move(move.x,move.y,move.z,move.dir,move.player);
      if (hasWon(player)) {
        return move;
      } else {
        this.board = this.previousBoards.get(this.previousBoards.size() - 1);
        this.previousBoards.remove(this.previousBoards.size()-1);
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
              move(move.x, move.y, move.z, move.dir, move.player);
              if (getNumDoubles(player1) == maxDoubles && getNumDoubles(player2) == 0) {
                goodPotentialMoves.add(move);
              }
              else if (getNumDoubles(player1) > maxDoubles && getNumDoubles(player2) == 0) {
                goodPotentialMoves.clear();
                goodPotentialMoves.add(move);
              }
              this.board = this.previousBoards.get(this.previousBoards.size() - 1);
              this.previousBoards.remove(this.previousBoards.size()-1);
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
              move(move.x, move.y, move.z, move.dir, move.player);
              if (getNumDoubles(player1) == maxDoubles && getNumDoubles(player2) == 0) {
                potentialMoves.add(move);
              } else if (getNumDoubles(player1) > maxDoubles && getNumDoubles(player2) == 0) {
                potentialMoves.clear();
                potentialMoves.add(move);
              }
              this.board = this.previousBoards.get(this.previousBoards.size() - 1);
              this.previousBoards.remove(this.previousBoards.size()-1);
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
