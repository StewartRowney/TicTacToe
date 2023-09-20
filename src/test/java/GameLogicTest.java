import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameLogicTest {

    GameLogic gameLogic;

    @BeforeEach
    public void initialiseGame() {
        Player player1 = new Player("Player 1", 'X');
        Player player2 = new Player("Player 2", 'O');
        gameLogic = new GameLogic( player1, player2);
    }

    @Test
    public void testCheckDrawForEmptyBoard() {
        assertFalse(gameLogic.checkDraw());
    }

    @Test
    public void testCheckDrawForHalfFullBoard() {
        char symbol = 'X';
        gameLogic.board.addPieceToBoard('O', new Location(1, 1));
        gameLogic.board.addPieceToBoard('X', new Location(3, 1));
        gameLogic.board.addPieceToBoard('O', new Location(1, 3));
        gameLogic.board.addPieceToBoard('X', new Location(3, 3));
        gameLogic.board.addPieceToBoard('O', new Location(2, 2));

        assertFalse(gameLogic.checkDraw());
    }

    @Test
    public void testCheckDrawForFullBoard() {
        gameLogic.board.addPieceToBoard('X', new Location(1, 1));
        gameLogic.board.addPieceToBoard('X', new Location(3, 1));
        gameLogic.board.addPieceToBoard('X', new Location(1, 3));
        gameLogic.board.addPieceToBoard('X', new Location(3, 3));
        gameLogic.board.addPieceToBoard('O', new Location(2, 2));
        gameLogic.board.addPieceToBoard('O', new Location(1, 2));
        gameLogic.board.addPieceToBoard('O', new Location(2, 1));
        gameLogic.board.addPieceToBoard('O', new Location(2, 3));
        gameLogic.board.addPieceToBoard('O', new Location(3, 2));

        assertTrue(gameLogic.checkDraw());
    }
}