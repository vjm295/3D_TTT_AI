public class StraightLineAI extends Player implements PlayerInt {

    private char letter;
    private String name;
    private int s = 0, r = 0, c = 0;
    public static final int HORIZONTAL = 0, VERT = 1, DIAG = 2,HSHEET = 3, VERTSHEET = 4, DSHEET = 5;
    private  int direction = (int)(Math.random()*5);

    public StraightLineAI(char letter, String name) {
        super(letter,name);
    }

    public void changeValues(char[][][] board)
    {
        s = (int)(Math.random()*4);
        r = (int)(Math.random()*4);
        c = (int)(Math.random()*4);
        direction = (int)(Math.random()*5);
        if(board[s][r][c] == 'x' || board[s][r][c] == 'o')
        {
            while(board[s][r][c] == 'x' || board[s][r][c] == 'o')
            {
                s = (int)(Math.random()*4);
                r = (int)(Math.random()*4);
                c = (int)(Math.random()*4);
            }
        }
    }

    @Override
    public char getLetter() {
        return letter;
    }



    @Override
    public Location getMove(char[][][] board) {

        if(direction == HORIZONTAL)
        {
            if(c + 1 < 4)
                c++;
            else if(c-1 >= 0)
                c--;
            while(board[s][r][c] == 'x' || board[s][r][c] == 'o') {
                if (board[s][r][c] == 'x' || board[s][r][c] == 'o')
                    changeValues(board);
                if(c + 1 < 4)
                    c++;
                else if(c-1 >= 0)
                    c--;
            }

        }
        else if(direction == VERT)
        {
            if(r + 1 < 4)
                r++;
            else if(r-1 >= 0)
                r--;
            while(board[s][r][c] == 'x' || board[s][r][c] == 'o') {
                if (board[s][r][c] == 'x' || board[s][r][c] == 'o')
                    changeValues(board);
                if(r + 1 < 4)
                    r++;
                else if(r-1 >= 0)
                    r--;
            }
        }
        else if(direction == DIAG)
        {
            if(r + 1 < 4 && c+1 < 4)
            {
                r++;
                c++;
            }
            else if(c-1 >= 0 && r-1 >= 0)
            {
                r--;
                c--;
            }
            while(board[s][r][c] == 'x' || board[s][r][c] == 'o') {
                if (board[s][r][c] == 'x' || board[s][r][c] == 'o')
                    changeValues(board);
                if(r + 1 < 4 && c+1 < 4)
                {
                    r++;
                    c++;
                }
                else if(c-1 >= 0 && r-1 >= 0)
                {
                    r--;
                    c--;
                }
            }
        }
        else if(direction == HSHEET)
        {
            if(c + 1 < 4 && s+1 < 4)
            {
                c++;
                s++;
            }
            else if(c-1 >= 0 && s-1 >= 0)
            {
                c--;
                s--;
            }
            while(board[s][r][c] == 'x' || board[s][r][c] == 'o') {
                if (board[s][r][c] == 'x' || board[s][r][c] == 'o')
                    changeValues(board);
                if(c + 1 < 4 && s+1 < 4)
                {
                    c++;
                    s++;
                }
                else if(c-1 >= 0 && s-1 >= 0)
                {
                    c--;
                    s--;
                }
            }
        }
        else if(direction == VERTSHEET)
        {
            if(r + 1 < 4 && s + 1 < 4)
            {
                r++;
                s++;
            }
            else if(r-1 >= 0 && s - 1 >= 0)
            {
                r--;
                s--;
            }
            while(board[s][r][c] == 'x' || board[s][r][c] == 'o') {
                if (board[s][r][c] == 'x' || board[s][r][c] == 'o')
                    changeValues(board);
                if(r + 1 < 4 && s + 1 < 4)
                {
                    r++;
                    s++;
                }
                else if(r-1 >= 0 && s-1 >= 0)
                {
                    r--;
                    s--;
                }
            }
        }
        else if(direction == DSHEET)
        {
            if(r + 1 < 4 && c+1 < 4 && s+1 < 4)
            {
                r++;
                c++;
                s++;
            }
            else if(c-1 >= 0 && r-1 >= 0 && s >= 0)
            {
                r--;
                c--;
                s--;
            }
            while(board[s][r][c] == 'x' || board[s][r][c] == 'o') {
                if (board[s][r][c] == 'x' || board[s][r][c] == 'o')
                    changeValues(board);
                if(r + 1 < 4 && c+1 < 4 && s+1 < 4)
                {
                    r++;
                    c++;
                    s++;
                }
                else if(c-1 >= 0 && r-1 >= 0 && s-1 >= 0)
                {
                    r--;
                    c--;
                    s--;
                }
            }
        }


        return new Location(c,r,s);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void reset() {

    }
}
