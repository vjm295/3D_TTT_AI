import java.util.ArrayList;

public class Ragnarok extends Player implements PlayerInt
{
    private char letter;
    private String name;

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
        char[][][] cube = new char[4][4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    cube[i][j][k] = board[i][j][k];
                    if(cube[i][j][k] != 'x' && cube[i][j][k] != 'o')
                        cube[i][j][k] = '-';
                }
            }
        }
        int index=0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    if(board[i][j][k] != 'x' && board[i][j][k] != 'o')
                    {
                        grader(cube, new Location(k, j, i));
                    }
                }
            }
        }

        move = moves.get(moves.size()-1).getData();

        System.out.println(move);
        return move;
    }

    public int grader(char[][][] cube)
    {
        int points=0;
        char enemy = 'o';
        if(letter == 'o')
            enemy = 'x';
        int hc=0, vc=0, tbdc=0, btdc=0, sc=0, svc=0, svc2=0, shc=0, shc2=0, tbdsc=0, tbdsc2=0, btdsc=0, btdsc2=0;
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                hc=0; vc=0; tbdc=0; btdc=0; sc=0; svc=0; svc2=0; shc=0; shc2=0; tbdsc=0; tbdsc2=0; btdsc=0; btdsc2=0;

                for(int x = 0, y = 3; x < 4 && y >= 0; x++, y--)
                {
                    if(cube[i][j][x] == letter) // row check
                        hc++;
                    if(cube[i][x][j] == letter) // col check
                        vc++;
                    if(cube[i][x][x] == letter) // top to bottom diagonal check
                        tbdc++;
                    if(cube[i][x][y] == letter) // bottom to top diagonal check
                        btdc++;
                    if(cube[x][i][j] == letter) // sheet check
                        sc++;
                    if(cube[x][x][i] == letter) // sheet col check
                        svc++;
                    if(cube[x][y][i] == letter) // sheet col check from last sheet (new)
                        svc2++;
                    if(cube[x][i][x] == letter) // sheet row check
                        shc++;
                    if(cube[x][i][y] == letter) // sheet row check from last sheet (new)
                        shc2++;
                    if(cube[x][x][x] == letter) // top to bottom diagonal sheet check (left to right)
                        tbdsc++;
                    if(cube[x][y][x] == letter) // bottom to top diagonal sheet check (left to right) (new)
                        btdsc++;
                    if(cube[x][x][y] == letter) // top to bottom diagonal sheet check (right to left)
                        tbdsc2++;
                    if(cube[x][y][y] == letter) // bottom to top diagonal sheet check (right to left) (new)
                        btdsc2++;
                }
                points += Math.pow(hc, 5) + Math.pow(vc, 5) +  Math.pow(sc, 5);
            }
            points += Math.pow(tbdc, 5) + Math.pow(btdc, 5) + Math.pow(svc, 5) + Math.pow(svc2, 5) + Math.pow(shc, 5) + Math.pow(shc2, 5);
        }
        points += Math.pow(tbdsc, 5) + Math.pow(btdsc, 5) + Math.pow(tbdsc2, 5) + Math.pow(btdsc2, 5);

      /*for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                hc=0; vc=0; tbdc=0; btdc=0; sc=0; svc=0; svc2=0; shc=0; shc2=0; tbdsc=0; tbdsc2=0; btdsc=0; btdsc2=0;

                for(int x = 0, y = 3; x < 4 && y >= 0; x++, y--)
                {
                    if(cube[i][j][x] == enemy) // row check
                        hc++;
                    if(cube[i][x][j] == enemy) // col check
                        vc++;
                    if(cube[i][x][x] == enemy) // top to bottom diagonal check
                        tbdc++;
                    if(cube[i][x][y] == enemy) // bottom to top diagonal check
                        btdc++;
                    if(cube[x][i][j] == enemy) // sheet check
                        sc++;
                    if(cube[x][x][i] == enemy) // sheet col check
                        svc++;
                    if(cube[x][y][i] == enemy) // sheet col check from last sheet (new)
                        svc2++;
                    if(cube[x][i][x] == enemy) // sheet row check
                        shc++;
                    if(cube[x][i][y] == enemy) // sheet row check from last sheet (new)
                        shc2++;
                    if(cube[x][x][x] == enemy) // top to bottom diagonal sheet check (left to right)
                        tbdsc++;
                    if(cube[x][y][x] == enemy) // bottom to top diagonal sheet check (left to right) (new)
                        btdsc++;
                    if(cube[x][x][y] == enemy) // top to bottom diagonal sheet check (right to left)
                        tbdsc2++;
                    if(cube[x][y][y] == enemy) // bottom to top diagonal sheet check (right to left) (new)
                        btdsc2++;
                }
                points -= Math.pow(hc, 5) + Math.pow(vc, 5) +  Math.pow(sc, 5);
            }
            points -= Math.pow(tbdc, 5) + Math.pow(btdc, 5) + Math.pow(svc, 5) + Math.pow(svc2, 5) + Math.pow(shc, 5) + Math.pow(shc2, 5);
        }
        points -= Math.pow(tbdsc, 5) + Math.pow(btdsc, 5) + Math.pow(tbdsc2, 5) + Math.pow(btdsc2, 5); */

        // This is commented out because when added it really adds a bunch of other possibilities so it might overcomplciate it... for now.
        // Eventually we'll have to base our grading off of enemy positions one way or another anyway so this is for then.

        return points;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void reset() {

    }
}
