public class Player {

    private String playerName;
    private char playerCharacter;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public char getPlayerCharacter() {
        return playerCharacter;
    }

    public void setPlayerCharacter(char playerCharacter) {
        this.playerCharacter = playerCharacter;
    }

    public Player(String playerName, char playerCharacter) {
        this.playerName = playerName;
        this.playerCharacter = playerCharacter;
    }
}



