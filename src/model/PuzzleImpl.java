package model;

import java.util.Scanner;

public class PuzzleImpl {
  public void start() {
    System.out.println("Welcome to puzzles!! There are currently 7 puzzles to choose from, in increasing " +
            "difficulty. Choose a number 1-7 to get started!");
    Scanner scan = new Scanner(System.in);
    String answer;
    String level;
    do {
      level = scan.next();
      scan.nextLine();
      switch (level) {
        case "1":
          do {
            System.out.println(
                    "+----------------------\n" +
                            "| ∖ BLACK    _      _   ∖\n" +
                            "|   ∖                     ∖\n" +
                            "|     ∖ BLACK    _      _   ∖\n" +
                            "|       ∖                     ∖\n" +
                            "|         ∖ WHITE  BLACK   RED  ∖\n" +
                            "|          ---------------------|\n" +
                            "|   BLACK |  _      _           |\n" +
                            "|         |                     |\n" +
                            "|        RED   BLACK    _       |\n" +
                            "|         |                     |\n" +
                            "|         | BLACK    _    BLACK |\n" +
                            "|         |                     |\n" +
                            " ∖  RED     _    BLACK          |\n" +
                            "   ∖      |                     |\n" +
                            "     ∖ WHITE    _      _        |\n" +
                            "       ∖  |                     |\n" +
                            "         ∖| WHITE  BLACK    _   |\n" +
                            "           ---------------------+");
            System.out.println("Find the win in 1!");
            answer = scan.nextLine();
            if (answer.equals("0 1 1 RIGHT") || answer.equals("0 1 1 R")) {
              System.out.println("You win!!");
              break;
            } else if (!answer.equals("q")) {
              System.out.println("Not quite, try again or press q to exit");
            }
          } while (!answer.equals("q"));
          break;
        case "2":
          do {
            System.out.println(
                    "+----------------------\n" +
                            "| ∖ WHITE    _    BLACK ∖\n" +
                            "|   ∖                     ∖\n" +
                            "|     ∖  RED   BLACK    _   ∖\n" +
                            "|       ∖                     ∖\n" +
                            "|         ∖   _      _    WHITE ∖\n" +
                            "|          ---------------------|\n" +
                            "|     _   |  _    BLACK         |\n" +
                            "|         |                     |\n" +
                            "|         _    BLACK  BLACK     |\n" +
                            "|         |                     |\n" +
                            "|         |  RED   BLACK  BLACK |\n" +
                            "|         |                     |\n" +
                            " ∖ BLACK   RED   WHITE          |\n" +
                            "   ∖      |                     |\n" +
                            "     ∖   _    BLACK  WHITE      |\n" +
                            "       ∖  |                     |\n" +
                            "         ∖|   _      _      _   |\n" +
                            "           ---------------------+");
            System.out.println("Stop white from winning next turn!");
            answer = scan.nextLine();
            if (answer.equals("2 2 2 FRONT") || answer.equals("2 2 2 F")) {
              System.out.println("You win!!");
              break;
            } else if (!answer.equals("q")) {
              System.out.println("Not quite, try again or press q to exit");
            }
          } while (!answer.equals("q"));
          break;
        case "3":
          do {
            System.out.println(
                    "+----------------------\n" +
                            "| ∖ BLACK  BLACK  BLACK ∖\n" +
                            "|   ∖                     ∖\n" +
                            "|     ∖ BLACK   RED     _   ∖\n" +
                            "|       ∖                     ∖\n" +
                            "|         ∖   _      _    WHITE ∖\n" +
                            "|          ---------------------|\n" +
                            "|     _   |  _    BLACK         |\n" +
                            "|         |                     |\n" +
                            "|         _      _    BLACK     |\n" +
                            "|         |                     |\n" +
                            "|         |   _    BLACK   RED  |\n" +
                            "|         |                     |\n" +
                            " ∖   _      _    BLACK          |\n" +
                            "   ∖      |                     |\n" +
                            "     ∖   _      _      _        |\n" +
                            "       ∖  |                     |\n" +
                            "         ∖| WHITE    _    BLACK |\n" +
                            "           ---------------------+");
            System.out.println("Win the game in 2 moves!");
            answer = scan.nextLine();
            if (answer.equals("2 0 0 LEFT") || answer.equals("2 0 0 L") ||
                    answer.equals("2 0 0 BACK") || answer.equals("2 0 0 B")) {
              System.out.println("You win!!");
              break;
            } else if (!answer.equals("q")) {
              System.out.println("Not quite, try again or press q to exit");
            }
          } while (!answer.equals("q"));
          break;
        case "4":
          do {
            System.out.println(
                    "+----------------------\n" +
                            "| ∖   _      _    BLACK ∖\n" +
                            "|   ∖                     ∖\n" +
                            "|     ∖   _      _      _   ∖\n" +
                            "|       ∖                     ∖\n" +
                            "|         ∖ WHITE    _      _   ∖\n" +
                            "|          ---------------------|\n" +
                            "|    RED  |BLACK  BLACK         |\n" +
                            "|         |                     |\n" +
                            "|       BLACK    _      _       |\n" +
                            "|         |                     |\n" +
                            "|         | BLACK  BLACK    _   |\n" +
                            "|         |                     |\n" +
                            " ∖   _    BLACK    _            |\n" +
                            "   ∖      |                     |\n" +
                            "     ∖ BLACK    _      _        |\n" +
                            "       ∖  |                     |\n" +
                            "         ∖| BLACK    _      _   |\n" +
                            "           ---------------------+");
            System.out.println("Win the game in 2 moves!");
            answer = scan.nextLine();
            if (answer.equals("0 0 1 RIGHT") || answer.equals("0 0 1 R")) {
              System.out.println("You win!!");
              break;
            } else if (!answer.equals("q")) {
              System.out.println("Not quite, try again or press q to exit");
            }
          } while (!answer.equals("q"));
          break;
        case "5":
          do {
            System.out.println(
                    "+----------------------\n" +
                            "| ∖ BLACK    _      _   ∖\n" +
                            "|   ∖                     ∖\n" +
                            "|     ∖ BLACK    _      _   ∖\n" +
                            "|       ∖                     ∖\n" +
                            "|         ∖   _    BLACK  BLACK ∖\n" +
                            "|          ---------------------|\n" +
                            "|   BLACK |  _      _           |\n" +
                            "|         |                     |\n" +
                            "|       BLACK  BLACK    _       |\n" +
                            "|         |                     |\n" +
                            "|         |   _     RED   BLACK |\n" +
                            "|         |                     |\n" +
                            " ∖   _      _      _            |\n" +
                            "   ∖      |                     |\n" +
                            "     ∖ BLACK    _      _        |\n" +
                            "       ∖  |                     |\n" +
                            "         ∖| WHITE    _      _   |\n" +
                            "           ---------------------+");
            System.out.println("Win the game in 2 moves!");
            answer = scan.nextLine();
            if (answer.equals("0 0 2 BACK") || answer.equals("0 0 2 B")) {
              System.out.println("You win!!");
              break;
            } else if (!answer.equals("q")) {
              System.out.println("Not quite, try again or press q to exit");
            }
          } while (!answer.equals("q"));
          break;
        case "6":
          do {
            System.out.println(
                    "+----------------------\n" +
                            "| ∖   _      _    BLACK ∖\n" +
                            "|   ∖                     ∖\n" +
                            "|     ∖   _      _    BLACK ∖\n" +
                            "|       ∖                     ∖\n" +
                            "|         ∖ WHITE    _    BLACK ∖\n" +
                            "|          ---------------------|\n" +
                            "|   BLACK |  _      _           |\n" +
                            "|         |                     |\n" +
                            "|         _      _    BLACK     |\n" +
                            "|         |                     |\n" +
                            "|         | BLACK  BLACK  BLACK |\n" +
                            "|         |                     |\n" +
                            " ∖   _      _    BLACK          |\n" +
                            "   ∖      |                     |\n" +
                            "     ∖   _      _      _        |\n" +
                            "       ∖  |                     |\n" +
                            "         ∖|   _      _     RED  |\n" +
                            "           ---------------------+");
            System.out.println("Win the game in 2 moves!");
            answer = scan.nextLine();
            if (answer.equals("1 1 2 UP")) {
              System.out.println("You win!!");
              break;
            } else if (!answer.equals("q")) {
              System.out.println("Not quite, try again or press q to exit");
            }
          } while (!answer.equals("q"));
          break;
        case "7":
          do {
            System.out.println("+----------------------\n" +
                    "| ∖ BLACK  BLACK   RED  ∖\n" +
                    "|   ∖                     ∖\n" +
                    "|     ∖ BLACK    _      _   ∖\n" +
                    "|       ∖                     ∖\n" +
                    "|         ∖   _    BLACK    _   ∖\n" +
                    "|          ---------------------|\n" +
                    "|     _   |BLACK  BLACK         |\n" +
                    "|         |                     |\n" +
                    "|         _    BLACK    _       |\n" +
                    "|         |                     |\n" +
                    "|         |   _      _      _   |\n" +
                    "|         |                     |\n" +
                    " ∖ BLACK    _      _            |\n" +
                    "   ∖      |                     |\n" +
                    "     ∖ BLACK    _      _        |\n" +
                    "       ∖  |                     |\n" +
                    "         ∖|   _    WHITE   RED  |\n" +
                    "           ---------------------+");
            System.out.println("Find the only move which prevents the opponent from winning in two!");
            answer = scan.nextLine();
            if (answer.equals("2 0 2 LEFT") || answer.equals("2 0 2 L")) {
              System.out.println("You win!!");
              break;
            } else if (!answer.equals("q")) {
              System.out.println("Not quite, try again or press q to exit");
            }
          } while (!answer.equals("q"));
        }
      if (!level.equals("q")) {
        System.out.println("Select another number puzzle, or press q again to quit puzzle mode");
      }
    } while (!level.equals("q"));
  }
}
