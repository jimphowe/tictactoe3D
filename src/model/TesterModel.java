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
    for(Move move : getPossibleMoves(player)) {
      move(move.x,move.y,move.z,move.dir,move.player);
      if (hasWon(player)) {
        undo();
        return move;
      } else {
        undo();
      }
    }
    return null;
  }

  Move getWinInTwo(LocationState player1, LocationState player2) {
    // loop through all computer moves
    for(Move move : getPossibleMoves(player1)) {
      move(move.x,move.y,move.z,move.dir,move.player);
      boolean found = true;
      if(getWinningMove(player2) == null) {
        for (Move inner : getPossibleMoves(player2)) {
          move(inner.x, inner.y, inner.z, inner.dir, inner.player);
          if (getWinningMove(player1) == null) {
            found = false;
          }
          undo();
        }
      }
      else {
        found = false;
      }
      undo();
      if (found) { return move; }
    }
    return null;
  }

  // returns a move where player2 cant win in either 1 or two moves, and maximizes own doubles
  Move getBestDefendingMove(LocationState player1, LocationState player2) {
    ArrayList<Move> potentialMoves = new ArrayList<>();
    int maxDoubles = -1;
    int minOpponentWins = 100;
    for(Move move : getPossibleMoves(player1)) {
      move(move.x, move.y, move.z, move.dir, move.player);
      int numOpponentWins = getNumWinInTwo(player2, player1);
      if(getWinningMove(player2) == null && numOpponentWins == minOpponentWins) {
        if(getNumDoubles(player1) == maxDoubles) {
          potentialMoves.add(move);
        }
        else if(getNumDoubles(player1) > maxDoubles) {
          potentialMoves.clear();
          potentialMoves.add(move);
          maxDoubles = getNumDoubles(player1);
        }
      }
      else if(getWinningMove(player2) == null && numOpponentWins < minOpponentWins) {
        potentialMoves.clear();
        potentialMoves.add(move);
        minOpponentWins = numOpponentWins;
        maxDoubles = getNumDoubles(player1);
      }
      undo();
    }
    if(potentialMoves.size() != 0) {
      //Cool fact ->
      //System.out.println("choose from " + potentialMoves.size() + " moves! Opponent ways to win: " + minOpponentWins);
      return potentialMoves.get(rand.nextInt(potentialMoves.size()));
    }
    else {
      return null;
    }
  }

  // returns a move where player2 cant win in either 1 or two moves, and maximizes own doubles
  Move old_getBestDefendingMove(LocationState player1, LocationState player2) {
    ArrayList<Move> potentialMoves = new ArrayList<>();
    int maxDoubles = -1;
    for(Move move : getPossibleMoves(player1)) {
      move(move.x, move.y, move.z, move.dir, move.player);
      if(getWinningMove(player2) == null && getWinInTwo(player2, player1) == null) {
        if(getNumDoubles(player1) == maxDoubles) {
          potentialMoves.add(move);
        }
        else if(getNumDoubles(player1) > maxDoubles) {
          potentialMoves.clear();
          potentialMoves.add(move);
          maxDoubles = getNumDoubles(player1);
        }
      }
      undo();
    }
    if(potentialMoves.size() != 0) {
      return potentialMoves.get(rand.nextInt(potentialMoves.size()));
    }
    else {
      return null;
    }
  }

  //returns a move if player1 has a move which stops opponent winning and maximizes their
  //own two in a rows
  Move getBetterDefendingMove(LocationState player1, LocationState player2) {
    ArrayList<Move> potentialMoves = new ArrayList<>();
    int maxDoubles = -1;
            for(Move move : getPossibleMoves(player1)) {
              move(move.x, move.y, move.z, move.dir, move.player);
              if(getWinningMove(player2) == null) {
                if(getNumDoubles(player1) == maxDoubles) {
                  potentialMoves.add(move);
                }
                else if(getNumDoubles(player1) > maxDoubles) {
                  potentialMoves.clear();
                  potentialMoves.add(move);
                }
              }
              undo();

            }
    if(potentialMoves.size() != 0) {
      return potentialMoves.get(rand.nextInt(potentialMoves.size()));
    }
    else {
      return null;
    }
  }

  //returns a move at random which stop the opponent from winning
  Move getDefendingMove(LocationState player1, LocationState player2) {
    ArrayList<Move> potentialMoves = new ArrayList<>();
    for(Move move : getPossibleMoves(player1)) {
      move(move.x, move.y, move.z, move.dir, move.player);
      if (getWinningMove(player2) == null) {
        potentialMoves.add(move);
      }
      undo();
    }
    if(potentialMoves.size() != 0) {
      return potentialMoves.get(rand.nextInt(potentialMoves.size()));
    }
    else {
      return null;
    }
  }

  // We couldn't defend... only happens if position is lost
  Move getRandomMove(LocationState player) {
    ArrayList<Move> potentialMoves = new ArrayList<>(getPossibleMoves(player));
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

  void printarray(ArrayList<Move> moves) {
    for(Move move : moves) {
      System.out.println(move.x + " " + move.y + " " + move.z + " " + move.dir.toString());
    }
  }

  int getNumWinInTwo(LocationState player1, LocationState player2) {
    // loop through all computer moves
    int count = 0;
    for(Move move : getPossibleMoves(player1)) {
      move(move.x,move.y,move.z,move.dir,move.player);
      boolean found = true;
      if(getWinningMove(player2) == null) {
        for (Move inner : getPossibleMoves(player2)) {
          move(inner.x, inner.y, inner.z, inner.dir, inner.player);
          if (getWinningMove(player1) == null) {
            found = false;
          }
          undo();
        }
      }
      else {
        found = false;
      }
      undo();
      if (found) {
        count++;
      }
    }
    return count;
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

}
