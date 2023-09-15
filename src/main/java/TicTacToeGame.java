import java.util.Scanner;

public class TicTacToeGame {
    Scanner myScanner = new Scanner(System.in);
    boolean gameIsRunning=true;

    void mainTicTacToeApp(){
        while (gameIsRunning)
            initializeGame();
    }

    private void initializeGame() {
    drawMainDesign();
        System.out.println("""
                Welcome to Tic Tac Toe!
                ===> 1. Play new Game
                ===> 2. Exit
                """);
        int option = myScanner.nextInt();
        switch (option){
            case 1 -> addPlayersAndStartGame();
            case 2 -> {
                System.out.println("Thanks for playing!");
                gameIsRunning = false;}
        }
    }

    private void addPlayersAndStartGame() {
        System.out.println("""
                This is a 2 player game!
                Enter Player 1 name: """);
        String playerOneName = myScanner.nextLine();
        myScanner.nextLine();
        System.out.println("Enter Player 2 name: ");
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