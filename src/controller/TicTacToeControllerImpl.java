package controller;

import model.OnePlayerImpl;
import model.ThreePlayerImpl;
import model.TicTacToeModel;
import model.LocationState;
import model.Direction;
import model.TwoPlayerImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TicTacToeControllerImpl implements TicTacToeController {

  private final Readable rd;
  private final Appendable ap;

  /**
   * creates an instance of our controller implementation given a Readable and Appendable object.
   * @param rd any readable object for our controller to read input from.
   * @param ap any appendable object for our controller to write output to.
   * @throws IllegalArgumentException if either object passed is null.
   */
  TicTacToeControllerImpl(Readable rd, Appendable ap) throws IllegalArgumentException {
    if (rd == null) {
      throw new IllegalArgumentException("Readable is null");
    }
    if (ap == null) {
      throw new IllegalArgumentException("Appendable is null");
    }
    this.rd = rd;
    this.ap = ap;
  }

  @Override
  public void playGame(TicTacToeModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Model is null");
    }
    Scanner scan = new Scanner(this.rd);
    ArrayList<String> command = new ArrayList<>();
    tryAppend(model.getGameState() + "\nRed players turn:\n");
    int turnCounter = 0;
    while (scan.hasNext()) {
      String val = scan.next();
      if (val.toUpperCase().equals("Q")) {
        tryAppend("\nGame quit!\nState of game when quit:\n\n" + model.getGameState() + "\n");
        return;
      }
      if (val.toUpperCase().equals("U")) {
        model.undo();
        turnCounter--;
        tryAppend("Move undone\n" + model.getGameState() + "\n");
        LocationState playersTurn = getPlayersTurn(model, turnCounter);
        tryAppend("\n\n" + playersTurn.toString() + " players turn: \n\n");
      }
      else if (isValidInput(val, command.size())) {
        command.add(val);
      }
      else {
        tryAppend("Input \"" + val + "\" not recognized, please re-enter the entire command\n");
        command = new ArrayList<>();
      }
      if (command.size() == 4) {
        try {
          LocationState playersTurn = getPlayersTurn(model, turnCounter);
          model.move(Integer.parseInt(command.get(0)), Integer.parseInt(command.get(1)),
                  Integer.parseInt(command.get(2)), handleDirection(command.get(3)),playersTurn);
          turnCounter++;
          playersTurn = getPlayersTurn(model, turnCounter);
          if(!model.isGameOver()) {
            tryAppend("\n\n" + model.getGameState() + playersTurn.toString() + " players turn: \n\n");
          }
          command.clear();
        } catch (IllegalArgumentException ex) {
          command.clear();
          tryAppend("This move is impossible, or would push a piece out of the board. Play again.\n");
        }
      }
      if (model.isGameOver()) {
        command.clear();
        return;
      }
    }
    throw new IllegalStateException();
  }

  private LocationState getPlayersTurn(TicTacToeModel model, int turnCounter) {
    if(model instanceof OnePlayerImpl) {
      return LocationState.RED;
    }
    else if(model instanceof TwoPlayerImpl) {
      if(turnCounter % 2 == 0) {
        return LocationState.RED;
      }
      else {
        return LocationState.WHITE;
      }
    }
    else if(model instanceof ThreePlayerImpl) {
      if(turnCounter % 3 == 0) {
        return LocationState.RED;
      }
      else if(turnCounter % 3 == 1) {
        return LocationState.WHITE;
      }
      else {
        return LocationState.BLACK;
      }
    }
    tryAppend("Turn calc failure");
    return null;
  }

  private void tryAppend(String msg) {
    try {
      this.ap.append(msg);
    } catch (IOException e) {
      e.printStackTrace();
      throw new IllegalStateException();
    }
  }

  private boolean isValidInput(String val, int position) {
    if(position < 3) {
      return validPosition(val);
    }
    else if(position == 3) {
      return validDirection(val);
    }
    else {
      throw new IllegalStateException("something weird happened");
    }
  }

  private boolean validPosition(String val) {
    List<String> valid = new ArrayList<>(Arrays.asList("0","1","2"));
    if(valid.contains(val)) {
      return true;
    }
    else {
      System.out.print("Must enter a valid position (0,1,2). ");
      return false;
    }
  }

  private boolean validDirection(String val) {
    List<String> valid = new ArrayList<>(Arrays.asList("UP","U","DOWN","D","LEFT","L","RIGHT","R","FRONT","F","BACK","B"));
    if(valid.contains(val.toUpperCase())) {
      return true;
    }
    else {
      System.out.print("Must enter a valid direction (UP,DOWN,LEFT,RIGHT,FRONT,BACK). ");
      return false;
    }
  }

  private Direction handleDirection(String dir) {
    switch (dir.toUpperCase()) {
      case "UP":
      //case "U":
        return Direction.UP;
      case "DOWN":
      case "D":
        return Direction.DOWN;
      case "LEFT":
      case "L":
        return Direction.LEFT;
      case "RIGHT":
      case "R":
        return Direction.RIGHT;
      case "FRONT":
      case "F":
        return Direction.FRONT;
      case "BACK":
      case "B":
        return Direction.BACK;
      default:
        System.out.println("Direction invalid");
        return null;
    }
  }
}
