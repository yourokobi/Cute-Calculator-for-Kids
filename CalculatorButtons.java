// The CalculatorButtons class acts as the "Controller" in the MVC pattern.
// It handles user interactions, such as button clicks, and updates the view accordingly.
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random; // Import the Random class

public class CalculatorButtons extends JPanel {
    private JTextField display;
    private JButton[] numberButtons;
    private JButton addButton, subButton, mulButton, divButton, clearButton, equalsButton, deleteButton, mathQuizButton;
    private CalculatorLogic calculatorLogic;

    public CalculatorButtons(JTextField display) {
        this.display = display;
        this.calculatorLogic = new CalculatorLogic(); // Initialize the CalculatorLogic
        setLayout(new GridLayout(7, 4, 15, 10));
        setOpaque(false); // Transparent to show background image

        initButtons();
        addButtonsToPanel();
    }

    private void initButtons() {
        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = createButton(String.valueOf(i), new Color(204, 237, 180));
        }

        addButton = createButton("+", new Color(245, 158, 157));
        subButton = createButton("-", new Color(245, 204, 157));
        mulButton = createButton("*", new Color(237, 222, 123));
        divButton = createButton("/", new Color(171, 245, 220));
        clearButton = createButton("Clear", new Color(171, 196, 245));
        equalsButton = createButton("=", new Color(213, 171, 245));
        deleteButton = createButton("Delete", new Color(245, 171, 225));
        mathQuizButton = createButton("Math Quiz", new Color(171, 222, 123));
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        button.setBackground(color);
        button.setFocusPainted(false);
        return button;
    }

    private void addButtonsToPanel() {
        // Row 1: Numbers and number labels
        for (int i = 0; i < 10; i++) {
            JLabel label = new JLabel(getNumberInWords(i), SwingConstants.CENTER);
            add(numberButtons[i]);
            add(label);
        }

        // Row 2: Arithmetic buttons (+, -, *, /)
        addButtonToRow(addButton);
        addButtonToRow(subButton);
        addButtonToRow(mulButton);
        addButtonToRow(divButton);

        // Row 3: Clear, Delete, and Equals buttons
        addButtonToRow(clearButton);
        addButtonToRow(deleteButton);
        addButtonToRow(equalsButton);

        // Row 4: Math Quiz Button
        addButtonToRow(mathQuizButton);

        // Action listeners
        for (JButton button : numberButtons) {
            button.addActionListener(new CalculatorActionListener());
        }
        addButton.addActionListener(new CalculatorActionListener());
        subButton.addActionListener(new CalculatorActionListener());
        mulButton.addActionListener(new CalculatorActionListener());
        divButton.addActionListener(new CalculatorActionListener());
        clearButton.addActionListener(new CalculatorActionListener());
        equalsButton.addActionListener(new CalculatorActionListener());
        deleteButton.addActionListener(new CalculatorActionListener());
        mathQuizButton.addActionListener(new MathQuizListener());
    }

    private String getNumberInWords(int num) {
        switch (num) {
            case 0: return "Zero";
            case 1: return "One";
            case 2: return "Two";
            case 3: return "Three";
            case 4: return "Four";
            case 5: return "Five";
            case 6: return "Six";
            case 7: return "Seven";
            case 8: return "Eight";
            case 9: return "Nine";
            default: return "";
        }
    }

    private void addButtonToRow(JButton button) {
        button.setPreferredSize(new Dimension(100, 50)); // Increase button size for clarity
        add(button);
    }

    private class CalculatorActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            String currentText = display.getText();

            // Number button actions
            for (int i = 0; i < 10; i++) {
                if (source == numberButtons[i]) {
                    display.setText(currentText + i);
                    return;
                }
            }

            // Operation actions
            if (source == addButton) {
                display.setText(currentText + " + ");
            } else if (source == subButton) {
                display.setText(currentText + " - ");
            } else if (source == mulButton) {
                display.setText(currentText + " * ");
            } else if (source == divButton) {
                display.setText(currentText + " / ");
            } else if (source == clearButton) {
                display.setText("");
            } else if (source == deleteButton) {
                if (!currentText.isEmpty()) {
                    display.setText(currentText.substring(0, currentText.length() - 1));
                }
            } else if (source == equalsButton) {
                try {
                    String result = calculatorLogic.evaluate(currentText); // Use CalculatorLogic to evaluate
                    display.setText(result);
                } catch (Exception ex) {
                    display.setText("Error");
                }
            }
        }
    }

    private class MathQuizListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] questions = {
                "5 + 3 = ?",
                "7 - 4 = ?",
                "6 * 2 = ?",
                "8 / 2 = ?"
            };

            Random random = new Random(); // Fix the random issue here
            int index = random.nextInt(questions.length);
            String selectedQuestion = questions[index];

            JOptionPane.showMessageDialog(null, selectedQuestion);
            String answer = JOptionPane.showInputDialog(null, "Your Answer:");
            int userAnswer = Integer.parseInt(answer.trim());

            int correctAnswer = evaluate(selectedQuestion);
            if (userAnswer == correctAnswer) {
                JOptionPane.showMessageDialog(null, "Correct! Great job!");
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect. The correct answer was " + correctAnswer + ".");
            }
        }

        private int evaluate(String question) {
            String[] parts = question.split(" ");
            int num1 = Integer.parseInt(parts[0]);
            String operator = parts[1];
            int num2 = Integer.parseInt(parts[2]);

            switch (operator) {
                case "+": return num1 + num2;
                case "-": return num1 - num2;
                case "*": return num1 * num2;
                case "/": return num1 / num2;
                default: return 0;
            }
        }
    }
}
