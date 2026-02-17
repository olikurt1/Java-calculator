import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;
public class Calculator {
    int boardwidth = 360;
    int boardheight = 540;
    //colours of the buttons initialised for the calculator 
    Color customLightGray = new Color(212, 212, 210);
    Color customDarkGray = new Color(80, 80, 80);
    Color customBlack = new Color(28, 28, 28);
    Color customOrange = new Color(255, 149, 0);

    // create a window, JFrame comes from javax.swing.*
    JFrame frame = new JFrame("Calculator");
    JLabel displayLabel = new JLabel();
    JPanel displayPanel = new JPanel();

    //class calling itself possibly recursion to keep it running indefinitely? 
    Calculator(){
        //makes window possible to view 
        frame.setVisible(true);
        //sets frame size to previously given variables
        frame.setSize(boardwidth, boardheight);
        frame.setLocationRelativeTo(null); //centers window
        frame.setResizable(false ); //prevents window to change size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//shuts window when close button pressed.
        frame.setLayout(new BorderLayout());
    }

}
