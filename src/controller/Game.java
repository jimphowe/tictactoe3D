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
        if(turn.toUpperCase().equals("FIRST") || turn.toUpperCase().equals("F")) {
          model = new OnePlayerImpl(false);
        }
        else if(turn.toUpperCase().equals("SECOND") || turn.toUpperCase().equals("S")) {
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
    System.out.println("Make sure you have read the README before starting\n1, 2, or 3 players?");
  }

}
