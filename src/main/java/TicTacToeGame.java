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
                Enter Player 1 name:\s""");
        String playerOneName = myScanner.nextLine();

        System.out.print("Enter Player 2 name: ");
        String playerTwoName = myScanner.nextLine();

        int mapsize = validateIntInput("Enter map size minimum 3: ");

        GameLogic logic = new GameLogic(new Player(playerOneName, 'X'), new Player(playerTwoName, 'O'), mapsize);
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

    private int validateIntInput(String question) {
        System.out.print(question);
        String input = myScanner.nextLine();
        int number;

        try {
            number = Integer.parseInt(input);
            if (number < 3 ) {
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
}