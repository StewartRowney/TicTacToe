public class Board {

    private final char[][] board;
    private final int height;
    private final int width;

    Board(int mapSize) {
        height = mapSize;
        width = mapSize;
        board = new char[height][width];
        createBoard();
    }

    public char[][] getBoard() {
        return board;
    }
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }

    private void createBoard() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public void printBoard() {
        System.out.println();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.printf(" %s ",board[i][j]);
                if (j != width - 1) {
                    System.out.print("|");
                }
            }
            if (i != height - 1) {
                System.out.println("\n---+---+---");
            }
        }
        System.out.println("\n");
    }

    public void addPieceToBoard(char symbol, int row, int column) {
        board[row - 1][column - 1] = symbol;
    }

}
