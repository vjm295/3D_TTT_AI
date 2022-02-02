public class Random implements PlayerInt
{
    private char letter;
    private Location move;
    private String name;

    public Random(char letter, String name)
    {
        this.letter = letter;
        this.name = name;
    }

    @Override
    public char getLetter() {
        return letter;
    }

    @Override
    public Location getMove(char[][][] board) {
        return move;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void reset() {

    }
}
