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

      //  gc.drawLine(50,100,150,100);
       // gc.drawLine(10,190,100,190);
       // gc.drawLine(50,100,10,190);
       // gc.drawLine(150,100,100,190);
        int xPos = 450, yPos = 100;
        int xPosV = 475, yPosV = 100;
        for(int a = 0; a < 4; a++)
        {
            gc.setColor(Color.ORANGE);
            gc.fillRect(450,100+a*170,100,100);
            for(int b = 0; b  < 4; b++)
            {
                gc.setColor(Color.BLACK);
                gc.drawLine(xPos,yPos,xPos+100,yPos+25*b);
                gc.drawLine(xPosV+25*b,yPosV,xPosV+25*b,yPosV+100);
            }
            yPos+= 170;
            yPosV += 170;

        }

















        g.drawImage(buffer, 0, 0, null);
    }

    public void update()
    {

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
        double x = e.getX(), y = e.getY();

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
