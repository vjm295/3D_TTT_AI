public class R2Sub extends Player implements PlayerInt
{
    private char letter;
    private String name;
    int[][][] cube = new int[4][4][4];
    private char enemy = 'o';
    boolean first = true;

    public R2Sub(char letter, String name)
    {
        super(letter, name);
        this.letter = letter;
        this.name = name;
        if(letter == 'o')
            enemy = 'x';
    }

    @Override
    public char getLetter() {
        return letter;
    }
 
    @Override
    public Location getMove(char[][][] board) {
        Location move;

        //int index=0;
        int points=7, a, b, c;
        do
        {
            a = (int)(Math.random()*4);
            b = (int)(Math.random()*4);
            c = (int)(Math.random()*4);
        } while(board[a][b][c] == 'x' || board[a][b][c] == 'o');
        if(board[1][1][1] != 'x' && first)
        {
            first = false;
            return move = new Location(1, 1, 1);
        }
        else if(first)
        {
            first = false;
            return move = new Location(2, 2, 2);
        }
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
        //System.out.println(move);
        return move;
    }



    public void grader(char[][][] board, Location move)
    {
        int points=0;
        board[move.getSheet()][move.getRow()][move.getCol()] = letter;

        int hc, vc, tbdc=0, btdc=0, sc, svc=0, svc2=0, shc=0, shc2=0, tbdsc=0, tbdsc2=0, btdsc=0, btdsc2=0, ehc=0, evc=0, etbdc=0, ebtdc=0, esc=0, esvc=0, esvc2=0, eshc=0, eshc2=0, etbdsc=0, etbdsc2=0, ebtdsc=0, ebtdsc2=0;
        for(int i=0; i < 4; i++)
        {
            for(int j=0; j < 4; j++)
            {
                hc=0; vc=0; tbdc=0; btdc=0; sc=0; svc=0; svc2=0; shc=0; shc2=0; tbdsc=0; tbdsc2=0; btdsc=0; btdsc2=0; ehc=0; evc=0; etbdc=0; ebtdc=0; esc=0; esvc=0; esvc2=0; eshc=0; eshc2=0; etbdsc=0; etbdsc2=0; ebtdsc=0; ebtdsc2=0;

                for(int x = 0, y = 3; x < 4 && y >= 0; x++, y--) {
                    if (board[i][j][x] == letter) // row check
                        hc++;
                    if (board[i][x][j] == letter) // col check
                        vc++;
                    if (board[i][x][x] == letter) // top to bottom diagonal check
                        tbdc++;
                    if (board[i][x][y] == letter) // bottom to top diagonal check
                        btdc++;
                    if (board[x][i][j] == letter) // sheet check
                        sc++;
                    if (board[x][x][i] == letter) // sheet col check
                        svc++;
                    if (board[x][y][i] == letter) // sheet col check from last sheet (new)
                        svc2++;
                    if (board[x][i][x] == letter) // sheet row check
                        shc++;
                    if (board[x][i][y] == letter) // sheet row check from last sheet (new)
                        shc2++;
                    if (board[x][x][x] == letter) // top to bottom diagonal sheet check (left to right)
                        tbdsc++;
                    if (board[x][y][x] == letter) // bottom to top diagonal sheet check (left to right) (new)
                        btdsc++;
                    if (board[x][x][y] == letter) // top to bottom diagonal sheet check (right to left)
                        tbdsc2++;
                    if (board[x][y][y] == letter) // bottom to top diagonal sheet check (right to left) (new)
                        btdsc2++;
                    if(board[i][j][x] == enemy) // row check
                        ehc++;
                    if(board[i][x][j] == enemy) // col check
                        evc++;
                    if(board[i][x][x] == enemy) // top to bottom diagonal check
                        etbdc++;
                    if(board[i][x][y] == enemy) // bottom to top diagonal check
                        ebtdc++;
                    if(board[x][i][j] == enemy) // sheet check
                        esc++;
                    if(board[x][x][i] == enemy) // sheet col check
                        esvc++;
                    if(board[x][y][i] == enemy) // sheet col check from last sheet (new)
                        esvc2++;
                    if(board[x][i][x] == enemy) // sheet row check
                        eshc++;
                    if(board[x][i][y] == enemy) // sheet row check from last sheet (new)
                        eshc2++;
                    if(board[x][x][x] == enemy) // top to bottom diagonal sheet check (left to right)
                        etbdsc++;
                    if(board[x][y][x] == enemy) // bottom to top diagonal sheet check (left to right) (new)
                        ebtdsc++;
                    if(board[x][x][y] == enemy) // top to bottom diagonal sheet check (right to left)
                        etbdsc2++;
                    if(board[x][y][y] == enemy) // bottom to top diagonal sheet check (right to left) (new)
                        ebtdsc2++;
                }
                points += Math.pow(hc, 5) + Math.pow(vc, 5) + Math.pow(sc, 5);

                // blocking enemy
                if(ehc == 3 && hc == 1)
                    points += Math.pow(3.5, 5);
                if(evc == 3 && vc == 1)
                    points += Math.pow(3.5, 5);
                if(esc == 3 && sc == 1)
                    points += Math.pow(3.5, 5);

                // blocked by enemy
                if(ehc == 1 && hc == 3)
                    points -= Math.pow(3, 5);
                if(evc == 1 && vc == 3)
                    points -= Math.pow(3, 5);
                if(esc == 1 && sc == 3)
                    points -= Math.pow(3, 5);

                // stops double 3's
                if(ehc == 2 && evc == 2 && ((hc==1 && vc==0) || (vc==1 && hc==0)))
                    points += Math.pow(3.4, 5);
            }
            points += Math.pow(tbdc, 5) + Math.pow(btdc, 5) + Math.pow(svc, 5) + Math.pow(svc2, 5) + Math.pow(shc, 5) + Math.pow(shc2, 5);

            if(etbdc == 3 && tbdc == 1)
                points += Math.pow(3.5, 5);
            if(ebtdc == 3 && btdc == 1)
                points += Math.pow(3.5, 5);
            if(esvc == 3 && svc == 1)
                points += Math.pow(3.5, 5);
            if(esvc2 == 3 && svc2 == 1)
                points += Math.pow(3.5, 5);
            if(eshc == 3 && shc == 1)
                points += Math.pow(3.5, 5);
            if(eshc2 == 3 && shc2 == 1)
                points += Math.pow(3.5, 5);

            if(etbdc == 1 && tbdc == 3)
                points -= Math.pow(3, 5);
            if(ebtdc == 1 && btdc == 3)
                points -= Math.pow(3, 5);
            if(esvc == 1 && svc == 3)
                points -= Math.pow(3, 5);
            if(esvc2 == 1 && svc2 == 3)
                points -= Math.pow(3, 5);
            if(eshc == 1 && shc == 3)
                points -= Math.pow(3, 5);
            if(eshc2 == 1 && shc2 == 3)
                points -= Math.pow(3, 5);

            //if(((etbdc == 1 && ebtdc == 2) || (etbdc == 2 && ebtdc == 1)) && ((tbdc==1 && btdc==0) || (btdc==1 && tbdc==0)))
            //    points += Math.pow(3.4, 5);
        }
        points += Math.pow(tbdsc, 5) + Math.pow(btdsc, 5) + Math.pow(tbdsc2, 5) + Math.pow(btdsc2, 5);

        if(etbdsc == 3 && tbdsc == 1)
            points += Math.pow(3.5, 5);
        if(ebtdsc == 3 && btdsc == 1)
            points += Math.pow(3.5, 5);
        if(etbdsc2 == 3 && tbdsc2 == 1)
            points += Math.pow(3.5, 5);
        if(ebtdsc2 == 3 && btdsc2 == 1)
            points += Math.pow(3.5, 5);

        if(etbdsc == 1 && tbdsc == 3)
            points -= Math.pow(3, 5);
        if(ebtdsc == 1 && btdsc == 3)
            points -= Math.pow(3, 5);
        if(etbdsc2 == 1 && tbdsc2 == 3)
            points -= Math.pow(3, 5);
        if(ebtdsc2 == 1 && btdsc2 == 3)
            points -= Math.pow(3, 5);

        board[move.getSheet()][move.getRow()][move.getCol()] = '-';

        cube[move.getSheet()][move.getRow()][move.getCol()] = points;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void reset() {

    }


    public void refresh() {
        first = true;
    }
}
