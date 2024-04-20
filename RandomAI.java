public class RandomAI extends Player implements PlayerInt
{
    private char letter;
    private String name;

    public RandomAI(char letter, String name)
    {
        super(letter, name);
        this.letter = letter;
        this.name = name;
    }
 
    @Override
    public char getLetter() {
        return letter;
    }

    @Override
    public Location getMove(char[][][] board) {
        Location move;
        do {
            move = new Location((int) (Math.random() * 4), (int) (Math.random() * 4), (int) (Math.random() * 4));
        } while(board[move.getSheet()][move.getRow()][move.getCol()] == 'x' || board[move.getSheet()][move.getRow()][move.getCol()] == 'o');
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
