import java.util.Stack;

public class CalculatorEvaluator {
    public static String evaluate(String expression) throws Exception {
        return String.valueOf(evaluateExpression(expression));
    }

    // Simplistic mathematical expression evaluator
    private static double evaluateExpression(String expression) {
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();
        StringBuilder tempNumber = new StringBuilder();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            // If digit or decimal point, build the number
            if (Character.isDigit(ch) || ch == '.') {
                tempNumber.append(ch);
            } else {
                // If it's not a digit, process the number and push to the stack
                if (tempNumber.length() > 0) {
                    numbers.push(Double.parseDouble(tempNumber.toString()));
                    tempNumber.setLength(0); // Clear tempNumber
                }

                // Process operators
                if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                    while (!operators.isEmpty() && hasPrecedence(ch, operators.peek())) {
                        numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.pop()));
                    }
                    operators.push(ch);
                }
            }
        }

        // Push the last number
        if (tempNumber.length() > 0) {
            numbers.push(Double.parseDouble(tempNumber.toString()));
        }

        // Evaluate the remaining operators
        while (!operators.isEmpty()) {
            numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.pop()));
        }

        return numbers.pop();
    }

    // Check precedence of operators
    private static boolean hasPrecedence(char op1, char op2) {
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) return false;
        return true;
    }

    // Perform the operation
    private static double applyOperation(char operator, double b, double a) {
        switch (operator) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/':
                if (b == 0) throw new ArithmeticException("Cannot divide by zero");
                return a / b;
        }
        return 0;
    }
}
