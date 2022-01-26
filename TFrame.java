import javax.swing.*;
import java.awt.*;

public class TFrame extends JFrame {
    private TPanel p;
    public TFrame(String title) {
        super(title); //creates frame with title

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //sets x button to close program

        setResizable(false); //sets user unable to resize item

        pack(); //creates frame (hidden at this point)

        p = new TPanel(); //creates panel

        Insets insets = getInsets(); //gets insets

        //calculating window size
        int width = p.getWidth() + insets.left + insets.right;
        int height = p.getHeight() + insets.top + insets.bottom;

        setPreferredSize(new Dimension(width, height)); //sets preferred size

        setLayout(null); //turn off layout options

        add(p); //adds panel to frame

        pack(); //adjusts to be the size we set with set preferred size

        setVisible(true);
    }
    public TPanel getP()
    {
        return p;
    }
}
