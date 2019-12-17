package controller;

import model.OnePlayerImpl;
import model.ThreePlayerImpl;
import model.TicTacToeModel;
import model.TwoPlayerImpl;

import java.io.InputStreamReader;
import java.util.Scanner;

public class Game {

  public static void main( String[] args ) {
    printWelcome();
    printRules();

    TicTacToeController controller = new TicTacToeControllerImpl(
            new InputStreamReader(System.in), System.out);
    TicTacToeModel model;
    Scanner scan = new Scanner(System.in);
    String val = scan.next();
    switch(val)  {
      case "1":
        System.out.print("Would you like to go first or second?\n");
        String turn = scan.next();
        if(turn.toLowerCase().equals("first")) {
          model = new OnePlayerImpl(false);
        }
        else if(turn.toLowerCase().equals("second")) {
          model = new OnePlayerImpl(true);
        }
        else {
          throw new IllegalStateException();
        }
        break;
      case "2":
        model = new TwoPlayerImpl();
        break;
      case "3":
        model = new ThreePlayerImpl();
        break;
      default:
        throw new IllegalArgumentException("not a valid command");
    }
    controller.playGame(model);
  }

  private static void printWelcome() {
    System.out.println("Welcome to tic tac toe!!! Now in three dimensions!!");
  }

  private static void printRules() {
    System.out.println("The rules are the same as normal tic tac toe\n1, 2, or 3 players?");
  }

}
