import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;



public class TPanel extends JPanel implements MouseListener, Runnable {

    private BufferedImage buffer;
    private Thread t;
    private int updateCount;
    private int waitTime;


    public TPanel(int waitTime) {
        super(); //creates the panel
        setSize(1000, 760); //sets panel size
        this.waitTime = waitTime;

        this.buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        addMouseListener(this);
        Thread t = new Thread(this);
        t.start();
    }

    public void paint(Graphics g)
    {
        Graphics gc = buffer.getGraphics();
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
                t.sleep(waitTime);
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