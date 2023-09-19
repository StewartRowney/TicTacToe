import java.util.Scanner;

public class GameLogic {

    Player[] players = new Player[2];
    Board board = new Board(3);
    Scanner scanner = new Scanner(System.in);

    public GameLogic(Player player1, Player player2) {
        players[0] = player1;
        players[1] = player2;
    }

    public void startGame() {
        board.printBoard();

        boolean  gameFinished = false;
        Player player = players[randomlySelectPlayerIndex()];

        while (!gameFinished) {
            Location location = makeMove(player);

            if (checkWinner(location)){
                gameFinished = true;
                System.out.println("You are a winner: " + player.getPlayerName());
            }
            else if (checkDraw()){
                gameFinished = true;
                System.out.println("There was a draw.");
            }

            if (player == players[0]) {
                player = players[1];
            }
            else {
                player = players[0];
            }
        }
    }

    private Location makeMove(Player player) {
        System.out.printf("%s's turn\n", player.getPlayerName());
        int row = validateIntInput("Enter row: ");
        int column = validateIntInput("Enter column: ");
        Location location = new Location(row, column);

        if (validateMove(location)) {
            board.addPieceToBoard(player.getPlayerCharacter(), location);
            board.printBoard();
            return location;
        }
        else {
            System.out.println("That space is already occupied");
            return makeMove(player);
        }
    }

    private boolean validateMove(Location location) {
        char[][] currentBoard = board.getBoard();
        return (currentBoard[location.getRow() - 1][location.getColumn() - 1] == ' ');
    }

    private int validateIntInput(String question) {
        System.out.print(question);
        String input = scanner.nextLine();
        int number;

        try {
            number = Integer.parseInt(input);
            if (number < 1 || number > 3) {
                System.out.println("Enter a valid input");
                return validateIntInput(question);
            }
        }
        catch (IllegalArgumentException ex) {
            System.out.println("Enter a valid input");
            return validateIntInput(question);
        }

        return number;
    }

    private int randomlySelectPlayerIndex() {
        double turn = Math.random() * 2;
        return (int) turn;
    }

    private boolean checkWinner(Location newPieceLocation) {
        char[][] currentBoard = board.getBoard();

        boolean rowWin = checkRow(newPieceLocation.getRow(), currentBoard);
        if (rowWin) {
            return true;
        }

        boolean columnWin = checkColumn(newPieceLocation.getColumn(), currentBoard);
        if (columnWin) {
            return true;
        }

        return checkDiagonals(currentBoard);
    }

    private boolean checkDraw() {
        char[][] currentBoard = board.getBoard();

        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                if (currentBoard[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkRow(int row, char[][] currentBoard) {
        if (currentBoard[row - 1][0] != ' ') {
            for (int j = 1; j < board.getWidth(); j++) {
                if (currentBoard[row - 1][0] != currentBoard[row - 1][j]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean checkColumn(int column, char[][] currentBoard) {
        if (currentBoard[0][column - 1] != ' ') {
            for (int i = 1; i < board.getHeight(); i++) {
                if (currentBoard[0][column - 1] != currentBoard[i][column - 1]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean checkDiagonals(char[][] currentBoard) {

        if (currentBoard[0][0] != ' ') {
            boolean firstDiagonalWinner = true;

            for (int a = 1; a < board.getHeight(); a++) {
                if (currentBoard[0][0] != currentBoard[a][a]) {
                    firstDiagonalWinner = false;
                    break;
                }
            }

            if (firstDiagonalWinner) {
                return true;
            }
        }

        if (currentBoard[0][board.getWidth() - 1] != ' ') {
            for (int a = 1; a < board.getWidth(); a++) {
                if (currentBoard[0][board.getWidth() - 1] != currentBoard[a][board.getWidth() - 1 - a]) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }
}
