import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        TFrame f = new TFrame("3D Tic Tac Toe");
        Thread t = new Thread(f.getP());
        t.start();
    }
}
