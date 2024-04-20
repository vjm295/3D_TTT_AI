public class BlockingAI extends Player implements PlayerInt {
    Location anchor = null;
    private int s = 0, r = 0, c = 0;
    public static final int VERT = 0, HORIZONTAL = 1,  DIAG = 2, VERTSHEET = 4, HSHEET = 3, DSHEET = 5;
    private  int direction = 0;
    private String temp;
    public BlockingAI(char letter, String name) {
        super(letter, name);
 
            this.anchor = new Location((int)(Math.random()*4),(int)(Math.random()*4),(int)(Math.random()*4));
    }

    public boolean testAnchor(Location l, char[][][]board)
    {
        int a = 0;
        int r = l.row;
        int c = l.col;
        int s = l.sheet;

        if (r + 1 < 4 && board[s][r+1][c] != 'x' && board[s][r+1][c] != 'o') //vert
            a++;
         if (r-1 >= 0 && board[s][r-1][c] != 'x' && board[s][r-1][c] != 'o')
             a++;

        if (c + 1 < 4 && board[s][r][c+1] != 'x' && board[s][r][c+1] != 'o') //horizontal
            a++;
         if (c-1 >= 0 && board[s][r][c-1] != 'x' && board[s][r][c-1] != 'o')
             a++;

        if (c + 1 < 4 && r+1 < 4 && board[s][r+1][c+1] != 'x' && board[s][r+1][c+1] != 'o') { //diagonal
            a++;
        }
         if (c-1 >= 0 && r-1 >=0 && board[s][r-1][c-1] != 'x' && board[s][r-1][c-1] != 'o') {
            a++;
        }
         if (c+1 < 4 && r-1 >=0 && board[s][r-1][c+1] != 'x' && board[s][r-1][c+1] != 'o') {
            a++;
        }
         if (c-1 >= 0 && r+1 < 4 && board[s][r+1][c-1] != 'x' && board[s][r+1][c-1] != 'o') {
            a++;
        }
        if (r + 1 < 4 && s + 1 < 4 && board[s+1][r+1][c] != 'x' && board[s+1][r+1][c] != 'o') //vertSheet
        {
            a++;
        }
         if (r-1 >= 0 && s - 1 >= 0 && board[s][r-1][c] != 'x' && board[s][r-1][c] != 'o')
        {
            a++;
        }

        if (c + 1 < 4 && s + 1 < 4 && board[s+1][r][c+1] != 'x' && board[s+1][r][c+1] != 'o') //HSheet
            a++;
         if (c-1 >= 0 && s - 1 >= 0 && board[s-1][r][c-1] != 'x' && board[s-1][r][c-1] != 'o')
             a++;


        if (c + 1 < 4 && r+1 < 4 && s + 1 < 4 && board[s][r+1][c+1] != 'x' && board[s][r+1][c+1] != 'o') { //DSheet
            a++;
        }
         if (c-1 >= 0 && r-1 >=0 && s-1 >= 0 && board[s][r-1][c-1] != 'x' && board[s][r-1][c-1] != 'o') {
             a++;
        }
         if (c+1 < 4 && r-1 >=0 && s-1 >= 0&& board[s][r-1][c+1] != 'x' && board[s][r-1][c+1] != 'o') {
             a++;
        }
         if (c-1 >= 0 && r+1 < 4 && s+1 < 4 && board[s][r+1][c-1] != 'x' && board[s][r+1][c-1] != 'o') {
             a++;
        }

        return a > 0;
    }

    public Location findAnchor(char[][][] board)
    {
        char[][][] tempBoard = board;
        int s1 = 0, r1 = 0, c1 = 0;
        Location l = null;
        for(int x = 0; x < 64; x++)
        {
         s1 = (int)(Math.random()*4);
         r1 = (int)(Math.random()*4);
         c1 = (int)(Math.random()*4);
         while(board[s1][r1][c1] == 'c')
         {
             s1 = (int)(Math.random()*4);
             r1 = (int)(Math.random()*4);
             c1 = (int)(Math.random()*4);
         }
         Location a = new Location(c1,r1,s1);
         if(testAnchor(a,board))
         {
             l = new Location(c1,r1,s1);
             break;
         }
         else
         {
             tempBoard[s1][r1][c1] = 'c';
         }

        }
        if(l == null)
        {
            s1 = (int)(Math.random()*4);
            r1 = (int)(Math.random()*4);
            c1 = (int)(Math.random()*4);

            if(board[s][r][c] == 'x' || board[s][r][c] == 'o')
            {
                while(board[s][r][c] == 'x' || board[s][r][c] == 'o')
                {
                    s1 = (int)(Math.random()*4);
                    r1 = (int)(Math.random()*4);
                    c1 = (int)(Math.random()*4);
                }
            }
        }
        return new Location(c1,r1,s1);

    }

    @Override
    public char getLetter() {
        return super.getLetter();
    }

    @Override
    public Location getMove(char[][][] board) {
        temp = "";
         direction = (int)(Math.random()*5);
         for(int s = 0; s < board.length; s++)
         {
             for(int r = 0; r < board[0].length; r++)
             {
                 for(int c = 0; c < board[0][0].length; c++)
                 {
                     temp += board[s][r][c];
                 }
             }
         }
         if(((getLetter() == 'x' || getLetter() == 'X') && temp.length() == 0) || !testAnchor(anchor,board))
             return anchor;
         else {
            anchor = findAnchor(board);
             s = anchor.sheet;
             r = anchor.row;
             c = anchor.col;
             if (direction == VERT) {
                 if (r + 1 < 4 && board[s][r + 1][c] != 'x' && board[s][r + 1][c] != 'o')
                     r++;
                 else if (r - 1 >= 0 && board[s][r - 1][c] != 'x' && board[s][r - 1][c] != 'o')
                     r--;
                 else
                     direction++;
             }
             if (direction == HORIZONTAL) {
                 if (c + 1 < 4 && board[s][r][c + 1] != 'x' && board[s][r][c + 1] != 'o')
                     c++;
                 else if (c - 1 >= 0 && board[s][r][c - 1] != 'x' && board[s][r][c - 1] != 'o')
                     c--;
                 else
                     direction++;
             }
             if (direction == DIAG) {
                 if (c + 1 < 4 && r + 1 < 4 && board[s][r + 1][c + 1] != 'x' && board[s][r + 1][c + 1] != 'o') {
                     c++;
                     r++;
                 } else if (c - 1 >= 0 && r - 1 >= 0 && board[s][r - 1][c - 1] != 'x' && board[s][r - 1][c - 1] != 'o') {
                     c--;
                     r--;
                 } else if (c + 1 < 4  && r - 1 >= 0 && board[s][r - 1][c + 1] != 'x' && board[s][r - 1][c + 1] != 'o') {
                     c++;
                     r--;
                 } else if (c - 1 >= 0 && r + 1 < 4 && board[s][r + 1][c - 1] != 'x' && board[s][r + 1][c - 1] != 'o') {
                     c--;
                     r++;
                 } else
                     direction++;
             }
             if (direction == VERTSHEET) {
                 if (r + 1 < 4 && s + 1 < 4 && board[s + 1][r + 1][c] != 'x' && board[s + 1][r + 1][c] != 'o') {
                     r++;
                     s++;
                 } else if (r - 1 >= 0 && s - 1 >= 0 && board[s][r - 1][c] != 'x' && board[s][r - 1][c] != 'o') {
                     r--;
                     s--;
                 } else
                     direction++;
             }
             if (direction == HSHEET) {
                 if (c + 1 < 4 && s + 1 < 4 && board[s + 1][r][c + 1] != 'x' && board[s + 1][r][c + 1] != 'o')
                     c++;
                 else if (c - 1 >= 0 && s - 1 >= 0 && board[s - 1][r][c - 1] != 'x' && board[s - 1][r][c - 1] != 'o')
                     c--;
                 else
                     direction++;
             }
             if (direction == DSHEET) {
                 if (c + 1 < 4 && r + 1 < 4 && s + 1 < 4 && board[s][r + 1][c + 1] != 'x' && board[s][r + 1][c + 1] != 'o') {
                     c++;
                     r++;
                     s++;
                 } else if (c - 1 >= 0 && r - 1 >= 0 && s - 1 >= 0 && board[s][r - 1][c - 1] != 'x' && board[s][r - 1][c - 1] != 'o') {
                     c--;
                     r--;
                     s--;
                 } else if (c + 1 < 4 && r - 1 >= 0 && s - 1 >= 0 && board[s][r - 1][c + 1] != 'x' && board[s][r - 1][c + 1] != 'o') {
                     c++;
                     r--;
                     s--;
                 } else if (c - 1 >= 0 && r + 1 < 4 && s + 1 < 4 && board[s][r + 1][c - 1] != 'x' && board[s][r + 1][c - 1] != 'o') {
                     c--;
                     r++;
                     s++;
                 } else
                     direction = 0;
             }
             anchor = new Location(c,r,s);
         }
                 return anchor;
    }

    @Override
    public void reset() {

    }
}
