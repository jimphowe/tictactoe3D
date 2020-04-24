package controller;

import model.OnePlayerImpl;
import model.PuzzleImpl;
import model.ThreePlayerImpl;
import model.TicTacToeModel;
import model.TwoPlayerImpl;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Game {
  public static void main(String[] args ) {
    while(true) {
      printWelcome();
      printRules();

      TicTacToeController controller = new TicTacToeControllerImpl(
              new InputStreamReader(System.in), System.out);
      TicTacToeModel model;
      Scanner scan = new Scanner(System.in);
      String val = scan.next();
      ArrayList<String> playersOptions = new ArrayList<>(Arrays.asList("1","2","3","p"));
      while(!playersOptions.contains(val)) {
        System.out.print("Please enter a valid choice (1, 2, 3, or p)\n");
        val = scan.next();
      }
      // Carve out for puzzle mode
      if(val.equals("p")) {
        PuzzleImpl puzzles = new PuzzleImpl();
        puzzles.start();
      }
      else {
        switch (val) {
          case "1":
            System.out.print("Would you like to go first or second?\n");
            String turn = scan.next();
            ArrayList<String> turnOptions = new ArrayList<>(Arrays.asList("1", "F", "FIRST", "2", "S", "SECOND"));
            while (!turnOptions.contains(turn.toUpperCase())) {
              System.out.print("Please enter a valid choice (first or second)\n");
              turn = scan.next();
            }
            System.out.print("What difficulty level would you prefer? (1, 2, or 3)\n");
            String level = scan.next();
            ArrayList<String> levelOptions = new ArrayList<>(Arrays.asList("1", "2", "3"));
            while (!levelOptions.contains(level)) {
              System.out.print("Please enter a valid choice (1, 2, 3, or p)\n");
              level = scan.next();
            }
            if (turn.toUpperCase().equals("FIRST") || turn.toUpperCase().equals("F") || turn.equals("1")) {
              model = new OnePlayerImpl(false, Integer.parseInt(level));
            } else if (turn.toUpperCase().equals("SECOND") || turn.toUpperCase().equals("S") || turn.equals("2")) {
              model = new OnePlayerImpl(true, Integer.parseInt(level));
            } else {
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
      System.out.print("Would you like to play again? (y/n)\n");
      String keepPlaying = scan.next();
      ArrayList<String> playAgainOptions = new ArrayList<>(Arrays.asList("Y","YES","N","NO"));
      while (!playAgainOptions.contains(keepPlaying.toUpperCase())) {
        System.out.print("Please enter a valid choice (y or n)\n");
        keepPlaying = scan.next();
      }
      if(keepPlaying.toUpperCase().equals("N")) {
        return;
      }
    }
  }

  private static void printWelcome() {
    System.out.println("Welcome to tic tac toe!!! Now in three dimensions!!");
  }

  private static void printRules() {
    System.out.println("Make sure you have read the README before starting\n\n1, 2, or 3 players?" +
            " Or 'p' for puzzles");
  }

}
