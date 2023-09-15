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

//    static String turn;
//    Scanner scanner = new Scanner(System.in);
//    Board board = new Board();
//
//
//    turn = "X";
//    String winner = null;
//    for (int a = 0; a < 9; a++) { board[a] = String.valueOf(a + 1);
//        System.out.println("Welcome to 3x3 Tic Tac Toe.");
//        printBoard();
//        System.out.println("x will play first. Enter a slot number to place X in:");
//        while(winner == null) {
//            int numInput;
//// Exception handling.
//// numInput will take input from user like from 1 to 9 .
//// If it is not in range from 1 to 9.
//// then it will show you an error "Invalid input."
//try {
//            numInput = scanner.nextInt();
//            if (!(numInput > 0 && numInput <= 9)) { System.out.println("Invalid input; re-enter slot number:");
//                continue;
//catch (InputMismatchException e) {
//                    System.out.println("Invalid input; re-enter slot number: ");
//                    continue;
//                }
//// This game has two player x and 0.
//// Here is the logic to decide the turn.
//    if (board[numInput - 1].equals(
//                String.valueOf(numInput))) {
//                    board [numInput 1] = turn;
//                    if (turn.equals("X")) {
//                        turn = "0";
//                    }
//                    else {
//                        turn = "X";
//                    }
//                    printBoard();
//                    winner checkWinner();
//                }
//    else {
//                    System.out.println("Slot already taken; re-enter slot number:");
//                }
//            }
//        }

}
