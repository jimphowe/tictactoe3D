package model;

import java.util.ArrayList;
import java.util.Random;

public class TwoPlayerImpl extends TicTacToeModelImpl {
  /**
   * Creates an instance of our implementation given no arguments.
   */
  public TwoPlayerImpl() {
    super();
    while(countColor(LocationState.BLACK) < 9) {
      int x = new Random().nextInt(3);
      int y = new Random().nextInt(3);
      int z = new Random().nextInt(3);
      board[x][y][z] = LocationState.BLACK;
    }
  }

  @Override
  public boolean isGameOver() {
    for (ArrayList<BoardLocation> run : runs) {
      if (locationToState(run.get(0)).equals(locationToState(run.get(1))) &&
              locationToState(run.get(1)).equals(locationToState(run.get(2))) &&
              !locationToState(run.get(0)).equals(LocationState.EMPTY) &&
              locationToState(run.get(0)) != LocationState.BLACK) {
        return true;
      }
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
}
