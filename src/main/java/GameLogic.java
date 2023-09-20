import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Scanner;

public class GameLogic {

    Player[] players = new Player[2];
    Board board;
    Scanner scanner = new Scanner(System.in);

    public GameLogic(Player player1, Player player2, int mapSize) {
        players[0] = player1;
        players[1] = player2;
        board = new Board(mapSize);
    }

    public void startGame() {
        board.printBoard();

        boolean  gameFinished = false;
        Player player = players[randomlySelectPlayerIndex()];

        while (!gameFinished) {
            Location location = makeMove(player);

            if (checkLocationForWinner(location)){
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
        return (board.getValueAt(location) == ' ');
    }

    private int validateIntInput(String question) {
        System.out.print(question);
        String input = scanner.nextLine();
        int number;

        try {
            number = Integer.parseInt(input);
            if (number < 1 || number > board.getHeight()) {
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

    protected boolean checkLocationForWinner(Location location) {

        ArrayList<CheckLine> lines = new ArrayList<>(EnumSet.allOf(CheckLine.class));

        if (location.row() != location.column()) {
            lines.remove(CheckLine.LEFT_DIAGONAL);
        }

        if (location.row() != board.getHeight() - location.column() + 1) {
            lines.remove(CheckLine.RIGHT_DIAGONAL);
        }

        for (CheckLine line : lines) {
            if (checkLineForWinner(line, location)) {
                return true;
            }
        }
        return false;
    }
    
    protected boolean checkLineForWinner(CheckLine line, Location location) {
        char symbol = board.getValueAt(location);
        boolean winner = false;
        
        for (CheckDirection direction : line.directions) {
            try {
                if (!checkNeighbourForWinner(symbol, direction.getNextLocation(location), direction)) {
                    return false;
                }
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                winner = true;
            }
        }

        return winner;
    }

    protected boolean checkNeighbourForWinner(char symbol, Location neighbourLocation, CheckDirection direction) throws ArrayIndexOutOfBoundsException{

        if (symbol != board.getValueAt(neighbourLocation)) {
            return false;
        }

        return checkNeighbourForWinner(symbol, direction.getNextLocation(neighbourLocation), direction);
    }

    protected boolean checkDraw() {
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                if (board.getValueAt(i, j) == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
