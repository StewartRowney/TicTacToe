import java.util.Scanner;

public class GameLogic {

    Player[] players = new Player[2];
    Board board = new Board();
    Scanner scanner = new Scanner(System.in);

    public GameLogic(Player player1, Player player2) {
        players[0] = player1;
        players[1] = player2;
    }

    public void startGame() {
        board.createBoard();
        board.printBoard();

        boolean  gameFinished = false;
        Player player = players[randomlySelectPlayerIndex()];

        while (!gameFinished) {
            makeMove(player);

            if (checkWinner()){
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

    private void makeMove(Player player) {
        System.out.printf("%s's turn\n", player.getPlayerName());
        int row = validateIntInput("Enter row: ");
        int column = validateIntInput("Enter column: ");

        if (validateMove(row, column)) {
            board.addPieceToBoard(player.getPlayerCharacter(), row, column);
            board.printBoard();
        }
        else {
            System.out.println("That space is already occupied");
            makeMove(player);
        }

    }

    private boolean validateMove(int row, int column) {
        char[][] currentBoard = board.getBoard();
        return (currentBoard[row - 1][column - 1] == ' ');
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

    private boolean checkWinner() {
        char[][] currentBoard = board.getBoard();

        for (int i = 0; i < currentBoard[0].length; i++) {
            boolean rowWin = checkRow(i, currentBoard);
            if (rowWin) {
                return true;
            }
        }

        for (int i = 0; i < currentBoard.length; i++) {
            boolean columnWin = checkColumn(i, currentBoard);
            if (columnWin) {
                return true;
            }
        }

        return checkDiagonals(currentBoard);
    }

    private boolean checkDraw() {
        char[][] currentBoard = board.getBoard();

        for (int i = 0; i < currentBoard[0].length; i++) {
            for (int j = 0; j < currentBoard.length; j++) {
                if (currentBoard[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkRow(int i, char[][] currentBoard) {
        if ((currentBoard[i][0] == currentBoard[i][1] && currentBoard[i][0] == currentBoard[i][2]) && currentBoard[i][0] != ' ') {
            return true;
        }
        return false;
    }

    private boolean checkColumn(int i, char[][] currentBoard) {
        if ((currentBoard[0][i] == currentBoard[1][i] && currentBoard[0][i] == currentBoard[2][i]) && currentBoard[0][i] != ' '){
            return true;
        }
        return false;
    }

    private boolean checkDiagonals(char[][] currentBoard) {
        if ((currentBoard[0][0] == currentBoard[1][1] && currentBoard[0][0] == currentBoard[2][2]) && currentBoard[0][0] != ' ') {
            return true;
        }
        else return (currentBoard[0][2] == currentBoard[1][1] && currentBoard[2][0] == currentBoard[0][2]) && currentBoard[1][1] != ' ';
    }
}
