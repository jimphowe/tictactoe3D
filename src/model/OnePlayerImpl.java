package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class OnePlayerImpl extends TicTacToeModelImpl {

  private LocationState computerColor = LocationState.WHITE;
  private LocationState playerColor = LocationState.RED;

  public OnePlayerImpl(boolean computerFirst) {
    super();
    while(countColor(LocationState.BLACK) < 9) {
      int x = new Random().nextInt(3);
      int y = new Random().nextInt(3);
      int z = new Random().nextInt(3);
      board[x][y][z] = LocationState.BLACK;
    }
    if(computerFirst) {
      computerMove();
    }
  }

  @Override
  public boolean isGameOver() {
    if(hasWon(computerColor) || hasWon(playerColor)) {
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

  @Override
  public void move(int x, int y, int z, Direction direction, LocationState player) throws IllegalArgumentException {
    super.move(x,y,z,direction,player);
    System.out.println(getGameState());
    if(!isGameOver()) {
      try {
        System.out.print(".\n");
        TimeUnit.SECONDS.sleep(1);
        System.out.print(".\n");
        TimeUnit.SECONDS.sleep(1);
        System.out.print(".\n");
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      computerMove();
    }
  }

  //picks random spot and direction (Computer is always white, you are always red)
  private void computerMove() {
    //if can win now, do
    if(!winningMove()) {
      TesterModel tester = new TesterModel(this.board);
      Move move = tester.getWinningMove(playerColor);
      //if the player has a winning move
      if(move != null) {
        if(!betterDefendingMove()) {
          betterRandomMove();
        }
      }
      //the player doesn't have a winning move, create the most 2 in a rows for ourself
      else {
        betterRandomMove();
      }
    }
  }

  // chooses move which maximises 2 in a rows
  private boolean betterRandomMove() {
    TesterModel tester = new TesterModel(this.board);
    Move move = tester.getBetterRandomMove(computerColor,playerColor);
    if(move != null) {
      System.out.print("\n\n(better random move)Computers move: " + move.x + " " + move.y + " " + move.z + " " + move.dir.toString() + "\n\n");
      super.move(move.x,move.y,move.z,move.dir,computerColor);
      return true;
    }
    else {
      return false;
    }
  }

  //leaves opponent with no 2 in a rows and maximizes own two in a rows
  private boolean betterDefendingMove() {
    TesterModel tester = new TesterModel(this.board);
    Move move = tester.getBetterDefendingMove(computerColor,playerColor);
    if(move != null) {
      System.out.print("\n\n(better defending move)Computers move: " + move.x + " " + move.y + " " + move.z + " " + move.dir.toString() + "\n\n");
      super.move(move.x,move.y,move.z,move.dir,computerColor);
      return true;
    }
    else {
      return false;
    }
  }

  //wins he game if possible(ties aren't accounted for)
  private boolean winningMove() {
    TesterModel tester = new TesterModel(this.board);
    Move move = tester.getWinningMove(computerColor);
    if(move != null) {
      System.out.print("\n\n(winning move)Computers move: " + move.x + " " + move.y + " " + move.z + " " + move.dir.toString() + "\n\n");
      super.move(move.x,move.y,move.z,move.dir,computerColor);
      return true;
    }
    else {
      return false;
    }
  }

  @Override
  public void undo() {
    if(previousBoards.size() > 1) {
      board = previousBoards.get(previousBoards.size()-2);
      previousBoards.remove(previousBoards.size()-1);
      previousBoards.remove(previousBoards.size()-1);
    }
  }
}