public class Board {

    private char[][] board = new char[3][3];

    public void createBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public void printBoard() {
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.printf(" %s ",board[i][j]);
                if (j != board[0].length - 1) {
                    System.out.print("|");
                }
            }
            if (i != board.length - 1) {
                System.out.println("\n---+---+---");
            }
        }
        System.out.println();
    }

    public void addPieceToBoard(char symbol, int row, int column) {
        board[row - 1][column - 1] = symbol;
    }

}
