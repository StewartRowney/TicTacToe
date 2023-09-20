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
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }
    public char getValueAt(int x, int y) {
        return board[x][y];
    }
    public char getValueAt(Location location) {
        return board[location.row() - 1][location.column() - 1];
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
                System.out.printf(" %s ", getValueAt(i, j));
                if (j != width - 1) {
                    System.out.print("|");
                }
            }
            if (i != height - 1) {
                StringBuilder builder = new StringBuilder();
                builder.append("\n---");
                builder.append("+---".repeat(Math.max(0, width - 1)));
                System.out.println(builder);
            }
        }
        System.out.println("\n");
    }

    public void addPieceToBoard(char symbol, Location location) {
        board[location.row() - 1][location.column() - 1] = symbol;
    }

}
