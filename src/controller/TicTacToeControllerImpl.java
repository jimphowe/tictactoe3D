package controller;

import model.TicTacToeModel;
import model.LocationState;
import model.Direction;

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
    tryAppend(model.getGameState());
    while (scan.hasNext()) {
      String val = scan.next();
      if (val.equals("q") || val.equals("Q")) {
        tryAppend("\nGame quit!\nState of game when quit:\n\n" + model.getGameState() + "\n");
        return;
      }
      if (val.equals("u") || val.equals("U")) {
        model.undo();
        tryAppend("Move undone\n" + model.getGameState() + "\n");
      }
      else if (isValidInput(val, command.size())) {
        command.add(val);
      }
      else {
        tryAppend("Input \"" + val + "\" not recognized, please re-enter it\n");
      }
      if (command.size() == 5) {
        try {
          model.move(Integer.parseInt(command.get(0)), Integer.parseInt(command.get(1)),
                  Integer.parseInt(command.get(2)), handleDirection(command.get(3)), handleState(command.get(4)));
          command.clear();
          tryAppend(model.getGameState() + "\n");
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
    else if(position == 4) {
      return validPlayer(val);
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
      System.out.print("Must enter a valid position (0,1,2)");
      return false;
    }
  }

  private boolean validDirection(String val) {
    List<String> valid = new ArrayList<>(Arrays.asList("UP","DOWN","LEFT","RIGHT","FRONT","BACK"));
    if(valid.contains(val.toUpperCase())) {
      return true;
    }
    else {
      System.out.print("Must enter a valid direction (UP,DOWN,LEFT,RIGHT,FRONT,BACK)");
      return false;
    }
  }

  private boolean validPlayer(String val) {
    List<String> valid = new ArrayList<>(Arrays.asList("RED","BLACK","WHITE"));
    if(valid.contains(val.toUpperCase())) {
      return true;
    }
    else {
      System.out.print(("Must enter a valid player (RED,WHITE,BLACK)"));
      return false;
    }
  }

  private Direction handleDirection(String dir) {
    switch (dir.toUpperCase()) {
      case "UP":
        return Direction.UP;
      case "DOWN":
        return Direction.DOWN;
      case "LEFT":
        return Direction.LEFT;
      case "RIGHT":
        return Direction.RIGHT;
      case "FRONT":
        return Direction.FRONT;
      case "BACK":
        return Direction.BACK;
      default:
        System.out.println("Direction invalid");
        return null;
    }
  }

  private LocationState handleState(String state) {
    switch (state.toUpperCase()) {
      case "RED":
        return LocationState.RED;
      case "BLACK":
        return LocationState.BLACK;
      case "WHITE":
        return LocationState.WHITE;
      default:
        System.out.println("Invalid player");
        return null;
    }
  }
}
