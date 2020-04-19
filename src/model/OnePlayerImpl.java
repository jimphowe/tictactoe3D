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
    Move move = null;
    switch (level) {
      case 1:
        move = lvl1Move();
        break;
      case 2:
        move = lvl2Move();
        break;
      case 3:
        move = lvl3Move();
        break;
      default:
          System.out.print("\nInternal level handling error\n");
          exit(1);
    }
    System.out.print("\n\nComputers move: " + move.x + " " + move.y + " " + move.z + " " + move.dir.toString() + "\n\n");
    super.move(move.x,move.y,move.z,move.dir,move.player);
  }

  private Move lvl1Move() {
    Move move = winningMove();
    if(move == null) {
      move = randomMove();
    }
    return move;
  }

  private Move lvl2Move() {
    Move move = winningMove();
    if(move == null) {
      move = betterDefendingMove();
      if(move == null) {
        move = betterRandomMove();
      }
    }
    return move;
  }

  private Move lvl3Move() {
    Move move = winningMove();
    if(move == null) {
      move = winInTwo();
      if(move == null) {
        move = betterDefendingMove();
        if(move == null) {
          move = betterRandomMove();
        }
      }
    }
    return move;
  }

  // chooses move which maximises 2 in a rows
  private Move betterRandomMove() {
    TesterModel tester = new TesterModel(this.board);
    return tester.getBetterRandomMove(computerColor,playerColor);
  }

  // chooses a move completely at random
  private Move randomMove() {
    TesterModel tester = new TesterModel(this.board);
    return tester.getRandomMove(computerColor);
  }

  //leaves opponent with no 2 in a rows and maximizes own two in a rows
  private Move betterDefendingMove() {
    TesterModel tester = new TesterModel(this.board);
    return tester.getBetterDefendingMove(computerColor,playerColor);
  }

  private Move moveWhereTheyDontWinInTwo() {
    TesterModel tester = new TesterModel(this.board);
    return tester.getMoveWhereTheyDontWinInTwo(computerColor,playerColor);
  }

  private Move winInTwo() {
    TesterModel tester = new TesterModel(this.board);
    return tester.getWinInTwo(computerColor,playerColor);
  }

  //wins the game if possible(ties aren't accounted for)
  private Move winningMove() {
    TesterModel tester = new TesterModel(this.board);
    return tester.getWinningMove(computerColor);
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