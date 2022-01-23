import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.ArrayList;

public class Calculator implements ActionListener {

    private String input = "";
    static JTextField textbox, textbox2;
    static JFrame frame;
    static JButton button;
    static JPanel panel;
    ArrayList<JButton> list;

    public Calculator() {

        list = new ArrayList<JButton>();
        panel = new JPanel();

        //setting up the frame
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(380,532);
        frame.setLayout(null);
        
        //setting up the display 
        textbox = new JTextField(1);
        textbox.setFont(new Font("Impact", Font.PLAIN, 40));
        textbox.setForeground(Color.black);
        textbox.setBackground(Color.lightGray);
        textbox.setHorizontalAlignment(textbox.LEFT);
        textbox.setBounds(2, 4, 360, 65);
        textbox.setEditable(false);
        frame.add(textbox);

        //setting up the second textbox to display the answer of the math problem
        textbox2 = new JTextField(1);
        textbox2.setFont(new Font("Impact", Font.PLAIN, 40));
        textbox2.setForeground(Color.black);
        textbox2.setBackground(Color.lightGray);
        textbox2.setHorizontalAlignment(textbox.RIGHT);
        textbox2.setBounds(2, 70, 360, 50);
        textbox2.setEditable(false);
        frame.add(textbox2);

        //making buttons for numbers
        int xord = 2;
        int yord = 197;
        int count = -2;
        for (int i = 9; i >= -2; i --) {
            if (i > 0) {
                button = new JButton(String.valueOf(i+count));
                button.setFont(new Font("Impact", Font.BOLD, 35));
            }
            if (i==0) {
                button = new JButton(String.valueOf(0));
                button.setFont(new Font("Impact", Font.BOLD, 35));
            }
            else if (i == -1) {
                button = new JButton(".");
                button.setFont(new Font("Impact", Font.BOLD, 35));
            }
            else if (i == -2) {
                button = new JButton("ANS");
                button.setFont(new Font("Impact", Font.BOLD, 22));
            }
            button.addActionListener(this);
            button.setBounds(xord, yord, 70, 70);
            button.setBackground(Color.white);
            button.setForeground(Color.black);
            list.add(button);
            frame.add(button);
            xord += 72;
            count += 2;
            if (xord >= 216) {
                xord = 2;
                yord += 72;
            }
            if (count > 3) count = -2;
        }


        ArrayList<String> signs = new ArrayList<String>();
        signs.add("DEL");
        signs.add("C");
        signs.add("+");
        signs.add("-");
        signs.add("*");
        signs.add("/");
        signs.add("EXP");
        signs.add("=");


        xord = 218;
        yord = 197;

        //buttons for operators, clear and DEL features
        for (int i = 0; i < 8; i ++) {
            button = new JButton(signs.get(i));
            button.addActionListener(this);
            button.setBounds(xord, yord, 70, 70);
            button.setBounds(xord, yord, 70, 70);
            if (signs.get(i).equals("C") || signs.get(i).equals("DEL")) button.setBackground(Color.RED);
            else button.setBackground(Color.darkGray);
            button.setForeground(Color.white);
            button.setFont(new Font("Impact", Font.BOLD, 23));
            list.add(button);
            frame.add(button);
            xord += 72;
            if (xord >= 362) {
                xord = 218;
                yord += 72;
            }
        }

        signs.clear();
        signs.add("π");
        signs.add("√");
        signs.add("x^2");
        signs.add("x^3");
        signs.add("^");
        

        xord = 2;
        yord = 125;

        //making buttons for addtional features
        for (int i = 0; i < 5; i ++) {
            button = new JButton(signs.get(i));
            button.addActionListener(this);
            button.setBounds(xord, yord, 70, 70);
            button.setBounds(xord, yord, 70, 70);
            button.setBackground(Color.darkGray);
            button.setForeground(Color.white);
            button.setFont(new Font("Impact", Font.PLAIN, 25));
            list.add(button);
            frame.add(button);
            xord += 72;
            if (xord >= 362) {
                xord = 218;
            }
        }

        frame.getContentPane().setBackground(Color.lightGray);
        frame.setVisible(true);
        
    }

    //button commands and how it displays in the output textbox
    public void actionPerformed(ActionEvent e) {

        String numbers = "1234567890";
        String signs = "+-*/.";
        

        if (numbers.contains(e.getActionCommand())) {
            input += e.getActionCommand();
            textbox.setText(input);
        }

        if (signs.contains(e.getActionCommand())) {
            input += e.getActionCommand();
            input = checkTextbok(input);
            textbox.setText(input);
        }

        else if ("DEL".equals(e.getActionCommand())) {
            input = textbox.getText();
    
            if (input.length() < 1) {
                textbox.setText("SYNTAX ERROR");
            }
            else {
                if (input.equals("SYNTAX ERROR")) textbox.setText(""); 
                else {
                    input = input.substring(0, input.length()-1);
                    textbox.setText(input);
                }
            }
        }
        else if ("C".equals(e.getActionCommand())) {
            input = "";
            textbox.setText(input);
            textbox2.setText(input);
        }

        else if ("√".equals(e.getActionCommand())) {
            input += e.getActionCommand();
            textbox.setText(input);
        }
        
        else if ("x^2".equals(e.getActionCommand())) {
            input += "²";
            input = checkTextbok(input);
            textbox.setText(input);
        }

        else if ("x^3".equals(e.getActionCommand())) {
            input += "³";
            input = checkTextbok(input);
            textbox.setText(input);
        }

        else if ("EXP".equals(e.getActionCommand())) {  
            input += "E";
            textbox.setText(input);
        }
        else if ("^".equals(e.getActionCommand())) {
            input += e.getActionCommand();
            input = checkTextbok(input);
            textbox.setText(input);

        }

        else if ("π".equals(e.getActionCommand())) {
            input += e.getActionCommand();
            input = checkTextbok(input);
            textbox.setText(input);
        }

        else if ("ANS".equals(e.getActionCommand())) {  //////////////////////////////////////////
            textbox.setText(input + "ANS");
            input += "ANS";
        }

        else if ("=".equals(e.getActionCommand())) {
            try {
                Solution sol = new Solution(input);
                input = sol.Calculate();
                textbox2.setText(input);
                input = "";
                textbox.setText("");
            }
            catch (Exception a) {
                System.out.print(a.getMessage());
            }
        }
        
    }

    public String checkTextbok(String input) {
        if (textbox.getText().equals("") && !textbox2.getText().equals("")) {
             return "ANS" + input;
        }
        else return input;
    }

}

