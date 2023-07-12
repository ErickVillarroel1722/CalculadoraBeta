import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora extends JFrame {

    private JTextField display;
    private JButton[] numberButtons;
    private JButton[] operatorButtons;
    private JButton[] trigonometricButtons;
    private JButton clearButton;
    private JButton deleteButton;
    private JButton equalsButton;

    private String displayText = "";
    private double firstOperand = 0;
    private String operator = "";

    public Calculadora() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4));

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            final int number = i;
            numberButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    displayText += number;
                    display.setText(displayText);
                }
            });
            buttonPanel.add(numberButtons[i]);
        }

        operatorButtons = new JButton[6];
        operatorButtons[0] = new JButton("+");
        operatorButtons[1] = new JButton("-");
        operatorButtons[2] = new JButton("*");
        operatorButtons[3] = new JButton("/");
        operatorButtons[4] = new JButton("^");
        operatorButtons[5] = new JButton("√");
        for (int i = 0; i < 6; i++) {
            final String operator = operatorButtons[i].getText();
            operatorButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    firstOperand = Double.parseDouble(displayText);
                    displayText = "";
                    display.setText(displayText);
                    Calculadora.this.operator = operator;
                }
            });
            buttonPanel.add(operatorButtons[i]);
        }

        trigonometricButtons = new JButton[3];
        trigonometricButtons[0] = new JButton("sin");
        trigonometricButtons[1] = new JButton("cos");
        trigonometricButtons[2] = new JButton("tan");
        for (int i = 0; i < 3; i++) {
            final String trigonometricFunction = trigonometricButtons[i].getText();
            trigonometricButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    double operand = Double.parseDouble(displayText);
                    double result = 0;
                    switch (trigonometricFunction) {
                        case "sin":
                            result = Math.sin(Math.toRadians(operand));
                            break;
                        case "cos":
                            result = Math.cos(Math.toRadians(operand));
                            break;
                        case "tan":
                            result = Math.tan(Math.toRadians(operand));
                            break;
                    }
                    displayText = String.valueOf(result);
                    display.setText(displayText);
                }
            });
            buttonPanel.add(trigonometricButtons[i]);
        }

        clearButton = new JButton("C");
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayText = "";
                display.setText(displayText);
            }
        });
        buttonPanel.add(clearButton);

        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (displayText.length() > 0) {
                    displayText = displayText.substring(0, displayText.length() - 1);
                    display.setText(displayText);
                }
            }
        });
        buttonPanel.add(deleteButton);

        equalsButton = new JButton("=");
        equalsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double secondOperand = Double.parseDouble(displayText);
                double result = 0;
                switch (operator) {
                    case "+":
                        result = firstOperand + secondOperand;
                        break;
                    case "-":
                        result = firstOperand - secondOperand;
                        break;
                    case "*":
                        result = firstOperand * secondOperand;
                        break;
                    case "/":
                        if (secondOperand != 0) {
                            result = firstOperand / secondOperand;
                        } else {
                            JOptionPane.showMessageDialog(null, "Error: División entre cero no permitida");
                        }
                        break;
                    case "^":
                        result = Math.pow(firstOperand, secondOperand);
                        break;
                    case "√":
                        result = Math.sqrt(secondOperand);
                        break;
                }
                displayText = String.valueOf(result);
                display.setText(displayText);
            }
        });
        buttonPanel.add(equalsButton);

        add(buttonPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Calculadora();
            }
        });
    }
}
