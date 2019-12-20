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
    if(!dumbWinningMove() && !betterDefendingMove() && !defendingMove() && !dumbBlockingMove()  && !betterRandomMove()) {
      randomMove();
    }
  }

  // random location and direction
  private void randomMove() {
    int x = new Random().nextInt(3);
    int y = new Random().nextInt(3);
    int z = new Random().nextInt(3);
    Direction dir = Direction.randomDirection();

    while(!isValidMove(x,y,z,dir)) {
      x = new Random().nextInt(3);
      y = new Random().nextInt(3);
      z = new Random().nextInt(3);
      dir = Direction.randomDirection();
    }
    super.move(x,y,z,dir,computerColor);
    System.out.print("\n\n(random move)Computers move: " + x + " " + y + " " + z + " " + dir.toString() + "\n\n");
  }

  //puts piece in opponents third spot
  private boolean dumbBlockingMove() {
    BoardLocation lastPosition;
    for(ArrayList<BoardLocation> run : runs) {
      lastPosition = getThirdPosition(run, playerColor);
      if(lastPosition != null) {
        int x = lastPosition.getX();
        int y = lastPosition.getY();
        int z = lastPosition.getZ();
        for(Direction direction : Direction.values()) {
          if(isValidMove(x,y,z,direction)) {
            super.move(x,y,z,direction,computerColor);
            System.out.print("\n\n(dumb blocking move)Computers move: " + x + " " + y + " " + z + " " + direction.toString() + "\n\n");
            return true;
          }
        }
      }
    }
    return false;
  }

  // chooses move which will be in line with one other piece
  private boolean betterRandomMove() {
    for(int i = 0; i < 1000; i++) {
      int x = new Random().nextInt(3);
      int y = new Random().nextInt(3);
      int z = new Random().nextInt(3);
      Direction dir = Direction.randomDirection();

      if(isValidMove(x,y,z,dir) && aRunContains(x,y,z,computerColor)) {
        super.move(x,y,z,dir,computerColor);
        System.out.print("\n\n(better random move)Computers move: " + x + " " + y + " " + z + " " + dir.toString() + "\n\n");
        return true;
      }
    }
    return false;
  }

  //disrupts 2 in a rows for the opponent and does one which also creates a 2 in a row if possible
  private boolean betterDefendingMove() {
    BoardLocation lastPosition;
    for(ArrayList<BoardLocation> run : runs) {
      lastPosition = getThirdPosition(run, playerColor);
      if(lastPosition != null) {
        BoardLocation toMove = run.get(0);
        int x = toMove.getX();
        int y = toMove.getY();
        int z = toMove.getZ();
        for(Direction direction : Direction.values()) {
          if(isValidMove(x,y,z,direction)) {
            switch(direction) {
              case UP:
                if(!aRunContains(x,y,z-1,playerColor) && aRunContains(x,y,z,computerColor)) {
                  board[x][y][z] = playerColor;
                  System.out.print("\n\n(better defending move)Computers move: " + x + " " + y + " " + z + " " + direction.toString() + "\n\n");
                  super.move(x,y,z,direction,computerColor);
                  return true;
                }
              case DOWN:
                if(!aRunContains(x,y,z+1,playerColor) && aRunContains(x,y,z,computerColor)) {
                  board[x][y][z] = playerColor;
                  System.out.print("\n\n(better defending move)Computers move: " + x + " " + y + " " + z + " " + direction.toString() + "\n\n");
                  super.move(x,y,z,direction,computerColor);
                  return true;
                }
              case LEFT:
                if(!aRunContains(x-1,y,z,playerColor) && aRunContains(x,y,z,computerColor)) {
                  board[x][y][z] = playerColor;
                  System.out.print("\n\n(better defending move)Computers move: " + x + " " + y + " " + z + " " + direction.toString() + "\n\n");
                  super.move(x,y,z,direction,computerColor);
                  return true;
                }
              case RIGHT:
                if(!aRunContains(x+1,y,z,playerColor) && aRunContains(x,y,z,computerColor)) {
                  board[x][y][z] = playerColor;
                  System.out.print("\n\n(better defending move)Computers move: " + x + " " + y + " " + z + " " + direction.toString() + "\n\n");
                  super.move(x,y,z,direction,computerColor);
                  return true;
                }
              case FRONT:
                if(!aRunContains(x,y-1,z,playerColor) && aRunContains(x,y,z,computerColor)) {
                  board[x][y][z] = playerColor;
                  System.out.print("\n\n(better defending move)Computers move: " + x + " " + y + " " + z + " " + direction.toString() + "\n\n");
                  super.move(x,y,z,direction,computerColor);
                  return true;
                }
              case BACK:
                if(!aRunContains(x,y+1,z,playerColor) && aRunContains(x,y,z,computerColor)) {
                  board[x][y][z] = playerColor;
                  System.out.print("\n\n(better defending move)Computers move: " + x + " " + y + " " + z + " " + direction.toString() + "\n\n");
                  super.move(x,y,z,direction,computerColor);
                  return true;
                }
            }
          }
        }
      }
    }
    return false;
  }

  //disrupts 2 in a rows for the opponent
  private boolean defendingMove() {
    BoardLocation lastPosition;
    for(ArrayList<BoardLocation> run : runs) {
      lastPosition = getThirdPosition(run, playerColor);
      if(lastPosition != null) {
        BoardLocation toMove = run.get(0);
        int x = toMove.getX();
        int y = toMove.getY();
        int z = toMove.getZ();
        for(Direction direction : Direction.values()) {
          if(isValidMove(x,y,z,direction)) {
            board[x][y][z] = LocationState.EMPTY;
            switch(direction) {
              case UP:
                if(!aRunContains(x,y,z-1,playerColor)) {
                  board[x][y][z] = playerColor;
                  super.move(x,y,z,direction,computerColor);
                  System.out.print("\n\n(defending move)Computers move: " + x + " " + y + " " + z + " " + direction.toString() + "\n\n");
                  return true;
                }
              case DOWN:
                if(!aRunContains(x,y,z+1,playerColor)) {
                  board[x][y][z] = playerColor;
                  super.move(x,y,z,direction,computerColor);
                  System.out.print("\n\n(defending move)Computers move: " + x + " " + y + " " + z + " " + direction.toString() + "\n\n");
                  return true;
                }
              case LEFT:
                if(!aRunContains(x-1,y,z,playerColor)) {
                  board[x][y][z] = playerColor;
                  super.move(x,y,z,direction,computerColor);
                  System.out.print("\n\n(defending move)Computers move: " + x + " " + y + " " + z + " " + direction.toString() + "\n\n");
                  return true;
                }
              case RIGHT:
                if(!aRunContains(x+1,y,z,playerColor)) {
                  board[x][y][z] = playerColor;
                  super.move(x,y,z,direction,computerColor);
                  System.out.print("\n\n(defending move)Computers move: " + x + " " + y + " " + z + " " + direction.toString() + "\n\n");
                  return true;
                }
              case FRONT:
                if(!aRunContains(x,y-1,z,playerColor)) {
                  board[x][y][z] = playerColor;
                  super.move(x,y,z,direction,computerColor);
                  System.out.print("\n\n9defending move)Computers move: " + x + " " + y + " " + z + " " + direction.toString() + "\n\n");
                  return true;
                }
              case BACK:
                if(!aRunContains(x,y+1,z,playerColor)) {
                  board[x][y][z] = playerColor;
                  super.move(x,y,z,direction,computerColor);
                  System.out.print("\n\n(defending move)Computers move: " + x + " " + y + " " + z + " " + direction.toString() + "\n\n");
                  return true;
                }
            }
          }
        }
      }
    }
    return false;
  }

  //doesn't recognize moves which push piece into winning position
  private boolean dumbWinningMove() {
    BoardLocation lastPosition;
    for(ArrayList<BoardLocation> run : runs) {
      lastPosition = getThirdPosition(run, computerColor);
      if(lastPosition != null) {
        int x = lastPosition.getX();
        int y = lastPosition.getY();
        int z = lastPosition.getZ();
        for(Direction direction : Direction.values()) {
          if(isValidMove(x,y,z,direction)) {
            super.move(x,y,z,direction,computerColor);
            System.out.print("\n\n(dumb winning move)Computers move: " + x + " " + y + " " + z + " " + direction.toString() + "\n\n");
            return true;
          }
        }
      }
    }
    return false;
  }

  private BoardLocation getThirdPosition(ArrayList<BoardLocation> run, LocationState player) {
    if(locationToState(run.get(0)).equals(player) && locationToState(run.get(1)).equals(player)) {
      return run.get(2);
    }
    else if(locationToState(run.get(0)).equals(player) && locationToState(run.get(2)).equals(player)) {
      return run.get(1);
    }
    else if(locationToState(run.get(1)).equals(player) && locationToState(run.get(2)).equals(player)) {
      return run.get(0);
    }
    else {
      return null;
    }
  }

  // any run going through the given position contains the given player
  private boolean aRunContains(int x, int y, int z, LocationState player) {
    BoardLocation loc = new BoardLocation(x,y,z);

    for(ArrayList<BoardLocation> run : runs) {
      if(run.get(0).equals(loc)) {
        if (locationToState(run.get(1)).equals(player) || locationToState(run.get(2)).equals(player)) {
          return true;
        }
      }
      else if(run.get(1).equals(loc)) {
        if(locationToState(run.get(0)).equals(player) || locationToState(run.get(2)).equals(player)) {
          return true;
        }
      }
      else if(run.get(2).equals(loc)) {
        if(locationToState(run.get(0)).equals(player) || locationToState(run.get(1)).equals(player)) {
          return true;
        }
      }
    }
    return false;
  }
}
