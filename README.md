**CUTE CALCULATORFOR KIDS**

**DESCRIPTION**
A visually appealing calculator application built with Java Swing. This calculator features a cloud-themed background, standard arithmetic operations, and displays the current date.

**FEATURES**
- Basic arithmetic operations (addition, subtraction, multiplication, division)
- Attractive cloud-themed UI
- Current date display with day of week
- Clean, intuitive interface

**HOW TO RUN**
1. Make sure you have Java installed on your system
2. Compile all the Java files: `javac *.java`
3. Run the main application: `java CuteCalculator`
4. Ensure you have the "cute_background.jpg" file in the same directory

**PROJECT STRUCTURE**
- CuteCalculator.java: Main application class (View)
- CalculatorLogic.java: Handles calculation operations (Model)
- CalculatorEvaluator.java: Expression parser and evaluator
- CalculatorButtons.java: Button interface and event handling (Controller)

**DESIGN PATTERN**
This project implements the Model-View-Controller (MVC) design pattern:
- Model: CalculatorLogic class for calculations
- View: JTextField and UI elements in CuteCalculator
- Controller: CalculatorButtons class for handling user input

**REQUIREMENTS**
- Java Runtime Environment
- cute_background.jpg image file

**FUTURE IMPROVEMENTS**
- More advanced operations (square root, percentage, etc.)
- Memory functions
- History of calculations
- Themes selection
