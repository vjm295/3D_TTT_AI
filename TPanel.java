import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Scanner;

public class TPanel extends JPanel implements MouseListener, Runnable, KeyListener {

    private BufferedImage buffer;
    private int updateCount;
    private char[][][] board = new char[4][4][4];
    private Player pX;
    private Player pO;
    public static final int X_TURN = 0;
    public static final int O_TURN = 1;
    public static final int O_WON = 2;
    public static final int X_WON = 3;
    public static final int TIE = 4;
    private int status = 0;
    private boolean ai = false;
    private boolean oai = false;
    private boolean xai = false;
    private int waitTime;
    private boolean done = false, end = false;
    private int gameCount=0, games=0, xWins=0, oWins=0, ties=0;


    public TPanel() {
        super();
        System.out.println("---3D Tic Tac Toe Menu---");
        Scanner keyboard = new Scanner(System.in);
        
        System.out.print("Will player x be an AI or a Human? (1 for AI, 2 for Human) "); // no AI right now
        int pXType = keyboard.nextInt();
        if(pXType==2)
        {
            System.out.print("Enter your name for player x: ");
            keyboard.nextLine();
            String pXName = keyboard.nextLine();
            pX = new Player('x', pXName);
        }
        else if(pXType==1)
        {
            System.out.println("Select an AI: ");
            System.out.print("1. Random\n2. Straight Line\n3. Blocking\n4. Straight Line Blocking\nEnter: ");
            int xAIType = keyboard.nextInt();
            if(xAIType == 1)
                pX = new RandomAI('x', "RandomX");
            if(xAIType == 2)
                pX = new StraightLineAI('x', "StraightLineX");
        }
        System.out.print("Will player o be AI or Human? (1 for AI, 2 for Human) ");
        int pOType = keyboard.nextInt();
        if(pOType==2)
        {
            System.out.print("Enter your name for player o: ");
            keyboard.nextLine();
            String pOName = keyboard.nextLine();
            pO = new Player('o', pOName);
        }
        else if(pOType==1)
        {
            System.out.println("Select an AI: ");
            System.out.print("1. Random\n2. Straight Line\n3. Blocking\n4. Straight Line Blocking\nEnter: ");
            int oAIType = keyboard.nextInt();
            if(oAIType == 1)
                pO = new RandomAI('o', "RandomO");
            if(oAIType == 2)
                pO = new StraightLineAI('o', "StraightLineO");
        }
        if(pOType==1 && pXType==1)
        {
            ai=true;
            System.out.print("How many games would you like the AI to play? ");
            gameCount = keyboard.nextInt();
            System.out.print("Would you like to view the games or have them happen in the background? (1 for viewing, 2 for background) ");
            int speed = keyboard.nextInt();
            if(speed==2)
                finish();
            if(speed==1) {
                System.out.print("How many milliseconds do you want between each AI move? ");
                waitTime = keyboard.nextInt();
            }
        }
        if(pXType==1 && pOType ==2)
            xai = true;
        if(pXType==2 && pOType ==1)
            oai = true;
        setSize(1000, 950);
        this.buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        addMouseListener(this);
        addKeyListener(this);
    }

    public void reset()
    {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        board = new char[4][4][4];
        status = X_TURN;
    }

    public void paint(Graphics g)
    {
        Graphics gc = buffer.getGraphics();
        gc.setColor(Color.BLACK);
        gc.fillRect(0,0,getWidth(),getHeight());
        gc.setColor(Color.ORANGE);
        Font f = new Font(Font.SERIF,Font.BOLD,30);
        gc.setFont(f);
        gc.setColor(Color.WHITE);
        if(status == X_WON)
            gc.drawString(pX.getName() + " has won the game!", 10, 40);
        else if(status == O_WON)
            gc.drawString(pO.getName() + " has won the game!", 10, 40);
        else if(status == TIE)
            gc.drawString("The game ended in a tie", 10, 40);
        int xPosV = 450, yPosV = 50;
        int xPos = 450, yPos = 50;
        for(int a = 0; a < 4; a++)
        {
            gc.setColor(Color.ORANGE);
            gc.fillRect(450,50+a*230,150,150);
            for(int b = 0; b  < 4; b++)
            {
                gc.setColor(Color.BLACK);
                gc.drawLine(xPos,yPos+37*b,xPos+150,yPos+37*b);
                gc.drawLine(xPosV+37*b,yPosV,xPosV+37*b,yPosV+150);
            }
            yPos+= 230;
            yPosV += 230;
        }
        gc.setColor(Color.BLUE);
        // gc.fillOval(456,55,25,25);
        // gc.drawString("X",459,80);
        // gc.drawString("O",495,310);
        // gc.drawString("X",531,80);//36 x  and y increment

        for(int s = 0; s < board.length; s++)
        {
            for(int r = 0; r < board[0].length; r++)
            {
                for(int c = 0; c < board[0][0].length; c++)
                {
                    if(board[s][r][c] == 'X' || board[s][r][c] == 'x')
                    {
                        gc.setColor(Color.BLUE);
                        gc.drawString("X",459+36*c,80+36*r + 230*s);
                    }
                    else if(board[s][r][c] == 'O' || board[s][r][c] == 'o')
                    {
                        gc.setColor(Color.RED);
                        gc.drawString("O",459+36*c,80+36*r + 230*s);
                    }
                }
            }
        }
        if(status == X_TURN)
        {
            f = new Font(Font.SERIF,Font.BOLD,40);
            gc.setFont(f);
            gc.setColor(new Color(0,204,255));
            gc.drawString( pX.getName()+"'s Turn (x)",50,90);
        }
        else if(status == O_TURN)
        {
            f = new Font(Font.SERIF,Font.BOLD,40);
            gc.setFont(f);
            gc.setColor(Color.RED);
            gc.drawString( pO.getName()+"'s Turn (o)",50,90);
        }
        if(end)
        {
            reset();
            System.exit(0);
        }
        if(done)
        {
            reset();
            done = false;
        }
        if(ai)
            live();
        if(xai && status == X_TURN)
            aiTurn(pX, 'x');
        if(oai && status == O_TURN)
            aiTurn(pO, 'o');
        g.drawImage(buffer, 0, 0, null);

    }

    public void aiTurn(Player p, char letter)
    {
        Location move = p.getMove(board);
        board[move.getSheet()][move.getRow()][move.getCol()] = letter;
        changeStatus();
        repaint();
    }

    public void finish()
    {
        Location xMove;
        Location oMove;
        do {
            do {
                xMove = pX.getMove(board);
                board[xMove.getSheet()][xMove.getRow()][xMove.getCol()] = 'x';
                oMove = pO.getMove(board);
                board[oMove.getSheet()][oMove.getRow()][oMove.getCol()] = 'o';
                changeStatus();

            } while (status == X_TURN || status == O_TURN);
            if(status==X_WON)
                xWins++;
            else if(status==O_WON)
                oWins++;
            else if(status==TIE)
                ties++;
            games++;
            reset();
        }while(games<gameCount);
        System.out.println(games + " games have been played and out of those\nX won " + xWins + " games\nO won " + oWins + " games\n" + ties + " games ended in ties");
        System.exit(0);
    }

    public void live()
    {
        Location xMove;
        Location oMove;
        if(status==X_TURN)
        {
            xMove = pX.getMove(board);
            board[xMove.getSheet()][xMove.getRow()][xMove.getCol()] = 'x';
        }
        if(status==O_TURN)
        {
            oMove = pO.getMove(board);
            board[oMove.getSheet()][oMove.getRow()][oMove.getCol()] = 'o';
        }

        if(status==X_WON)
            xWins++;
        else if(status==O_WON)
            oWins++;
        else if(status==TIE)
            ties++;
        if(status != X_TURN && status != O_TURN) {
            games++;
            done=true;
        }
        changeStatus();
        if(games == gameCount)
        {
            System.out.println(games + " games have been played and out of those\nX won " + xWins + " games\nO won " + oWins + " games\n" + ties + " games ended in ties");
            end = true;
        }
        try {
            Thread.sleep(waitTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        repaint();
    }

    public int won(char[][][] board)
    {
        for(int i=0; i < 4; i++) // for X
        {
            for(int j=0; j < 4; j++)
            {
                for(int k=0; k < 4; k++)
                {
                    int hc=0, vc=0, tbdc=0, btdc=0, sc=0, svc=0, svc2=0, shc=0, shc2=0, tbdsc=0, tbdsc2=0, btdsc=0, btdsc2=0;

                    for(int x = 0, y = 3; x < 4 && y >= 0; x++, y--)
                    {
                        if(board[i][j][x] == 'x') // row check
                            hc++;
                        if(board[i][x][k] == 'x') // col check
                            vc++;
                        if(board[i][x][x] == 'x') // top to bottom diagonal check
                            tbdc++;
                        if(board[i][x][y] == 'x') // bottom to top diagonal check
                            btdc++;
                        if(board[x][j][k] == 'x') // sheet check
                            sc++;
                        if(board[x][x][k] == 'x') // sheet col check
                            svc++;
                        if(board[x][y][k] == 'x') // sheet col check from last sheet (new)
                            svc2++;
                        if(board[x][j][x] == 'x') // sheet row check
                            shc++;
                        if(board[x][j][y] == 'x') // sheet row check from last sheet (new)
                            shc2++;
                        if(board[x][x][x] == 'x') // top to bottom diagonal sheet check (left to right)
                            tbdsc++;
                        if(board[x][y][x] == 'x') // bottom to top diagonal sheet check (left to right) (new)
                            btdsc++;
                        if(board[x][x][y] == 'x') // top to bottom diagonal sheet check (right to left)
                            tbdsc2++;
                        if(board[x][y][y] == 'x') // bottom to top diagonal sheet check (right to left) (new)
                            btdsc2++;
                    }

                    if(hc==4 || vc==4 || tbdc==4 || btdc==4 || sc==4 || svc==4 || shc==4 || svc2==4 || tbdsc==4 || btdsc==4 || shc2==4 || tbdsc2==4 || btdsc2==4)
                        return 1;
                }
            }
        }
        for(int i=0; i < 4; i++) // for O
        {
            for(int j=0; j < 4; j++)
            {
                for(int k=0; k < 4; k++)
                {
                    int hc=0, vc=0, tbdc=0, btdc=0, sc=0, svc=0, svc2=0, shc=0, shc2=0, tbdsc=0, tbdsc2=0, btdsc=0, btdsc2=0;

                    for(int x = 0, y = 3; x < 4 && y >= 0; x++, y--)
                    {
                        if(board[i][j][x] == 'o') // row check
                            hc++;
                        if(board[i][x][k] == 'o') // col check
                            vc++;
                        if(board[i][x][x] == 'o') // top to bottom diagonal check
                            tbdc++;
                        if(board[i][x][y] == 'o') // bottom to top diagonal check
                            btdc++;
                        if(board[x][j][k] == 'o') // sheet check
                            sc++;
                        if(board[x][x][k] == 'o') // sheet col check
                            svc++;
                        if(board[x][y][k] == 'o') // sheet col check from last sheet
                            svc2++;
                        if(board[x][j][x] == 'o') // sheet row check
                            shc++;
                        if(board[x][j][y] == 'o') // sheet row check from last sheet
                            shc2++;
                        if(board[x][x][x] == 'o') // top to bottom diagonal sheet check (left to right)
                            tbdsc++;
                        if(board[x][y][x] == 'o') // bottom to top diagonal sheet check (left to right)
                            btdsc++;
                        if(board[x][x][y] == 'o') // top to bottom diagonal sheet check (right to left)
                            tbdsc2++;
                        if(board[x][y][y] == 'o') // bottom to top diagonal sheet check (right to left)
                            btdsc2++;
                    }

                    if(hc==4 || vc==4 || tbdc==4 || btdc==4 || sc==4 || svc==4 || shc==4 || svc2==4 || tbdsc==4 || btdsc==4 || shc2==4 || tbdsc2==4 || btdsc2==4)
                        return 2;
                }
            }
        }
        int tieCounter=0;
        for(int i=0; i < 4; i++) // for tie
        {
            for(int j=0; j < 4; j++)
            {
                for(int k=0; k < 4; k++)
                {
                    if(board[i][j][k] == 'x' || board[i][j][k] == 'o' )
                        tieCounter++;
                }
            }
        }
        if(tieCounter == 64)
            return -1;
        return 0;
    }

    public void changeStatus()
    {
        if(status == X_TURN)
            status = O_TURN;
        else if(status == O_TURN)
            status = X_TURN;
        if(won(board) == 1)
            status = X_WON;
        else if(won(board) == 2)
            status = O_WON;
        else if(won(board) == -1)
            status = TIE;
    }
    @Override
    public void run() {}
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {
        if(!ai || (xai && status == O_TURN) || (oai && status == X_TURN))
        {
            int x = e.getX(), y = e.getY();
            int s = (y - 75) / 205;
            int r = (y - 49) / 38;
            if (s > 0)
                r = (y - 49 - 230 * s) / 38;
            int c = (x - 450) / 37;
            Color c1 = new Color(buffer.getRGB(x, y));
            if (c1.equals(Color.ORANGE)) {
                if (status == 0 || status == 1) {
                    if (status == X_TURN && board[s][r][c] != 'o' && board[s][r][c] != 'x') {
                        board[s][r][c] = 'x';
                        changeStatus();
                    } else if (status == O_TURN && board[s][r][c] != 'x' && board[s][r][c] != 'o') {
                        board[s][r][c] = 'o';
                        changeStatus();
                    }
                }
            }
            repaint();
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char c = e.getKeyChar();
        if(c == 'r')
            reset();
        repaint();
    }

    public void addNotify()
    {
        super.addNotify();
        requestFocus();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
