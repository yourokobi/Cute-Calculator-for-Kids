// Design Pattern Description: 
// We implemented the MVC (Model-View-Controller) design pattern.
// - Model: The `CalculatorLogic` class handles the calculation logic (e.g., evaluating expressions, maintaining number stacks).
// - View: The `JTextField` for displaying results.
// - Controller: The `CalculatorButtons` class, handling user input and updating the view using the CalculatorLogic class.
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.DayOfWeek;

public class CuteCalculator extends JFrame {
    private JTextField display;
    private JLabel dateLabel;
    private CalculatorButtons buttons;

    public CuteCalculator() {
        // Frame setup
        setTitle("Cute Calculator");
        setSize(400, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // Display field
        display = new JTextField();
        display.setBounds(30, 20, 340, 50);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        add(display);

        // Add date label
        dateLabel = new JLabel();
        dateLabel.setBounds(30, 80, 340, 30);
        dateLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        dateLabel.setHorizontalAlignment(JLabel.CENTER);
        updateDateLabel();
        add(dateLabel);

        // Add buttons panel
        buttons = new CalculatorButtons(display);
        buttons.setBounds(30, 130, 340, 400);
        add(buttons);

        // Background image
        JLabel background = new JLabel(new ImageIcon("cute_background.jpg")); // Add the image file name here
        background.setBounds(0, 0, 400, 600);
        add(background);

        setVisible(true);
    }

    private void updateDateLabel() {
        LocalDate currentDate = LocalDate.now();
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        String formattedDate = currentDate.format(formatter);
        dateLabel.setText(dayOfWeek + ", " + formattedDate);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CuteCalculator::new);
    }
}
