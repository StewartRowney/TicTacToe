import java.util.Scanner;

public class TicTacToeGame {
    Scanner myScanner = new Scanner(System.in);
    boolean gameIsRunning = true;
    String option;

    void mainTicTacToeApp() {
        drawMainDesign();
        while (gameIsRunning)
            initializeGame();
    }

    private void initializeGame() {

        option = presentMenu();
        switch (option) {
            case "1" -> addPlayersAndStartGame();
            case "2" -> {
                System.out.println("Thanks for playing");
                gameIsRunning = false;
            }
            default -> {
                System.out.println("That's not a valid input, please try again");
                initializeGame();
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
       // myScanner.nextLine();
        System.out.print("Enter Player 2 name: ");
        String playerTwoName = myScanner.nextLine();
        //printBoard();
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
}