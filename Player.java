 public class Player {
    private char letter;
    private String name;

    public Player(char letter, String name)
    {
        this.letter = letter;
        this.name = name;
    }

    public char getLetter() {
        return letter;
    }

    public String getName() {
        return name;
    }

    public Location getMove(char[][][] board) {
        return null;
    }
}
