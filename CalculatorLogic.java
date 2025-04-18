// The CalculatorLogic class implements the "Model" part of the MVC pattern.
// It holds the logic for evaluating the expressions and maintains state.
import java.util.Stack;

public class CalculatorLogic {
    private Stack<Double> numbers;
    private Stack<Character> operations;

    public CalculatorLogic() {
        numbers = new Stack<>();
        operations = new Stack<>();
    }

    // Evaluate the expression and return the result
    public String evaluate(String expression) {
        StringBuilder currentNumber = new StringBuilder();
        for (char ch : expression.toCharArray()) {
            if (Character.isDigit(ch) || ch == '.') {
                currentNumber.append(ch);
            } else {
                if (currentNumber.length() > 0) {
                    numbers.push(Double.parseDouble(currentNumber.toString()));
                    currentNumber.setLength(0);
                }
                operations.push(ch);
            }
        }
        if (currentNumber.length() > 0) {
            numbers.push(Double.parseDouble(currentNumber.toString()));
        }

        while (!operations.isEmpty()) {
            double num1 = numbers.pop();
            double num2 = numbers.pop();
            char op = operations.pop();

            double result;
            switch (op) {
                case '+': result = num2 + num1; break;
                case '-': result = num2 - num1; break;
                case '*': result = num2 * num1; break;
                case '/': result = num2 / num1; break;
                default: throw new IllegalArgumentException("Invalid Operator");
            }
            numbers.push(result);
        }

        return String.valueOf(numbers.pop());
    }
}



