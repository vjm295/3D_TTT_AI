import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Scanner;

public class TPanel extends JPanel implements MouseListener, Runnable {

    private BufferedImage buffer;
    private int updateCount;
    private int waitTime=0;
    private char[][][] board = new char[4][4][4];
    private Player pX;
    private Player pO;
    public static final int X_TURN = 0;
    public static final int O_TURN = 1;
    public static final int O_WON = 2;
    public static final int X_WON = 3;
    public static final int TIE = 4;
    private int status = 0;

    public TPanel() {
        super();
        System.out.println("---3D Tic Tac Toe Menu---");
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Will player X be an AI or a human? "); // no AI right now
        String pXType = keyboard.nextLine();
        if(pXType.equals("human") || pXType.equals("Human"))
        {
            System.out.print("Enter your name for player X: ");
            String pXName = keyboard.nextLine();
            pX = new Player('x', pXName);
        }
        else if(pXType.equals("AI"))
        {
            System.out.println("Select an AI: ");
            System.out.print("1. Random\n2. Straight Line\n3. Blocking\n4. Straight Line Blocking\n Enter: ");
            int xAIType = keyboard.nextInt();
        }
        // put it into AI class and stuff
        System.out.print("Will player O be AI or Human? ");
        String pOType = keyboard.nextLine();
        if(pOType.equals("human") || pOType.equals("Human"))
        {
            System.out.print("Enter your name for player O: ");
            String pOName = keyboard.nextLine();
            pO = new Player('o', pOName);
        }
        else if(pOType.equals("AI"))
        {
            System.out.println("Select an AI: ");
            System.out.print("1. Random\n2. Straight Line\n3. Blocking\n4. Straight Line Blocking\n Enter: ");
            int oAIType = keyboard.nextInt();
        }
        // put it into AI class and stuff
        if(pOType.equals("AI") && pXType.equals("AI"))
        {
            System.out.print("How many milliseconds do you want the AI to wait between moves? ");
            int waitTime = keyboard.nextInt();
        }
        setSize(1000, 950);
        this.buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        addMouseListener(this);
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
            gc.drawString(pX.getName() + " has won the game!", 40, 40);
        else if(status == O_WON)
            gc.drawString(pO.getName() + " has won the game!", 40, 40);
        else if(status == TIE)
            gc.drawString("The game ended in a tie", 40, 40);
        int xPosV = 450, yPosV = 50;
        int xPos = 450, yPos = 50; //recalculate values
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
            f = new Font(Font.SERIF,Font.BOLD,60);
            gc.setFont(f);
            gc.setColor(new Color(0,204,255));
            gc.drawString( pX.getName()+"'s Turn (X)",50,90);
        }
        else if(status == O_TURN)
        {
            f = new Font(Font.SERIF,Font.BOLD,60);
            gc.setFont(f);
            gc.setColor(Color.RED);
            gc.drawString( pO.getName()+"'s Turn (O)",50,90);
        }
        g.drawImage(buffer, 0, 0, null);
    }

    public int won(char[][][] board, Player pX, Player pO)
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

    public void update()
    {

    }

    public void changeStatus()
    {
        if(status == X_TURN)
            status = O_TURN;
        else if(status == O_TURN)
            status = X_TURN;
        if(won(board, pX, pO) == 1)
            status = X_WON;
        else if(won(board, pX, pO) == 2)
            status = O_WON;
        else if(won(board, pX, pO) == -1)
            status = TIE;
    }

    @Override
    public void run() {
        int waitToUpdate = (1000/35);
        long startTime = System.nanoTime();
        while(true)
        {
            boolean shouldRepaint = false;
            long currentTime = System.nanoTime();

            long updatesNeeded = (((currentTime-startTime)/1000000))/waitToUpdate;
            for(long x = updateCount; x<updatesNeeded; x++)
            {
                update();
                shouldRepaint = true;
                updateCount++;
            }
            if(shouldRepaint)
                repaint();
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


             update();
        }
        /* Call update and paint the correct # of times per second */

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX(), y = e.getY();
        int s = (y-75)/205;
        int r = (y-49)/38;
        if(s > 0)
            r = (y-49-230*s)/38;
        int c = (x-450)/37;
        System.out.println();
        Color c1 = new Color(buffer.getRGB(x,y));
        if(c1.equals(Color.ORANGE))
        {
            if(status == 0 || status == 1) {
                if (status == X_TURN && board[s][r][c] != 'o' && board[s][r][c] != 'x')
                {
                    board[s][r][c] = 'x';
                    changeStatus();
                }
                else if (status == O_TURN && board[s][r][c] != 'x' && board[s][r][c] != 'o')
                {
                    board[s][r][c] = 'o';
                    changeStatus();
                }
            }
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
