import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Scanner;

public class TPanel extends JPanel implements MouseListener, Runnable {

    private BufferedImage buffer;
    private int updateCount;
    private int waitTime = 0; // this is only for now because no AI
    private Player pX;
    private Player pO;


    public TPanel() {
        super(); //creates the panel
        System.out.println("---3D Tic Tac Toe Menu---"); //moved this section from Main into Panel bcs it actually works this way
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter your name for player X: ");
        String pXName = keyboard.nextLine();
        pX = new Player('X', pXName);
        System.out.println("The recorded name for X is " + pX.getName()); // just confirms that name for X actually registered
        System.out.print("Enter your name for player O: ");
        String pOName = keyboard.nextLine();
        pO = new Player('O', pOName);
        System.out.println("The recorded name for O is " + pO.getName()); // just confirms that name for O actually registered
        /*System.out.print("Will player X be AI or Human? "); // This is your section in main, moved it here too bt commented it out because no AI right now
        String pXType = keyboard.nextLine();
        System.out.print("Will player O be AI or Human? ");
        String pOType = keyboard.nextLine();
        if(pOType.equals("AI") && pXType.equals("AI"))
        {
            System.out.print("How many milliseconds do you want the AI to wait between moves? ");
            int waitTime = keyboard.nextInt();
                    
        } */
        setSize(1000, 760); //sets panel size
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
    


    public void changeStatus()
    {
        if(status == X_TURN)
            status = O_TURN;
        else
            status = X_TURN;
    }



    public boolean won()
    {


        return false;
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
               // update();
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


            // update();
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
        System.out.println("sheet: "+s +"row: "+r +"col: "+c);
        Color c1 = new Color(buffer.getRGB(x,y));
        if(c1.equals(Color.ORANGE))
        {
            if(status == 0 || status == 1) {
                if (status == X_TURN && board[s][r][c] != 'O' && board[s][r][c] != 'X')
                {
                    board[s][r][c] = 'X';
                    changeStatus();
                }
                else if (status == O_TURN && board[s][r][c] != 'X' && board[s][r][c] != 'O')
                {
                    board[s][r][c] = 'O';
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
