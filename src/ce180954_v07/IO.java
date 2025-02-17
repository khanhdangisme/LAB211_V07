/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ce180954_v07;

import java.util.Scanner;

/**
 * V07 - File Handling
 *
 * @author Le Khanh Dang - CE180954 16/02/2025
 */
public class IO {

    private Scanner sc = new Scanner(System.in);

    /**
     * Validates and retrieves the name of a file from the user. Ensures the
     * file name matches a specific pattern: alphanumeric characters followed by
     * a dot and a file extension.
     *
     * @param msg The message to display when prompting the user
     * @return A valid file name
     */
    public String checkNameFile(String msg) {
        String input; // Declare a variable to store the file name
        while (true) { // Infinite loop to keep prompting the user until valid input is entered
            System.out.print(msg); // Prompt the user to enter the file name
            input = sc.nextLine(); // Read the user input
            try {
                // Check if the input matches the file name pattern (alphanumeric followed by a dot and extension)
                if (!input.matches("^[a-zA-Z0-9]+\\.[a-zA-Z]+")) {
                    throw new IllegalArgumentException("Invalid or empty file name structure"); // Throw an error if invalid
                }
                return input; // Return the valid file name
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage()); // Print error message if invalid file name
            }
        }
    }

    /**
     * Validates and retrieves the user's menu choice. Ensures that the input is
     * a number between 1 and 7.
     *
     * @return A valid menu choice (1-7)
     */
    public int checkChoice() {
        int input; // Declare a variable to store the user's choice

        while (true) { // Infinite loop to keep prompting until a valid input is entered
            System.out.print("Choice features: "); // Prompt the user to enter a choice
            String userInput = sc.nextLine().trim(); // Read and trim whitespace from input

            try {
                // Check if input is empty or contains non-numeric characters
                if (userInput.isEmpty() || !userInput.matches("[0-9]+")) {
                    throw new IllegalArgumentException("Input cannot be empty or contain special characters."); // Error if input is invalid
                }
                input = Integer.parseInt(userInput); // Convert the input string to an integer

                // Ensure the choice is within the valid range (1-7)
                if (input > 7 || input < 1) {
                    throw new IllegalArgumentException("You can only choose between 1 and 7."); // Error if out of range
                }

                return input; // Return the valid choice

            } catch (IllegalArgumentException e) {
                // Catch custom exception if input is invalid
                System.out.println(e.getMessage()); // Print error message if invalid input
            }
        }
    }

    /**
     * Prompts the user with a message and returns a boolean based on their
     * response.
     *
     * @param msg The message to display to the user.
     * @return True if the user responds with 'Y' or 'yes', false otherwise.
     */
    public boolean checkYN(String msg) {
        String input; // Declare a variable to store the user's input
        while (true) { // Infinite loop to keep prompting the user until valid input is entered
            System.out.print(msg); // Display prompt message
            input = sc.nextLine().trim(); // Read user input and remove leading/trailing spaces

            try {
                // Check if the input contains only alphabetic characters and is not empty
                if (!input.matches("[a-zA-Z]+") || input.isEmpty()) {
                    throw new IllegalArgumentException("Input cannot be empty or contain special characters."); // Throw error if invalid
                } else if (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("yes")) {
                    return true; // Return true if the user confirms
                }
                return false; // Return false if the user does not confirm
            } catch (IllegalArgumentException e) { // Catch input validation exceptions
                System.out.println(e.getMessage()); // Print error message
            }
        }
    }

    /**
     * Prompts the user to enter content and returns it as a string.
     *
     * @return The user-inputted content.
     */
    public String writeContent() {
        System.out.println("Enter content for the file:"); // Display prompt message
        String input = sc.nextLine(); // Read user input
        return input; // Return the entered content
    }
}
