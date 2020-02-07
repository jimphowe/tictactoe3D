package model;

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
    //if can win now, do, if not, continue
    if(!winningMove()) {
      if(!winInTwo()) {
        //if(!moveWhereTheyDontWinInTwo()) {
          if (!betterDefendingMove()) {
            betterRandomMove();
          }
        //}
      }
    }
  }

  // chooses move which maximises 2 in a rows
  private void betterRandomMove() {
    TesterModel tester = new TesterModel(this.board);
    Move move = tester.getBetterRandomMove(computerColor,playerColor);
    if(move != null) {
      System.out.print("\n\nComputers move: " + move.x + " " + move.y + " " + move.z + " " + move.dir.toString() + "\n\n");
      super.move(move.x,move.y,move.z,move.dir,move.player);
    }
  }

  //leaves opponent with no 2 in a rows and maximizes own two in a rows
  private boolean betterDefendingMove() {
    TesterModel tester = new TesterModel(this.board);
    Move move = tester.getBetterDefendingMove(computerColor,playerColor);
    if(move != null) {
      System.out.print("\n\nComputers move: " + move.x + " " + move.y + " " + move.z + " " + move.dir.toString() + "\n\n");
      super.move(move.x,move.y,move.z,move.dir,move.player);
      return true;
    }
    else {
      return false;
    }
  }

  private boolean moveWhereTheyDontWinInTwo() {
    TesterModel tester = new TesterModel(this.board);
    Move move = tester.getMoveWhereTheyDontWinInTwo(computerColor,playerColor);
    if(move != null) {
      System.out.print("\n\n(theydontwinin2)Computers move: " + move.x + " " + move.y + " " + move.z + " " + move.dir.toString() + "\n\n");
      super.move(move.x,move.y,move.z,move.dir,move.player);
      return true;
    }
    else {
      return false;
    }
  }

  private boolean winInTwo() {
    TesterModel tester = new TesterModel(this.board);
    Move move = tester.getWinInTwo(computerColor,playerColor);
    if(move != null) {
      System.out.print("\n\nComputers move: " + move.x + " " + move.y + " " + move.z + " " + move.dir.toString() + "\n\n");
      super.move(move.x,move.y,move.z,move.dir,move.player);
      return true;
    }
    return false;
  }

  //wins the game if possible(ties aren't accounted for)
  private boolean winningMove() {
    TesterModel tester = new TesterModel(this.board);
    Move move = tester.getWinningMove(computerColor);
    if(move != null) {
      System.out.print("\n\nComputers move: " + move.x + " " + move.y + " " + move.z + " " + move.dir.toString() + "\n\n");
      super.move(move.x,move.y,move.z,move.dir,move.player);
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