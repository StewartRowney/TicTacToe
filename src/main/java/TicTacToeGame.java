import models.Player;

import java.util.Scanner;

public class TicTacToeGame {
    Scanner myScanner = new Scanner(System.in);

    void mainTicTacToeApp() {
        drawMainDesign();
        boolean gameIsRunning = true;

        while (gameIsRunning) {
            gameIsRunning = initializeGame();
        }
    }

    private boolean initializeGame() {
        switch (presentMenu()) {
            case "1" -> {addPlayersAndStartGame(); return true;}
            case "2" -> {
                System.out.println("Thanks for playing");
                return false;
            }
            default -> {
                System.out.println("That's not a valid input, please try again");
                return initializeGame();
            }
        }
    }

    private String presentMenu() {

        System.out.println("""
                Welcome to Tic Tac Toe!
                ===> 1. Play Game
                ===> 2. Exit""");

        return myScanner.nextLine();
    }

    private void addPlayersAndStartGame() {

        System.out.print("""
                This is a 2 player game!
                Enter models.Player 1 name:\s""");
        String playerOneName = myScanner.nextLine();

        System.out.print("Enter models.Player 2 name: ");
        String playerTwoName = myScanner.nextLine();

        int mapSize = validateMapSizeInput(3, 15);

        GameLogic logic = new GameLogic(new Player(playerOneName, 'X'), new Player(playerTwoName, 'O'), mapSize);
        logic.startGame();
    }

    void drawMainDesign() {
        System.out.println("""
                                
                 /$$$$$$$$/$$                 /$$$$$$$$                        /$$$$$$$$                \s
                |__  $$__/__/                |__  $$__/                       |__  $$__/                \s
                   | $$   /$$  /$$$$$$$         | $$  /$$$$$$   /$$$$$$$         | $$  /$$$$$$   /$$$$$$\s
                   | $$  | $$ /$$_____/         | $$ |____  $$ /$$_____/         | $$ /$$__  $$ /$$__  $$
                   | $$  | $$| $$               | $$  /$$$$$$$| $$               | $$| $$  \\ $$| $$$$$$$$
                   | $$  | $$| $$               | $$ /$$__  $$| $$               | $$| $$  | $$| $$_____/
                   | $$  | $$|  $$$$$$$         | $$|  $$$$$$$|  $$$$$$$         | $$|  $$$$$$/|  $$$$$$$
                   |__/  |__/ \\_______/         |__/ \\_______/ \\_______/         |__/ \\______/  \\_______/
                                                                                                        \s
                                                                                                        \s
                                                                                                        \s""");
    }

    private int validateMapSizeInput(int minSize, int maxSize) {
        String question = String.format("Enter map size (minimum %d, maximum %d: ", minSize, maxSize);
        System.out.print(question);
        String input = myScanner.nextLine();
        int number;

        try {
            number = Integer.parseInt(input);
            if (number < minSize || number > maxSize) {
                System.out.println("Enter a valid input");
                return validateMapSizeInput(minSize, maxSize);
            }
        }
        catch (IllegalArgumentException ex) {
            System.out.println("Enter a valid input");
            return validateMapSizeInput(minSize, maxSize);
        }

        return number;
    }
}