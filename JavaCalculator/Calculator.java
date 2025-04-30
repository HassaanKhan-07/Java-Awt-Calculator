import java.awt.*;
import java.awt.event.*;

public class Calculator extends Frame implements ActionListener {
    private TextField display;
    private String operator = "";
    private double num1, num2, result;

    public Calculator() {
        Frame frame = new Frame("Calculator");
        frame.setSize(300, 400);  // Reduced frame size to fit smaller buttons
        frame.setLayout(new BorderLayout());

        display = new TextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 40));  // Smaller font for the display
        frame.add(display, BorderLayout.NORTH);

        Panel panel = new Panel();
        panel.setLayout(new GridLayout(4, 4, 3, 3));  // Reduced gaps between buttons to 3px

        String[] buttons = {
            "1", "2", "3", "+",
            "4", "5", "6", "-",
            "7", "8", "9", "*",
            "0", "C", "=", "/"
        };

        for (String label : buttons) {
            Button button = new Button(label);
            button.setFont(new Font("Arial", Font.PLAIN, 12));  // Smaller font for buttons
            button.setPreferredSize(new Dimension(35, 35));     // Smaller button size
            button.addActionListener(this);
            panel.add(button);
        }

        frame.add(panel, BorderLayout.CENTER);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            display.setText(display.getText() + command);
        } else if (command.equals("C")) {
            display.setText("");
            operator = "";
        } else if (command.equals("=")) {
            num2 = Double.parseDouble(display.getText());
            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        display.setText("Error");
                        return;
                    }
                    break;
            }
            display.setText(String.valueOf(result));
            operator = "";
        } else {
            if (!operator.isEmpty()) {
                return;
            }
            operator = command;
            num1 = Double.parseDouble(display.getText());
            display.setText("");
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
