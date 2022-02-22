import java.util.ArrayList;
public class Ragnarok extends Player implements PlayerInt
{
    private char letter;
    private String name;
    private ArrayList<Integer> scores = new ArrayList<>();
    private ArrayList<Location> moves = new ArrayList<>();
    int[][][] cube = new int[4][4][4];

    public Ragnarok(char letter, String name)
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
        
        //int index=0;
        int points=0, a=0, b=0, c=0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    if(board[i][j][k] != 'x' && board[i][j][k] != 'o')
                    {
                        grader(board, new Location(k, j, i));
                    }
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    if(cube[i][j][k] > points && board[i][j][k] != 'x' && board[i][j][k] != 'o')
                    {
                        points = cube[i][j][k];
                        a=i;
                        b=j;
                        c=k;
                    }
                }
            }
        }
        move = new Location(c, b, a);

        System.out.println(move);
        return move;
    }

    public void grader(char[][][] board, Location move)
    {
        int points=0;
        char enemy = 'o';
        if(letter == 'o')
            enemy = 'x';
        board[move.getSheet()][move.getRow()][move.getCol()] = letter;
        int hc=0, vc=0, tbdc=0, btdc=0, sc=0, svc=0, svc2=0, shc=0, shc2=0, tbdsc=0, tbdsc2=0, btdsc=0, btdsc2=0;
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                hc=0; vc=0; tbdc=0; btdc=0; sc=0; svc=0; svc2=0; shc=0; shc2=0; tbdsc=0; tbdsc2=0; btdsc=0; btdsc2=0;

                for(int x = 0, y = 3; x < 4 && y >= 0; x++, y--)
                {
                    if(board[i][j][x] == letter) // row check
                        hc++;
                    if(board[i][x][j] == letter) // col check
                        vc++;
                    if(board[i][x][x] == letter) // top to bottom diagonal check
                        tbdc++;
                    if(board[i][x][y] == letter) // bottom to top diagonal check
                        btdc++;
                    if(board[x][i][j] == letter) // sheet check
                        sc++;
                    if(board[x][x][i] == letter) // sheet col check
                        svc++;
                    if(board[x][y][i] == letter) // sheet col check from last sheet (new)
                        svc2++;
                    if(board[x][i][x] == letter) // sheet row check
                        shc++;
                    if(board[x][i][y] == letter) // sheet row check from last sheet (new)
                        shc2++;
                    if(board[x][x][x] == letter) // top to bottom diagonal sheet check (left to right)
                        tbdsc++;
                    if(board[x][y][x] == letter) // bottom to top diagonal sheet check (left to right) (new)
                        btdsc++;
                    if(board[x][x][y] == letter) // top to bottom diagonal sheet check (right to left)
                        tbdsc2++;
                    if(board[x][y][y] == letter) // bottom to top diagonal sheet check (right to left) (new)
                        btdsc2++;
                }
                points += Math.pow(hc, 5) + Math.pow(vc, 5) +  Math.pow(sc, 5);
            }
            points += Math.pow(tbdc, 5) + Math.pow(btdc, 5) + Math.pow(svc, 5) + Math.pow(svc2, 5) + Math.pow(shc, 5) + Math.pow(shc2, 5);
        }
        points += Math.pow(tbdsc, 5) + Math.pow(btdsc, 5) + Math.pow(tbdsc2, 5) + Math.pow(btdsc2, 5);

        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                hc=0; vc=0; tbdc=0; btdc=0; sc=0; svc=0; svc2=0; shc=0; shc2=0; tbdsc=0; tbdsc2=0; btdsc=0; btdsc2=0;

                for(int x = 0, y = 3; x < 4 && y >= 0; x++, y--)
                {
                    if(board[i][j][x] == enemy) // row check
                        hc++;
                    if(board[i][x][j] == enemy) // col check
                        vc++;
                    if(board[i][x][x] == enemy) // top to bottom diagonal check
                        tbdc++;
                    if(board[i][x][y] == enemy) // bottom to top diagonal check
                        btdc++;
                    if(board[x][i][j] == enemy) // sheet check
                        sc++;
                    if(board[x][x][i] == enemy) // sheet col check
                        svc++;
                    if(board[x][y][i] == enemy) // sheet col check from last sheet (new)
                        svc2++;
                    if(board[x][i][x] == enemy) // sheet row check
                        shc++;
                    if(board[x][i][y] == enemy) // sheet row check from last sheet (new)
                        shc2++;
                    if(board[x][x][x] == enemy) // top to bottom diagonal sheet check (left to right)
                        tbdsc++;
                    if(board[x][y][x] == enemy) // bottom to top diagonal sheet check (left to right) (new)
                        btdsc++;
                    if(board[x][x][y] == enemy) // top to bottom diagonal sheet check (right to left)
                        tbdsc2++;
                    if(board[x][y][y] == enemy) // bottom to top diagonal sheet check (right to left) (new)
                        btdsc2++;
                }
                points -= Math.pow(hc, 5) + Math.pow(vc, 5) +  Math.pow(sc, 5);
            }
            points -= Math.pow(tbdc, 5) + Math.pow(btdc, 5) + Math.pow(svc, 5) + Math.pow(svc2, 5) + Math.pow(shc, 5) + Math.pow(shc2, 5);
        }
        points -= Math.pow(tbdsc, 5) + Math.pow(btdsc, 5) + Math.pow(tbdsc2, 5) + Math.pow(btdsc2, 5);

        // This is commented out because when added it really adds a bunch of other possibilities so it might overcomplciate it... for now.
        // Eventually we'll have to base our grading off of enemy positions one way or another anyway so this is for then.


        // run again with diff move by iterating through a for loop

        board[move.getSheet()][move.getRow()][move.getCol()] = '-';

        cube[move.getSheet()][move.getRow()][move.getCol()] = points;
        /*moves.add(move);
        scores.add(points);*/
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void reset() {
        moves.clear();
    }
}
