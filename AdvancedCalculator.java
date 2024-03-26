import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AdvancedCalculator extends Application {
    private TextField display; // TextField to display input and result
    private String operator = ""; // To store the current operator
    private double num1 = 0; // To store the first operand
    private boolean start = true; // To track if the start of a new calculation

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Advanced Calculator");

        display = new TextField();
        display.setEditable(false); // Making the display non-editable
        display.setAlignment(Pos.CENTER_RIGHT); // Aligning text to the right
        display.setPrefHeight(50);
        display.setStyle("-fx-font-size: 20"); // Styling the text

        GridPane gridPane = new GridPane(); // Creating a GridPane layout
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10); // Horizontal gap between buttons
        gridPane.setVgap(10); // Vertical gap between buttons

        // Labels for buttons in the calculator
        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        int col = 0;
        int row = 1;
        // Loop to create buttons and add them to the gridPane
        for (String label : buttonLabels) {
            Button button = new Button(label);
            button.setPrefHeight(50);
            button.setPrefWidth(50);
            button.setStyle("-fx-font-size: 20"); // Styling the button text
            // Setting action for button click
            button.setOnAction(event -> handleButtonAction(label));
            // Adding button to the gridPane
            gridPane.add(button, col, row);
            col++;
            // Adjusting column and row for the next button
            if (col > 3) {
                col = 0;
                row++;
            }
        }

        // Creating a clear button
        Button clearButton = new Button("C");
        clearButton.setPrefHeight(50);
        clearButton.setPrefWidth(50);
        clearButton.setStyle("-fx-font-size: 20"); // Styling the button text
        // Setting action for clear button click
        clearButton.setOnAction(event -> {
            display.clear(); // Clearing the display
            start = true; // Setting start to true for new calculation
        });

        // Adding clearButton and display to gridPane
        gridPane.add(clearButton, 3, 0);
        gridPane.add(display, 0, 0, 4, 1);

        // Creating the scene with gridPane as the root node
        Scene scene = new Scene(gridPane, 250, 300);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false); // Disabling window resizing
        primaryStage.show(); // Displaying the window
    }

    // Method to handle button actions
    private void handleButtonAction(String value) {
        if (value.matches("[0-9]") || value.equals(".")) { // If button clicked is a number or decimal
            if (start) { // If it's the start of a new calculation
                display.clear();
                start = false; // Resetting start to false
            }
            display.appendText(value); // Appending text to display
        } else if (value.equals("=")) { // If button clicked is "="
            if (!start) { // If it's not the start of a new calculation
                double num2 = Double.parseDouble(display.getText());
                double result = calculate(num1, num2, operator); // Calculating result
                display.setText(String.valueOf(result)); // Displaying result
                start = true; // Setting start to true for new calculation
            }
        } else { // If button clicked is an operator
            if (!start) { // If it's not the start of a new calculation
                num1 = Double.parseDouble(display.getText());
                start = true; // Setting start to true for new calculation
            }
            operator = value; // Storing the operator
        }
    }

    // Method to perform arithmetic calculations
    private double calculate(double num1, double num2, String operator) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 == 0)
                    return 0; // Handling division by zero
                return num1 / num2;
            default:
                return 0;
        }
    }

    // Main method to launch the application
    public static void main(String[] args) {
        launch(args);
    }
}
