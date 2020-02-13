package model;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static java.lang.System.exit;

public class OnePlayerImpl extends TicTacToeModelImpl {

  private LocationState computerColor = LocationState.WHITE;
  private LocationState playerColor = LocationState.RED;
  private int level;

  public OnePlayerImpl(boolean computerFirst, int level) {
    super();
    this.level = level;
    while(countColor(LocationState.BLACK) < 9) {
      int x = new Random().nextInt(3);
      int y = new Random().nextInt(3);
      int z = new Random().nextInt(3);
      board[x][y][z] = LocationState.BLACK;
    }
    if(computerFirst) {
      computerMove(level);
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
      computerMove(this.level);
    }
  }

  //picks random spot and direction (Computer is always white, you are always red)
  private void computerMove(int level) {
    switch (level) {
      case 1:
        lvl1Move();
        break;
      case 2:
        lvl2Move();
        break;
      case 3:
        lvl3Move();
        break;
      default:
          System.out.print("\nInternal level handling error\n");
          exit(1);
    }
  }

  private void lvl1Move() {
    if(!winningMove()) {
      randomMove();
    }
  }

  private void lvl2Move() {
    if(!winningMove()) {
      if (!betterDefendingMove()) {
        betterRandomMove();
      }
    }
  }

  private void lvl3Move() {
    if(!winningMove()) {
      if(!winInTwo()) {
        if (!betterDefendingMove()) {
          betterRandomMove();
        }
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

  // chooses a move completely at random
  private void randomMove() {
    TesterModel tester = new TesterModel(this.board);
    Move move= tester.getRandomMove(computerColor);
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
      System.out.print("\n\nComputers move: " + move.x + " " + move.y + " " + move.z + " " + move.dir.toString() + "\n\n");
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