import enums.CheckLine;
import models.Location;
import models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class GameLogicTest {

    GameLogic gameLogic;

    @BeforeEach
    public void initialiseGame() {
        Player player1 = new Player("models.Player 1", 'X');
        Player player2 = new Player("models.Player 2", 'O');
        gameLogic = new GameLogic( player1, player2, 3);
    }

    @Test
    public void testCheckDraw_ForEmptyBoard() {
        assertFalse(gameLogic.checkDraw());
    }

    @Test
    public void testCheckDraw_ForHalfFullBoard() {
        gameLogic.board.addPieceToBoard('O', new Location(1, 1));
        gameLogic.board.addPieceToBoard('X', new Location(3, 1));
        gameLogic.board.addPieceToBoard('O', new Location(1, 3));
        gameLogic.board.addPieceToBoard('X', new Location(3, 3));
        gameLogic.board.addPieceToBoard('O', new Location(2, 2));

        assertFalse(gameLogic.checkDraw());
    }

    @Test
    public void testCheckDraw_ForFullBoard() {
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

    @ParameterizedTest
    @CsvSource (value = {"1:1", "1:2", "1:3", "2:1", "2:2", "2:3", "3:1", "3:2", "3:3"}, delimiter = ':')
    public void testAddingValue_ToEmptyBoard(int x, int y) {
        gameLogic.board.addPieceToBoard('O', new Location(x, y));
        assertEquals('O', gameLogic.board.getValueAt(x - 1 , y - 1));
    }

    @ParameterizedTest
    @CsvSource (value = {"0:1", "1:0", "4:3", "2:4"}, delimiter = ':')
    public void testAddingValue_OutOfBoardLimits(int x, int y) {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> gameLogic.board.addPieceToBoard('O', new Location(x, y)));
    }

    @ParameterizedTest
    @CsvSource (value = {"1:1", "1:2", "1:3", "2:1", "2:2", "2:3", "3:1", "3:2", "3:3"}, delimiter = ':')
    public void testValidateMove_ToEmptySpot(int x, int y) {
        assertTrue(gameLogic.validateMove(new Location(x, y)));
    }

    @ParameterizedTest
    @CsvSource (value = {"1:1", "1:2", "1:3", "2:1", "2:2", "2:3", "3:1", "3:2", "3:3"}, delimiter = ':')
    public void testValidateMove_ToTakenSpot(int x, int y) {
        Location location = new Location(x, y);
        gameLogic.board.addPieceToBoard('X', location);
        assertFalse(gameLogic.validateMove(location));
    }

    @ParameterizedTest
    @CsvSource (value = {"1:1", "1:2", "1:3", "2:1", "2:2", "2:3", "3:1", "3:2", "3:3"}, delimiter = ':')
    public void testCheckLocationForWinner_OnFirstMove(int row, int column) {
        Location location = new Location(row, column);
        gameLogic.board.addPieceToBoard('X', location);
        assertFalse(gameLogic.checkLocationForWinner(location));
    }

    @ParameterizedTest
    @ValueSource (ints = {1, 2, 3})
    public void testRowCheckForWinner_LeftColumn_WhenEmptyCentreAndRightSameSymbol(int row) {
        Location location = new Location(row, 1);
        gameLogic.board.addPieceToBoard('X', new Location(row, 3));
        gameLogic.board.addPieceToBoard('X', location);
        assertFalse(gameLogic.checkLineForWinner(CheckLine.HORIZONTAL, location));
    }

    @ParameterizedTest
    @ValueSource (ints = {1, 2, 3})
    public void testRowCheckForWinner_RightColumn_WhenEmptyCentreAndLeftSameSymbol(int row) {
        Location location = new Location(row, 3);
        gameLogic.board.addPieceToBoard('X', new Location(row, 1));
        gameLogic.board.addPieceToBoard('X', location);
        assertFalse(gameLogic.checkLineForWinner(CheckLine.HORIZONTAL, location));
    }

    @ParameterizedTest
    @ValueSource (ints = {1, 2, 3})
    public void testRowCheckForWinner_CentreColumn_WhenEmptyLeftAndRightSameSymbol(int row) {
        Location location = new Location(row, 2);
        gameLogic.board.addPieceToBoard('X', new Location(row, 3));
        gameLogic.board.addPieceToBoard('X', location);
        assertFalse(gameLogic.checkLineForWinner(CheckLine.HORIZONTAL, location));
    }

    @ParameterizedTest
    @ValueSource (ints = {1, 2, 3})
    public void testRowCheckForWinner_CentreColumn_WhenEmptyRightAndLeftSameSymbol(int row) {
        Location location = new Location(row, 2);
        gameLogic.board.addPieceToBoard('X', new Location(row, 1));
        gameLogic.board.addPieceToBoard('X', location);
        assertFalse(gameLogic.checkLineForWinner(CheckLine.HORIZONTAL, location));
    }

    @ParameterizedTest
    @ValueSource (ints = {1, 2, 3})
    public void testRowCheckForWinner_LeftColumn_ForWinnerAllSameSymbol(int row) {
        Location location = new Location(row, 1);
        gameLogic.board.addPieceToBoard('X', new Location(row, 2));
        gameLogic.board.addPieceToBoard('X', new Location(row, 3));
        gameLogic.board.addPieceToBoard('X', location);
        assertTrue(gameLogic.checkLineForWinner(CheckLine.HORIZONTAL, location));
    }

    @ParameterizedTest
    @ValueSource (ints = {1, 2, 3})
    public void testRowCheckForWinner_CentreColumn_AllSameSymbol(int row) {
        Location location = new Location(row, 2);
        gameLogic.board.addPieceToBoard('X', new Location(row, 1));
        gameLogic.board.addPieceToBoard('X', new Location(row, 3));
        gameLogic.board.addPieceToBoard('X', location);
        assertTrue(gameLogic.checkLineForWinner(CheckLine.HORIZONTAL, location));
    }

    @ParameterizedTest
    @ValueSource (ints = {1, 2, 3})
    public void testRowCheckForWinner_RightColumn_AllSameSymbol(int row) {
        Location location = new Location(row, 3);
        gameLogic.board.addPieceToBoard('X', new Location(row, 2));
        gameLogic.board.addPieceToBoard('X', new Location(row, 1));
        gameLogic.board.addPieceToBoard('X', location);
        assertTrue(gameLogic.checkLineForWinner(CheckLine.HORIZONTAL, location));
    }

    @ParameterizedTest
    @ValueSource (ints = {1, 2, 3})
    public void testColumnCheckForWinner_TopRow_WhenEmptyCentreAndBottomSameSymbol(int column) {
        Location location = new Location(1, column);
        gameLogic.board.addPieceToBoard('X', new Location(3, column));
        gameLogic.board.addPieceToBoard('X', location);
        assertFalse(gameLogic.checkLineForWinner(CheckLine.VERTICAL, location));
    }

    @ParameterizedTest
    @ValueSource (ints = {1, 2, 3})
    public void testColumnCheckForWinner_BottomRow_WhenEmptyCentreAndTopSameSymbol(int column) {
        Location location = new Location(3, column);
        gameLogic.board.addPieceToBoard('X', new Location(1, column));
        gameLogic.board.addPieceToBoard('X', location);
        assertFalse(gameLogic.checkLineForWinner(CheckLine.VERTICAL, location));
    }

    @ParameterizedTest
    @ValueSource (ints = {1, 2, 3})
    public void testColumnCheckForWinner_CentreRow_WhenEmptyBottomAndTopSameSymbol(int column) {
        Location location = new Location(2, column);
        gameLogic.board.addPieceToBoard('X', new Location(1, column));
        gameLogic.board.addPieceToBoard('X', location);
        assertFalse(gameLogic.checkLineForWinner(CheckLine.VERTICAL, location));
    }

    @ParameterizedTest
    @ValueSource (ints = {1, 2, 3})
    public void testColumnCheckForWinner_CentreRow_WhenEmptyTopAndBottomSameSymbol(int column) {
        Location location = new Location(2, column);
        gameLogic.board.addPieceToBoard('X', new Location(3, column));
        gameLogic.board.addPieceToBoard('X', location);
        assertFalse(gameLogic.checkLineForWinner(CheckLine.VERTICAL, location));
    }

    @ParameterizedTest
    @ValueSource (ints = {1, 2, 3})
    public void testColumnCheckForWinner_TopRow_AllSameSymbol(int column) {
        Location location = new Location(1, column);
        gameLogic.board.addPieceToBoard('X', new Location(2, column));
        gameLogic.board.addPieceToBoard('X', new Location(3, column));
        gameLogic.board.addPieceToBoard('X', location);
        assertTrue(gameLogic.checkLineForWinner(CheckLine.VERTICAL, location));
    }

    @ParameterizedTest
    @ValueSource (ints = {1, 2, 3})
    public void testColumnCheckForWinner_CentreRow_AllSameSymbol(int column) {
        Location location = new Location(2, column);
        gameLogic.board.addPieceToBoard('X', new Location(1, column));
        gameLogic.board.addPieceToBoard('X', new Location(3, column));
        gameLogic.board.addPieceToBoard('X', location);
        assertTrue(gameLogic.checkLineForWinner(CheckLine.VERTICAL, location));
    }

    @ParameterizedTest
    @ValueSource (ints = {1, 2, 3})
    public void testColumnCheckForWinner_BottomRow_AllSameSymbol(int column) {
        Location location = new Location(3, column);
        gameLogic.board.addPieceToBoard('X', new Location(2, column));
        gameLogic.board.addPieceToBoard('X', new Location(1, column));
        gameLogic.board.addPieceToBoard('X', location);
        assertTrue(gameLogic.checkLineForWinner(CheckLine.VERTICAL, location));
    }

    @Test
    public void testLeftDiagonalCheckForWinner_TopLeft_WhenEmptyCentreAndBottomRightSameSymbol() {
        Location location = new Location(1, 1);
        gameLogic.board.addPieceToBoard('X', new Location(3, 3));
        gameLogic.board.addPieceToBoard('X', location);
        assertFalse(gameLogic.checkLineForWinner(CheckLine.LEFT_DIAGONAL, location));
    }

    @Test
    public void testLeftDiagonalCheckForWinner_BottomRight_WhenEmptyCentreAndTopLeftSameSymbol() {
        Location location = new Location(3, 3);
        gameLogic.board.addPieceToBoard('X', new Location(1, 1));
        gameLogic.board.addPieceToBoard('X', location);
        assertFalse(gameLogic.checkLineForWinner(CheckLine.LEFT_DIAGONAL, location));
    }

    @Test
    public void testLeftDiagonalCheckForWinner_Centre_WhenEmptyTopLeftAndBottomRightSameSymbol() {
        Location location = new Location(2, 2);
        gameLogic.board.addPieceToBoard('X', new Location(3, 3));
        gameLogic.board.addPieceToBoard('X', location);
        assertFalse(gameLogic.checkLineForWinner(CheckLine.LEFT_DIAGONAL, location));
    }

    @Test
    public void testLeftDiagonalCheckForWinner_Centre_WhenEmptyBottomRightAndTopLeftSameSymbol() {
        Location location = new Location(2, 2);
        gameLogic.board.addPieceToBoard('X', new Location(1, 1));
        gameLogic.board.addPieceToBoard('X', location);
        assertFalse(gameLogic.checkLineForWinner(CheckLine.LEFT_DIAGONAL, location));
    }

    @Test
    public void testLeftDiagonalCheckForWinner_TopLeft_AllSameSymbol() {
        Location location = new Location(1, 1);
        gameLogic.board.addPieceToBoard('X', new Location(2, 2));
        gameLogic.board.addPieceToBoard('X', new Location(3, 3));
        gameLogic.board.addPieceToBoard('X', location);
        assertTrue(gameLogic.checkLineForWinner(CheckLine.LEFT_DIAGONAL, location));
    }

    @Test
    public void testLeftDiagonalCheckForWinner_Centre_AllSameSymbol() {
        Location location = new Location(2, 2);
        gameLogic.board.addPieceToBoard('X', new Location(1, 1));
        gameLogic.board.addPieceToBoard('X', new Location(3, 3));
        gameLogic.board.addPieceToBoard('X', location);
        assertTrue(gameLogic.checkLineForWinner(CheckLine.LEFT_DIAGONAL, location));
    }

    @Test
    public void testLeftDiagonalCheckForWinner_BottomRight_AllSameSymbol() {
        Location location = new Location(3, 3);
        gameLogic.board.addPieceToBoard('X', new Location(2, 2));
        gameLogic.board.addPieceToBoard('X', new Location(1, 1));
        gameLogic.board.addPieceToBoard('X', location);
        assertTrue(gameLogic.checkLineForWinner(CheckLine.LEFT_DIAGONAL, location));
    }

    @Test
    public void testRightDiagonalCheckVTopRight_WhenEmptyCentreAndBottomLeftSameSymbol() {
        Location location = new Location(1, 3);
        gameLogic.board.addPieceToBoard('X', new Location(3, 1));
        gameLogic.board.addPieceToBoard('X', location);
        assertFalse(gameLogic.checkLineForWinner(CheckLine.RIGHT_DIAGONAL, location));
    }

    @Test
    public void testRightDiagonalCheckForWinner_BottomLeft_WhenEmptyCentreAndTopRightSameSymbol() {
        Location location = new Location(3, 1);
        gameLogic.board.addPieceToBoard('X', new Location(1, 3));
        gameLogic.board.addPieceToBoard('X', location);
        assertFalse(gameLogic.checkLineForWinner(CheckLine.RIGHT_DIAGONAL, location));
    }

    @Test
    public void testRightDiagonalCheckForWinner_Centre_WhenEmptyTopRightAndBottomLeftSameSymbol() {
        Location location = new Location(2, 2);
        gameLogic.board.addPieceToBoard('X', new Location(3, 1));
        gameLogic.board.addPieceToBoard('X', location);
        assertFalse(gameLogic.checkLineForWinner(CheckLine.RIGHT_DIAGONAL, location));
    }

    @Test
    public void testRightDiagonalCheckForWinner_Centre_WhenEmptyTopLeftAndBottomRightSameSymbol() {
        Location location = new Location(2, 2);
        gameLogic.board.addPieceToBoard('X', new Location(1, 3));
        gameLogic.board.addPieceToBoard('X', location);
        assertFalse(gameLogic.checkLineForWinner(CheckLine.RIGHT_DIAGONAL, location));
    }

    @Test
    public void testRightDiagonalCheckForWinner_TopRight_AllSameSymbol() {
        Location location = new Location(1, 3);
        gameLogic.board.addPieceToBoard('X', new Location(2, 2));
        gameLogic.board.addPieceToBoard('X', new Location(3, 1));
        gameLogic.board.addPieceToBoard('X', location);
        assertTrue(gameLogic.checkLineForWinner(CheckLine.RIGHT_DIAGONAL, location));
    }

    @Test
    public void testRightDiagonalCheckForWinner_Centre_AllSameSymbol() {
        Location location = new Location(2, 2);
        gameLogic.board.addPieceToBoard('X', new Location(1, 3));
        gameLogic.board.addPieceToBoard('X', new Location(3, 1));
        gameLogic.board.addPieceToBoard('X', location);
        assertTrue(gameLogic.checkLineForWinner(CheckLine.RIGHT_DIAGONAL, location));
    }

    @Test
    public void testRightDiagonalCheckForWinner_Bottom_WinnerAllSameSymbol() {
        Location location = new Location(3, 1);
        gameLogic.board.addPieceToBoard('X', new Location(2, 2));
        gameLogic.board.addPieceToBoard('X', new Location(1, 3));
        gameLogic.board.addPieceToBoard('X', location);
        assertTrue(gameLogic.checkLineForWinner(CheckLine.RIGHT_DIAGONAL, location));
    }

}