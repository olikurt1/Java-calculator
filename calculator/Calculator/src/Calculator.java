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

    //Setting the labels for all buttons
     String[] buttonValues = {
        "AC", "+/-", "%", "÷", 
        "7", "8", "9", "×", 
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "0", ".", "√", "="
    };
    //setting which symbols will get different colours
    String[] rightSymbols = {"÷", "×", "-", "+", "="};
    String[] topSymbols = {"AC", "+/-", "%"}; 
    
    // create a window, JFrame comes from javax.swing.*
    JFrame frame = new JFrame("Calculator");
    JLabel displayLabel = new JLabel(); //text will go inside label
    JPanel displayPanel = new JPanel(); //label will go inside panel
    JPanel buttonsPanel = new JPanel(); //panels for the buttons
    
    //will keep track of values in the field eg: A+B
    String A = "0";
    String operator = null;
    String B = null;


    //class calling itself possibly recursion to keep it running indefinitely? 
    Calculator(){
        //makes window possible to view 
        
        //sets frame size to previously given variables
        frame.setSize(boardwidth, boardheight);
        frame.setLocationRelativeTo(null); //centers window
        frame.setResizable(false ); //prevents window to change size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//shuts window when close button pressed.
        frame.setLayout(new BorderLayout());

        displayLabel.setBackground(customBlack);
        displayLabel.setForeground(Color.white);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 80));
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayLabel.setText("0");
        displayLabel.setOpaque(true);

        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(displayLabel);//add labels into panels
        frame.add(displayPanel, BorderLayout.NORTH);//add panels into the frame but sets it to be at the top
        
        //separate panel to the number panel but same colour so before buttons still looks like one panel
        buttonsPanel.setLayout(new GridLayout(5, 4));
        buttonsPanel.setBackground(customBlack);
        frame.add(buttonsPanel);

        //for loop which creates a button corresponding to each symbol in the full array
        for(int x = 0; x < buttonValues.length;x++){
            JButton button = new JButton();
            String buttonValue = buttonValues[x];
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            button.setText(buttonValue);
            button.setFocusable(false);//removes border around symbol when pressing button
            button.setBorder(new LineBorder(customBlack));
            //determining what colour the current button should be based on whether its included in one of the other arrays
            if(Arrays.asList(topSymbols).contains(buttonValue)){
                button.setForeground(customBlack);
                button.setBackground(customLightGray);
            }
            else if(Arrays.asList(rightSymbols).contains(buttonValue)){
                button.setForeground(Color.white);
                button.setBackground(customOrange);
            }
            else{
                button.setForeground(Color.white);
                button.setBackground(customDarkGray);
            }
            buttonsPanel.add(button);
            //event handling to respond whenever a button is pressed
            button.addActionListener(new ActionListener(){
                //e is the action 
                public void actionPerformed(ActionEvent e){
                    //stores the source of the action event into the button variable
                    JButton button = (JButton) e.getSource();
                    String buttonValue = button.getText();
                    if(Arrays.asList(rightSymbols).contains(buttonValue)){
                        if(buttonValue == "="){
                            if (A!=null){
                                B = displayLabel.getText();
                                double numA = Double.parseDouble(A);
                                double numB = Double.parseDouble(B); 

                                if(operator == "+"){
                                    displayLabel.setText(removeZeroDecimal(numA+numB));
                                }
                                if(operator == "-"){
                                    displayLabel.setText(removeZeroDecimal(numA-numB));
                                }
                                if(operator == "×"){
                                    displayLabel.setText(removeZeroDecimal(numA*numB));
                                }
                                if(operator == "÷"){
                                    displayLabel.setText(removeZeroDecimal(numA/numB));
                                }
                            }
                        }
                        else if("+-×÷".contains(buttonValue)){
                            //sets the operator value to null so that if another operator is pressed straight after, it replaces it
                            if(operator == null){
                                A = displayLabel.getText();
                                displayLabel.setText("0");
                                B = "0";
                            }
                            operator = buttonValue;
                        }
                        
                    }
                    else if(Arrays.asList(topSymbols).contains(buttonValue)){
                        if(buttonValue == "AC"){
                            //function call and setting text back to 0 when pressed
                            clearAll();
                            displayLabel.setText("0");
                        }
                        else if(buttonValue == "+/-"){
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay *= -1;
                            displayLabel.setText(removeZeroDecimal(numDisplay));
                        }
                        else if (buttonValue == "%"){
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay /= 100;
                            displayLabel.setText(removeZeroDecimal(numDisplay));
                        }
                    }
                    else{
                        if(buttonValue == "."){
                            if(!displayLabel.getText().contains(buttonValue)){
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                            }

                        }
                        else if("0123456789".contains(buttonValue)){
                            if(displayLabel.getText() == "0"){
                                displayLabel.setText(buttonValue); //get immediate value rather than decimal value
                            }
                            else{
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                            }
                        }
                        else if(buttonValue == "√"){
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay = Math.pow(numDisplay, 0.5);
                            displayLabel.setText(removeZeroDecimal(numDisplay));
                        }
                    }
                }
            });
            frame.setVisible(true);  
        }
    }
    //sets the value of A back to 0 and removes everything else
    void clearAll(){
        A = "0";
        operator = null;
        B =null;
    }
    //function checks if modulus value of number is 0 to determine if number should show decimal value if that value is 0
    String removeZeroDecimal(double numDisplay){
        if (numDisplay % 1 == 0){
            return Integer.toString((int)numDisplay);
        }
        else{
            return Double.toString(numDisplay);
        }
    }
}
