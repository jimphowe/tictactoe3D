package model;

public interface TicTacToeModel {

  void move(int x, int y, int z, Direction direction, LocationState player) throws
          IllegalArgumentException;

  boolean isGameOver();

  String getGameState();

  void undo();
}
