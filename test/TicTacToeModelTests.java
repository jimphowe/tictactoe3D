import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import model.Direction;
import model.LocationState;
import model.ThreePlayerImpl;
import model.TicTacToeModel;

public class TicTacToeModelTests {

  @Test
  public void testConstructor() {
    TicTacToeModel game = new ThreePlayerImpl();
    assertEquals("Top: \nEMPTY  EMPTY  EMPTY  \n\nEMPTY  EMPTY  EMPTY  \n\nEMPTY  EMPTY  EMPTY  \n\n" +
            "Middle: \nEMPTY  EMPTY  EMPTY  \n\nEMPTY  EMPTY  EMPTY  \n\nEMPTY  EMPTY  EMPTY  \n\n" +
            "Bottom: \nEMPTY  EMPTY  EMPTY  \n\nEMPTY  EMPTY  EMPTY  \n\nEMPTY  EMPTY  EMPTY  \n\n"
            , game.getGameState());
  }

  @Test
  public void testIsGameOver1() {
    TicTacToeModel game = new ThreePlayerImpl();
    assertFalse(game.isGameOver());
  }

  @Test
  public void testIsGameOver2() {
    TicTacToeModel game = new ThreePlayerImpl();
    game.move(0, 0, 0, Direction.BACK, LocationState.RED);
    game.move(0, 0, 1, Direction.BACK, LocationState.RED);
    game.move(0, 0, 2, Direction.BACK, LocationState.RED);
    assertTrue(game.isGameOver());
  }

  @Test
  public void testMoveUp() {
    TicTacToeModel game = new ThreePlayerImpl();
    game.move(0, 0, 2, Direction.UP, LocationState.WHITE);
    game.move(0, 0, 2, Direction.UP, LocationState.RED);
  }

}
